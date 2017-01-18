package com.gramcha.autocompleteservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ping")
public class PingController {
  
  private final Logger LOG = LoggerFactory.getLogger(PingController.class);
  
  @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<String > index(HttpServletRequest request) {
    LOG.info("Recieved a ping from " + request.getRemoteAddr());
    return ResponseEntity.ok("{\"message\" : \"It works :)\"}");    
  }
  
}
