/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;


/**
 *
 * @author iqnev
 */
public enum CommandIndeficator {
     Sensor(1), //byte 
     Motor(2); //byte
     
     private byte type;
  
  private CommandIndeficator(int _type) {
    this.type = (byte) _type;
  }
  
  /**
   * @return the {@code int} representing this {@code ComPortType.}
   */
  public int getInt() {
    return this.type;
  }
}
