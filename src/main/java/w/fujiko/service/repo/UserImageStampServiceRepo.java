package w.fujiko.service.repo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import w.fujiko.service.UserImageStampService;
import w.fujiko.util.common.filehandler.FileHandler;

@Service
@Transactional
public class UserImageStampServiceRepo implements UserImageStampService {
    
    @Autowired
    FileHandler imageFileHandler;

    public UserImageStampServiceRepo(){}

    public UserImageStampServiceRepo(FileHandler imageFileHandler){
        this.imageFileHandler = imageFileHandler;
    }

    @Override
    public FileHandler getImageFileHandler() {
        return this.imageFileHandler;
    }

    @Override
    public void setImageFileHandler(FileHandler imageFileHandler) {
        this.imageFileHandler = imageFileHandler;
    }

    /**
     * Writes the image file
     */
    @Override
    public void write(MultipartFile file) throws IllegalStateException, IOException{
        String fileName = file.getOriginalFilename();
        this.write(file,fileName);
    }

    /**
     * Writes the image file using the specified ImageFileHandler
     */
    @Override
    public void write(MultipartFile file, FileHandler imageFileHandler) throws IllegalStateException, IOException {
        String fileName = file.getOriginalFilename();
        this.write(file,fileName,imageFileHandler);
    }

    /**
     * Writes the image file as a specified filename
     */
    @Override
    public void write(MultipartFile file,String fileName) throws IllegalStateException, IOException {
        this.imageFileHandler.store(file,fileName);
    }

    /**
     * Writes the image file as a specified filename using the specified ImageFileHandler
     */
    @Override
    public void write(MultipartFile file, String fileName, FileHandler imageFileHandler)
            throws IllegalStateException, IOException {

        imageFileHandler.store(file,fileName);
    }

     /**
     * Gets the image file by its fileName
     */
    @Override
    public InputStream get(String imageFileName) throws FileNotFoundException {
        return this.imageFileHandler.get(imageFileName);
    }

    /**
     * Gets the image file by its fileName using the specified ImageFileHandler
     */
    @Override
    public InputStream get(String imageFileName, FileHandler imageFileHandler) throws FileNotFoundException {
        return imageFileHandler.get(imageFileName);
    }

    @Override
    public boolean delete(String imageFileName) throws FileNotFoundException {
        return this.imageFileHandler.delete(imageFileName);
    }

    @Override
    public boolean delete(String imageFileName, FileHandler imageFileHandler) throws FileNotFoundException {
        return imageFileHandler.delete(imageFileName);
    }

}