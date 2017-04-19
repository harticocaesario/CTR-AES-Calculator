import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class CtrAES {

	static String begin(String type, String fileLoc, String keyLoc) throws Exception {
		String result = "";
		Path filePath = Paths.get(fileLoc);
		Path keyPath = Paths.get(keyLoc);
		Charset utf8 = Charset.forName("UTF-8");

		byte[] input = Files.readAllBytes(filePath);
		String keyHex = Files.readAllLines(keyPath, utf8).get(0);
		byte[] key = hexToBytes(keyHex);
		String filename = filePath.getFileName().toString();

		if (type.equalsIgnoreCase("ENCRYPT")) {
			result = encrypt(filename, input, key);
		} else {
			if (filename.contains("encrypted")) {
				filename = filename.substring(12);

				result = decrypt(filename, input, key);
			} else {
				result = decrypt(filename, input, key);
			}
		}

		return result;
	}

	private static String encrypt(String filename, byte[] input, byte[] keyBytes) throws Exception {
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		byte[] ivBytes = generateIV();

		IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
		Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
		//		System.out.println("input : " + new String(input));
		//		System.out.println("Max Key Length : " + Cipher.getMaxAllowedKeyLength("AES"));

		// encryption pass
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
		ByteArrayInputStream bIn = new ByteArrayInputStream(input);
		CipherInputStream cIn = new CipherInputStream(bIn, cipher);
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();

		int ch;
		while ((ch = cIn.read()) >= 0) {
			bOut.write(ch);
		}

		byte[] cipherText = bOut.toByteArray();

		//		System.out.println("cipher: " + new String(cipherText));
		Path cipherLoc = Paths.get("[encrypted] " + filename);

		Files.write(cipherLoc, ArrayUtils.addAll(ivBytes, cipherText));

		return "Encrypted\nLocation : " + cipherLoc.toAbsolutePath().toString();
	}

	private static String decrypt(String filename, byte[] cipherText, byte[] keyBytes) throws Exception {
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		byte[] ivByte = new byte[16];
		System.arraycopy(cipherText, 0, ivByte, 0, 16);
		cipherText = Arrays.copyOfRange(cipherText, 16, cipherText.length);
		IvParameterSpec ivSpec = new IvParameterSpec(ivByte);

		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");

		cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
		bOut = new ByteArrayOutputStream();
		CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
		cOut.write(cipherText);
		cOut.close();
		//		System.out.println("plain : " + new String(bOut.toByteArray()));

		byte[] decrypted = bOut.toByteArray();
		Path decryptLoc = Paths.get("[decrypted] " + filename);

		Files.write(decryptLoc, decrypted);

		return "Decrypted\nLocation	 : " + decryptLoc.toAbsolutePath().toString();
	}

	static byte[] hexToBytes(String hex) {
		int len = hex.length();
		byte[] result = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			result[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i+1), 16));
		}
		return result;

	}

	public static byte[] generateIV() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] seeds = secureRandom.generateSeed(16);
		secureRandom.setSeed(seeds);

		byte[] ivBytes = new byte[16];
		secureRandom.nextBytes(ivBytes);

		return ivBytes;
	}

}
