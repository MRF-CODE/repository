package com.hzyc.ccs.tools;

import java.io.IOException;

  
public class sendInfo {
	
	
	
	//用户名
	private static String Uid = "luwei19820521";
	
	//接口安全秘钥
	private static String Key = "04daba44dac97471594f";
	
	//手机号码，多个号码如13800000000,13800000001,13800000002
	//private static String smsMob = "13800000000";
	
	//短信内容
	//private static String smsText = "8888";
	
	public  void send(String tel,String content) {
		
		HttpClientUtil client = HttpClientUtil.getInstance();
		
		//GBK发送
		int resultGbk = client.sendMsgGbk(Uid, Key, content, tel );
		if(resultGbk>0){
			System.out.println("GBK成功发送条数=="+resultGbk);
		}else{
			System.out.println(client.getErrorMsg(resultGbk));
		}
	}
	
	
	
	
	/*//String host = "http://sms.webchinese.cn/web_api/";
	String host = "http://gbk.api.smschinese.cn";
	String uname = "luwei19820521";
	String pwd = "04daba44dac97471594f";
	    *//** 
	     * @author dengsilinming 
	     * @date Sep 18, 2012 
	     * @time 9:38:25 AM 
	     * @param args 
	     * @throws IOException 
	     * @throws HttpException 
	     * @description 
	     *//*  
	    public static void main(String[] args) throws HttpException, IOException {  
	        HttpClient client = new HttpClient();  
	        //PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");  
	         PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");  
	        post.addRequestHeader("Content-Type",  
	                "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码  
	        NameValuePair[] data = { new NameValuePair("Uid", "shaoVSshuai"),// 注册的用户名  
	                new NameValuePair("Key", "f8d8c7b19a8bae908341"),// 注册成功后，登录网站后得到的密钥  
	                new NameValuePair("smsMob", "13244236072"),// 手机号码  
	                new NameValuePair("smsText", "这是专用于测试的信息，能否正常发短信呢？") };// 短信内容  
	        post.setRequestBody(data);  
	  
	        client.executeMethod(post);  
	        Header[] headers = post.getResponseHeaders();  
	        int statusCode = post.getStatusCode();  
	        System.out.println("statusCode:" + statusCode);  
	        for (Header h : headers) {  
	            System.out.println("---" + h.toString());  
	        }  
	        String result = new String(post.getResponseBodyAsString().getBytes(  
	                "gbk"));  
	        System.out.println(result);  
	  
	    }  
	    
	    *//**
	     * @param tel 手机号
	     * @param content 内容
	     * @throws HttpException
	     * @throws IOException
	     *//*
	    public void send(String tel,String content) throws HttpException, IOException{
	    	
	    	
	    	
	    	HttpClient client = new HttpClient();
	    	PostMethod post = new PostMethod("http://gbk.api.smschinese.cn"); 
	    	post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
	    	NameValuePair[] data ={ new NameValuePair("Uid", "luwei19820521"),new NameValuePair("Key", "04daba44dac97471594f"),new NameValuePair("smsMob","手机号码"),new NameValuePair("smsText","验证码：8888")};
	    	post.setRequestBody(data);

	    	client.executeMethod(post);
	    	Header[] headers = post.getResponseHeaders();
	    	int statusCode = post.getStatusCode();
	    	System.out.println("statusCode:"+statusCode);
	    	for(Header h : headers)
	    	{
	    	System.out.println(h.toString());
	    	}
	    	String result = new String(post.getResponseBodyAsString().getBytes("gbk")); 
	    	System.out.println(result); //打印返回消息状态


	    	post.releaseConnection();
	    	
	    	
	    	
	    	
	    	
	    	
	    	 HttpClient client = new HttpClient();  
	    	  //   PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");  
		      PostMethod post = new PostMethod(host);  
		        post.addRequestHeader("Content-Type",  
		                "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码  
		        NameValuePair[] data = { new NameValuePair("Uid", uname),// 注册的用户名  
		                new NameValuePair("Key", pwd),// 注册成功后，登录网站后得到的密钥  

		                new NameValuePair("smsMob", tel),// 手机号码  
		                new NameValuePair("smsText", content
		                ) };// 短信内容  
		        post.setRequestBody(data);  
		  
		        client.executeMethod(post);  
		        Header[] headers = post.getResponseHeaders();  
		        int statusCode = post.getStatusCode();  
		        System.out.println("statusCode:" + statusCode);  
		        for (Header h : headers) {  
		            System.out.println("---" + h.toString());  
		        }  
		        String result = new String(post.getResponseBodyAsString().getBytes(  
		                "gbk"));  
		        System.out.println(result);  
		        try {
		        	if( Integer.parseInt(result) <= 0  ){
			        	MessageBox mb = new MessageBox(new Shell());
			        	mb.setText("系统提示");
			        	mb.setMessage("短信发送失败,请登录请检查余额");
			        	mb.open();
			        }else if(Integer.parseInt(result) > 0){
			        	MessageBox mb = new MessageBox(new Shell());
			        	mb.setText("系统提示");
			        	mb.setMessage("短信通知成功");
			        	mb.open();
			        }
				} catch (Exception e) {
					// TODO: handle exception
				}
		        
		  
	    }*/
	    
	    
}
