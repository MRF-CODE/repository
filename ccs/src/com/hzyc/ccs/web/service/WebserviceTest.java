package com.hzyc.ccs.web.service;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


@WebService
@SOAPBinding(style = Style.RPC)
public interface WebserviceTest {
    
	public String setStore();
	public String getData(String code);
	public boolean sednData(String data,String store) throws Exception;
}