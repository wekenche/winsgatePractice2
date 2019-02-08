package w.fujiko.util.common.filehandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.util.isourcepath.IDirectorySourcePath;

@Service
public class CSVErrorFileHandler {

    @Autowired
    protected IDirectorySourcePath directorySourcePath;

    public CSVErrorFileHandler(){

    }

    public CSVErrorFileHandler(IDirectorySourcePath directorySourcePath){
        this.directorySourcePath = directorySourcePath;
    }

    public IDirectorySourcePath getDirectorySourcePath(){
        return this.directorySourcePath;
    }

    public void setDirectorySourcePath(IDirectorySourcePath directorySourcePath){
        this.directorySourcePath = directorySourcePath;
    }

    public void store(String content) throws IllegalStateException, IOException{        
        this.store(content,this.getFileName());
    }

    public void store(String content,String fileName) throws IllegalStateException, IOException{        
        String fileDirectory = directorySourcePath.getSourcePath();
        fileName = fileDirectory.concat("/").concat(fileName);
        File imageFileDirectory = new File(fileDirectory);
        if(!imageFileDirectory.exists())
            imageFileDirectory.mkdirs();

        Files.write(Paths.get(fileName), content.getBytes());
    }

    public InputStream get(String fileName) throws FileNotFoundException{
        
        String filePath = directorySourcePath.getSourcePath()+"/"+fileName;    
        File imageFile = new File(filePath);
        return new FileInputStream(imageFile);
    }

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

    private String getFileName(){
        final String BASE_NAME="csv_error";
		Date date = Calendar.getInstance().getTime();  
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	    String stringifyDate = dateFormat.format(date);
		stringifyDate=stringifyDate.replaceAll("\\s+","").replaceAll(":","").replaceAll("-","");
		
		return BASE_NAME
			  .concat("_")
			  .concat(stringifyDate)
			  .concat(".csv");
    }
}