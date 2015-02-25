package me.tunsi.example.blueprint.impl;

import me.tunsi.example.blueprint.api.ExampleService;

public class ExampleServiceImpl implements ExampleService {

	private String somethingPrefix;

	@Override
	public String doSomething(String something) {
		System.out.println(getSomethingPrefix() + something);
		return getSomethingPrefix() + something;
	}

	public String getSomethingPrefix() {
		return somethingPrefix;
	}

	public void setSomethingPrefix(String somethingPrefix) {
		this.somethingPrefix = somethingPrefix;
	}

}
