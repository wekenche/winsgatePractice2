package w.fujiko.util.filetype;

import w.fujiko.util.filenamecreator.FilenameCreator;

public interface FujikoFile {
    String getFileName();
    String getFileName(FilenameCreator filenameCreator);
    <T> String getFileNameModel(T model);
    <T> T setFileNameModel(T model,String name);
}
