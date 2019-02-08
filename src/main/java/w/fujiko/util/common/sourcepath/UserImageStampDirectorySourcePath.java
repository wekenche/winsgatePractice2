package w.fujiko.util.common.sourcepath;

public class UserImageStampDirectorySourcePath extends DefaultImageDirectorySourcePath {

    private static final String DIRECTORY = "/home/storage/images/user/stamp";

    public UserImageStampDirectorySourcePath(){
        super(DIRECTORY);
    }
}