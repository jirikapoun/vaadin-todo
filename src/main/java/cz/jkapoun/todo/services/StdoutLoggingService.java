package cz.jkapoun.todo.services;

import cz.jkapoun.todo.model.GeoIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Provides the logging capability of the application.
 * 
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 */
@Component
public class StdoutLoggingService implements LoggingService {

  protected GeoIPService geoIPService;

  @Autowired
  public StdoutLoggingService(SoapGeoIPService geoIPService) {
    this.geoIPService = geoIPService;
  }

  /**
   * Logs the HTTP request to the standard output.
   * For demonstration of use of the Spring Web Services library, the method
 uses SoapGeoIPService to look up the country the IP address is from.
   * 
   * @see SoapGeoIPService
   */
  @Override
  public void logRequest(String ipAddress) {
    GeoIP  geoIp   = geoIPService.getGeoIP(ipAddress);
    String country = geoIp.getCountryName();

    System.out.println("=== New request ===");
    System.out.println("IP: " + ipAddress);
    System.out.println("Country: " + country);
  }
  
}
