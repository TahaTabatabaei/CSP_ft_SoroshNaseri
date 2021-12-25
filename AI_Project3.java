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
  public static int NegetivesInRows [] ;
    public static int PositivesInRows [] ;
    public static int NegetivesInCoulmn [] ;
    public static int PositvesInCoulmn [] ;
    public static int FirstTable [][] ;
    public static Variable variables []  ;
    public static int Coulmns ; 
    public static int Rows ;
    public static pice TableOfValues[][] ;
    
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
// Get the information ................................................................
        Scanner io = new Scanner(System.in) ;
        Rows = io.nextInt() ;
        Coulmns = io.nextInt() ;
        
        PositivesInRows = new int [Rows] ;
        PositvesInCoulmn = new int [Coulmns] ;
        NegetivesInRows = new int [Rows] ;
        NegetivesInCoulmn = new int [Coulmns] ;
        
        for(int i = 0 ; i < Rows ; i ++ ) {
            
            PositivesInRows[i] = io.nextInt() ; 
            
        }
        for(int i = 0 ; i < Rows ; i ++ ) {
            
            NegetivesInRows[i] = io.nextInt() ;
            
        }
        
        for(int i = 0 ; i < Coulmns ; i++) {
            
            PositvesInCoulmn[i] = io.nextInt() ;
            
        }
        
         for(int i = 0 ; i < Coulmns ; i++) {
            
            NegetivesInCoulmn[i] = io.nextInt() ;
            
        }
        
        FirstTable = new int [Rows][Coulmns] ;
        
        for(int i = 0 ; i < Rows ; i ++ ){
            for(int j = 0 ; j < Coulmns ; j ++ ){
                
                FirstTable [i][j] = io.nextInt() ; 
                
            }
        }
 //........................................................................................

        TableOfValues  = new pice[Rows][Coulmns] ; 
        for(int i = 0 ; i < Rows ; i ++ ){
            for(int j = 0 ; j < Coulmns ; j ++ ){
                TableOfValues[i][j] = new pice() ;
                
            }
        }
        
        int numberOfVariable = Rows*Coulmns / 2 ;
        variables = new Variable[numberOfVariable] ;
        for(int u = 0 ; u < numberOfVariable ; u ++ ){
             variables[u] = new Variable() ;
        }
        int counter1 = 0 ; 
        int pp [] = new int [numberOfVariable] ;
        
        for(int i = 0  ; i < Rows ; i ++ ){
            for(int j = 0 ; j < Coulmns ; j ++ ) {
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
                    variables [counter1].pice1X = i  ; 
                    variables [counter1].pice1Y = j ;
                    variables [counter1].numberOfVariable = counter1 ;
                    if(i < Rows -1 ){
                        if(FirstTable[i+1][j] == number ) {
                            TableOfValues[i+1][j].Variable = counter1 ;
                            variables[counter1].pice2X = i+1 ;
                            variables[counter1].pice2Y =j ;
                            

                            
                        }
                    }
                    if(j < Coulmns -1 ){
                         if(FirstTable[i][j+1] == number ) {
                             TableOfValues[i][j+1].Variable = counter1 ;
                            variables[counter1].pice2X = i ;
                            variables[counter1].pice2Y =j+1 ;
                            
                           
                        }
                        
                    }
                    counter1 += 1  ;
                }
            }
        }
        
//        for(int i = 0 ; i < numberOfVariable ; i ++ ){
//         
//            System.out.print(variables[i].pice1X + "  ");
//            System.out.print(variables[i].pice1Y + "  ");
//            System.out.print(variables[i].pice2X + "  ");
//            System.out.print(variables[i].pice2Y + "  ");
//            System.out.println();
            
            
//        }

//while(true ){
//    int Domain = io.nextInt() ;
//    int varo = io.nextInt() ;
//    add(Domain , varo ) ;
//    for(int i = 0 ; i < Rows ; i ++ ){
//        for(int j = 0 ; j < Coulmns;j++ ){
//            System.out.print(TableOfValues[i][j].value + "  ");
//        }
//        System.out.println();
//    }
//}
      
 
        
//        System.out.println(isComplete()) ;
//        System.out.println( satisfy_Constrains ());
//for ( int i = 0 ; i < Rows ; i ++ ) {
//    for (int j = 0 ; j < Coulmns ; j ++ ){
//        TableOfValues[i][j].value = io.next() ;
//    }
//}
// for (int ioo = 0  ; ioo < Rows ; ioo ++ ){
//                for( int j = 0 ; j < Coulmns ; j ++){
//                    System.out.print(TableOfValues[ioo][j].value + "   ");
//                }
//                System.out.println() ;
//            }
//System.out.println(satisfy_Constrains () ) ;


        boolean i = Backtracking() ;
        if(i == true){
            
            for (int ioo = 0  ; ioo < Rows ; ioo ++ ){
                for( int j = 0 ; j < Coulmns ; j ++){
                    System.out.print(TableOfValues[ioo][j].value+"  ");
                }
                System.out.println() ;
            }
            
        }else{
            System.out.print("sdsdfasdfsfsafsa");
        }
        
        
    }
    
    
    public static boolean  isSati(int var ){
        if( variables[var].value == 1 || variables[var].value == 2){
        int x1 , x2  , y1 , y2  ;
        x1 = variables[var].pice1X ; 
        y1 = variables[var].pice1Y ; 
        x2 = variables[var].pice2X ; 
        y2 = variables[var].pice2Y ;
        int positiveInRow1 = 0  ;
        int positiveInCoulmn1 =0;
        int positiveInRow2  =0 ;
        int positiveInCoulmn2 = 0;
        int negetiveInRow1  =0 ;
        int negetiveInCoulmn1 =0  ;
        int negetiveInRow2  = 0;
        int negetiveInCoulmn2  = 0;
        for(int q1 = 0 ; q1 < Coulmns ; q1 ++ ){
            if(TableOfValues[x1][q1].value.equals("+")){
                 positiveInRow1 ++ ;
            }else{
                if(TableOfValues[x1][q1].value.equals("-")){
                    negetiveInRow1 ++ ;
                }
            }
            
            
             if(TableOfValues[x2][q1].value.equals("+")){
                 positiveInRow2 ++ ;
            }else{
                if(TableOfValues[x2][q1].value.equals("-")){
                    negetiveInRow2 ++ ;
                }
            }
            
            
        }
        
        for(int q1 = 0 ; q1 < Rows ; q1 ++ ){
            if(TableOfValues[q1][y1].value.equals("+")){
                 positiveInCoulmn1 ++ ;
            }else{
                if(TableOfValues[q1][y1].value.equals("-")){
                    negetiveInCoulmn1 ++ ;
                }
            }
            
            
             if(TableOfValues[q1][y2].value.equals("+")){
                 positiveInCoulmn2 ++ ;
            }else{
                if(TableOfValues[q1][y2].value.equals("-")){
                    negetiveInCoulmn2 ++ ;
                }
            }
            
            
        }
        
        
        if(positiveInRow1 > PositivesInRows [x1] || positiveInRow2 > PositivesInRows [x2] || positiveInCoulmn1 > PositvesInCoulmn [y1] ||
        positiveInCoulmn2 > PositvesInCoulmn [y2] || negetiveInRow1 > NegetivesInRows [x1] || negetiveInRow2 > NegetivesInRows [x2]
        || negetiveInCoulmn1 > NegetivesInCoulmn [y1] ||negetiveInCoulmn2 > NegetivesInCoulmn [y2]  ){
   
    return false ;
}
        
        
        
        
        if(x1 < Rows -1  && TableOfValues[x1+1][y1] == TableOfValues[x1][y1] ){
            return false ;
        }
        if (x1 > 0 && TableOfValues[x1 -1][y1] == TableOfValues[x1][y1]) {
            return false;
        }
         if(y1 < Coulmns -1  && TableOfValues[x1][y1+1] == TableOfValues[x1][y1] ){
            return false ;
        }
        if (y1 > 0 && TableOfValues[x1 ][y1 -1 ] == TableOfValues[x1][y1]) {
            return false;
        }
         if(x2 < Rows -1  && TableOfValues[x2+1][y2] == TableOfValues[x2][y2] ){
            return false ;
        }
        if (x2 > 0 && TableOfValues[x2 -1][y2] == TableOfValues[x2][y2]) {
            return false;
        }
         if(y2 < Coulmns -1  && TableOfValues[x2][y2+1] == TableOfValues[x2][y2] ){
            return false ;
        }
        if (y2 > 0 && TableOfValues[x2 ][y2 -1 ] == TableOfValues[x2][y2]) {
            return false;
        }
        
        
        
        
        }
        return true ;
    }
    public static boolean Backtracking(){
        
            
        
        if(isComplete() &&satisfy_Constrains () ){return true ;}
        if(isComplete() && !satisfy_Constrains()){return false ;}
        
        int var = Select_Unsighnd_Variable(Coulmns * Rows / 2) ;
       // System.out.println(var) ;
        for (int i = 0 ; i <3 ; i++){
            if (variables[var].Domain[i] != 0 ){
               
                int domainVar = variables[var].value ; 
                add(i   , var );
         //        System.out.println(variables[var].value);
                boolean iop = isSati(var) ;
                
            if( iop){
                
                // write infrenses  AC_3 Forward 
                
                
                boolean result = Backtracking() ;
                
                
                if(result == true){
                    return true ;
                }
                variables[var].value = -1 ;
                TableOfValues[variables[var].pice1X][variables[var].pice1Y].value  = "0" ;
                TableOfValues[variables[var].pice2X][variables[var].pice2Y].value  = "0" ;

                
                
                
            } else{
               variables[var].value = -1 ;
               TableOfValues[variables[var].pice1X][variables[var].pice1Y].value  = "0" ;
               TableOfValues[variables[var].pice2X][variables[var].pice2Y].value  = "0" ;

                
           
            
            
            
            
            
            
            
            }
                
             
            
            
            
                
            }
            
        }
        return false ;
        
        
        
    }
    
    public static void AC_3 (){
    
}
    public static void  Forward(){
        
    }
    
    // in this function we give a variable and one of its domains and the function
    // will change the variable 
    public static void   add (int Domain , int var){
        
        if(Domain == 0 ) {
           variables[var].value = 0  ;
           variables[var].pice1 = "0"  ;
           variables[var].pice2 = "0"  ;
           TableOfValues[variables[var].pice1X][variables[var].pice1Y].value = "0" ;
           TableOfValues[variables[var].pice2X][variables[var].pice2Y].value = "0" ;
            
        }else{
            if(Domain == 1) {
           variables[var].value = 1 ; 
           variables[var].pice1 = "+"  ;
           variables[var].pice2 = "-"  ;
           TableOfValues[variables[var].pice1X][variables[var].pice1Y].value = "+" ;
           TableOfValues[variables[var].pice2X][variables[var].pice2Y].value = "-" ;
            }else{
                if(Domain == 2 ){
           variables[var].value = 2 ;
           variables[var].pice1 = "-"  ;
           variables[var].pice2 = "+"  ;
           TableOfValues[variables[var].pice1X][variables[var].pice1Y].value = "-" ;
           TableOfValues[variables[var].pice2X][variables[var].pice2Y].value = "+" ;
                }
            }
        }
        
        
        
        
    }
    
    
    
    
    
    
    
    
    public static  int Select_Unsighnd_Variable(int sizeOfVariables){
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
    for(int i = 0  ; i  < Coulmns ; i ++ ) {
        int positive = 0  ; 
        int negetive = 0 ; 
        for(int j = 0 ; j < Rows ; j ++ ) {
          //  System.out.println(TableOfValues[j][i].value) ;
            if (TableOfValues[j][i].value.equals("+")){
                
                positive+=1 ; 
                
            }else{
                if(TableOfValues[j][i].value.equals("-")){
                    negetive +=1 ; 
                }
            }
            
        }
        if(PositvesInCoulmn [i] != positive || NegetivesInCoulmn[i] != negetive){
         
       return false ;
        }
    }
    
     for(int i = 0  ; i  < Rows ; i ++ ) {
        int positive = 0  ; 
        int negetive = 0 ; 
        for(int j = 0 ; j < Coulmns ; j ++ ) {
            if (TableOfValues[i][j].value.equals("+")){
                positive++ ; 
                
            }else{
                if(TableOfValues[i][j].value.equals("-")){
                    negetive ++ ; 
                }
            }
            
        }
        if(PositivesInRows[i] != positive || NegetivesInRows[i] != negetive){
                       

       return false ;
        }
    }
    
    for(int i = 0 ; i < Rows  ; i ++ ) {
        for(int j = 0 ; j < Coulmns ; j ++ ) {
            
            if(TableOfValues[i][j].value.equals("+")){
                
                 if(i < Rows -1 ){
                        if(TableOfValues[i+1][j].value.equals("+")) {
                            

                          return false ;    
                        }
                    }
                    if(j < Coulmns -1 ){
                         if(TableOfValues[i][j+1].value.equals("+") ) {
                                        

                            return false ;
                           
                        }
                        
                    }
                
            }
            
            
             if(TableOfValues[i][j].value.equals("-")){
                
                 if(i < Rows -1 ){
                        if(TableOfValues[i+1][j].value.equals("-") ) {
                                       

                          return false ;    
                        }
                    }
                    if(j < Coulmns -1 ){
                         if(TableOfValues[i][j+1].value.equals("-") ) {
                                        
                            return false ;
                           
                        }
                        
                    }
                
            }
            
            
        }
    }
    
     return true ;
    
        
        
}
    
    


public static boolean isComplete(){
    for(int i = 0 ; i  <  variables.length ; i ++ ){
        if( variables[i].value == -1 ){
            return false ;
        }
      
    }
      return true ;
    
}
   
}
