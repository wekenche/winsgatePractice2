package fte.api.auth;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class Token implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2414972235717428708L;
	private static ObjectMapper map = new ObjectMapper();
	private String sub;
	private String iss;
	private Date date;
	private ArrayList<Map<String, String>> auth;
	
	public Token() {}

	public static Token token(Jws<Claims> jwt) throws IOException {
		String json = map.writeValueAsString(jwt.getBody());
		Token token = map.readValue(json, Token.class);
		return token;
	}

	/**
	 * @param iss
	 * @param date
	 * @param auth
	 */
	public Token(String iss, Date date, ArrayList<Map<String, String>> auth) {
		super();
		this.iss = iss;
		this.date = date;
		this.auth = auth;
	}

	public String getIss() {
		return iss;
	}
	
	public void setIss(String iss) {
		this.iss = iss;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public ArrayList<Map<String, String>> getAuth() {
		return auth;
	}
	
	public void setAuth(ArrayList<Map<String, String>> auth) {
		this.auth = auth;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}
}
