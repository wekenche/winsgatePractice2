/**
 * 
 */
package w.fujiko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Try-Parser
 *
 */
@Controller
public class NavigationController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
}