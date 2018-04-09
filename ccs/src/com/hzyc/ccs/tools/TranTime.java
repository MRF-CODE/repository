package com.hzyc.ccs.tools;

public class TranTime {
	public String trandate(String date){
		String nosecond = date.substring(0,16);
		String minute = nosecond.substring(14,16);
		String num = "";
		int q = Integer.parseInt(minute);
		if(q==0){
			String hour = nosecond.substring(11,13);
			int h = Integer.parseInt(hour);
			h = h-1;
			q= 59;
			num = date.substring(0,11)+h+":"+q+"";
		}else{
			q = q-1;
			num = date.substring(0,14)+String.format("%02d",q);
		}
		return num;
	}
}
