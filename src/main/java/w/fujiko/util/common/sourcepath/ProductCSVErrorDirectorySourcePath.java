package w.fujiko.util.common.sourcepath;

public class ProductCSVErrorDirectorySourcePath extends DefaultDirectorySourcePath {

    private static final String DIRECTORY = "/home/storage/error/product/csvextraction";

    public ProductCSVErrorDirectorySourcePath(String directory){
        super(directory);
    }

    public ProductCSVErrorDirectorySourcePath(){
        super(DIRECTORY);
    }

    public static String getBasePath(){
        return DIRECTORY;
    }

}