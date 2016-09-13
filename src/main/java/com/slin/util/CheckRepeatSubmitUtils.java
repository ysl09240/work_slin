package com.slin.util;


public class CheckRepeatSubmitUtils {

	/**
	 * 效验重复提交
	 * @param checkRepeatSubmit
	 * @return true表示业务正常  false表示已重复提交
	 */
	public static boolean checkRepeatSubmit(String checkRepeatSubmit){
		if(checkRepeatSubmit == null || checkRepeatSubmit.length() < 1){
			return false;
		}
		try{
			 MemCacheManager mc = MemCacheManager.getInstance();	 
			 String bb =  mc.get(checkRepeatSubmit);
			 if(bb == null){
				 mc.set(checkRepeatSubmit, 30*60*1, checkRepeatSubmit ); //30分钟
				 return true;	
			 }else{
				 return false;
			 }
		 }catch(Exception e){
			 return true;
		 }
		
	}
}
