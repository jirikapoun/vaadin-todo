package cz.jkapoun.todo;

import com.vaadin.spring.server.SpringVaadinServlet;
import javax.servlet.annotation.WebServlet;

/**
 * Java Servlet of the application.
 * Ensures that the application is accessible through the web interface. All the
 * set-up code is provided by SpringVaadinServlet parent class, there is no need
 * to program anything.
 * 
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 */
@WebServlet(urlPatterns = "/*", asyncSupported = true)
public class AppServlet extends SpringVaadinServlet {}