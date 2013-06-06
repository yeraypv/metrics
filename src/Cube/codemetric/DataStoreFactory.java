package Cube.codemetric;

import Cube.codemetric.definition.CubeDefinition;
import Cube.codemetric.definition.DataStoreDefinition;
import Cube.codemetric.definition.DimensionDefinition;
import Cube.codemetric.definition.FeatureDefinition;
import Cube.codemetric.definition.MetricDefinition;
import java.io.File;
import org.sumus.dwh.DatawareHouse;
import org.sumus.dwh.DatawareHouseException;
import org.sumus.dwh.cube.Cube;
import org.sumus.dwh.datastore.DataStore;
import org.sumus.dwh.dimension.Dimension;
import org.sumus.dwh.ext.DatawareHouseDelegate;
import org.sumus.dwh.ext.filelocator.FileLocator;
import org.sumus.dwh.feature.Feature;
import org.sumus.dwh.metric.Metric;

public class DataStoreFactory {

	private final File folder;
	
	public DataStoreFactory(File folder) {
		this.folder = folder;
	}

	public DataStore createDataStore() {
		try {
			DatawareHouse datawareHouse = new DatawareHouse (new DatawareHouseDelegate(new FileLocator(folder)));
			DataStore dataStore = datawareHouse.createDataStore(DataStoreDefinition.DATA_STORE_NAME);
			defineDataStore(dataStore);
			return dataStore;
		}
		catch (DatawareHouseException e) {
			throw new RuntimeException("DataStore " + DataStoreDefinition.DATA_STORE_NAME + " already exists");
		}
	}

	private void defineDataStore(DataStore dataStore) {
		createDimensions(dataStore, DataStoreDefinition.getDimensions());
		createCubes(dataStore, DataStoreDefinition.getCubes());
	}
	
	private void createDimensions(DataStore dataStore, DimensionDefinition[] definitions) {
		for (DimensionDefinition definition : definitions)
			createDimension(dataStore, definition);
	}

	private void createCubes(DataStore dataStore, CubeDefinition[] definitions) {
		for (CubeDefinition definition : definitions)
			createCube(dataStore, definition);
	}

	private void createDimension(DataStore dataStore, DimensionDefinition definition){
		try {
			definition.addFeatures();
			Dimension dimension = dataStore.createDimension(definition.getName());
			createFeatures(dimension, definition.getFeaturesDefinition());
		}
		catch (Exception e) {
		}
	}
	
	private void createFeatures(Dimension dimension, FeatureDefinition[] definitions) {
		for (FeatureDefinition definition : definitions)
			createFeature(dimension, definition);
	}

	private void createFeature(Dimension dimension, FeatureDefinition definition){
		try {
			dimension.add(new Feature(definition.getName(), definition.getType()));
		}
		catch (Exception e) {
		}
	}
	
	private void createCube(DataStore dataStore, CubeDefinition definition){
		try {
			definition.addDimensions();
			definition.addMetrics();
			Cube cube = dataStore.createCube(definition.getName(), definition.getTimeScale());
			addDimensions(cube, dataStore, definition.getDimensionDefinitions());
			addMetrics(cube, dataStore, definition.getMetricDefinitions());
		}
		catch (Exception e) {
		}
	}
	
	private void addDimensions(Cube cube, DataStore dataStore, DimensionDefinition[] definitions) {
		for (DimensionDefinition definition : definitions)
			addDimension(cube, dataStore, definition);
	}

	private void addDimension(Cube cube, DataStore dataStore, DimensionDefinition definition){
		try {
			cube.add(dataStore.getDimension(definition.getName()));
		}
		catch (Exception e) {
		}
	}
	
	private void addMetrics(Cube cube, DataStore dataStore, MetricDefinition[] definitions) {
		for (MetricDefinition definition : definitions)
			addMetric(cube, dataStore, definition);
	}

	private void addMetric(Cube cube, DataStore dataStore, MetricDefinition definition){
		try {
			cube.add(new Metric(definition.getName()));
		}
		catch (Exception e) {
		}
	}
}
