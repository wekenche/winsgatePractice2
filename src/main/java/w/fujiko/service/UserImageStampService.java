package w.fujiko.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import w.fujiko.util.common.filehandler.FileHandler;

@Service
public interface UserImageStampService{
    FileHandler getImageFileHandler();
    void setImageFileHandler(FileHandler imageFileHandler);
    void write(MultipartFile file) throws IllegalStateException, IOException;
    void write(MultipartFile file,FileHandler imageFileHandler) throws IllegalStateException, IOException;
    void write(MultipartFile file,String fileName) throws IllegalStateException, IOException;
    void write(MultipartFile file,String fileName,FileHandler imageFileHandler) throws IllegalStateException, IOException;
    InputStream get(String imageFileName) throws FileNotFoundException;
    InputStream get(String imageFileName,FileHandler imageFileHandler) throws FileNotFoundException;
    boolean delete(String imageFileName) throws FileNotFoundException;
    boolean delete(String imageFileName,FileHandler imageFileHandler) throws FileNotFoundException;
}
