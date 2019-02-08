package w.fujiko.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import w.fujiko.service.UserImageStampService;
import w.fujiko.util.common.filehandler.FileHandler;
import w.fujiko.util.common.sourcepath.UserImageStampDirectorySourcePath;

@Controller
public class UserImageStampController {
    
    @Autowired UserImageStampService userImageStampService;
	private FileHandler imageFileHandler;

	public UserImageStampController(){
        this.imageFileHandler = new FileHandler(new UserImageStampDirectorySourcePath());
	}
	
	@GetMapping("/static/images/user/stamp/{filename}")
	public void getImageAsByteArray(@PathVariable(value="filename") final String filename,HttpServletResponse response) throws IOException {
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    IOUtils.copy(userImageStampService.get(filename,imageFileHandler), response.getOutputStream());
	}
	
}
