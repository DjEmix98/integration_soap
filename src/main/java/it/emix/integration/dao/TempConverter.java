package it.emix.integration.dao;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface TempConverter {

  @Gateway(requestChannel = "convert.input")
  float fahrenheitToCelcius(float fahren);

}


