import com.ouyang.util.SecureUtil;

/** 
 * Copyright: Copyright (c) 2016
 * @author: ouyangping
 * @version 1.0
 * @date 2017-1-20
 */

/** 
 * <p>create time: 2017-1-20 </p>
 * @version V1.0  
 * @author ouyangping
 */
public class Main { 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(SecureUtil.ByteToHex("111111".getBytes()));
		System.out.println(SecureUtil.Hex2Str("06BA7899BCCB5668".getBytes()));
	}

}
