package w.fujiko.util.common.sourcepath;

public class ProductFilesDirectorySourcePath extends DefaultCustomerCompanyDocsDirectorySourcePath {

    private static final String DIRECTORY = "/home/storage/documents/product";

    public ProductFilesDirectorySourcePath(String directory){
        super(directory);
    }

    public ProductFilesDirectorySourcePath(){
        super(DIRECTORY);
    }

    public static String getBasePath(){
        return DIRECTORY;
    }

}