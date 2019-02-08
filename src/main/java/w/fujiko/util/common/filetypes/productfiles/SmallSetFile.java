package w.fujiko.util.common.filetypes.productfiles;

import w.fujiko.util.filenamecreator.FilenameCreator;
import w.fujiko.util.filetype.FujikoFile;
import static w.fujiko.util.common.constants.ProductFileConstants.SMALL_SET_FILE;

import w.fujiko.model.infos.ProductFile;

public class SmallSetFile implements FujikoFile {

    @Override
    public String getFileName() {
        return SMALL_SET_FILE;
    }

    @Override
    public String getFileName(FilenameCreator filenameCreator) {
        return this.getFileName().concat("_").concat(filenameCreator.createName());
	}

    @Override
    public <T> String getFileNameModel(T model) {
        ProductFile productFile = (ProductFile) model;
        return productFile.getSmallSetFilename();
    }

    @Override
    public <T> T setFileNameModel(T model,String name) {
        ProductFile productFile = (ProductFile) model;
        productFile.setSmallSetFilename(name);
        return model;
    }

}