package com.cpm.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class Filtro1 extends ZuulFilter {

	 @Override
	    public boolean shouldFilter() {
	       return true;
	       }

	    @Override
	    public String filterType() {
	       return "pre";
	       }

	    @Override
	    public int filterOrder() {
	       return 0;
	       }

	    @Override
	    public Object run() throws ZuulException {			
	       RequestContext ctx = RequestContext.getCurrentContext();
	       HttpServletRequest request = ctx.getRequest();
	       HttpServletResponse response = ctx.getResponse();

	       System.out.println("Method: "+request.getMethod());
	       System.out.println("URL: "+request.getRequestURL().toString());

	       return null;
	       }


}
