package w.fujiko.util.common.filetypes.customercompanyfiles;

import w.fujiko.util.filenamecreator.FilenameCreator;
import w.fujiko.util.filetype.FujikoFile;
import static w.fujiko.util.common.constants.CustomerCompanyFileConstants.TRANSACTION_APPROVAL_DOCUMENT;

import w.fujiko.model.infos.CustomerCompanyFile;

public class TransactionApprovalFile implements FujikoFile {

    @Override
    public String getFileName() {
        return TRANSACTION_APPROVAL_DOCUMENT;
    }

    @Override
    public String getFileName(FilenameCreator filenameCreator) {
        return this.getFileName().concat("_").concat(filenameCreator.createName());
	}

    @Override
    public <T> String getFileNameModel(T model) {
        CustomerCompanyFile customerCompanyFile = (CustomerCompanyFile) model;
        return customerCompanyFile.getTransactionApprovalFilename();
    }

    @Override
    public <T> T setFileNameModel(T model, String name) {
        CustomerCompanyFile customerCompanyFile = (CustomerCompanyFile) model;
        customerCompanyFile.setTransactionApprovalFilename(name);
        return model;
    }

}