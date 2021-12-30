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
    public static Piece[][] TableOfValues;




    public static void main(String[] args) {
        // TODO code application logic here

// Get the information ................................................................
        System.out.println("enter:\n");
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

        TableOfValues  = new Piece[Rows][Columns] ;
        for(int i = 0 ; i < Rows ; i ++ ){
            for(int j = 0; j < Columns; j ++ ){
                TableOfValues[i][j] = new Piece() ;
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

        // TODO get neighbors
        for (Variable var : variables) {
            var.neighbors = getNeighbors(var);
//            for (int neighbor :
//                    var.neighbors) {
//
//                System.out.println("var "+var.whichVarInArray+" has neighbor "+neighbor +"\n");
//            }
        }

//        for (Variable varK : variables) {
//            int f = varK.whichVarInArray+1;
//            System.out.println("var " + f + ", x1: " +varK.piece1X+", y1: "+varK.piece1Y+"\n x2: "+varK.piece2X+", y2: "+varK.piece2Y+"\n");
//        }
        /*
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
         */

//        // test AC3
//        Piece[][] table = new Piece[Rows][Columns] ;
//        for (int i = 0; i < Rows; i++) {
//            for (int j = 0; j < Columns; j++) {
//                table[i][j] = TableOfValues[i][j];
//            }
//        }
//        updateDomain(1,1);
//        updateDomain(2,1);
//        AC_3(table);
//
//        for (Variable var :
//                variables) {
//            System.out.println("\ndomain "+var.whichVarInArray+" is: ");
//            for (int d :
//                    var.Domain) {
//                System.out.println(d+",");
//            }
//        }

        // test forward
//        Forward(variables[0],1);
//        Forward(variables[1],2);
//        Forward(variables[16],1);
//        Forward(variables[6],1);
//        Forward(variables[11],1);
//        for (Variable var : variables) {
//            System.out.println("\ndomain "+var.whichVarInArray+" is: ");
//            for (int d :
//                    var.Domain) {
//                System.out.println(d+",");
//            }
//        }


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
                add(i ,var);
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
    public static boolean AC_3 (Piece[][] tableOfValues){
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

    public static boolean Revise(Variable var1, Variable var2, Piece[][] tableOfValues){
        boolean revised = false;
        System.out.println("revise arc for var "+var1.whichVarInArray+" ,var "+var2.whichVarInArray);
        for (int i =0 ; i < 3 ; i++) {
            // TODO use value
            if (checkDomain(i,var1.whichVarInArray)){
                add_s(i,var1.whichVarInArray,tableOfValues);
                System.out.println("consider domain "+i+" for var "+var1.whichVarInArray);
            }
            else {
                continue;
            }
            int counter = 0;
            int domainCounter = 0;
            for (int j = 0; j < 3; j++) {
                if (checkDomain(j,var2.whichVarInArray)){
                    domainCounter++;
                    add_s(j,var2.whichVarInArray,tableOfValues);
                    System.out.println("try domain "+j+" for var "+var2.whichVarInArray);
                    if (!satisfy_arc(var2.whichVarInArray)){
                        System.out.println("does not satisfy");
                        counter++;
                    }
                }
                else {
                    continue;
                }
            }
            if (counter >= domainCounter){
                revised = true;
            }
            if (revised){
                updateDomain(i,var1.whichVarInArray);
                System.out.println("domain "+i+"  of var "+var1.whichVarInArray+" has updated");
            }
        }
        return revised;
    }
    public static void Forward(Variable currentVar, int domain_value){
        //Variable currentVar = minimum_remaining_value(variables);
        add(domain_value,currentVar.whichVarInArray);
        System.out.println("got var "+currentVar.whichVarInArray+" with domain "+domain_value+"\n");

        int x1 = currentVar.piece1X;
        int x2 = currentVar.piece2X;
        int y1 = currentVar.piece1Y;
        int y2 = currentVar.piece2Y;
        int v1 =0;
        int v2 =0;
        int v3 =0;
        int v4 =0;
        int v5 =0;
        int v6 =0;

        boolean b1 = false,b2= false,b3 = false,b4 =false,b5 = false,b6 =false;
        if (isVertical(currentVar)){
            // y1==y2
            if (x1-1 >= 0){
                v1 = TableOfValues[x1-1][y1].Variable;
                b1 = true;
                System.out.println("vertical b1, var "+v1);
            }
            if (x2+1 < Rows){
                v2 = TableOfValues[x2+1][y1].Variable;
                b2 = true;
                System.out.println("vertical b2, var "+v2);
            }
            if (y1-1 >= 0){
                v3 = TableOfValues[x1][y1-1].Variable;
                b3 = true;
                System.out.println("vertical b3, var "+v3);
            }
            if (y1+1 < Columns){
                v4 = TableOfValues[x1][y1+1].Variable;
                b4 = true;
                System.out.println("vertical b4, var "+v4);
            }
            if (y2-1 >= 0){
                v5 = TableOfValues[x2][y2-1].Variable;
                b5 = true;
                System.out.println("vertical b5, var "+v5);
            }
            if (y2+1 < Columns){
                v6 = TableOfValues[x2][y2+1].Variable;
                b6 = true;
                System.out.println("vertical b6, var "+v6);
            }

            if (domain_value == 1 ){ // (+.-)
                if (variables[v1].piece1X == (x1-1) && b1){
                    if (variables[v1].piece1Y == y1){
                        updateDomain(1,v1);
                        System.out.println("match");
                    }
                    else if (variables[v1].piece2Y == y1){
                        updateDomain(2,v1);
                        System.out.println("not match");
                    }
                    System.out.println("#1 b1 lock type1");
                }else if(variables[v1].piece2X == (x1-1) && b1) {
                    updateDomain(2,v1);
                    System.out.println("#1 b1 lock type2");
                }
                if (variables[v2].piece1X == (x2+1) && b2){
                    if (variables[v2].piece1Y == y2){
                        updateDomain(2,v2);
                        System.out.println("match*");
                    }
                    else if (variables[v2].piece2Y == y2){
                        updateDomain(1,v2);
                        System.out.println("not match*");
                    }
                    System.out.println("#1 b2 lock type1");
                }else if(variables[v2].piece2X == (x2+1) && b2){
                    updateDomain(1,v2);
                    System.out.println("#1 b2 lock type2");
                }
                if (variables[v3].piece1Y == (y1-1) && b3){
                    if (variables[v3].piece1X == x1){
                        updateDomain(1,v3);
                        System.out.println("match");
                    }
                    else if (variables[v3].piece2X == x1){
                        updateDomain(2,v3);
                        System.out.println("not match");
                    }
                    System.out.println("#1 b3 lock type1");
                }else if(variables[v3].piece2Y == (y1-1) && b3){
                    updateDomain(2,v3);
                    System.out.println("#1 b3 lock type2");
                }
                if(variables[v4].piece1Y == (y1+1) && b4){
                    if (variables[v4].piece1X == x1){
                        updateDomain(1,v4);
                        System.out.println("match");
                    }
                    else if (variables[v4].piece2X == x1){
                        updateDomain(2,v4);
                        System.out.println("not match");
                    }
                    System.out.println("#1 b4 lock type1");
                }else if (variables[v4].piece2Y == (y1+1) && b4){
                    updateDomain(2,v4);
                    System.out.println("#1 b4 lock type2");
                }
                if (variables[v5].piece1Y == (y2-1) && b5){
                    if (variables[v5].piece1X == x2){
                        updateDomain(2,v5);
                        System.out.println("match");
                    }
                    else if (variables[v5].piece2X == x2){
                        updateDomain(1,v5);
                        System.out.println("not match");
                    }
                    System.out.println("#1 b5 lock type1");
                }else if(variables[v5].piece2Y == (y2-1) && b5){
                    updateDomain(1,v5);
                    System.out.println("#1 b5 lock type2");
                }
                if(variables[v6].piece1Y == (y2+1) && b6){
                    if (variables[v6].piece1X == x2){
                        updateDomain(2,v6);
                        System.out.println("match");
                    }
                    else if (variables[v6].piece2X == x2){
                        updateDomain(1,v6);
                        System.out.println("not match");
                    }
                    System.out.println("#1 b6 lock type1");
                }else if(variables[v6].piece2Y == (y2+1) &&b6){
                    updateDomain(2,v6);
                    System.out.println("#1 b6 lock type2");
                }
            }else if (domain_value == 2){ // (+,-)
                if (variables[v1].piece1X == (x1-1) && b1){
                    if (variables[v1].piece1Y == y1){
                        updateDomain(2,v1);
                        System.out.println("match");
                    }
                    else if (variables[v1].piece2Y == y1){
                        updateDomain(1,v1);
                        System.out.println("not match");
                    }
                    System.out.println("#2 b1 lock type1");
                }else if(variables[v1].piece2X == (x1-1) && b1) {
                    updateDomain(1,v1);
                    System.out.println("#2 b1 lock type2");
                }
                if (variables[v2].piece1X == (x2+1) && b2){
                    if (variables[v2].piece1Y == y2){
                        updateDomain(1,v2);
                        System.out.println("match");
                    }
                    else if (variables[v2].piece2Y == y2){
                        updateDomain(2,v2);
                        System.out.println("not match");
                    }
                    System.out.println("#2 b2 lock type1");
                }else if(variables[v2].piece2X == (x2+1) && b2){
                    updateDomain(2,v2);
                    System.out.println("#2 b2 lock type2");
                }
                if (variables[v3].piece1Y == (y1-1) && b3){
                    if (variables[v3].piece1X == x1){
                        updateDomain(2,v3);
                        System.out.println("match");
                    }
                    else if (variables[v3].piece2X == x1){
                        updateDomain(1,v3);
                        System.out.println("not match");
                    }
                    System.out.println("#2 b1 lock type1");
                }else if(variables[v3].piece2Y == (y1-1) && b3){
                    updateDomain(1,v3);
                    System.out.println("#2 b3 lock type2");
                }
                if(variables[v4].piece1Y == (y1+1) && b4){
                    if (variables[v4].piece1X == x1){
                        updateDomain(2,v4);
                        System.out.println("match");
                    }
                    else if (variables[v4].piece2X == x1){
                        updateDomain(1,v4);
                        System.out.println("not match");
                    }
                    System.out.println("#2 b4 lock type1");
                }else if (variables[v4].piece2Y == (y1+1) && b4){
                    updateDomain(1,v4);
                    System.out.println("#2 b4 lock type2");
                }
                if (variables[v5].piece1Y == (y2-1) && b5){
                    if (variables[v5].piece1X == x2){
                        updateDomain(1,v5);
                        System.out.println("match");
                    }
                    else if (variables[v5].piece2X == x2){
                        updateDomain(2,v5);
                        System.out.println("not match");
                    }
                    System.out.println("#2 b5 lock type1");
                }else if(variables[v5].piece2Y == (y2-1) && b5){
                    updateDomain(2,v5);
                    System.out.println("#2 b5 lock type2");
                }
                if(variables[v6].piece1Y == (y2+1) && b6){
                    if (variables[v6].piece1X == x2){
                        updateDomain(1,v6);
                        System.out.println("match");
                    }
                    else if (variables[v6].piece2X == x2){
                        updateDomain(2,v6);
                        System.out.println("not match");
                    }
                    System.out.println("#2 b6 lock type1");
                }else if(variables[v6].piece2Y == (y2+1) && b6){
                    updateDomain(1,v6);
                    System.out.println("#2 b6 lock type2");
                }
            }
        }else {
            // x1==x2
            if (y1-1 >= 0){
                v1 = TableOfValues[x1][y1-1].Variable;
                b1 = true;
                System.out.println("horizontal b1, var "+v1);
            }
            if (y2+1 < Columns){
                v2 = TableOfValues[x2][y2+1].Variable;
                b2 = true;
                System.out.println("horizontal b2, var "+v2);
            }
            if (x1-1 >= 0){
                v3 = TableOfValues[x1-1][y1].Variable;
                b3 = true;
                System.out.println("horizontal b3, var "+v3);
            }
            if (x1+1 < Rows){
                v4 = TableOfValues[x1+1][y1].Variable;
                b4 = true;
                System.out.println("horizontal b4, var "+v4);
            }
            if (x2-1 >= 0){
                v5 = TableOfValues[x2-1][y2].Variable;
                b5 = true;
                System.out.println("horizontal b5, var "+v5);
            }
            if (x2+1 < Rows){
                v6 = TableOfValues[x2+1][y2].Variable;
                b6 = true;
                System.out.println("horizontal b6, var "+v6);
            }
            if (domain_value == 1){ // (+.-)

                if (variables[v1].piece1Y == (y1-1) && b1){
                    if (variables[v1].piece1X == x1){
                        updateDomain(1,v1);
                        System.out.println("match");
                    }
                    else if (variables[v1].piece2X == x1){
                        updateDomain(2,v1);
                        System.out.println("not match");
                    }
                    System.out.println("#1 b1 lock type1");
                }else if(variables[v1].piece2Y == (y1-1) && b1) {
                    updateDomain(2,v1);
                    System.out.println("#1 b1 lock type2");
                }
                if (variables[v2].piece1Y == (y2+1) && b2){
                    if (variables[v2].piece1X == x2){
                        updateDomain(2,v2);
                        System.out.println("match");
                    }
                    else if (variables[v2].piece2X == x2){
                        updateDomain(1,v2);
                        System.out.println("not match");
                    }
                    System.out.println("#1 b2 lock type1");
                }else if(variables[v2].piece2Y == (y2+1) && b2){
                    updateDomain(1,v2);
                    System.out.println("#1 b2 lock type2");
                }
                if (variables[v3].piece1X == (x1-1) && b3){
                    if (variables[v3].piece1Y == y1){
                        updateDomain(1,v3);
                        System.out.println("match");
                    }
                    else if (variables[v3].piece2Y == y1){
                        updateDomain(2,v3);
                        System.out.println("not match");
                    }
                    System.out.println("#1 b3 lock type1");
                }else if(variables[v3].piece2X == (x1-1) && b3){
                    updateDomain(2,v3);
                    System.out.println("#1 b3 lock type2");
                }
                if(variables[v4].piece1X == (x1+1) && b4){
                    if (variables[v4].piece1Y == y1){
                        updateDomain(1,v4);
                        System.out.println("match");
                    }
                    else if (variables[v4].piece2Y == y1){
                        updateDomain(2,v4);
                        System.out.println("not match");
                    }
                    System.out.println("#1 b4 lock type1");
                }else if (variables[v4].piece2X == (x1+1) && b4){
                    updateDomain(2,v4);
                    System.out.println("#1 b4 lock type2");
                }
                if (variables[v5].piece1X == (x2-1) && b5){
                    if (variables[v5].piece1Y == y2){
                        updateDomain(2,v5);
                        System.out.println("match");
                    }
                    else if (variables[v5].piece2Y == y2){
                        updateDomain(1,v5);
                        System.out.println("not match");
                    }
                    System.out.println("#1 b5 lock type1");
                }else if(variables[v5].piece2X == (x2-1) && b5){
                    updateDomain(1,v5);
                    System.out.println("#1 b5 lock type2");
                }
                if(variables[v6].piece1X == (x2+1) && b6){
                    if (variables[v6].piece1Y == y2){
                        updateDomain(2,v6);
                        System.out.println("match");
                    }
                    else if (variables[v6].piece2Y == y2){
                        updateDomain(1,v6);
                        System.out.println("not match");
                    }
                    System.out.println("#1 b6 lock type1");
                }else if(variables[v6].piece2X == (x2+1) && b6){
                    updateDomain(2,v6);
                    System.out.println("#1 b6 lock type2");
                }
            }else if (domain_value == 2){ // (-,+)
                if (variables[v1].piece1Y == (y1-1) && b1){
                    if (variables[v1].piece1X == x1){
                        updateDomain(2,v1);
                        System.out.println("match");
                    }
                    else if (variables[v1].piece2X == x1){
                        updateDomain(1,v1);
                        System.out.println("not match");
                    }
                    System.out.println("#2 b1 lock type1");
                }else if(variables[v1].piece2Y == (y1-1) && b1) {
                    updateDomain(1,v1);
                    System.out.println("#2 b1 lock type2");
                }
                if (variables[v2].piece1Y == (y2+1) && b2){
                    if (variables[v2].piece1X == x2){
                        updateDomain(1,v2);
                        System.out.println("match");
                    }
                    else if (variables[v2].piece2X == x2){
                        updateDomain(2,v2);
                        System.out.println("not match");
                    }
                    System.out.println("#2 b2 lock type1");
                }else if(variables[v2].piece2Y == (y2+1) && b2){
                    updateDomain(2,v2);
                    System.out.println("#2 b2 lock type2");
                }
                if (variables[v3].piece1X == (x1-1) && b3){
                    if (variables[v3].piece1Y == y1){
                        updateDomain(2,v3);
                        System.out.println("match");
                    }
                    else if (variables[v3].piece2Y == y1){
                        updateDomain(1,v3);
                        System.out.println("not match");
                    }
                    System.out.println("#2 b3 lock type1");
                }else if(variables[v3].piece2X == (x1-1) && b3){
                    updateDomain(1,v3);
                    System.out.println("#2 b3 lock type2");
                }
                if(variables[v4].piece1X == (x1+1) && b4){
                    if (variables[v4].piece1Y == y1){
                        updateDomain(2,v4);
                        System.out.println("match");
                    }
                    else if (variables[v4].piece2Y == y1){
                        updateDomain(1,v4);
                        System.out.println("not match");
                    }
                    System.out.println("#2 b4 lock type1");
                }else if (variables[v4].piece2X == (x1+1) && b4){
                    updateDomain(1,v4);
                    System.out.println("#2 b4 lock type2");
                }
                if (variables[v5].piece1X == (x2-1) && b5){
                    if (variables[v5].piece1Y == y2){
                        updateDomain(1,v5);
                        System.out.println("match");
                    }
                    else if (variables[v5].piece2Y == y2){
                        updateDomain(2,v5);
                        System.out.println("not match");
                    }
                    System.out.println("#2 b5 lock type1");
                }else if(variables[v5].piece2X == (x2-1) && b5){
                    updateDomain(2,v5);
                    System.out.println("#2 b5 lock type2");
                }
                if(variables[v6].piece1X == (x2+1) && b6){
                    if (variables[v6].piece1Y == y2){
                        updateDomain(1,v6);
                        System.out.println("match");
                    }
                    else if (variables[v6].piece2Y == y2){
                        updateDomain(2,v6);
                        System.out.println("not match");
                    }
                    System.out.println("#2 b6 lock type1");
                }else if(variables[v6].piece2X == (x2+1) && b6){
                    updateDomain(1,v6);
                    System.out.println("#2 b6 lock type2");
                }
            }

        }

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
    public static void   add (int Domain , int var){

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

    public static void   add_s (int Domain , int var, Piece[][] tableOfValues){

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
        if (v.Domain[domain] == 0){
            System.out.println("domain "+domain+" of var "+variable+" is already 0");
            return;
        }
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
                if( ( ((variable.piece1X-1 <= var.piece1X ) && (variable.piece2X+1 >= var.piece1X) ) ||
                        ( (variable.piece1X-1 <= var.piece2X ) && (variable.piece2X+1 >= var.piece2X) )) &&
                        ( ( (variable.piece1Y-1 <= var.piece1Y) && (variable.piece2Y+1 >= var.piece1Y) ) ||
                        ( (variable.piece1Y-1 <= var.piece2Y) && (variable.piece2Y+1 >= var.piece2Y) ) ) ){

                    if (  ( ((variable.piece1X <= var.piece1X ) && (variable.piece2X >= var.piece1X) ) ||
                            ( (variable.piece1X <= var.piece2X ) && (variable.piece2X >= var.piece2X) ))  ){
                        neighbors.add(var.whichVarInArray);
                    }else if ( ( ( (variable.piece1Y <= var.piece1Y) && (variable.piece2Y >= var.piece1Y) ) ||
                            ( (variable.piece1Y <= var.piece2Y) && (variable.piece2Y >= var.piece2Y) ) ) ){
                        neighbors.add(var.whichVarInArray);
                    }
                }
            }
        }
        return neighbors;
    }

    public static boolean satisfy_arc(int var) {

        if (variables[var].value == 1 || variables[var].value == 2) {
            System.out.println("satisfy_arc");
            int x1, x2, y1, y2;
            x1 = variables[var].piece1X;
            y1 = variables[var].piece1Y;
            x2 = variables[var].piece2X;
            y2 = variables[var].piece2Y;

            if (x1 < Rows - 1 && TableOfValues[x1 + 1][y1] == TableOfValues[x1][y1]) {
                return false;
            }
            if (x1 > 0 && TableOfValues[x1 - 1][y1] == TableOfValues[x1][y1]) {
                return false;
            }
            if (y1 < Columns - 1 && TableOfValues[x1][y1 + 1] == TableOfValues[x1][y1]) {
                return false;
            }
            if (y1 > 0 && TableOfValues[x1][y1 - 1] == TableOfValues[x1][y1]) {
                return false;
            }
            if (x2 < Rows - 1 && TableOfValues[x2 + 1][y2] == TableOfValues[x2][y2]) {
                return false;
            }
            if (x2 > 0 && TableOfValues[x2 - 1][y2] == TableOfValues[x2][y2]) {
                return false;
            }
            if (y2 < Columns - 1 && TableOfValues[x2][y2 + 1] == TableOfValues[x2][y2]) {
                return false;
            }
            if (y2 > 0 && TableOfValues[x2][y2 - 1] == TableOfValues[x2][y2]) {
                return false;
            }
        }
        return true;
    }

    public static Variable minimum_remaining_value(Variable[] variables){
        Variable mostConstrained = variables[0];
        int s = 3;
        for (int i = 0 ; i < variables.length; i++){
            int counter = 0;
            for (int j = 0; j < 3; j++) {
                if (variables[i].Domain[j] == 1){
                    counter++;
                }
            }
            if ((counter < s) && (counter != 0)){
                mostConstrained = variables[i];
                s = counter;
            }
        }

        return mostConstrained;
    }

    public static boolean isVertical(Variable variable){
        if( variable.piece1X == variable.piece2X){
            return false;
        }
        return true;
    }
}
