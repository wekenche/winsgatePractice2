package w.fujiko.util.common.sourcepath;

public class CustomerCompanyDocsDirectorySourcePath extends DefaultCustomerCompanyDocsDirectorySourcePath {

    private static final String DIRECTORY = "/home/storage/documents/customer/company";

    public CustomerCompanyDocsDirectorySourcePath(String directory){
        super(directory);
    }

    public CustomerCompanyDocsDirectorySourcePath(){
        super(DIRECTORY);
    }

    public static String getBasePath(){
        return DIRECTORY;
    }

}