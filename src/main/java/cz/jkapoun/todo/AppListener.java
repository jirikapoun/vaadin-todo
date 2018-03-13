package cz.jkapoun.todo;

import javax.servlet.annotation.WebListener;
import org.springframework.web.context.ContextLoaderListener;

/**
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 */
@WebListener
public class AppListener extends ContextLoaderListener {}
