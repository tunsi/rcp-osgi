package me.tunsi.cxf.client;

import me.tunsi.cxf.api.HelloWorldService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

	private ServiceTracker<HelloWorldService, HelloWorldService> tracker;

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		tracker = new ServiceTracker<HelloWorldService, HelloWorldService>(bundleContext, HelloWorldService.class, null) {
			@Override
			public HelloWorldService addingService(ServiceReference<HelloWorldService> reference) {
				HelloWorldService helloWorldService = super.addingService(reference);

				String sayHello = helloWorldService.sayHello();
				System.out.println(sayHello);

				return helloWorldService;
			}
		};
		tracker.open();
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;

		tracker.close();
	}

}
