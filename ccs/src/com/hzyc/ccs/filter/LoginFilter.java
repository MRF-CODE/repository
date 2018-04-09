package com.hzyc.ccs.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String url = httpRequest.getRequestURI();
		String lastUrl = url.substring(url.lastIndexOf("/") , url.length() );
		String lastUrl1 = "";
		System.out.println(url);
		if(url.indexOf("endpoint") > 0){
			lastUrl1 = url.substring(url.lastIndexOf("_") , url.length() );
		}
		if(httpRequest.getSession().getAttribute("uname")!=null || lastUrl1.equals("_endpoint") || lastUrl.equals("/login.jsp") || lastUrl.equals("/load.action") ){
			chain.doFilter(request, response);
		}else{
			//HttpServletResponse httpResponse = (HttpServletResponse)response;
			//httpResponse.sendRedirect("login.jsp");
			//response.setContentType("text/html;charset=UTF-8");  
			String path = httpRequest.getContextPath();
	        response.getWriter().println("<script>window.top.location.href='"+path+"/login.jsp"+"';</script>"); 
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
