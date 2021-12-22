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
import java.util.* ;

public class AI_Project3 {

    /**
     * @param args the command line arguments
     */

    public static int[] NegativesInRows;
    public static int[] PositivesInRows;
    public static int[] NegativesInColumns;
    public static int[] PositiveInColumns;
    public static int[][] FirstTable;
    public static Variable[] variables; // change domain
    public static int Columns;
    public static int Rows ;
    public static piece[][] TableOfValues; // copy begir!

    public static void main(String[] args) {
        // TODO code application logic here
        
// Get the information ................................................................
        Scanner io = new Scanner(System.in) ;
        Rows = io.nextInt() ;
        Columns = io.nextInt() ;
        
        PositivesInRows = new int [Rows] ;
        PositiveInColumns = new int [Columns] ;
        NegativesInRows = new int [Rows] ;
        NegativesInColumns = new int [Columns] ;
        
        for(int i = 0 ; i < Rows ; i ++ ) {
            
            PositivesInRows[i] = io.nextInt() ; 
            
        }
        for(int i = 0 ; i < Rows ; i ++ ) {
            
            NegativesInRows[i] = io.nextInt() ;
            
        }
        
        for(int i = 0; i < Columns; i++) {
            
            PositiveInColumns[i] = io.nextInt() ;
            
        }
        
         for(int i = 0; i < Columns; i++) {
            
            NegativesInColumns[i] = io.nextInt() ;
            
        }
        
        FirstTable = new int [Rows][Columns] ;
        
        for(int i = 0 ; i < Rows ; i ++ ){
            for(int j = 0; j < Columns; j ++ ){
                
                FirstTable [i][j] = io.nextInt() ;
            }
        }
 //........................................................................................

        TableOfValues  = new piece[Rows][Columns] ;
        for(int i = 0 ; i < Rows ; i ++ ){
            for(int j = 0; j < Columns; j ++ ){
                TableOfValues[i][j] = new piece() ;
            }
        }
        
        int numberOfVariable = Rows* Columns / 2 ;
        variables = new Variable[numberOfVariable] ;
        for(int u = 0 ; u < numberOfVariable ; u ++ ){
             variables[u] = new Variable() ;
        }
        int counter1 = 0 ; 
        int pp [] = new int [numberOfVariable] ;
        
        for(int i = 0  ; i < Rows ; i ++ ){
            for(int j = 0; j < Columns; j ++ ) {
                int counter = 0 ; 
                int number = FirstTable[i][j] ;
                for(int p = 0 ; p < numberOfVariable ; p ++ ){
                    if(  pp[p] == number ){
                        counter +=1 ;
                        break ; 
                    }
                }
                if(counter == 0 ) {
                    
                    pp[counter1] = number ; 
                    TableOfValues[i][j].Variable = counter1 ;
                    variables[counter1].whichVarInArray = counter1 ;
                    variables [counter1].piece1X = i  ;
                    variables [counter1].piece1Y = j ;
                    variables [counter1].numberOfVariable = counter1 ;
                    if(i < Rows -1 ){
                        if(FirstTable[i+1][j] == number ) {
                            TableOfValues[i+1][j].Variable = counter1 ;
                            variables[counter1].piece2X = i+1 ;
                            variables[counter1].piece2Y =j ;
                            

                            
                        }
                    }
                    if(j < Columns -1 ){
                         if(FirstTable[i][j+1] == number ) {
                             TableOfValues[i][j+1].Variable = counter1 ;
                            variables[counter1].piece2X = i ;
                            variables[counter1].piece2Y =j+1 ;
                            
                           
                        }
                        
                    }
                    counter1 += 1  ;
                }
            }
        }

        System.out.println(isComplete()) ;
        System.out.println( satisfy_Constrains ());
    }

    public static void Backtracking(){
        
    }
    
    public static void AC_3 (){
    
}
    public static void  Forward(){
        
    }
    
    // TODO cheack Domain
    // TODO Update Domain
    
    // in this function we give a variable and one of its domains and the function
    // will change the variable 
    public static void  add(int Domain , int var){
        
        if(Domain == 0 ) {
           variables[var].value = 0  ;
           variables[var].piece1 = "0"  ;
           variables[var].piece2 = "0"  ;
           TableOfValues[variables[var].piece1X][variables[var].piece1Y].value = "0" ;
           TableOfValues[variables[var].piece2X][variables[var].piece2Y].value = "0" ;
            
        }else{
            if(Domain == 1) {
           variables[var].value = 1 ; 
           variables[var].piece1 = "+"  ;
           variables[var].piece2 = "-"  ;
           TableOfValues[variables[var].piece1X][variables[var].piece1Y].value = "+" ;
           TableOfValues[variables[var].piece2X][variables[var].piece2Y].value = "-" ;
            }else{
                if(Domain == 2 ){
           variables[var].value = 2 ;
           variables[var].piece1 = "-"  ;
           variables[var].piece2 = "+"  ;
           TableOfValues[variables[var].piece1X][variables[var].piece1Y].value = "-" ;
           TableOfValues[variables[var].piece2X][variables[var].piece2Y].value = "+" ;
                }
            }
        }
    }

    public int Select_Unsigned_Variable(int sizeOfVariables){
        int variable = -1 ;
        for(int i = 0  ; i < sizeOfVariables ; i ++ ){
            if(variables[i].value == -1){
                variable = i ;
                break ;
            }
        }
        return variable;
    }

public static boolean satisfy_Constrains (){
    for(int i = 0; i  < Columns; i ++ ) {
        int positive = 0  ; 
        int negative = 0 ;
        for(int j = 0 ; j < Rows ; j ++ ) {
            if (TableOfValues[i][j].value == "+"){
                positive++ ; 
                
            }else{
                if(TableOfValues[i][j].value == "-"){
                    negative ++ ;
                }
            }
            
        }
        if(PositiveInColumns[i] != positive || NegativesInColumns[i] != negative){
       return false ;
        }
    }
    
     for(int i = 0  ; i  < Rows ; i ++ ) {
        int positive = 0  ; 
        int negetive = 0 ; 
        for(int j = 0; j < Columns; j ++ ) {
            if (TableOfValues[i][j].value == "+"){
                positive++ ; 
                
            }else{
                if(TableOfValues[i][j].value == "-"){
                    negetive ++ ; 
                }
            }
            
        }
        if(PositivesInRows[i] != positive || NegativesInRows[i] != negetive){
       return false ;
        }
    }
    
    for(int i = 0 ; i < Rows  ; i ++ ) {
        for(int j = 0; j < Columns; j ++ ) {
            
            if(TableOfValues[i][j].value == "+"){
                
                 if(i < Rows -1 ){
                        if(TableOfValues[i+1][j].value != "+" ) {
                          return false ;    
                        }
                    }
                    if(j < Columns -1 ){
                         if(TableOfValues[i][j+1].value != "+" ) {
                            
                            return false ;
                        }
                    }
            }

             if(TableOfValues[i][j].value == "-"){
                
                 if(i < Rows -1 ){
                        if(TableOfValues[i+1][j].value != "-" ) {
                          return false ;    
                        }
                    }
                    if(j < Columns -1 ){
                         if(TableOfValues[i][j+1].value != "-" ) {
                            
                            return false ;
                           
                        }
                        
                    }
            }
        }
    }
    
     return true ;
}
    
// check meghdar dehi hame variable ha
public static boolean isComplete(){
    for(int i = 0 ; i  <  variables.length ; i ++ ){
        if( variables[i].value == -1 ){
            return false ;
        }
    }
      return true ;
}
}
