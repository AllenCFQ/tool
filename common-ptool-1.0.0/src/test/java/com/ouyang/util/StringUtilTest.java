
package com.ouyang.util;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author unascribed
 * @version 1.0
 */
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class StringUtilTest {
	/*
	 * 将字符串"元"转换成"分"
	 */
	public static String Dollar2Cent(String s) {
		s = StringUtil.trim(s);
		int i = s.indexOf(".");
		if (i == -1)
			return s + "00";
		String intStr = s.substring(0, i);
		if (intStr.length() <= 0)
			intStr = "0";
		String decStr = s.substring(i + 1, s.length());
		if (decStr.length() <= 0)
			decStr = "00";
		if (decStr.length() == 1)
			decStr += "0";
		if (decStr.length() > 2)
			decStr = decStr.substring(0, 2);
		int iInt = Integer.parseInt(intStr);
		if (iInt <= 0)
			return decStr;
		else
			return intStr + decStr;
	}
	public static Map<String,String> str2HashMap(String line,String delim,String delimkv){//用逗号分割每一个键值对，=号分割key和value
//		System.out.println(line);
		if(line!=null&&!line.equals("")){
			String[] keyValues=line.split(delim);
			Map<String,String> map=new HashMap<String ,String>();
			for(String keyValue:keyValues){
				String[] kv=keyValue.split(delimkv);
				map.put(StringUtil.trim(kv[0]), StringUtil.trim(kv[1]));
			}
			return map;
		}
		return null;
	}

//	public static void main(String[]args){
//
//	}
	/*
	 * 将字符串"分"转换成"元"（长格式），如：100分被转换为1.00元。
	 */
	public static String Cent2Dollar(String s) {
		long l = 0;
		try {
			if (s.charAt(0) == '+') {
				s = s.substring(1);
			}
			l = Long.parseLong(s);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		boolean negative = false;
		if (l < 0) {
			negative = true;
			l = Math.abs(l);
		}
		s = Long.toString(l);
		if (s.length() == 1)
			return (negative ? ("-0.0" + s) : ("0.0" + s));
		if (s.length() == 2)
			return (negative ? ("-0." + s) : ("0." + s));
		else
			return (negative ? ("-" + s.substring(0, s.length() - 2) + "." + s
					.substring(s.length() - 2)) : (s.substring(0,
					s.length() - 2)
					+ "." + s.substring(s.length() - 2)));
	}

	/*
	 * 将字符串"分"转换成"元"（短格式），如：100分被转换为1元。
	 */
	public static String Cent2DollarShort(String s) {
		String ss = Cent2Dollar(s);
		ss = "" + Double.parseDouble(ss);
		if (ss.endsWith(".0"))
			return ss.substring(0, ss.length() - 2);
		if (ss.endsWith(".00"))
			return ss.substring(0, ss.length() - 3);
		else
			return ss;
	}

	/*
	 * 去掉字符串首尾的 <空格字符>（包括TAB），如果s为null则返回空字符串""。
	 */
	public static String trim(String s) {
		if (s == null)
			return "";
		else
			return s.trim();
	}

	/*
	 * 去掉字符串首部位置中连续出现的chars中的字符， 如chars=" ,;#$";s=",#$, abcd"，则返回abcd。
	 */
	public static String trimLeft(String chars, String s) {
		if (s == null)
			return "";
		if (s.length() <= 0)
			return s;
		if (chars == null)
			return s;
		if (chars.length() <= 0)
			return s;
		int i = 0;
		for (i = 0; i < s.length(); i++) {
			if (-1 == chars.indexOf(s.charAt(i)))
				break;
		}
		return s.substring(i);
	}

	/*
	 * 去掉字符串尾部位置中连续出现的chars中的字符， 如chars=" ,;#$";s=",#$, abcd"，则返回abcd。
	 */
	public static String trimRight(String chars, String s) {
		if (s == null)
			return "";
		if (s.length() <= 0)
			return s;
		if (chars == null)
			return s;
		if (chars.length() <= 0)
			return s;
		int i = s.length();
		for (i = s.length() - 1; i > -1; i--) {
			if (-1 == chars.indexOf(s.charAt(i)))
				break;
		}
		if (i < 0)
			return "";
		else
			return s.substring(0, i + 1);
	}

	/*
	 * 去掉字符串中连续出现的chars中的字符， 如chars=" ,;#$";s=",#$, ab,c##d;;;"，则返回abcd。 added
	 * by liuxd 2004-12-02
	 */
	public static String trimAll(String chars, String s) {
		if (s == null)
			return "";
		if (s.length() <= 0)
			return s;
		if (chars == null)
			return s;
		if (chars.length() <= 0)
			return s;
		int i = 0, j = 0;
		StringBuffer sb = new StringBuffer(s);
		do {
			j = sb.length();
			for (i = 0; i < sb.length(); i++) {
				if (chars.indexOf(sb.charAt(i)) != -1) {
					sb.replace(i, i + 1, "");
				}
			}
		} while (j != sb.length());
		return sb.toString();
	}

	/*
	 * 压缩字符串中连续 <空格字符>，及将多个连续的 <空格字符>变成一个 <空格字符> 如s=",#$, abcd"，则返回,#$, abcd。
	 */
	public static String compressSpace(String s) {
		if (s == null)
			return "";
		String ss = s.trim();
		String tmp = "";
		boolean lastIsSpace = false;
		for (int i = 0; i < ss.length(); i++) {
			if (ss.charAt(i) != ' ' && ss.charAt(i) != '\t') {
				tmp += ss.charAt(i);
				lastIsSpace = false;
			} else if (!lastIsSpace) {
				tmp += ' ';
				lastIsSpace = true;
			} else
				continue;
		}
		return tmp;
	}

	/*
	 * 判断字符串是否为数字字符串。是则返回true，否则返回false。
	 */
	public static boolean isDigitalString(String s) {
		if (s == null)
			return false;
		if (s.length() == 0)
			return false;
		String ds = "0123456789";
		for (int i = 0; i < s.length(); i++) {
			if (ds.indexOf(s.charAt(i)) < 0)
				return false;
		}
		return true;
	}

	/*
	 * 判断字符串是否为英文字符串。是则返回true，否则返回false。
	 */
	public static boolean isLetterString(String s) {
		if (s == null)
			return false;
		if (s.length() == 0)
			return false;
		String ds = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < s.length(); i++) {
			if (ds.indexOf(s.charAt(i)) < 0)
				return false;
		}
		return true;
	}

	/*
	 * ISO8859字符串转换成GB2312字符串
	 */
	public static String ISO8859toGB2312(String s) {
		try {
			return new String(s.getBytes("ISO-8859-1"), "GB2312");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/*
	 * ISO8859字符串转换成GBK字符串（目前该函数已经失效）
	 */
	public static String DBCharset2HostCharset(String s) {
		try {
			return new String(s.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}

	public static String toUnicode(String theString, boolean escapeSpace) {
		int len = theString.length();
		int bufLen = len * 2;
		if (bufLen < 0) {
			bufLen = Integer.MAX_VALUE;
		}
		StringBuffer outBuffer = new StringBuffer(bufLen);

		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			// Handle common case first, selecting largest block that
			// avoids the specials below
			if ((aChar > 61) && (aChar < 127)) {
				if (aChar == '\\') {
					outBuffer.append('\\');
					outBuffer.append('\\');
					continue;
				}
				outBuffer.append(aChar);
				continue;
			}
			switch (aChar) {
			case ' ':
				if (x == 0 || escapeSpace)
					outBuffer.append('\\');
				outBuffer.append(' ');
				break;
			case '\t':
				outBuffer.append('\\');
				outBuffer.append('t');
				break;
			case '\n':
				outBuffer.append('\\');
				outBuffer.append('n');
				break;
			case '\r':
				outBuffer.append('\\');
				outBuffer.append('r');
				break;
			case '\f':
				outBuffer.append('\\');
				outBuffer.append('f');
				break;
			case '=': // Fall through
			case ':': // Fall through
			case '#': // Fall through
			case '!':
				outBuffer.append('\\');
				outBuffer.append(aChar);
				break;
			default:
				if ((aChar < 0x0020) || (aChar > 0x007e)) {
					outBuffer.append('\\');
					outBuffer.append('u');
					outBuffer.append(toHex((aChar >> 12) & 0xF));
					outBuffer.append(toHex((aChar >> 8) & 0xF));
					outBuffer.append(toHex((aChar >> 4) & 0xF));
					outBuffer.append(toHex(aChar & 0xF));
				} else {
					outBuffer.append(aChar);
				}
			}
		}
		return outBuffer.toString();
	}

	/**
	 * dec:得到指定长度的字符串
	 * @param requiredLength 指定输出字段长度
	 * @param number 输入的数字
	 * @return
	 */
	public static String prefixPadding(int requiredLength,int number) {
		String padding = "000000000000000";//15长度
		StringBuffer r = new StringBuffer(number+"");
		while(r.length() < requiredLength) {
			r.insert(0, padding);
		}
		return r.substring(r.length()-requiredLength, r.length());
	}

	/**
	 * desc:左补位
	 * <p>创建人：huangzhe , 2014年11月19日上午10:50:16</p>
	 * @param oriStr
	 * @param len
	 * @param alexin
	 * @return
	 */
	public static String padLeft(String oriStr, int len, char alexin) {
		int strlen = oriStr.length();
		StringBuilder sb = new StringBuilder();
		if (strlen < len) {
			for (int i = 0; i < len - strlen; i++) {
				sb.append(alexin);
			}
		}
		sb.append(oriStr);
		return sb.toString();
	}
	
	
	public static boolean isEmpty(String s){
		return (s == null || "".equals(s.trim())) ? true : false;
	}
	
	  public static String trimToEmpty(String str) {
	  
	    return str == null ? "" : str.trim();
	  }
}