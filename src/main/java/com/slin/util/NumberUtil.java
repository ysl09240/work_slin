package com.slin.util;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 格式化double
 * @author vajra
 *Jan 30, 2011
 */
public class NumberUtil {
	private static final String NUMBER_FORMAT = "0.00";
	private static final String NUMBER_VALUE = "0";
	
	public static String formatDouble(Object obj){
		if(obj == null){
			return NUMBER_VALUE;
		}
		DecimalFormat df = new DecimalFormat(NUMBER_FORMAT);
		return df.format(obj);
	}
	public static String formatInt(Double value){
		if(value == null){
			return NUMBER_VALUE;
		}
		DecimalFormat df = new DecimalFormat(NUMBER_FORMAT);
		return df.format(value);
	}
	
	/* 生成任意位数随机数 
     * @param code_len(位数) 
     * @return 
     */  
    public static String validateCode(int code_len) {  
        int count = 0;  
        char str[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };  
        StringBuffer pwd = new StringBuffer("");  
        Random r = new Random();  
        while (count < code_len) {  
            int i = Math.abs(r.nextInt(10));  
            if (i >= 0 && i < str.length) {  
                pwd.append(str[i]);  
                count++;  
            }  
        }  
        return pwd.toString();  
    }
    
    /* *
     * 生成任意位数随机数,去除零 
     * @param code_len(位数) 
     * @return 
     */ 
    public static String validateCodeNotZero(int code_len) {  
        int count = 0;  
        char str[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9' };  
        StringBuffer pwd = new StringBuffer("");  
        Random r = new Random();  
        while (count < code_len) {  
            int i = Math.abs(r.nextInt(10));  
            if (i >= 0 && i < str.length) {  
                pwd.append(str[i]);  
                count++;  
            }  
        }  
        return pwd.toString();  
    }
    
    
    /**
     * 取数的最大值
     * @param values
     * @return
     */
	public static Object getMaxVal(Object... values) {
		Arrays.sort(values);
        
		return values[values.length-1];
	}
    /**
     * 取数的最小值
     * @param values
     * @return
     */
	public static Object getMinVal(Object... values) {
		Arrays.sort(values);        
		return values[0];
	}
	
	/**
	 * 获取指定长度的非0开头数值字符串
	 */
	public static String getNotZeroBeginNumberStr(int length){
		String cc = NumberUtil.validateCodeNotZero(1) + NumberUtil.validateCode(length - 1 );
		return cc;		
	}
	
	
	
	
	/**
	 * 生成效验码 
	 */
	public static String getValidateCode(){
		String cc = NumberUtil.validateCodeNotZero(1) + NumberUtil.validateCode(7);
		/*Integer xx = Integer.parseInt(cc.substring(0, 1)) + Integer.parseInt(cc.substring(2, 3)) 
				+ Integer.parseInt(cc.substring(4, 5)) + Integer.parseInt(cc.substring(5, 6));
		String seven = xx.toString().substring(0,1);		
		Integer bb = Integer.parseInt(cc.substring(1, 2)) + Integer.parseInt(cc.substring(3, 4)) 
			+ Integer.parseInt(cc.substring(5, 6));
		String eight = bb.toString().substring(0,1);		
		cc =  cc + seven + eight ;*/
		return cc;		
	}
	
	
	/**
	 * 生成效验码 
	 */
	public static String getCheckCode(){
		String cc = NumberUtil.validateCodeNotZero(1) + NumberUtil.validateCode(7);
		/*Integer xx = Integer.parseInt(cc.substring(0, 1)) + Integer.parseInt(cc.substring(2, 3)) 
				+ Integer.parseInt(cc.substring(4, 5)) + Integer.parseInt(cc.substring(5, 6));
		String seven = xx.toString().substring(0,1);		
		Integer bb = Integer.parseInt(cc.substring(1, 2)) + Integer.parseInt(cc.substring(3, 4)) 
			+ Integer.parseInt(cc.substring(5, 6));
		String eight = bb.toString().substring(0,1);		
		cc =  cc + seven + eight ;*/
		return cc;		
	}
	
	
	public static boolean isNumeric(String str){ 
		if(str == null){
			return false;
		}
		Pattern pattern = Pattern.compile("-?[0-9]+"); 
	    return pattern.matcher(str).matches();    
	 } 
	
	
	/**
	 * 获取范围内随机数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomByMinMax(int min,int max){
	     Random random = new Random();
	     int s = random.nextInt(max)%(max-min+1) + min;
	     return s;
	}
	
	
	public static void main(String args[]){
		boolean c = NumberUtil.isNumeric(null);
		System.out.println(c);
		
	}
	
	
	
}
