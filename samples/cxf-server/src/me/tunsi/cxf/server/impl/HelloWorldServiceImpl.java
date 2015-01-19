package me.tunsi.cxf.server.impl;

import java.util.Date;

import me.tunsi.cxf.api.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService {

	@Override
	public String sayHello() {
		System.out.println("Hello!");
		return new Date().toString();
	}

}
