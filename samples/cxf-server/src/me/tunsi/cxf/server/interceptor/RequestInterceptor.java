package me.tunsi.cxf.server.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

public class RequestInterceptor extends AbstractPhaseInterceptor<Message> {

	public RequestInterceptor() {
		super(Phase.RECEIVE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleMessage(Message arg0) throws Fault {
		// TODO Auto-generated method stub
		System.out.println("handler");

		HttpServletRequest request = (HttpServletRequest) arg0.get(AbstractHTTPDestination.HTTP_REQUEST);
		String ip = request.getRemoteAddr();
		System.out.println(ip);
	}

}
