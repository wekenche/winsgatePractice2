package w.fujiko.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import w.fujiko.util.common.filehandler.ProductFileHandler;

@Service
public interface ProductFileHandlerService{
    ProductFileHandler getFileHandler();
    void setFileHandler(ProductFileHandler fileHandler);
    void write(MultipartFile file) throws IllegalStateException, IOException;
    void write(MultipartFile file,ProductFileHandler fileHandler) throws IllegalStateException, IOException;
    void write(MultipartFile file,String fileName) throws IllegalStateException, IOException;
    void write(MultipartFile file,String fileName,ProductFileHandler fileHandler) throws IllegalStateException, IOException;
    InputStream get(String fileName) throws FileNotFoundException;
    InputStream get(String fileName,ProductFileHandler fileHandler) throws FileNotFoundException;
    boolean delete(String fileName) throws FileNotFoundException;
    boolean delete(String fileName,ProductFileHandler fileHandler) throws FileNotFoundException;
    boolean deleteDirectory(ProductFileHandler fileHandler) throws FileNotFoundException;  
    boolean deleteDirectory() throws FileNotFoundException;
}
