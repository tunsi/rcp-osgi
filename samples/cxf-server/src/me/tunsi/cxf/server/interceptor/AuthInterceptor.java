package me.tunsi.cxf.server.interceptor;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public static final String xml_namespaceUR_att = "http://tunsi.github.com/test";
	public static final String xml_header_el = "soap:Header";
	public static final String xml_authentication_el = "auth:authentication";
	public static final String xml_systemID_el = "auth:systemID";
	public static final String xml_userID_el = "auth:userID";
	public static final String xml_password_el = "auth:password";

	public AuthInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("修改soap头");

		List<Header> headers = message.getHeaders();

		if (headers != null && headers.size() > 0) {
			for (Header header : headers) {

				Element element = (Element) header.getObject();
				

				System.out.println("userIdNodes: " + element.getFirstChild().getChildNodes().item(1).getTextContent());
				System.out.println("pwdNodes: " + element.getFirstChild().getChildNodes().item(2).getTextContent());
				System.out.println("systemIdNodes: " + element.getFirstChild().getChildNodes().item(0).getTextContent());
				
				
			}
		}
	}

}
