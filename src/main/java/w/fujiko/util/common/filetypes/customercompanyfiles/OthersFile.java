package w.fujiko.util.common.filetypes.customercompanyfiles;

import w.fujiko.util.filenamecreator.FilenameCreator;
import w.fujiko.util.filetype.FujikoFile;
import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.OTHER_DOCUMENT;

import w.fujiko.model.infos.CustomerCompanyFile;;

public class OthersFile implements FujikoFile {

    @Override
    public String getFileName() {
        return OTHER_DOCUMENT;
    }

    @Override
    public String getFileName(FilenameCreator filenameCreator) {
        return this.getFileName().concat("_").concat(filenameCreator.createName());
	}

    @Override
    public <T> String getFileNameModel(T model) {
        CustomerCompanyFile customerCompanyFile = (CustomerCompanyFile) model;
        return customerCompanyFile.getOthersDocFilename();
    }

    @Override
    public <T> T setFileNameModel(T model, String name) {
        CustomerCompanyFile customerCompanyFile = (CustomerCompanyFile) model;
        customerCompanyFile.setOthersDocFilename(name);
        return model;
    }

}