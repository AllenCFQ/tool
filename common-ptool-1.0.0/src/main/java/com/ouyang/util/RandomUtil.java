/** 
 * Copyright: Copyright (c) 2016
 * @author: ouyangping
 * @version 1.0
 * @date 2017-1-3
 */
package com.ouyang.util;

import java.util.Random;

/** 
 * <p>create time: 2017-1-3 </p>
 * @version V1.0  
 * @author ouyangping
 * @see
 */
public class RandomUtil {
	
	/**
	 * 任意长度的随机数字字符串
	 * @param length
	 * @return
	 */
	public static String getRandomStr (int length) {
		Random rand = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}
}
