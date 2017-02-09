package com.benchmark.psma.common.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.codec.Base64;

public class RestClientAuthenticationHandler {

	public static boolean isAuthnticated(String param) throws Exception{
		if(StringUtils.isEmpty(param)){
			return false;
		}
		return "490154203237518".equals(decryptAuthenticationParam(param));
	}
	private static String decryptAuthenticationParam(String param) throws Exception {
		
		byte[] decrypted = Base64.decode(param.getBytes("UTF8"));
		SecretKey key = new SecretKeySpec("PSMA_SeC".getBytes(),
				"DES");
		Cipher ecipher = Cipher.getInstance("DES");
		ecipher.init(Cipher.DECRYPT_MODE, key);
		// decode the string into a sequence of bytes using the named charset
		// storing the result into a new byte array.
		byte[] dec = ecipher.doFinal(decrypted);
		return new String(dec);
	}
}
