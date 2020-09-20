package com.safetynet.alerts.interfaces;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.safetynet.alerts.model.ListObjects")
public interface IJsonReader {

	void readerList();

}