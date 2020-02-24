package it.emix.integration.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.ws.SimpleWebServiceOutboundGateway;
import org.springframework.integration.ws.WebServiceHeaders;
import org.springframework.integration.xml.transformer.XPathTransformer;

@Configuration
@EnableIntegration
public class IntegrationUtils {

	  @Bean
	  public IntegrationFlow convert() {
	      return f -> f
	    		  .log("Transform payload...")
	        .transform(payload ->
	              "<FahrenheitToCelsius xmlns=\"https://www.w3schools.com/xml/\">"
	            +     "<Fahrenheit>" + payload + "</Fahrenheit>"
	            + "</FahrenheitToCelsius>").log("get headers...")
	        .enrichHeaders(h -> h
	            .header(WebServiceHeaders.SOAP_ACTION,
	                "https://www.w3schools.com/xml/FahrenheitToCelsius")).log("added Header soap_action")
	        .handle(new SimpleWebServiceOutboundGateway(
	            "https://www.w3schools.com/xml/tempconvert.asmx")).log("added handler bound gateway")
	        .transform(new XPathTransformer("/*[local-name()=\"FahrenheitToCelsiusResponse\"]"
	            + "/*[local-name()=\"FahrenheitToCelsiusResult\"]"));
	  }
}
