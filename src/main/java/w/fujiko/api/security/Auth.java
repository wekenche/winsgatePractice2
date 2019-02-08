/**
 * 
 */
package w.fujiko.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Try-Parser
 *
 */
@RestController
@RequestMapping("/security")
public class Auth {
		
	@SuppressWarnings("unused")
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	public HttpHeaders getJsonHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json");
		return headers;
	}
	
	@GetMapping("/signin")
	public ResponseEntity<String> authApi() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(getJsonHeaders()).build();
	}
	
	@GetMapping("/auth-failure")
	public ResponseEntity<String> authFailureApi() {
		return ResponseEntity.ok().headers(getJsonHeaders()).body("{\"authenticated\" : false, \"message\" : \"authentication-failure\"}");
	}
	
//	@GetMapping("/default")
//	public ResponseEntity<String> authDefaultApi() {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String token = new TokenGenerator<Authentication>().generateToken(auth);
//		return ResponseEntity.ok().headers(getJsonHeaders()).body("{\"authenticated\" : true, \"message\" : \"authentication-successfull\", \"token\": \"" + token +"\"}");
//	}

	// @PatchMapping("/register")
	// public @ResponseBody ResponseEntity<?> reg(@Valid @RequestBody User t, Errors error) {
	// 	if(error.hasErrors()) 
	// 		return ResponseEntity.badRequest().body(error.getAllErrors());
		
	// 	try 
	// 		t.setPassword(passwordEncoder.encode(t.getPassword())); 
	// 		List<Response> response = service.saveOrUpdate(t);
	// 		return ResponseEntity.ok().body(response);
	// 	} catch(Exception e) {
	// 		return ResponseEntity.badRequest().body("{ status: 500, message: bad data. }");
	// 	}
	// }
}
