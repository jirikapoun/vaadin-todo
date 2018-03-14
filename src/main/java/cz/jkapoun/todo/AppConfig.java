package cz.jkapoun.todo;

import com.vaadin.spring.annotation.EnableVaadin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Spring configuration class.
 * Methods annotated with @Bean instruct Spring how to create objects of the
 * given type. Spring-Vaadin integration is set up by @EnableVaadin annotation.
 * 
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 */
@Configuration
@EnableVaadin
public class AppConfig {

  /**
   * Sets up the marshaller for Spring Web Services.
   * In order to the marshaller to work properly, the context path passed to
   * marshaller.setContextPath() method must match with the context path of the
   * classes generated from the WSDL specification.
   */
  @Bean
  public static Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("cz.jkapoun.todo.model");
    return marshaller;
  }
  
}
