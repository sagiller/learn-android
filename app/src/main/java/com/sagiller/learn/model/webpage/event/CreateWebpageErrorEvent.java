package com.sagiller.learn.model.webpage.event;

import com.hannesdorfmann.mosby.sample.mail.model.mail.Mail;

/**
 * @author Hannes Dorfmann
 */
public class CreateWebpageErrorEvent {

  private Mail mail;
  private Throwable exception;

  public CreateWebpageErrorEvent(Mail mail, Throwable exception) {
    this.mail = mail;
    this.exception = exception;
  }

  public Mail getMail() {
    return mail;
  }

  public Throwable getException() {
    return exception;
  }
}
