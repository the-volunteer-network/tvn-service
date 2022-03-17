package edu.cnm.deepdive.tvnservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *Starting and entry point of the application TVN.
 */
@SpringBootApplication
public class TvnServiceApplication {

  /**
   * Main method of the application to start the application.
   * @param args passed to the method.
   */
  public static void main(String[] args) {
    SpringApplication.run(TvnServiceApplication.class, args);
  }

}
