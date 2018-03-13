package cz.jkapoun.todo.services;

import cz.jkapoun.todo.model.GeoIP;
import cz.jkapoun.todo.model.GetGeoIP;
import cz.jkapoun.todo.model.GetGeoIPResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class GeoIPService extends WebServiceGatewaySupport {

  protected static String SERVICE_URL      = "http://www.webservicex.net/geoipservice.asmx";
  protected static String ACTION_BASE_PATH = "http://www.webservicex.net/";

  public GeoIPService(Jaxb2Marshaller marshaller) {
    super();
    setDefaultUri(SERVICE_URL);
    setMarshaller(marshaller);
    setUnmarshaller(marshaller);
  }

  public GeoIP getGeoIP(String ipAddress) {
    GetGeoIP request = new GetGeoIP();
    request.setIPAddress(ipAddress);

    SoapActionCallback callback = new SoapActionCallback(ACTION_BASE_PATH + "GetGeoIP");
    GetGeoIPResponse   response = (GetGeoIPResponse) getWebServiceTemplate().marshalSendAndReceive(SERVICE_URL, request, callback);

    GeoIP geoIp = response.getGetGeoIPResult();
    return geoIp;
  }
}
