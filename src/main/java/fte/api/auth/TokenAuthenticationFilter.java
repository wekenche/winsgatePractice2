/**
 * 
 */
package fte.api.auth;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * @author Try-Parser
 *
 */
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    protected TokenAuthenticationFilter() {
        super("/api/**"); //defaultFilterProcessesUrl - specified in applicationContext.xml.  
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/**")); //Authentication will only be initiated for the request url matching this pattern
        setAuthenticationManager(new NoOpAuthenticationManager());
        setAuthenticationSuccessHandler(new AuthenticationSuccessHandler());
    }

    /**
     * Attempt to authenticate request 
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException,
            IOException, ServletException {
        String token = request.getHeader("Authorization");
        
        AbstractAuthenticationToken userAuthenticationToken = authUserByToken(token);
        if(userAuthenticationToken == null) throw new AuthenticationServiceException("Invalid Token");
        return userAuthenticationToken;
    }
    
    /**
     * authenticate the user based on token
     * @return
     */
	private AbstractAuthenticationToken authUserByToken(String token) {
        if(token==null) return null;
        logger.info(token);

        return (new TokenGenerator<Authentication>().parseToken(token)).map(tkn -> {
            List<GrantedAuthority> authorities = tkn.getAuth().stream().map(e -> new SimpleGrantedAuthority(e.get("authority"))).collect(Collectors.toList());
            User principal = new User(tkn.getSub(), "", authorities); 
            AbstractAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
            return authToken;
        }).orElseThrow(() -> new AuthenticationServiceException("Invalid Token"));
    }
 
 
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
}