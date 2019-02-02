package com.timur.test.controller.authentication;

import com.timur.test.repository.MessageByLocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint
    implements AuthenticationEntryPoint {

  private MessageByLocaleService messageByLocaleService;

  @Autowired
  public void setMessageByLocaleService(
      MessageByLocaleService messageByLocaleService) {
    this.messageByLocaleService = messageByLocaleService;
  }

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException) throws IOException {

    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
        messageByLocaleService.getMessage("auth.error.unAuth"));
  }
}