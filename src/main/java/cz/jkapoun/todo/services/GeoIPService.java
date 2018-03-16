package cz.jkapoun.todo.services;

import cz.jkapoun.todo.model.GeoIP;

/**
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 */
public interface GeoIPService {

  /**
   * Looks up the origin country of the given IP address.
   * Returns an object of the GeoIP class, which is auto-generated from the WSDL
   * specification.
   */
  GeoIP getGeoIP(String ipAddress);

}
