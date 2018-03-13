package cz.jkapoun.todo;

import com.vaadin.spring.server.SpringVaadinServlet;
import javax.servlet.annotation.WebServlet;

/**
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 */
@WebServlet(urlPatterns = "/*", asyncSupported = true)
public class AppServlet extends SpringVaadinServlet {}