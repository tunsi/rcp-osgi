package me.tunsi.example.blueprint;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext arg0) throws Exception {
		System.out.println("start example-blueprint");
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		System.out.println("stop example-blueprint");
	}

}
