package me.tunsi.cxf.client.interceptor;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AddSoapHeader extends AbstractSoapInterceptor {

	public static final String xml_namespaceUR_att = "http://tunsi.github.com/test";
	public static final String xml_header_el = "soap:Header";
	public static final String xml_authentication_el = "auth:authentication";
	public static final String xml_systemID_el = "auth:systemID";
	public static final String xml_userID_el = "auth:userID";
	public static final String xml_password_el = "auth:password";

	public AddSoapHeader() {
		super(Phase.WRITE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		
		System.out.println("handler soap header");
		
		QName qname = new QName("RequestSOAPHeader");
		Document doc = (Document) DOMUtils.createDocument();
		Element root = doc.createElement(xml_header_el);
		Element eSysId = doc.createElement(xml_systemID_el);
		eSysId.setTextContent("abcdeSystemIdddddddd");
		Element eUserId = doc.createElement(xml_userID_el);
		eUserId.setTextContent("myNamessss");
		Element ePwd = doc.createElement(xml_password_el);
		ePwd.setTextContent("1234567");
		Element child = doc.createElementNS(xml_namespaceUR_att, xml_authentication_el);
		child.appendChild(eSysId);
		child.appendChild(eUserId);
		child.appendChild(ePwd);
		root.appendChild(child);
		SoapHeader head = new SoapHeader(qname, root);
		List<Header> headers = message.getHeaders();
		headers.add(head);
		
		System.out.println("handler soap header finish.");

	}

}
