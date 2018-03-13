package cz.jkapoun.todo;

import com.vaadin.spring.annotation.EnableVaadin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 */
@Configuration
@EnableVaadin
public class AppConfig {

  @Bean
  public static Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("cz.jkapoun.todo.model");
    return marshaller;
  }
  
}
