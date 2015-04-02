/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package leapkiosk;

import com.leapmotion.leap.Controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.TimerTask;
import javax.swing.JPanel;

public class Draw extends JPanel{
    Controller controller = new Controller(); //initializes leap
    SampleListener listener = new SampleListener();
    Flappy flappy=new Flappy();
    Pong pong=new Pong();
    Menu menu=new Menu();
    public int screenWidth=1366;
    public int screenHeight=740;
    public int radius=300;
    public int appY_1,appY_2;
    public boolean isPong=false;
    public boolean isMenu=false;
    public boolean isFlappy=true;
    java.util.Timer timer = new java.util.Timer();
    public Draw(){
        setBackground(Color.BLACK);
        controller.addListener(listener);
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                repaint();
            }
        }, 30, 30);
    }
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Font nameCopy = new Font("Helvetica", Font.BOLD,20);
        g.setColor(Color.gray);
        g.setFont(nameCopy);
        g.drawString("Bensu Sicim", screenWidth-150, screenHeight-50);
        if(isPong==true){
            drawPong(g2d);
            pong.pongRun(listener);
            isPong=pong.isPong;
            isMenu=pong.isMenu;
        }
        else if (isFlappy==true){
            drawFlappy(g2d);
            flappy.flappyRun(listener);
            isMenu=flappy.isMenu;
            isFlappy=flappy.isFlappy;
        }
        else if (isMenu==true){
            drawMenu(g2d);
            menu.menuRun(listener);
            isPong=menu.isPong;
            isMenu=menu.isMenu;
            isFlappy=menu.isFlappy;
        }
    }
    public void drawMenu(Graphics g){
        Font directions = new Font("Helvetica", Font.BOLD,25);
        Font information = new Font("Helvetica", Font.BOLD,20);
        Font letter = new Font("Helvetica", Font.BOLD,100);
        Font name = new Font("Helvetica", Font.BOLD,50);
        int angle=menu.angle;
        g.setColor(Color.gray);
        g.fillOval(screenWidth/2-radius,screenHeight/2-radius,radius*2,radius*2);
        g.setColor(Color.red);
        g.fillArc(screenWidth/2-radius-30,screenHeight/2-radius-30,radius*2+60,radius*2+60,angle,90);
        g.setColor(Color.black);
        g.fillOval(screenWidth/2-radius/2,screenHeight/2-radius/2,radius,radius);
        g.setFont(letter);
        g.setColor(Color.white);
        g.drawString("P", screenWidth/2-30, screenHeight/2-180);
        g.drawString("S", screenWidth/2+180, screenHeight/2+20);
        g.drawString("F", screenWidth/2-30, screenHeight/2+250);
        g.drawString("M", screenWidth/2-250, screenHeight/2+20);
        g.setFont(directions);
        g.drawString("Move your finger in circular motion to browse", 40, 50);
        g.drawString("Make a clicking gesture to select", 40, 80);         
        if(angle==45){
            g.setFont(name);
            g.drawString("Pong", screenWidth/2-65, screenHeight/2+15);
            g.setFont(information);
            g.drawString("Two Player", screenWidth/2-60, screenHeight/2+60);
        }
        else if(angle==135){
            g.setFont(name);
            g.drawString("Mole Mash", screenWidth/2-130, screenHeight/2+15);
            g.setFont(information);
            g.drawString("Not Available", screenWidth/2-70, screenHeight/2+60);
            }
        else if(angle==225){
            g.setFont(name);
            g.drawString("Flappy Bird", screenWidth/2-138, screenHeight/2+15);
            g.setFont(information);
            g.drawString("Not Available", screenWidth/2-70, screenHeight/2+60);
        }
        else if(angle==315){
            g.setFont(name);
            g.drawString("Snake", screenWidth/2-80, screenHeight/2+15);
            g.setFont(information);
            g.drawString("Not Available", screenWidth/2-70, screenHeight/2+60);
        }
    }
    public void drawPong(Graphics g){
        boolean chron=pong.chron;
        int countNo=pong.countNo;
        int score1=pong.score1;
        int score2=pong.score2;
        int ballX=pong.ballX;
        int ballY=pong.ballY;
        int winner=pong.winner;
        int appY_2=pong.appY_2;
        int appY_1=pong.appY_1;
        boolean inGame=pong.inGame;
        boolean quit=listener.getQuit();
        if(quit==true){
            isPong=false;
            isMenu=true;
        }
        Font bigNumbers = new Font("Helvetica", Font.BOLD,400);
        Font directions = new Font("Helvetica", Font.BOLD,25);
        Font gOver = new Font("Helvetica", Font.BOLD,100);
        Font gOverLabel = new Font("Helvetica", Font.BOLD,50);
        if(inGame==true){ //paints game objects when game isn't over
            g.setColor(Color.white);
            for (int i=0;i<=10;i++){
                g.fillRect(screenWidth/2, i*((screenHeight)/10), 20, 40); 
            }
            if(chron){
               g.setColor(Color.red);
               g.setFont(bigNumbers);
               g.drawString(Integer.toString(countNo), 600,500);
            }
            else if(!chron){
                g.setColor(Color.gray);
                g.setFont(bigNumbers);
                g.drawString(Integer.toString(score1), (screenWidth/4-100), screenHeight/2+100);
                g.drawString(Integer.toString(score2), screenWidth-(screenWidth/4)-100, screenHeight/2+100);
                g.setColor(Color.red);
                g.fillOval(ballX,ballY,20,20);
            }
            g.setFont(directions);
            g.setColor(Color.white);
            g.fillRect(40, appY_1, 20, 100);
            g.fillRect(screenWidth-60, appY_2, 20, 100);
            g.setColor(Color.gray);
            g.drawString("Draw little circles to exit", 550, screenHeight-80);
            g.drawString("Your palms should face the ground. Up and down to move", 350, screenHeight-50); 
            Toolkit.getDefaultToolkit().sync();
        }
        else if(inGame==false){ //game over page
            g.setColor(Color.white);
            g.setFont(gOver);
            g.drawString("Game Over", 400, screenHeight/2-100);
            g.setFont(gOverLabel);
            g.drawString("Player "+winner+" won!", 500, screenHeight/2);
            g.setFont(directions);
            g.drawString("Draw little circles with your finger to exit", 435, (screenHeight-50));
            g.drawString("Make a clicking gesture to replay", 470, (screenHeight-80));
        }
    }
    public void drawSnake(){
        
    }
    public void drawFlappy(Graphics g){
        Font scoreFont = new Font("Helvetica", Font.BOLD,120);
        Font gOver = new Font("Helvetica", Font.BOLD,100);
        Font gOverLabel = new Font("Helvetica", Font.BOLD,50);
        boolean inGame=flappy.inGame;
        int birdY=flappy.birdY;
        int wallX=flappy.wallX;
        int wallX2=flappy.wallX2;
        int space=flappy.space;
        int score=flappy.score;
        int wallHeight=flappy.wallHeight;
        int wallHeight2=flappy.wallHeight2;
        if(inGame==true){ //paints game objects when game isn't over
            //g.drawRect(corner,2*corner,((screenWidth-140)/sqSize)*20,((screenHeight-70)/sqSize)*10);
            g.setColor(Color.red);
            g.fillOval(30, birdY,70,70);
            g.setColor(Color.white);
            g.fillRect(wallX, 0, 170, wallHeight);
            g.fillRect(wallX, wallHeight+space, 170, screenHeight-wallHeight-space);
            g.fillRect(wallX2, 0, 170, wallHeight2);
            g.fillRect(wallX2, wallHeight2+space, 170, screenHeight-wallHeight2-space);
            g.setColor(Color.gray);
            g.setFont(scoreFont);
            g.drawString(Integer.toString(score), screenWidth-180, 120);
            Toolkit.getDefaultToolkit().sync();
        }
        else if(inGame==false){ //game over page
            g.setColor(Color.white);
            g.setFont(gOver);
            g.drawString("Game Over", 400, screenHeight/2-100);
            g.setFont(gOverLabel);
            g.drawString("Score: "+ score, 500, screenHeight/2);
            g.drawString("Draw little circles with your finger to exit", 435, (screenHeight-50));
            g.drawString("Make a clicking gesture to replay", 470, (screenHeight-80));
        }
    }
}

