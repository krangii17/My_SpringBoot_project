package com.timur.test.repository;

/**
 * Use for localize messages for user
 */
public interface MessageByLocaleService {

  public String getMessage(String id);

  public String getMessage(String id, Object... args);
}
