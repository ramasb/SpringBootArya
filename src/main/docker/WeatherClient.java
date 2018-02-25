package balaji.nila.arya.client;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;

import NET.webserviceX.www.GetCitiesByCountry;
import NET.webserviceX.www.GetCitiesByCountryResponse;
import NET.webserviceX.www.ObjectFactory;

@Component
public class WeatherClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherClient.class);

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	public String getCities(String countryName) {
		GetCitiesByCountry country = new ObjectFactory().createGetCitiesByCountry();
		country.setCountryName(countryName);
		GetCitiesByCountryResponse cities = (GetCitiesByCountryResponse) webServiceTemplate.marshalSendAndReceive(country, new WebServiceMessageCallback() {
			@Override
			public void doWithMessage(WebServiceMessage webServiceMessage) throws IOException, TransformerException {
				((SoapMessage) webServiceMessage).setSoapAction("http://www.webserviceX.NET/GetCitiesByCountry");
			}
		});

		LOGGER.info("Client received cities='{}'", cities);
		return cities.getGetCitiesByCountryResult();
	}
}
