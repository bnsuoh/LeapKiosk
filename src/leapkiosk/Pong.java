/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package leapkiosk;

/**
 *
 * @author bensu
 */
public class Pong {
    public static int screenWidth=1366;
    public static int screenHeight=740;
    public int ballX=screenWidth/2;
    public int ballY=screenHeight/2;
    public boolean inGame=true;
    public int appY_1, appY_2;
    public int score1=0;
    public int score2=0;
    public int hitCount=0;
    public int winner;
    public boolean replay=false;
    public boolean quit=false;
    public boolean chron=false;
    public int Xspeed=5;
    public int Yspeed=5;
    public int countNo=3;
    public int count=1;
    public boolean isPong=true;
    public boolean isMenu=false;
    public void ballMove(){
        if(hitCount!=0&&hitCount%5==0){ //increases ball's speed at every 5 hits given by the players
            Xspeed++;
            Yspeed++;
            hitCount++;
        }
        if(((ballX<=60&&ballX>=55)&&((appY_1<=ballY)&&((appY_1+100)>=ballY)))||
                (((ballX+20>=screenWidth-60)&&(ballX+20<=screenWidth-55))&&(ballY>=appY_2&&ballY<=(appY_2+100)))){
            Xspeed=0-Xspeed;
            hitCount++; //when players hit the ball, changes direction and increases hit count
        }
        else if((ballY<=0||(ballY+20)>=screenHeight)){
            Yspeed=0-Yspeed; //when the ball hits the borders, changes direction
        }
        else if(ballX+20>=screenWidth){ //if the ball goes through the border to the right, respawns the ball and increases p1's score
            ballY=screenHeight/2;
            ballX=screenWidth/2;
            Xspeed=0-Xspeed;
            score1++;
        }
        else if(ballX<=0){ //if the ball goes through the border to the left, respawns the ball and increases p2's score
            ballY=screenHeight/2;
            ballX=screenWidth/2;
            Xspeed=0-Xspeed;
            score2++;
        }
        ballX+=Xspeed; //moves to ball
        ballY+=Yspeed;
    }
    public void pongRun(SampleListener listener){
        quit=false;
        replay=false;
        quit=listener.getQuit();
        if(inGame==true){
            replay=false;
            appY_2=listener.getCoorY(2); //gets Y coordinates of fingers from the leap
            appY_1=listener.getCoorY(1);
            if(count<90){
                chron=true;
                if(count%30==0){
                    countNo--;
                }
                count++;
            }
            else if(count>=90){
                chron=false;
                ballMove(); //moves the ball
            }
            if(score1==3){ //ends game
                winner=1;
                inGame=false;
            }
            else if(score2==3){
                winner=2;
                inGame=false;
            }
        }
        else if (inGame==false){
            countNo=3;
            count=1;
            replay=false;
            replay=listener.getClick();
            //replay=listener.getReplay(); //checks if replay is true from the leap
            if(replay){
                score1=0;
                score2=0;
                inGame=true;
                replay=false;
            }
        }
        if(quit){
            isMenu=true;
            isPong=false;
            inGame=false;
            ballX=screenWidth/2;
            ballY=screenHeight/2;
            score1=0;
            score2=0;
            Xspeed=5;
            Yspeed=5;
            count=1;
            countNo=3;
            hitCount=0;
        }
    }
}
