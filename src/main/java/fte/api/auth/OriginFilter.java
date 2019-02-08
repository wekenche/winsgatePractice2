/**
 * 
 */
package fte.api.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Try-Parser
 *
 */
public class OriginFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		if (StringUtils.isEmpty(request.getHeader(HttpHeaders.ORIGIN))) {
			chain.doFilter(request, response);
	        return;
		}

		request.setCharacterEncoding("UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", HttpHeaders.AUTHORIZATION);
				
		if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
			System.out.println("Preflight");
			// CORS "pre-flight" request
			response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS, PATCH");
            response.addHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
			response.addHeader("Access-Control-Max-Age", "1728000");
		} else {
			chain.doFilter(request, response);
		}
	}
}
