/*
 */

package com.example.jaxws;

/**
 *
 * @author education.com
 */
public class MessagingAPIMessage {
  private String methodName;
  private String argument;
  private String result;

  public MessagingAPIMessage() {}

  public MessagingAPIMessage( String methodName, String argument ) {
    this.methodName = methodName;
    this.argument = argument;
  }

  /**
   * @return the methodName
   */
  public String getMethodName() {
    return methodName;
  }

  /**
   * @param methodName the methodName to set
   */
  public void setMethodName( String methodName ) {
    this.methodName = methodName;
  }

  /**
   * @return the argument
   */
  public String getArgument() {
    return argument;
  }

  /**
   * @param argument the argument to set
   */
  public void setArgument( String argument ) {
    this.argument = argument;
  }

  /**
   * @return the result
   */
  public String getResult() {
    return result;
  }

  /**
   * @param result the result to set
   */
  public void setResult( String result ) {
    this.result = result;
  }
}
