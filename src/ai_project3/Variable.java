/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project3;

/**
 *
 * @author Patric
 */
public class Variable {
    
    
  public int Domain [] = {1 , 1 , 1 } ;   // domain[0] = (0,0) , domain[1] = (+,-) , domain[2] = (-,+)
  public int numberOfVariable ;
  public int value = -1;
  
  public int whichVarInArray ;
  public int piece1X;
  public int piece1Y;
  
  public int piece2X;
  public int piece2Y;
    
  public String piece1;
  public String piece2;
}
