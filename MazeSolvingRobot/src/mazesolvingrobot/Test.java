/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mazesolvingrobot;


/**
 *
 * @author OnderCnC
 */
public class Test {public static void main(String[] args) throws InterruptedException{
     
    Grid grid = new Grid(20,20);
    Graph tree = new Graph(1,1);
    
    int width = 0;
    int height = 0;
    int url = 1;
    
    while (true) {                
        int toplamKare = 0;
        int cozumKare = 0;
        
        if(grid.getStart() == 1){
            long startTime = System.currentTimeMillis();
            
            grid.Fog();
            Thread.sleep(800);
            for (int i = 0; i < 1000; i++) {
                tree.nodeList = grid.playerLocation(tree.lastNode.x,tree.lastNode.y);
                tree.insertFromList(tree.nodeList);
                toplamKare++;
                if(tree.finished == true){
                    break;
                }
                Thread.sleep(50);
            }
            
            for (int i = 0; i < 1000; i++) {
                grid.paintSolution(tree.lastNode.x,tree.lastNode.y);
                System.out.println(tree.lastNode.x + " " + tree.lastNode.y);
                cozumKare++;
                tree.returnRoot();
                if(tree.root.x == tree.lastNode.x && tree.root.y == tree.lastNode.y){
                    grid.setStart(false);
                    break;
                }
            }
            grid.paintSolution(tree.root.x,tree.root.y);
            
            grid.setToplam(toplamKare);
            grid.setCozum(cozumKare);
            grid.setZaman(System.currentTimeMillis() - startTime);
        }
        if(grid.getRestart() == 1){
            width = grid.getNewWidth();
            height = grid.getNewHeight();
            grid.setFrame();
            grid = null;
            tree = null;
            grid = new Grid(width,height);
            tree = new Graph(1,1);
            
        }
        if(grid.getSwap() == true){
            if(url == 1){
                url = 2;
                grid.setSwap();
                grid.setFrame();
                grid = null;
                tree = null;
                grid = new Grid(2);
                tree = new Graph(1,1);
            }
            else if(url == 2){
                url = 1;
                grid.setSwap();
                grid.setFrame();
                grid = null;
                tree = null;
                grid = new Grid(1);
                tree = new Graph(1,1);
            }
        }
        
    }
    
    
}


}
