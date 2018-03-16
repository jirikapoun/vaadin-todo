package cz.jkapoun.todo.services;

/**
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 */
public interface LoggingService {

  /**
   * Logs the HTTP request to the standard output.
   * For demonstration of use of the Spring Web Services library, the method
  uses SoapGeoIPService to look up the country the IP address is from.
   *
   * @see SoapGeoIPService
   */
  void logRequest(String ipAddress);

}
