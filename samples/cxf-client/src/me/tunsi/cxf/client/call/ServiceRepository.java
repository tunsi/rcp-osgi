package me.tunsi.cxf.client.call;

import me.tunsi.cxf.api.HelloWorldService;

public class ServiceRepository {

	private HelloWorldService helloWorldService;

	public void bindHelloWorld(HelloWorldService svc) {
		helloWorldService = svc;
	}

	public void unbindHelloWorld(HelloWorldService svc) {
		helloWorldService = null;
	}

	public void execService() {
		helloWorldService.sayHello();
	}
}
