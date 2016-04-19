/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridprinter;


/**
 *
 * @author markhancharik
 */
public class Block {
    
    int x;
    int y;
    
     Block parent;
    // int distance;
    
     
     int hValue;  // determined by hueristic, we will be using distance
     int gValue;  // the cost to get to this step from the parent block
     int fValue;  // we get this by adding h + g
    
    public Block(int a, int b){
        
      x=a;
      y=b;
 
    }  // end constructor
    
  
    
        
        // don't need this , using int cost instead
    private void setDistance(){
        
        // distance = (int)(Math.sqrt(( endx-x)*( endx-x)+( endy-y)*( endy-y))*10);
         hValue = (int)(Math.sqrt(( gridprinter.GridPrinter.graph.end.x-x)*( gridprinter.GridPrinter.graph.end.x-x)+(  gridprinter.GridPrinter.graph.end.y-y)*(  gridprinter.GridPrinter.graph.end.y-y))*10);
       
    }
   
     private void setfValue(){
        
         fValue = gValue + hValue;
       
    }
     
     
     
   public void addNeighborsToOpenList(){
       
       int tempCost = 0;
       int costCalcInt = 0;
        //gridprinter.GridPrinter.graph.openBlocks.remove(this);  
       System.out.println("adding [" + this.x + "][" + this.y + "] to the open list");
      
      
       for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                
                Block temp = new Block(x+i,y+j);
                
                if(withinMapBoundaries(x+i,y+j)){
                    
              if(gridprinter.GridPrinter.graph.end.x == x+i && gridprinter.GridPrinter.graph.end.y == y+j){
                 System.out.println("end found");
                 gridprinter.GridPrinter.graph.endFound = true; 
                 gridprinter.GridPrinter.graph.penultimate = this;
                }else{
           
                if(gridprinter.GridPrinter.graph.mapGrid[x+i][y+j]==0){
               if(!gridprinter.GridPrinter.graph.openContainsThisBlock(temp.x , temp.y) &&  !gridprinter.GridPrinter.graph.closedContainsThisBlock(temp.x , temp.y)){
                        temp.setfValue(); 
                   gridprinter.GridPrinter.graph.addToOpenList(temp);
               
                // add a block to the open list
              // gridprinter.GridPrinter.graph.blocks.add(new Block(x+i,y+j));
               
               // record parent
               //gridprinter.GridPrinter.graph.blocks.get( gridprinter.GridPrinter.graph.openListSize()-1).parent = this;
               temp.parent = this;
               
               //calculate hueristic (H)
              // gridprinter.GridPrinter.graph.blocks.get( gridprinter.GridPrinter.graph.openListSize()-1).setDistance();
               temp.setDistance();
               
               
               // calculate the cost to move to this block from parent (G)
               // this is from policyalminac.org/games/aStarTutorial - the discussion of multiplying by ten and casting to int for speed - haven't tested the speed claim yet - Math.sqrt(2) to 14 and ten 
                if((Math.abs(i) + Math.abs(j)) == 2){
                   temp.gValue = 14 + gValue;
                }else {
                   temp.gValue = 10 + gValue;
                }
                temp.setfValue();
                // check of the block is in the open list, or null, or an obstacle
                // add a block to the open list
              //  if(i == 0 && j == 0){
                 //gridprinter.GridPrinter.graph.blocks.get( gridprinter.GridPrinter.graph.openListSize()-1).setfValue();
               // }else{
                     }
                 
                //}
               
                }
                
                }
            } // end if within map boundaries 
       }  // end for j
       }  // end for i
       gridprinter.GridPrinter.graph.removeFromOpenList(this);
        gridprinter.GridPrinter.graph.addToClosedList(this);  
        System.out.println("adding [" + this.x + "][" + this.y + "] to the closed list");
      
   } // end add neighbors to the open list
    
    
  public boolean withinMapBoundaries(int x, int y){
      
      boolean temp = true;
      
      if(x < 0 || x > gridprinter.GridPrinter.graph.size-1){
         temp = false; 
      }
      
       if(y < 0 || y > gridprinter.GridPrinter.graph.size-1){
          temp = false;
      }
      
      return temp;
  }  
    
public String printInfo(){
    
   return "["+this.x+"]["+this.y+"] = "+ fValue;
    
    
}
  
  
}   // end class
