package org.ekal.ivd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
	private static final Logger initLogger = LogManager.getLogger(ServletInitializer.class);
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		initLogger.fatal("[ APP STARTED ]");
		return application.sources(EkalIvd.class);
	}

}
