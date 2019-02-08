package w.fujiko.util.common.sourcepath;

import w.fujiko.util.isourcepath.IDirectorySourcePath;

public class DefaultCustomerCompanyDocsDirectorySourcePath implements IDirectorySourcePath {

    private String directory = "/home/storage/documents/customer/company";

    public DefaultCustomerCompanyDocsDirectorySourcePath(){}
    public DefaultCustomerCompanyDocsDirectorySourcePath(String directory){
        this.directory = directory;
    }
    
    @Override
    public String getSourcePath() {
        return this.directory;
    }

}