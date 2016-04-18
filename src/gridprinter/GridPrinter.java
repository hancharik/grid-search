/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridprinter;



import java.util.ArrayList;

/**
 *
 * @author mark
 */
public class GridPrinter {

    static ArrayList<Block> blocks;// = new ArrayList();
    static ArrayList<Block> path;// = new ArrayList();
    static  ArrayList<Block> openBlocks = new ArrayList();
    static ArrayList<Block> closedBlocks = new ArrayList();
    static int[][] mapGrid;// = new ArrayList();
    static Block start;
    static Block end;
    static boolean endFound = false;
    static int size = 10;
    static int counter = 0;
    static Block penultimate;
    
    
    public static void main(String[] args) {
      
       blocks = new ArrayList();
       createGrid();
        start = new Block(1,1);
        openBlocks.add(start);
        end = new Block(8,8);
        start.addNeighborsToOpenList();
       
       
        do {
            sortBlocksByValue().addNeighborsToOpenList();
           // counter++;
        } while (!endFound);
      
        addBlocksToGrid();
        printGrid();
        printBlockValues();
        buildPath();
        showPath();
    }
   
    /*
   public static void printGrid(){
       
         for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
       
                for(int k = 0; k < blocks.size();k++){
                    if(blocks.get(k).x == i && blocks.get(k).y == j){
                    System.out.print(blocks.get(k).fValue);
                } 
                }
               System.out.print("\t");
                
                
            }
            System.out.print("\n");
         }
       
   } // end print grid arraylist
    
    */
    
    
    private static void makeWalls(){
        
       //  mapGrid[3][2] = 1;
         mapGrid[3][3] = 1;
         mapGrid[2][3] = 1;
         mapGrid[0][3] = 1;
         mapGrid[1][3] = 1;
        // mapGrid[3][1] = 1;
         
         /*
         mapGrid[1][6] = 1;
         mapGrid[2][6] = 1;
         mapGrid[3][6] = 1;
       //  mapGrid[4][6] = 1;
        // mapGrid[5][6] = 1;
        // mapGrid[6][6] = 1;
         mapGrid[7][6] = 1;
         mapGrid[8][6] = 1;
         mapGrid[9][6] = 1;
        */
        
        
    }
    
    
    
        public static void createGrid(){
             mapGrid = new int[size][size];
           for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
           
                    mapGrid[i][j] = 0;
                
            }
           }
         makeWalls();
      }
      public static void addBlocksToGrid(){
           for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
            for(int k = 0; k < blocks.size();k++){
                    
                    if(blocks.get(k).x == i && blocks.get(k).y == j){
                    mapGrid[i][j] = blocks.get(k).fValue;
                     } 
                    
                }
            }
           }
          // set end
          mapGrid[end.x][end.y] = 777;
      }
      
      
     public static void printGrid(){
       
         for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
       
             
               System.out.print(mapGrid[i][j] + "\t");
                
                
            }
            System.out.print("\n");
         }
       
   } // end print grid array [][] 
     
     
          public static void printBlockValues() {
        System.out.println("printing open nodes");
        for (int k = 0; k < openBlocks.size(); k++) {

            System.out.println(openBlocks.get(k).fValue);

        }
        System.out.println("\n\nprinting closed nodes");
        for (int k = 0; k < closedBlocks.size(); k++) {

            System.out.println(closedBlocks.get(k).fValue);

        }

    } // end print block value
    
          
   public static Block sortBlocksByValue(){
       
       Block temp = openBlocks.get(0);
       
      for (int k = 0; k < openBlocks.size(); k++) {

            if(openBlocks.get(k).fValue < temp.fValue){
              //  openBlocks.get(0) = openBlocks.get(k); 
               temp = openBlocks.get(k) ; 
               
            }

        }
       return temp;
       
   }   // end sort blocks by value    
          
   
   // later we can swith this from void top arraylist of nodes
   public static void buildPath(){
       
      path = new ArrayList(); 
      path.add(penultimate);
       System.out.println("adding penultimate [" +penultimate.x + "][" + penultimate.y + "]");
      // System.out.println(" penultimate parent =  [" +penultimate.parent.x + "][" + penultimate.parent.y + "]");
      Block temp = penultimate.parent;
      for(int i = 0; i < 50; i++){
           System.out.println("path step [" +temp.x + "][" + temp.y + "]");
        path.add(temp);
        if(temp.parent!=null){
             temp = temp.parent;
        }else{
            System.out.println("path complete."); break;
        }
       
          
       }
       
       
       
       
       
       
   }  // end build path
   
        public static void showPath(){
           
           for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
           
                    mapGrid[i][j] = 0;
                
            }
           }
         makeWalls();
         
         for (int k = 0; k < path.size(); k++) {

           mapGrid[path.get(k).x][path.get(k).y] = 5;

        }
         
         printGrid();
         
      }  // end show path
   
          
}// end class
