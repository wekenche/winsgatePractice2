package w.fujiko.service.repo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import w.fujiko.service.ProductFileHandlerService;
import w.fujiko.util.common.filehandler.ProductFileHandler;

@Service
@Transactional
public class ProductFileHandlerServiceRepo implements ProductFileHandlerService {
    
    @Autowired
    ProductFileHandler fileHandler;

    public ProductFileHandlerServiceRepo(){}

    public ProductFileHandlerServiceRepo(ProductFileHandler fileHandler){
        this.fileHandler = fileHandler;
    }

    @Override
    public ProductFileHandler getFileHandler() {
        return this.fileHandler;
    }

    @Override
    public void setFileHandler(ProductFileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    /**
     * Writes the file
     */
    @Override
    public void write(MultipartFile file) throws IllegalStateException, IOException{
        String fileName = file.getOriginalFilename();
        this.write(file,fileName);
    }

    /**
     * Writes the file using the specified fileHandler
     */
    @Override
    public void write(MultipartFile file, ProductFileHandler fileHandler) throws IllegalStateException, IOException {
        String fileName = file.getOriginalFilename();
        this.write(file,fileName,fileHandler);
    }

    /**
     * Writes the file as a specified filename
     */
    @Override
    public void write(MultipartFile file,String fileName) throws IllegalStateException, IOException {
        this.fileHandler.store(file,fileName);
    }

    /**
     * Writes the file as a specified filename using the specified fileHandler
     */
    @Override
    public void write(MultipartFile file, String fileName, ProductFileHandler fileHandler)
            throws IllegalStateException, IOException {

        fileHandler.store(file,fileName);
    }

     /**
     * Gets the file by its fileName
     */
    @Override
    public InputStream get(String fileName) throws FileNotFoundException {
        return this.fileHandler.get(fileName);
    }

    /**
     * Gets the file by its fileName using the specified fileHandler
     */
    @Override
    public InputStream get(String fileName, ProductFileHandler fileHandler) throws FileNotFoundException {
        return fileHandler.get(fileName);
    }

    @Override
    public boolean delete(String fileName) throws FileNotFoundException {
        return this.fileHandler.delete(fileName);
    }

    @Override
    public boolean delete(String fileName, ProductFileHandler fileHandler) throws FileNotFoundException {
        return fileHandler.delete(fileName);
    }

    @Override
    public boolean deleteDirectory(ProductFileHandler fileHandler) throws FileNotFoundException{
         return fileHandler.deleteDirectory();
    }

    @Override
    public boolean deleteDirectory() throws FileNotFoundException{
         return this.fileHandler.deleteDirectory();
    }
}