package com.workfront.intern.cb.web;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 *
 */
public class Initializer implements ServletContextListener {
	private static Logger LOG = Logger.getLogger(Initializer.class);

	/***/
	private static ApplicationContext APPLICATION_CONTEXT;
	private static ServletContext SERVLET_CONTEXT;
	private static String SERVER_ROOT_PATH;
	private static String RESOURCES_PATH;
	private static String FILES_PATH;

	public static final File tempDir = new File(System.getProperty("java.io.tmpdir"));

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		SERVLET_CONTEXT = sce.getServletContext();
		APPLICATION_CONTEXT = (ApplicationContext)SERVLET_CONTEXT.getAttribute(WebApplicationContext.
			ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		SERVER_ROOT_PATH = SERVLET_CONTEXT.getRealPath("/");

		RESOURCES_PATH = SERVER_ROOT_PATH + File.separator + "WEB-INF/resources";
		FILES_PATH = RESOURCES_PATH + File.separator + "files";
		File filesDir = new File(FILES_PATH);
		if (!filesDir.exists())
			if (!filesDir.mkdirs()) {
				LOG.error("-- could not create files directory");
			}

		LOG.info("-- context initialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOG.info("-- context destroyed");
	}

	///////////////////////////////////////////////

	public static ApplicationContext getApplicationContext() { return APPLICATION_CONTEXT; }
	public static ServletContext getServletContext() { return SERVLET_CONTEXT; }
	public static String getServerRootPath() { return SERVER_ROOT_PATH; }
	public static String getResourcesPath() { return RESOURCES_PATH; }
	public static String getFilesPath() { return FILES_PATH; }
}
