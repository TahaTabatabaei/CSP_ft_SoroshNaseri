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
    public static int[] NegativeInRows;
    public static int[] PositivesInRows;
    public static int[] NegativeInColumns;
    public static int[] PositiveInColumns;
    public static int[][] FirstTable;
    public static Variable[] variables;
    public static int Columns;
    public static int Rows ;
    public static piece[][] TableOfValues;




    public static void main(String[] args) {
        // TODO code application logic here

// Get the information ................................................................
        Scanner io = new Scanner(System.in) ;
        Rows = io.nextInt() ;
        Columns = io.nextInt() ;

        PositivesInRows = new int [Rows] ;
        PositiveInColumns = new int [Columns] ;
        NegativeInRows = new int [Rows] ;
        NegativeInColumns = new int [Columns] ;

        for(int i = 0 ; i < Rows ; i ++ ) {

            PositivesInRows[i] = io.nextInt() ;
        }
        for(int i = 0 ; i < Rows ; i ++ ) {

            NegativeInRows[i] = io.nextInt() ;
        }

        for(int i = 0; i < Columns; i++) {

            PositiveInColumns[i] = io.nextInt() ;
        }

        for(int i = 0; i < Columns; i++) {

            NegativeInColumns[i] = io.nextInt() ;
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
        /*
        // TODO get neighbors
        for (Variable var : variables) {
            var.neighbors = getNeighbors(var);
        }
         */
        boolean i = Backtracking() ;
        if(i == true){

            for (int ioo = 0  ; ioo < Rows ; ioo ++ ){
                for(int j = 0; j < Columns; j ++){
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
            x1 = variables[var].piece1X ;
            y1 = variables[var].piece1Y ;
            x2 = variables[var].piece2X ;
            y2 = variables[var].piece2Y ;
            int positiveInRow1 = 0  ;
            int positiveInColumn1 =0;
            int positiveInRow2  =0 ;
            int positiveInColumn2 = 0;
            int negativeInRow1  =0 ;
            int negativeInColumn1 =0  ;
            int negativeInRow2  = 0;
            int negativeInColumn2  = 0;
            for(int q1 = 0; q1 < Columns; q1 ++ ){
                if(TableOfValues[x1][q1].value.equals("+")){
                    positiveInRow1 ++ ;
                }else{
                    if(TableOfValues[x1][q1].value.equals("-")){
                        negativeInRow1 ++ ;
                    }
                }
                if(TableOfValues[x2][q1].value.equals("+")){
                    positiveInRow2 ++ ;
                }else{
                    if(TableOfValues[x2][q1].value.equals("-")){
                        negativeInRow2 ++ ;
                    }
                }
            }

            for(int q1 = 0 ; q1 < Rows ; q1 ++ ){
                if(TableOfValues[q1][y1].value.equals("+")){
                    positiveInColumn1 ++ ;
                }else{
                    if(TableOfValues[q1][y1].value.equals("-")){
                        negativeInColumn1 ++ ;
                    }
                }

                if(TableOfValues[q1][y2].value.equals("+")){
                    positiveInColumn2 ++ ;
                }else{
                    if(TableOfValues[q1][y2].value.equals("-")){
                        negativeInColumn2 ++ ;
                    }
                }
            }

            if(positiveInRow1 > PositivesInRows [x1] || positiveInRow2 > PositivesInRows [x2] || positiveInColumn1 > PositiveInColumns[y1] ||
                    positiveInColumn2 > PositiveInColumns[y2] || negativeInRow1 > NegativeInRows[x1] || negativeInRow2 > NegativeInRows[x2]
                    || negativeInColumn1 > NegativeInColumns[y1] ||negativeInColumn2 > NegativeInColumns[y2]  ){

                return false ;
            }

            if(x1 < Rows -1  && TableOfValues[x1+1][y1] == TableOfValues[x1][y1] ){
                return false ;
            }
            if (x1 > 0 && TableOfValues[x1 -1][y1] == TableOfValues[x1][y1]) {
                return false;
            }
            if(y1 < Columns -1  && TableOfValues[x1][y1+1] == TableOfValues[x1][y1] ){
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
            if(y2 < Columns -1  && TableOfValues[x2][y2+1] == TableOfValues[x2][y2] ){
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

        int var = Select_Unsigned_Variable(Columns * Rows / 2) ;
        // System.out.println(var) ;
        for (int i = 0 ; i <3 ; i++){
            if (variables[var].Domain[i] != 0 ){

                int domainVar = variables[var].value ;
                add(i   , var , TableOfValues);
                //        System.out.println(variables[var].value);
                boolean iop = isSati(var) ;

                if( iop){
                    // write infrenses  AC_3 Forward

                    boolean result = Backtracking() ;

                    if(result == true){
                        return true ;
                    }
                    variables[var].value = -1 ;
                    TableOfValues[variables[var].piece1X][variables[var].piece1Y].value  = "0" ;
                    TableOfValues[variables[var].piece2X][variables[var].piece2Y].value  = "0" ;
                } else{
                    variables[var].value = -1 ;
                    TableOfValues[variables[var].piece1X][variables[var].piece1Y].value  = "0" ;
                    TableOfValues[variables[var].piece2X][variables[var].piece2Y].value  = "0" ;
                }
            }
        }
        return false ;
    }
    public static boolean AC_3 (piece[][] tableOfValues){
        // add all (vi , vj) pairs
        ArrayList<Pair> queue = new ArrayList<Pair>();
        for (Variable var : variables) {
            for (int neighbor : var.neighbors) {
                queue.add(new Pair(var, variables[neighbor]));
            }
        }

        while (!queue.isEmpty()) {
            Pair pair = queue.get(0);
            queue.remove(0);

            if (Revise(pair.var1 , pair.var2, tableOfValues)){
                if( (pair.var1.Domain[0] == 0) && (pair.var1.Domain[1] == 0) && (pair.var1.Domain[2] == 0)){
                    return false;
                }
                for (int neighbor : pair.var1.neighbors) {
                    if (neighbor != pair.var1.whichVarInArray){
                        queue.add(new Pair(variables[neighbor], pair.var1));
                    }
                }
            }
        }
        return true;
    }

    public static boolean Revise(Variable var1, Variable var2, piece[][] tableOfValues){
        boolean revised = false;
        for (int i =0 ; i < 3 ; i++) {

            // TODO use value
            if (checkDomain(i,var1.whichVarInArray)){
                add(i,var1.whichVarInArray,tableOfValues);
            }
            else {
                continue;
            }

            for (int j = 0; j < 3; j++) {
                if (checkDomain(j,var2.whichVarInArray)){
                    add(j,var2.whichVarInArray,tableOfValues);
                    if (!isSati(var2.whichVarInArray)){
                        revised = true;
                    }else {
                        revised = false;
                    }
                }
                else {
                    continue;
                }
            }

            if (revised){
                updateDomain(i,var1.whichVarInArray);
            }
        }

        return revised;
    }
    public static void  Forward(){

    }

    // TODO check Domain
    public static boolean checkDomain(int domain , int var){
        Variable v = variables[var];
        if (v.Domain[domain] == 0){
            return false;
        }
        return true;
    }

    // in this function we give a variable and one of its domains and the function
    // will change the variable
    public static void   add (int Domain , int var, piece[][] tableOfValues){

        if(Domain == 0 ) {
            variables[var].value = 0  ;
            variables[var].piece1 = "0"  ;
            variables[var].piece2 = "0"  ;
            tableOfValues[variables[var].piece1X][variables[var].piece1Y].value = "0" ;
            tableOfValues[variables[var].piece2X][variables[var].piece2Y].value = "0" ;

        }else{
            if(Domain == 1) {
                variables[var].value = 1 ;
                variables[var].piece1 = "+"  ;
                variables[var].piece2 = "-"  ;
                tableOfValues[variables[var].piece1X][variables[var].piece1Y].value = "+" ;
                tableOfValues[variables[var].piece2X][variables[var].piece2Y].value = "-" ;
            }else{
                if(Domain == 2 ){
                    variables[var].value = 2 ;
                    variables[var].piece1 = "-"  ;
                    variables[var].piece2 = "+"  ;
                    tableOfValues[variables[var].piece1X][variables[var].piece1Y].value = "-" ;
                    tableOfValues[variables[var].piece2X][variables[var].piece2Y].value = "+" ;
                }
            }
        }
    }

    // TODO update Domain
    public static void updateDomain(int domain, int variable){
        Variable v = variables[variable];
        v.Domain[domain] = 0;
    }

    public static  int Select_Unsigned_Variable(int sizeOfVariables){
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
                //  System.out.println(TableOfValues[j][i].value) ;
                if (TableOfValues[j][i].value.equals("+")){

                    positive+=1 ;

                }else{
                    if(TableOfValues[j][i].value.equals("-")){
                        negative +=1 ;
                    }
                }
            }
            if(PositiveInColumns[i] != positive || NegativeInColumns[i] != negative){

                return false ;
            }
        }

        for(int i = 0  ; i  < Rows ; i ++ ) {
            int positive = 0  ;
            int negetive = 0 ;
            for(int j = 0; j < Columns; j ++ ) {
                if (TableOfValues[i][j].value.equals("+")){
                    positive++ ;
                }else{
                    if(TableOfValues[i][j].value.equals("-")){
                        negetive ++ ;
                    }
                }
            }
            if(PositivesInRows[i] != positive || NegativeInRows[i] != negetive){
                return false ;
            }
        }

        for(int i = 0 ; i < Rows  ; i ++ ) {
            for(int j = 0; j < Columns; j ++ ) {

                if(TableOfValues[i][j].value.equals("+")){

                    if(i < Rows -1 ){
                        if(TableOfValues[i+1][j].value.equals("+")) {
                            return false ;
                        }
                    }
                    if(j < Columns -1 ){
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
                    if(j < Columns -1 ){
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

    static ArrayList<Integer> getNeighbors(Variable variable){
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (Variable var : variables) {
            if (var.whichVarInArray != variable.whichVarInArray) {
                if(( (variable.piece1X-1 < var.piece1X ) && (variable.piece2X+1 > var.piece1X) ) ||
                        ( (variable.piece1X-1 < var.piece2X ) && (variable.piece2X+1 > var.piece2X) ) ||
                        ( (variable.piece1Y-1 < var.piece1Y) && (variable.piece2Y+1 > var.piece1Y) ) ||
                        ( (variable.piece1Y-1 < var.piece2Y) && (variable.piece2Y+1 > var.piece2Y) ) ){
                    neighbors.add(var.whichVarInArray);
                }
            }
        }
        return neighbors;
    }
}
