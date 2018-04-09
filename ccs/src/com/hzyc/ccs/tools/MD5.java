package com.hzyc.ccs.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	 /**
	  * ���MD5���ܺ��32λ����
	  * 
	  * @author website copy
	  * @param sourceStr Ҫ���ܵ��ַ�
	  * @return String ���ܵ�����
	  */
	  public static String getMD5(String sourceStr) {
	        String result = "";
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(sourceStr.getBytes());
	            byte b[] = md.digest();
	            int i;
	            StringBuffer buf = new StringBuffer("");
	            for (int offset = 0; offset < b.length; offset++) {
	                i = b[offset];
	                if (i < 0)
	                    i += 256;
	                if (i < 16)
	                    buf.append("0");
	                buf.append(Integer.toHexString(i));
	            }
	            result = buf.toString();
	        } catch (NoSuchAlgorithmException e) {
	            System.out.println(e);
	        }
	        return result;
	    }
		public static void main(String[] args) {
			System.out.println(getMD5("123456"));
		}
}
