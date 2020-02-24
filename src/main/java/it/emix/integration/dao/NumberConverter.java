package it.emix.integration.dao;

import org.springframework.stereotype.Component;

@Component
public interface NumberConverter {

	  Integer getInteger(String number);
}
