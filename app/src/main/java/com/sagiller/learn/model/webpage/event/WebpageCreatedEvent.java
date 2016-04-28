package com.sagiller.learn.model.webpage.event;


/**
 * Thrown to inform that a mail has been sent successfully
 * @author Hannes Dorfmann
 */
public class WebpageCreatedEvent {

  private Mail mail;

  public WebpageCreatedEvent(Mail mail) {
    this.mail = mail;
  }

  public Mail getMail() {
    return mail;
  }
}

