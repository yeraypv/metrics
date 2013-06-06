package Cube.codemetric.definition;

import org.sumus.dwh.feature.Feature;
import org.sumus.dwh.time.TimeScale;

public class DataStoreDefinition {

    public final static String DATA_STORE_NAME = "CodeMetrics";
    public static final DimensionDefinition MODULES = new DimensionDefinition("Modules") {
        @Override
        public void addFeatures() {
            setFeaturesDefinition(new FeatureDefinition[]{NAME, TYPE});
        }
    };
    
    public static final CubeDefinition PACKAGE_CUBE = new CubeDefinition("PackageCube", TimeScale.DAY) {
        @Override
        public void addDimensions() {
            setDimensionDefinitions(new DimensionDefinition[]{MODULES});
        }

        @Override
        public void addMetrics() {
            setMetricDefinitions(new MetricDefinition[]{LINES_OF_CODE, EFFECTIVE_LINES_CODE, EFFERENT_COUPLING_LIBRARY, AFFERENT_COUPLING, PACKAGES, FILESFOLDERS});
        }
    };
    
    public static final CubeDefinition CLASS_CUBE = new CubeDefinition("ClassCube", TimeScale.DAY) {
        @Override
        public void addDimensions() {
            setDimensionDefinitions(new DimensionDefinition[]{MODULES});
        }

        @Override
        public void addMetrics() {
            setMetricDefinitions(new MetricDefinition[]{LINES_OF_CODE, EFFECTIVE_LINES_CODE, CONSTRUCTS, CLASSES, ATTRIBUTES, METHODS, LACK_OF_COHESION_OF_METHODS, EFFERENT_COUPLING_LIBRARY, AFFERENT_COUPLING});
        }
    };
    
    public static final CubeDefinition METHOD_CUBE = new CubeDefinition("MethodCube", TimeScale.DAY) {
        @Override
        public void addDimensions() {
            setDimensionDefinitions(new DimensionDefinition[]{MODULES});
        }

        @Override
        public void addMetrics() {
            setMetricDefinitions(new MetricDefinition[]{LINES_OF_CODE, EFFECTIVE_LINES_CODE, PARAMETERS, CYCLOMATIC_COMPLEXITY});
        }
    };
    
    public static final FeatureDefinition NAME = new FeatureDefinition("Name", Feature.ENUM) {};
    public static final FeatureDefinition TYPE = new FeatureDefinition("Type", Feature.ENUM) {};
    
    public static final MetricDefinition LINES_OF_CODE = new MetricDefinition("LinesOfCode");
    public static final MetricDefinition EFFECTIVE_LINES_CODE = new MetricDefinition("EffectiveLinesOfCode");
    public static final MetricDefinition CLASSES = new MetricDefinition("Classes");
    public static final MetricDefinition EFFERENT_COUPLING_LIBRARY = new MetricDefinition("EfferentCouplingLibrary");
    public static final MetricDefinition AFFERENT_COUPLING = new MetricDefinition("AfferentCoupling");
    public static final MetricDefinition ATTRIBUTES = new MetricDefinition("Attributes");
    public static final MetricDefinition METHODS = new MetricDefinition("Methods");
    public static final MetricDefinition LACK_OF_COHESION_OF_METHODS = new MetricDefinition("LackOfCohesionOfMethods");
    public static final MetricDefinition PARAMETERS = new MetricDefinition("TotalParameters");
    public static final MetricDefinition CYCLOMATIC_COMPLEXITY = new MetricDefinition("CyclomaticComplexity");
    public static final MetricDefinition CONSTRUCTS = new MetricDefinition("Constructs");
    public static final MetricDefinition METHOD_NAME = new MetricDefinition("MethodName");
    public static final MetricDefinition METHOD_LINES = new MetricDefinition("MethodLines");
    public static final MetricDefinition FILESFOLDERS = new MetricDefinition("FilesFolders");
    public static final MetricDefinition PACKAGES = new MetricDefinition("Packages");

    public static DimensionDefinition[] getDimensions() {
        return new DimensionDefinition[]{MODULES};
    }

    public static CubeDefinition[] getCubes() {
        return new CubeDefinition[] {PACKAGE_CUBE, CLASS_CUBE, METHOD_CUBE};
    }
}
