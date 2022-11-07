package com.example.takehome.config;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationEventListener {

	@EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) throws Exception {
        log.info("ContextRefreshedEvent");
        
        /*
        try {
        	log.info("Load data");
        	ContinentDao.loadData();
        	CountryDao.loadData();
        	log.info("Data loaded successfully");
        } catch (Exception e) {
        	log.error("Data load failure", e);
        	throw e;
		}
		*/
        
    }
	 
}
