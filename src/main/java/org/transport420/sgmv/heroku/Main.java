package org.transport420.sgmv.heroku;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * This class launches the web application in an embedded Jetty container. This
 * is the entry point to your application. The Java command that is used for
 * launching should fire this main method.
 */
public class Main {

	public static void main(String[] args) throws Exception {
		// The port that we should run on can be set into an environment variable
		// Look for that variable and default to 8080 if it isn't there.
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateFormatter.parse("2018-09-21 11:25:30");

		// Now create the time and schedule it
		Timer timer = new Timer();

		// Use this if you want to execute it once
		// timer.schedule(new MyTimeTask(), date);

		// Use this if you want to execute it repeatedly
		int period = 10000;// 10secs
		timer.schedule(new MyTimeTask(), date, period);

		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}

		final Server server = new Server(Integer.valueOf(webPort));
		final WebAppContext root = new WebAppContext();

		root.setContextPath("/");
		// Parent loader priority is a class loader setting that Jetty accepts.
		// By default Jetty will behave like most web containers in that it will
		// allow your application to replace non-server libraries that are part of the
		// container. Setting parent loader priority to true changes this behavior.
		// Read more here: http://wiki.eclipse.org/Jetty/Reference/Jetty_Classloading
		root.setParentLoaderPriority(true);

		final String webappDirLocation = "src/main/webapp/";
		root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
		root.setResourceBase(webappDirLocation);

		server.setHandler(root);

		server.start();
		server.join();
	}
}
