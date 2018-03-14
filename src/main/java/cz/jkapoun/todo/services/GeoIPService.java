package cz.jkapoun.todo.services;

import cz.jkapoun.todo.model.GeoIP;
import cz.jkapoun.todo.model.GetGeoIP;
import cz.jkapoun.todo.model.GetGeoIPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Sample SOAP client consuming GeoIP service using Spring WS.
 * 
 * @author Jiří Kapoun <jiri.kapoun@profinit.eu>
 */
@Component
public class GeoIPService extends WebServiceGatewaySupport {

  protected static String SERVICE_URL      = "http://www.webservicex.net/geoipservice.asmx";
  protected static String ACTION_BASE_PATH = "http://www.webservicex.net/";

  @Autowired
  public GeoIPService(Jaxb2Marshaller marshaller) {
    super();
    setDefaultUri(SERVICE_URL);
    setMarshaller(marshaller);
    setUnmarshaller(marshaller);
  }

  /**
   * Looks up the origin country of the given IP address.
   * Returns an object of the GeoIP class, which is auto-generated from the WSDL
   * specification.
   */
  public GeoIP getGeoIP(String ipAddress) {
    GetGeoIP request = new GetGeoIP();
    request.setIPAddress(ipAddress);

    SoapActionCallback callback = new SoapActionCallback(ACTION_BASE_PATH + "GetGeoIP");
    GetGeoIPResponse   response = (GetGeoIPResponse) getWebServiceTemplate().marshalSendAndReceive(SERVICE_URL, request, callback);

    GeoIP geoIp = response.getGetGeoIPResult();
    return geoIp;
  }
}
