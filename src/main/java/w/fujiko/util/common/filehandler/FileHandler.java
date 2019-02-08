package w.fujiko.util.common.filehandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import w.fujiko.util.isourcepath.IDirectorySourcePath;

@Service
public class FileHandler {

    @Autowired
    protected IDirectorySourcePath directorySourcePath;

    public FileHandler(){

    }

    public FileHandler(IDirectorySourcePath directorySourcePath){
        this.directorySourcePath = directorySourcePath;
    }

    public IDirectorySourcePath getDirectorySourcePath(){
        return this.directorySourcePath;
    }

    public void setDirectorySourcePath(IDirectorySourcePath directorySourcePath){
        this.directorySourcePath = directorySourcePath;
    }

    public void store(MultipartFile sourcefile) throws IllegalStateException, IOException{        
        this.store(sourcefile,sourcefile.getOriginalFilename());
    }

    public void store(MultipartFile sourcefile,String filePath) throws IllegalStateException, IOException{        
        String sourcePath = directorySourcePath.getSourcePath();    
        File file = new File(sourcePath);

        if(!file.exists()) file.mkdirs();

        sourcefile.transferTo(new File(sourcePath+"/"+filePath)); 
    }

    public InputStream get(String filePath) throws FileNotFoundException{
        
        String sourcePath = directorySourcePath.getSourcePath()+"/"+filePath;    
        File imageFile = new File(sourcePath);
        return new FileInputStream(imageFile);
    }

    public boolean delete(String filePath) throws FileNotFoundException{        
        filePath = directorySourcePath.getSourcePath()+"/"+filePath;    
        File imageFile = new File(filePath);
        return imageFile.delete();
    }
}