package fte.api.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5077392952534665006L;

	public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}
