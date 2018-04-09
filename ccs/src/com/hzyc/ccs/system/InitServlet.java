package com.hzyc.ccs.system;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public InitServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		DataDictionary.init();
        try {
        	new GetImage().getImage1(config.getServletContext().getRealPath("/"));
			new GetImage().getImage(config.getServletContext().getRealPath("/"));
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
