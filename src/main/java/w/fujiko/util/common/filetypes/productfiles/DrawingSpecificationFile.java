package w.fujiko.util.common.filetypes.productfiles;

import w.fujiko.util.filenamecreator.FilenameCreator;
import w.fujiko.util.filetype.FujikoFile;
import static w.fujiko.util.common.constants.ProductFileConstants.DRAWING_SPEC_FILE;

import w.fujiko.model.infos.ProductFile;

public class DrawingSpecificationFile implements FujikoFile {

    @Override
    public String getFileName() {
        return DRAWING_SPEC_FILE;
    }

    @Override
    public String getFileName(FilenameCreator filenameCreator) {
        return this.getFileName().concat("_").concat(filenameCreator.createName());
	}

    @Override
    public <T> String getFileNameModel(T model) {
        ProductFile productFile = (ProductFile) model;
        return productFile.getDrawingSpecFilename();
    }

    @Override
    public <T> T setFileNameModel(T model,String name) {
        ProductFile productFile = (ProductFile) model;
        productFile.setDrawingSpecFilename(name);
        return model;
    }

}