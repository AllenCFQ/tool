/** 
 * Copyright: Copyright (c) 2016
 * @author: ouyangping
 * @version 1.0
 * @date 2017-1-13
 */
package com.ouyang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * <p>create time: 2017-1-13 </p>
 * @version V1.0  
 * @author ouyangping
 * @see
 */
public class TimeUtil {
	
	public static String date (String fmt) {
		return new SimpleDateFormat(fmt).format(new Date());
	}
	
	public static void main (String[] args) {
		System.out.println(date("yyyyMMdd"));
	}

}
