package me.tunsi.cxf.server.interceptor;

import org.apache.cxf.binding.soap.interceptor.SoapHeaderInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;

public class MySoapHeaderInterceptor extends SoapHeaderInterceptor {
	@Override
	public void handleMessage(Message arg0) throws Fault {
		// TODO Auto-generated method stub
		super.handleMessage(arg0);
		
		arg0.getDestination();
		System.out.println("soap header");
	}
}
