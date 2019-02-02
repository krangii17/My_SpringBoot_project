package com.timur.test.controller.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timur.test.repository.MessageByLocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

public class JsonUsernamePasswordAuthenticationFilter extends
        UsernamePasswordAuthenticationFilter {

  private String username;
  private String password;
  private final String HEADER_CONTENT_PARAM_NAME="Content-Type";
  private final String JSON_CONTENT_TYPE ="application/json";

  private MessageByLocaleService messageByLocaleService;

  @Autowired
  public void setMessageByLocaleService(
      MessageByLocaleService messageByLocaleService) {
    this.messageByLocaleService = messageByLocaleService;
  }

  @Autowired
  public void setSuccessHandler(
      CustomSavedRequestAwareAuthenticationSuccessHandler successHandler) {
    super.setAuthenticationSuccessHandler(successHandler);
  }

  @Override
  protected String obtainPassword(HttpServletRequest request) {
    String password = null;
    if (JSON_CONTENT_TYPE.equals(request.getHeader(HEADER_CONTENT_PARAM_NAME))) {
      password = this.password;
    } else {
      password = super.obtainPassword(request);
    }

    return password;
  }

  @Override
  protected String obtainUsername(HttpServletRequest request) {
    String username = null;

    if (JSON_CONTENT_TYPE.equals(request.getHeader(HEADER_CONTENT_PARAM_NAME))) {
      username = this.username;
    } else {
      username = super.obtainUsername(request);
    }

    return username;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response) {

    if (JSON_CONTENT_TYPE.equals(request.getHeader(HEADER_CONTENT_PARAM_NAME))) {
      try {
        StringBuilder sb = new StringBuilder();
        String line = null;

        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
          sb.append(line);
        }

        ObjectMapper mapper = new ObjectMapper();
        LoginRequest loginRequest = mapper.readValue(sb.toString(), LoginRequest.class);

        this.username = loginRequest.getUsername();
        this.password = loginRequest.getPassword();
      } catch (Exception e) {
        throw new AuthenticationServiceException(
            messageByLocaleService.getMessage("auth.error.json.params"));/* NOP */
      }
    } else {
      throw new AuthenticationServiceException(
          messageByLocaleService.getMessage("auth.error.json.type"));
    }
    return super.attemptAuthentication(request, response);
  }
}