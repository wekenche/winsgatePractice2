package w.fujiko.util.common.filetypes.determiner;

import java.util.HashMap;

import w.fujiko.exceptions.FileTypeNotRecognizeException;
import w.fujiko.util.filetype.FujikoFile;

import org.springframework.stereotype.Service;

@Service
public class DefaultFileTypeDeterminer {

    private HashMap<String, FujikoFile> fileTypeHashMap;
    
    public DefaultFileTypeDeterminer(){

    }

    public DefaultFileTypeDeterminer(HashMap<String, FujikoFile> fileTypeHashMap){
        this.fileTypeHashMap = fileTypeHashMap;
    }

    public void setfileTypeDictionary(HashMap<String, FujikoFile> fileTypeHashMap){
        this.fileTypeHashMap = fileTypeHashMap;
    }

    public FujikoFile determine(String fileType) throws FileTypeNotRecognizeException {
        
        FujikoFile file= this.fileTypeHashMap.get(fileType);
        if(file==null)
            throw new FileTypeNotRecognizeException();
        return file;
	}

}