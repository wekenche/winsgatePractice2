package w.fujiko.util.common.stringconverter;

import java.io.UnsupportedEncodingException;

public class StringConverter {
	
	public static byte[] convertToReadable(String message) {
		return convertToReadable(message, "UTF-8");
	}
	
	public static byte[] convertToReadable(String message, String encoding) {
		try {
			return message.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

}