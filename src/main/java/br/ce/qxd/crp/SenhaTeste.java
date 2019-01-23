package br.ce.qxd.crp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SenhaTeste {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String palavra = scan.nextLine();
		// TODO Auto-generated method stub
		MessageDigest algorithm;
		byte messageDigest[] = null;
		try {
			algorithm = MessageDigest.getInstance("MD5");
			messageDigest = algorithm.digest(palavra.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}
		String senha = hexString.toString();
		
		System.out.println(senha);
	}

}
