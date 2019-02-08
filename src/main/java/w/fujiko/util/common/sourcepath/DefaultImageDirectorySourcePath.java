package w.fujiko.util.common.sourcepath;

import w.fujiko.util.isourcepath.IDirectorySourcePath;

public class DefaultImageDirectorySourcePath implements IDirectorySourcePath {

    private String directory = "/home/storage/images";

    public DefaultImageDirectorySourcePath(){}
    public DefaultImageDirectorySourcePath(String directory){
        this.directory = directory;
    }
    
    @Override
    public String getSourcePath() {
        return this.directory;
    }

}