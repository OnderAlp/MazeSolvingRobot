/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mazesolvingrobot;

import java.awt.event.*; 
import java.util.Arrays;
import javax.swing.*;
import java.util.Random;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

//JButton [][]a;
//        int b = 5;
//        a = new JButton[b][b];
//        JFrame f = new JFrame();

class Blok extends JButton{
    int x,y;
    boolean isWall = false;
    boolean isWisited = false;
    boolean isFinish = false;
    Icon tile = new ImageIcon("D:\\tile.png");
    Icon wall = new ImageIcon("D:\\wall.png");
    Icon wisit = new ImageIcon("D:\\wisited.png");
    Icon finish = new ImageIcon("D:\\finish.png");
    Icon fog = new ImageIcon("D:\\fog.png");
    public Blok(int x, int y) {
        setIcon(tile);
        this.x = x;
        this.y = y;
    }
    
    void setFog(Blok [][]butons){
        setIcon(fog);
    }
    
    void deleteFog(Blok [][]butons){
        if(this.isWall == true){
            setIcon(wall);
        }
        if(this.isFinish == true){
            setIcon(finish);
        }
        if(this.isFinish == false && this.isWall == false && this.isWisited == false){
            setIcon(tile);
        }
    }
    
    public void setWall(Blok [][]butons, int random){
        if(random == 1){
            setIcon(wall);
            this.isWall = true;
        }
    }
    
    public void setWisited(Blok [][]butons){
            setIcon(wisit);
            this.isWisited = true;
        
    }
    
    public void setFinish(Blok [][]butons){
            setIcon(finish);
            this.isFinish = true;
            this.isWall = false;
        
    }
    
    public void setGreen(Blok [][]butons){
            setIcon(finish);
        
    }
    
    public void fonk(Blok [][]butons){
        this.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                
                boolean  a = butons[x][y].getisWall();
                if(a == true){
                    butons[x][y].setIcon(tile);
                    setisWall(false);
                }
                if(a == false){
                    butons[x][y].setIcon(wall);
                    setisWall(true);
                }
            }
        }); 
        
   }
    
   public boolean getisWall(){
       return this.isWall;
   }
   public void setisWall(boolean a){
       if(a){
           this.isWall = true;
           setIcon(wall);
       }
       else{
           this.isWall = false;
           setIcon(tile);
       }
       
   }
    
}

class Start extends JButton{
    public boolean start;
    
    public Start(){
        start = false;
        this.setText("Çalıştır");
    }
    
    public void fonk(){
        this.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                
                start = true;
                System.out.println("Start true--------------------");
            }
        }); 
        
   }
}

class Restart extends JButton{
    public boolean restart;
    public int yeniWidth;
    public int yeniHeight;
    
    public Restart(){
        restart = false;
        this.setText("Yeni Labirent");
    }
    
    public void fonk(JTextField t1, JTextField t2){
        this.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                
                yeniWidth = Integer.parseInt(t1.getText());
                yeniHeight = Integer.parseInt(t2.getText());
                restart = true;
                System.out.println("Restart true------------------------");
            }
        }); 
        
   }
}

class UrlSwap extends JButton{
    public boolean swap;
    
    public UrlSwap(){
        swap = false;
        this.setText("Url Değiştir");
    }
    
    public void fonk(){
        this.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                
                swap  = true;
                
            }
        }); 
        
   }
}


public class Grid {
    
    
    private int width;
    private int height;
    JFrame f = new JFrame();
    JPanel p = new JPanel();
    Blok [][]butons;
    Start start = new Start();
    Restart restart = new Restart();
    UrlSwap swap = new UrlSwap();
    JTextField t1,t2;
    JLabel l1,l2,l3;  
    Random random = new Random();
    
    
    public Grid(int width, int height){
        
        this.width = width;
        this.height = height;
        
        butons = new Blok[width][height];
        
        int x, y;
        for (x = 0; x < width; x++) {
            for (y = 0; y < height; y++) {
                butons[x][y] = new Blok(x,y);//(new ImageIcon("D:\\tile.png"))
                butons[x][y].fonk(butons);
                butons[x][y].setBounds(x * 32, y * 32, 32, 32);
                f.add(butons[x][y]);
                if(y == 0 || y == height-1 || x == 0 || x == width-1){
                    butons[x][y].setWall(butons, 1);
                }
                else{
                    int a = random.nextInt(3); 
                    butons[x][y].setWall(butons, a);
                }
            }
        }
        butons[width-2][height-2].setFinish(butons);
        butons[width-3][height-2].setisWall(false);
        butons[width-2][height-3].setisWall(false);
        butons[1][1].setisWall(false);
        butons[1][2].setisWall(false);
        butons[2][1].setisWall(false);
        butons[1][3].setisWall(false);
        butons[3][1].setisWall(false);
        
        String s = Integer.toString(width);
        t1 = new JTextField(s);  
        t1.setBounds(1300,50, 100,30);
        s = Integer.toString(height);
        t2 = new JTextField(s);
        t2.setBounds(1300,100, 100,30);
        f.add(t1);f.add(t2);
        
        start.setBounds(1300, 200, 150, 100);
        start.fonk();
        f.add(start);
        restart.setBounds(1300, 350, 150, 100);
        restart.fonk(t1, t2);
        f.add(restart);
        
          
        l1 = new JLabel("Toplam Gidilen Kare: ");
        l1.setBounds(1300, 450, 200, 100);
        l2 = new JLabel("Toplam Çözüm Kare: ");
        l2.setBounds(1300, 500, 200, 100);
        l3 = new JLabel("Geçen Süre: ");
        l3.setBounds(1300, 550, 200, 100);
        f.add(l1); f.add(l2); f.add(l3);
        
        f.setSize(1920,1080);
        f.setLayout(null);    
        f.setVisible(true);    
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public Grid(int url){
        
        String[] a = null;
        String[] b = null;
        if(url == 1){
            try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt")).build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			a = response.body().split("\\r?\\n|\\r");
                        b = response.body().split("");

			System.out.println(a[0].length() + "X" + a.length);
                        
                        
                        
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        }
        if(url == 2){
            try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://bilgisayar.kocaeli.edu.tr/prolab2/url2.txt")).build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			a = response.body().split("\\r?\\n|\\r");
                        b = response.body().split("");

			System.out.println(a[0].length() + "X" + a.length);
                        
                        
                        
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        }
        
        this.width = a[0].length();
        this.height = a.length;
        
        butons = new Blok[width][height];
        
        int x, y, sira = 0;
        for (x = 0; x < width; x++) {
            for (y = 0; y < height; y++) {
                butons[x][y] = new Blok(x,y);//(new ImageIcon("D:\\tile.png"))
                butons[x][y].fonk(butons);
                butons[x][y].setBounds(x * 32, y * 32, 32, 32);
                f.add(butons[x][y]);
                if(y == 0 || y == height-1 || x == 0 || x == width-1){
                    butons[x][y].setWall(butons, 1);
                }
                if(!b[sira].contains("0")){
                    int sayi = random.nextInt(2); 
                    butons[x][y].setWall(butons, sayi);
                }
                sira++;
            }
            sira++;
        }
        butons[width-2][height-2].setFinish(butons);
        butons[width-3][height-2].setisWall(false);
        butons[width-2][height-3].setisWall(false);
        butons[1][1].setisWall(false);
        butons[1][2].setisWall(false);
        butons[2][1].setisWall(false);
        butons[1][3].setisWall(false);
        butons[3][1].setisWall(false);
        
        String s = Integer.toString(width);
        t1 = new JTextField(s);  
        t1.setBounds(1300,50, 100,30);
        s = Integer.toString(height);
        t2 = new JTextField(s);
        t2.setBounds(1300,100, 100,30);
        f.add(t1);f.add(t2);
        
        start.setBounds(1300, 200, 150, 100);
        start.fonk();
        f.add(start);
        
        swap.setBounds(1300, 350, 150, 100);
        swap.fonk();
        f.add(swap);
        
        restart.setBounds(1300, 500, 150, 100);
        restart.fonk(t1, t2);
        f.add(restart);
        
        l1 = new JLabel("Toplam Gidilen Kare: ");
        l1.setBounds(1300, 600, 200, 100);
        l2 = new JLabel("Toplam Çözüm Kare: ");
        l2.setBounds(1300, 650, 200, 100);
        l3 = new JLabel("Geçen Süre: ");
        l3.setBounds(1300, 700, 200, 100);
        f.add(l1); f.add(l2); f.add(l3);
        
        f.setSize(1920,1080);
        f.setLayout(null);    
        f.setVisible(true);    
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    void Fog(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                butons[x][y].setFog(butons);
            }
        }
    }
    
    void setToplam(int toplam){
        l1.setText("Toplam Gidilen Kare: " + Integer.toString(toplam));
    }
    
    void setCozum(int cozum){
        l2.setText("Toplam Çözüm Kare: " + Integer.toString(cozum));
    }
    
    void setZaman(long time){
        l3.setText("Geçen Süre: " + Long.toString(time / 1000) + " sn");
    }
    
    int getStart(){
        if(start.start == false){
            return 0;
        }
        else{
            return 1;
        }
    }
    
    int getRestart(){
        if(restart.restart == false){
            return 0;
        }
        else{
            return 1;
        }
    }
    
    boolean getSwap(){
        return swap.swap;
    }
    
    void setSwap(){
        swap.swap  = false;
    }
    
    
    int getNewWidth(){
        return restart.yeniWidth;
    }
    
    int getNewHeight(){
        return restart.yeniHeight;
    }
    
    void setStart(boolean deger){
        start.start = deger;
    }
    
    void setFrame(){
        f.setVisible(false);
    }
    
    int howManyAllowed(int x, int y){
        int allowed = 1;
        if(butons[x-1][y].isWall != true && butons[x-1][y].isWisited != true){
            allowed++;
        }
        if(butons[x][y-1].isWall != true && butons[x][y-1].isWisited != true){
            allowed++;
        }
        if(butons[x][y+1].isWall != true && butons[x][y+1].isWisited != true){
            allowed++;
        }
        if(butons[x+1][y].isWall != true && butons[x+1][y].isWisited != true){
            allowed++;
        }
        
        return allowed;
    }
    
    int[] playerLocation(int x, int y){
        
        butons[x][y].setWisited(butons);
        int allowed = howManyAllowed(x, y);
        int[] nodeList = new int[12];
        Arrays.fill(nodeList, -1);
//        System.out.println(x + " " + y);
        
        butons[x-1][y].deleteFog(butons);
        if(butons[x-1][y].isWall != true && butons[x-1][y].isWisited != true){
            
            if(butons[x-1][y].isFinish == true){
                nodeList[0] = x-1; nodeList[1] = y; 
                nodeList[2] = 11;
                allowed = 10;
            }
            else{
                nodeList[0] = x-1; nodeList[1] = y; 
                allowed--;
                if(allowed == 1){
                    nodeList[2] = allowed;
                }
            }
        }
        
        butons[x][y-1].deleteFog(butons);
        if(butons[x][y-1].isWall != true && butons[x][y-1].isWisited != true){
            
            if(butons[x][y-1].isFinish == true){
                nodeList[3] = x; nodeList[4] = y-1; 
                nodeList[5] = 11;
                allowed = 10;
            }
            else{
                nodeList[3] = x; nodeList[4] = y-1;
                allowed--;
                if(allowed == 1){
                    nodeList[5] = allowed;
                }
            }
        }
        
        butons[x][y+1].deleteFog(butons);
        if(butons[x][y+1].isWall != true && butons[x][y+1].isWisited != true){
            
            if(butons[x][y+1].isFinish == true){
                nodeList[6] = x; nodeList[7] = y+1; 
                nodeList[8] = 11;
                allowed = 10;
            }
            else{
                nodeList[6] = x; nodeList[7] = y+1;
                allowed--;
                if(allowed == 1){
                nodeList[8] = allowed;
                }
            }
            
        }
        
        butons[x+1][y].deleteFog(butons);
        if(butons[x+1][y].isWall != true && butons[x+1][y].isWisited != true){
            
            if(butons[x+1][y].isFinish == true){
                nodeList[9] = x+1; nodeList[10] = y; 
                nodeList[11] = 11;
                allowed = 10;
            }
            else{
                nodeList[9] = x+1; nodeList[10] = y;
                allowed--;
                if(allowed == 1){
                    nodeList[11] = allowed;
                }
            }
            
        }
         return nodeList;
    }
    
    void paintSolution(int x, int y){
        butons[x][y].setGreen(butons);
    }
    
}
