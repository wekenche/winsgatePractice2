package w.fujiko.api;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fte.api.Success;
import w.fujiko.model.masters.users.User;
import w.fujiko.service.UserImageStampService;
import w.fujiko.service.users.UserService;
import w.fujiko.util.common.filehandler.FileHandler;
import w.fujiko.util.common.sourcepath.UserImageStampDirectorySourcePath;


/**
 * 
 * @author johnl
 *
 */
@MultipartConfig 
@RestController
@RequestMapping("/api/stamp")
public class UserImageStampApi {

    @Autowired UserService userService;
    @Autowired UserImageStampService userImageStampService;

    private FileHandler imageFileHandler;

    public UserImageStampApi(){
        this.imageFileHandler = new FileHandler(new UserImageStampDirectorySourcePath());
    }

    @PostMapping("/{userid}/")
    public @ResponseBody ResponseEntity<?> singleFileUpload(@RequestParam("file") MultipartFile file,@PathVariable("userid") Integer userid) {
      
        try{            
            if (!file.isEmpty()) {                
                User user = userService.get(userid).orElseThrow();
                String fileName = this.createFileName(user);
                userImageStampService.write(file, fileName, this.imageFileHandler);
            }
            else {                             
                    return ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .build();
            } 
        }catch(NoSuchElementException ex){
            ex.printStackTrace();
            return ResponseEntity
            .notFound()
            .build();
        }
        catch (IllegalStateException  | IOException ex) {
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(new Success());
    }

    @DeleteMapping("/{userid}/")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable("userid") Integer userid) {
      
        try{            
            User user = userService.get(userid).orElseThrow();
            String fileName = this.createFileName(user);
            Boolean successfullyDelete = userImageStampService.delete(fileName, this.imageFileHandler);
            if(!successfullyDelete){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        }catch(NoSuchElementException ex){
            ex.printStackTrace();
            return ResponseEntity
            .notFound()
            .build();
        }
        catch (IllegalStateException  | IOException ex) {
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(new Success());
    }

    private String createFileName(User user){
        return user.getId()+"_"+user.getCode();
    }
}
