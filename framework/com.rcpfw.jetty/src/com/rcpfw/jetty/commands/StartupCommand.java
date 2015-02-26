package com.rcpfw.jetty.commands;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartupCommand {
	@Execute
	public void execute() {
		Server server = new Server(8080);

		WebAppContext context = new WebAppContext();
		context.setResourceBase("");
		context.setParentLoaderPriority(true);

		server.setHandler(context);
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}