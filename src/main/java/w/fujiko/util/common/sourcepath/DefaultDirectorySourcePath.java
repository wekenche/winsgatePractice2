package w.fujiko.util.common.sourcepath;

import w.fujiko.util.isourcepath.IDirectorySourcePath;

public class DefaultDirectorySourcePath implements IDirectorySourcePath {

    private String directory = "/home/storage";

    public DefaultDirectorySourcePath(){}
    public DefaultDirectorySourcePath(String directory){
        this.directory = directory;
    }
    
    @Override
    public String getSourcePath() {
        return this.directory;
    }

}