package cz.jkapoun.todo;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import javax.servlet.annotation.WebServlet;

/**
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 */
@WebServlet(urlPatterns = "/*", name = "AppServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = App.class, productionMode = false)
public class AppServlet extends VaadinServlet {
}