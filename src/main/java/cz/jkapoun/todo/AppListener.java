package cz.jkapoun.todo;

import javax.servlet.annotation.WebListener;
import org.springframework.web.context.ContextLoaderListener;

/**
 * Provides the initialization of Spring's ApplicationContext.
 * This listener hooks to the application server (using @WebListener annotation)
 * and automatically initializes the ApplicationContext using the information
 * provided by server.
 * 
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 */
@WebListener
public class AppListener extends ContextLoaderListener {}
