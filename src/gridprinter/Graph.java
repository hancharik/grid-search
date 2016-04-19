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
public class Graph {

    static ArrayList<Block> blocks;// = new ArrayList();
    static ArrayList<Block> path;// = new ArrayList();
    static private ArrayList<Block> openBlocks = new ArrayList();
    static private ArrayList<Block> closedBlocks = new ArrayList();
    static int[][] mapGrid;// = new ArrayList();
    static Block start;
    static Block end;
    static boolean endFound = false;
    static int size = 40;
    static int counter = 0;
    static Block penultimate;
    static double startTime;
    static double endTime;
    
    public Graph() {
      
        startTime = System.currentTimeMillis();
       blocks = new ArrayList();
       createGrid();
        start = new Block(0,2);
        openBlocks.add(start);
        end = new Block(size - 4, size - 3);
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
         endTime = System.currentTimeMillis() - startTime;
         System.out.println("time in miliseconds to complete = " + endTime );
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
        
        mapGrid[3][2] = 1;
        
        
         mapGrid[0][3] = 1;
         mapGrid[1][3] = 1;
          mapGrid[2][3] = 1;
           mapGrid[3][3] = 1;
           
           
         mapGrid[3][1] = 1;
          mapGrid[4][1] = 1;
           mapGrid[5][1] = 1;
         
         /*
         mapGrid[1][6] = 1;
         mapGrid[2][6] = 1;
         mapGrid[3][6] = 1;
       //  mapGrid[4][6] = 1;

        mapGrid[5][6] = 1;
       
 */
          mapGrid[3][6] = 1;
      mapGrid[4][6] = 1;
       mapGrid[5][6] = 1;
         mapGrid[6][6] = 1;
         mapGrid[7][6] = 1;
         mapGrid[8][6] = 1;
         mapGrid[9][6] = 1;
       
        mapGrid[3][7] = 1;
        mapGrid[3][8] = 1;
       // mapGrid[3][6] = 1;
        
    }
    
    
    
        public static void createGrid(){
             mapGrid = new int[size][size];
           for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
           
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
       
         for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
       
             
               System.out.print(mapGrid[i][j] + "\t");
                
                
            }
            System.out.print("\n");
         }
       
   } // end print grid array [][] 
     
     
          public static void printBlockValues() {
        System.out.println("printing "+openBlocks.size()+" open nodes");
        for (int k = 0; k < openBlocks.size(); k++) {

           // System.out.println(openBlocks.get(k).fValue);
            System.out.println(openBlocks.get(k).printInfo());
        }
        System.out.println("\n\nprinting "+closedBlocks.size()+" closed nodes");
        for (int k = 0; k < closedBlocks.size(); k++) {

           // System.out.println(closedBlocks.get(k).fValue);
            System.out.println(closedBlocks.get(k).printInfo());
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
      path.add(end);
      System.out.println("path step [" +end.x + "][" + end.y + "]");//System.out.println("adding end [" +end.x + "][" + end.y + "]");
      path.add(penultimate);
       System.out.println("path step [" +penultimate.x + "][" + penultimate.y + "]");// System.out.println("adding penultimate [" +penultimate.x + "][" + penultimate.y + "]");
      // System.out.println(" penultimate parent =  [" +penultimate.parent.x + "][" + penultimate.parent.y + "]");
      Block temp = penultimate.parent;
      for(int i = 0; i < closedBlocks.size(); i++){
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
   
  
static public void addToOpenList(Block b){
    
    
    openBlocks.add(b);
    
    
}// end add to open list


static public void addToClosedList(Block b){
    
    
   closedBlocks.add(b);
    
    
}// end add to open list
static public void removeFromOpenList(Block b){
    
    
    openBlocks.remove(b);
    
    
}// end add to open list

static public int openListSize(){
    
    
    return openBlocks.size();
    
    
}// end add to open list
     
 static public boolean openContainsThisBlock(int x , int y){
      
      boolean temp = false;
      
      for(int q = 0; q < openBlocks.size(); q++){
          
          if(openBlocks.get(q).x == x && openBlocks.get(q).y == y ){
             temp = true; break; 
          }
          
      }
      
      return temp;
      
      
  }// end open contains this block       
 
  static public boolean closedContainsThisBlock(int x , int y){
      
      boolean temp = false;
      
      for(int q = 0; q < closedBlocks.size(); q++){
          
          if(closedBlocks.get(q).x == x && closedBlocks.get(q).y == y ){
             temp = true; break; 
          }
          
      }
      
      return temp;
      
      
  }// end open contains this block 
  
}// end class
