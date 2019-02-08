package w.fujiko.util.common.filehandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import w.fujiko.util.isourcepath.IDirectorySourcePath;

@Service
public class ProductFileHandler extends FileHandler{


    public ProductFileHandler(){

    }

    public ProductFileHandler(IDirectorySourcePath directorySourcePath){
        super(directorySourcePath);
    }

    @Override
    public void store(MultipartFile file) throws IllegalStateException, IOException{        
        this.store(file,file.getOriginalFilename());
    }

    @Override
    public void store(MultipartFile file,String fileName) throws IllegalStateException, IOException{        
        String fileDirectory = directorySourcePath.getSourcePath();    
        File imageFileDirectory = new File(fileDirectory);
        if(!imageFileDirectory.exists())
            imageFileDirectory.mkdirs();

        file.transferTo(new File(fileDirectory+"/"+fileName)); 
    }

    @Override
    public InputStream get(String fileName) throws FileNotFoundException{
        
        String filePath = directorySourcePath.getSourcePath()+"/"+fileName;    
        File imageFile = new File(filePath);
        return new FileInputStream(imageFile);
    }

    @Override
    public boolean delete(String fileName) throws FileNotFoundException{
        
        String filePath = directorySourcePath.getSourcePath()+"/"+fileName;    
        File imageFile = new File(filePath);
        return imageFile.delete();
    }

    public boolean deleteDirectory() throws FileNotFoundException{
        
        String filePath = directorySourcePath.getSourcePath();  
        File file = new File(filePath);
        for(String entry : file.list()){
             File currentFile = new File(filePath,entry);
            currentFile.delete();
        } 
        
        return file.delete();
    }
}