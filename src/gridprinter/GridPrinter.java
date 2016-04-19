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

   static Graph graph;// = new Graph();
    
    public static void main(String[] args) {
      
     graph = new Graph();
     
     
     System.out.println("printing out the path....");
     for(int i = 0; i < graph.path.size(); i++){
         System.out.println( graph.path.get(i).printInfo());
     }
    
     
     
    }
   
 
  
}// end class
