/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mazesolvingrobot;

class Graph {
 
    /* Class containing left
       and right child of current node
     * and key value*/
    class Node {
        int x, y, bitti;
        Node north, south, east, west, parent;
 
        public Node(int x, int y)
        {
            this.x = x;
            this.y = y;
            this.bitti = 1;
            north = south = east = west = parent = null;
        }
    }
 
    // Root of BST
    Node root;
    Node lastNode = null;
    Node temp = null;
    int[] nodeList = new int[12];
    boolean finished = false;
 
    // Constructor
    Graph() { root = null; }
 
    Graph(int x, int y) { 
        root = new Node(x,y);
        lastNode = root;
    }
 
    void lastNodeUpdate(Node lastNode){
        this.lastNode = lastNode;
    }
    
    void Finished(){
        this.finished = true;
    }
    
    void returnRoot(){
        if(this.lastNode.parent != null){
            this.lastNode = this.lastNode.parent;
        }
    }
    
    
    void returnParent(){
        temp = returnParentRec(lastNode);
    }
    
    Node returnParentRec(Node lastNode){
        lastNode.bitti = 0;
        lastNode = lastNode.parent;
        if(lastNode.north != lastNode.parent && lastNode.north != null && lastNode.north.bitti == 1){
            lastNodeUpdate(lastNode.north);
            return lastNode.north;
        }
        else if(lastNode.east != lastNode.parent && lastNode.east != null && lastNode.east.bitti == 1){
            lastNodeUpdate(lastNode.east);
            return lastNode.east;
        }
        else if(lastNode.west != lastNode.parent && lastNode.west != null && lastNode.west.bitti == 1){
            lastNodeUpdate(lastNode.west);
            return lastNode.west;
        }
        else if(lastNode.south != lastNode.parent && lastNode.south != null && lastNode.south.bitti == 1){
            lastNodeUpdate(lastNode.south);
            return lastNode.south;
        }
        else
            returnParentRec(lastNode);
        
        return lastNode;
        
    }
    
    void insertFromList(int [] nodeList){
        
        boolean deadEnd = true;
        for (int i = 0; i < 12; i++) {
            if(nodeList[i] != -1){
                deadEnd = false;
            }
        }
        if(deadEnd == false){
            if(nodeList[0] != -1){
            insert(nodeList[0], nodeList[1], nodeList[2]);
            }
            if(nodeList[3] != -1){
                insert(nodeList[3], nodeList[4], nodeList[5]);
            }
            if(nodeList[6] != -1){
                insert(nodeList[6], nodeList[7], nodeList[8]);
            }
            if(nodeList[9] != -1){
                insert(nodeList[9], nodeList[10], nodeList[11]);
            }
        }
        else
            returnParent();
        
            
    }
    
    // This method mainly calls insertRec()
    void insert(int x, int y, int son) { 
        temp = insertRec(root, lastNode, x, y, 0, son); 
    }
 
    /* A recursive function to
       insert a new key in BST */
    Node insertRec(Node parent, Node root, int x, int y, int yön, int son)
    {
 
        /* If the tree is empty,
           return a new node */
        if (root == null) {
            
            root = new Node(x, y);
            
            if (yön == 1) {
                root.south = root.parent = parent;
            }
            else if(yön == 2){
                root.north = root.parent = parent;
            }
            else if(yön == 3){
                root.west = root.parent = parent;
            }
            else if(yön == 4){
                root.east = root.parent = parent;
            }
            
            if(son == 1){
                lastNodeUpdate(root);
            }
            if(son == 11){
                lastNodeUpdate(root);
                Finished();
                System.out.println("11 ALINDI ALINDI ALINDI-----------------------");
            }
            
            return root;
        }
        
 
        /* Otherwise, recur down the tree */
        else if (root.x == x && root.y == y+1)
            root.north = insertRec(root, root.north, x, y, 1, son);
        else if (root.x == x && root.y == y-1)
            root.south = insertRec(root, root.south, x, y, 2, son);
        else if(root.x == x-1 && root.y == y)
            root.east = insertRec(root, root.east, x, y, 3, son);
        else if(root.x == x+1 && root.y == y)
            root.west = insertRec(root, root.west, x, y, 4, son);
       
                
        /* return the (unchanged) node pointer */
        return root;
    }
 
    // This method mainly calls InorderRec()
    void inorder() { inorderRec(root); }
 
    // A utility function to
    // do inorder traversal of BST
    void inorderRec(Node root)
    {
        if (root != null) {
            System.out.println(root.x + " " + root.y);
            if(root.north != null && root.north != root.parent){
                inorderRec(root.north);
            }
            if(root.south != null && root.south != root.parent){
                inorderRec(root.south);
            }
            if(root.east != null && root.east != root.parent){
                inorderRec(root.east);
            }
            if(root.west != null && root.west != root.parent){
                inorderRec(root.west);
            }
        }
    }
}
