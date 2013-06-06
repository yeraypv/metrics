package Methods.Lines;

import Package.Entities.Metric;
import Package.Files.ReaderFile;
import Package.Files.ReaderMethod;
import Package.Utils.FilesUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LineComments implements Metric {

    private FilesUtils fileutil = new FilesUtils();
    private int num = 0;

    public LineComments(ReaderFile readerfile) {
        count(readerfile);
    }

    public LineComments(ReaderMethod readerfile) {
        count(readerfile);
    }

    private int getLineComments(ReaderFile readerfile) throws IOException {
        return fileutil.getFilePattern(readerfile, "//");
    }

    private int getLineComments(ReaderMethod readerfile) throws IOException {
        return fileutil.getFilePattern(readerfile, "//");
    }

    @Override
    public int getCount() {
        return num;
    }

    @Override
    public void count(ReaderFile readerfile) {
        try {
            num = getLineComments(readerfile);
        } catch (IOException ex) {
            Logger.getLogger(LineComments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public HashMap getArrayInfo(ReaderFile readerfile) {
        return null;
    }

    @Override
    public void add(Metric d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Metric d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Metric getChild(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void count(ReaderMethod readermethod) {
        try {
            num = getLineComments(readermethod);
        } catch (IOException ex) {
            Logger.getLogger(LineComments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
