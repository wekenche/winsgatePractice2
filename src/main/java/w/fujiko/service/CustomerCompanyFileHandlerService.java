package w.fujiko.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import w.fujiko.util.common.filehandler.CustomerCompanyDocsFileHandler;

@Service
public interface CustomerCompanyFileHandlerService{
    CustomerCompanyDocsFileHandler getFileHandler();
    void setFileHandler(CustomerCompanyDocsFileHandler fileHandler);
    void write(MultipartFile file) throws IllegalStateException, IOException;
    void write(MultipartFile file,CustomerCompanyDocsFileHandler fileHandler) throws IllegalStateException, IOException;
    void write(MultipartFile file,String fileName) throws IllegalStateException, IOException;
    void write(MultipartFile file,String fileName,CustomerCompanyDocsFileHandler fileHandler) throws IllegalStateException, IOException;
    InputStream get(String fileName) throws FileNotFoundException;
    InputStream get(String fileName,CustomerCompanyDocsFileHandler fileHandler) throws FileNotFoundException;
    boolean delete(String fileName) throws FileNotFoundException;
    boolean delete(String fileName,CustomerCompanyDocsFileHandler fileHandler) throws FileNotFoundException;
    boolean deleteDirectory(CustomerCompanyDocsFileHandler fileHandler) throws FileNotFoundException;  
    boolean deleteDirectory() throws FileNotFoundException;
}
