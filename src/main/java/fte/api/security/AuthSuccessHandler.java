package fte.api.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import fte.api.auth.TokenGenerator;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.systems.SystemLogs;
import w.fujiko.model.masters.users.User;
import w.fujiko.service.systems.SystemLogsService;
import w.fujiko.service.users.UserService;

public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler  {
	@Autowired SystemLogsService service;
	@Autowired UserService userService;
    
	/**
	 * 
	 */
	public AuthSuccessHandler() {
		super();
		setRedirectStrategy(new NoRedirectStrat());
	}

	/**
	 * @param defaultTargetUrl
	 */
	public AuthSuccessHandler(String defaultTargetUrl) {
		super(defaultTargetUrl);
		// TODO Auto-generated constructor stub
	}

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
        
        SystemLogs logs = new SystemLogs();
        User user = new User();
        Program program = new Program();
        
        program.setId("LOGIN");
        
        user.setId(Integer.parseInt(authentication.getName().split("_")[0]));
        
        logs.setCreatedBy(user);
        logs.setUpdatedBy(user);
        logs.setCreatedAt(program);
        logs.setUpdatedAt(program);
        logs.setProgram_id(program);
        logs.setIp(request.getRemoteAddr());
        logs.setIss(request.getRemoteHost());
        
        response.getWriter().write("{\"authenticated\" : true, \"message\" : \"authentication-successfull\", \"token\": \"" + new TokenGenerator<Authentication>().generateToken(authentication) +"\"}");
    	response.setStatus(HttpServletResponse.SC_OK);
    }
}
