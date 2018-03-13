package cz.jkapoun.todo.services;

import cz.jkapoun.todo.model.GeoIP;

public class LoggingService {

  protected GeoIPService geoIPService;

  public LoggingService(GeoIPService geoIPService) {
    this.geoIPService = geoIPService;
  }

  public void logIP(String ip) {
    GeoIP  geoIp   = geoIPService.getGeoIP(ip);
    String country = geoIp.getCountryName();

    System.out.println("=== New user session ===");
    System.out.println("IP: " + ip);
    System.out.println("Country: " + country);
  }
  
}
