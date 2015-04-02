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
public class Flappy {
    public static int screenWidth=1366;
    public static int screenHeight=740;
    public int speed=0;
    public int wallX=screenWidth;
    public int wallX2=screenWidth+screenWidth/2+85;
    public int wallHeight;
    public int wallHeight2;
    public int height;
    public boolean fall=false;
    public int birdY=300;
    public double acceleration=0;
    public int space=250;
    public int fallCount=1;
    public int time=0;
    public boolean flap=false;
    public int score=0;
    public boolean init=true;
    public boolean init2=true;
    public boolean isMenu=false;
    public boolean isFlappy=true;
    public boolean replay=false;
    public boolean inGame=true;
    public void flappyRun(SampleListener listener){
        while (init==true){
            locateWall();
            wallHeight=height;
            locateWall();
            wallHeight2=height;
            init=false;
        }
        if(inGame){
            replay=false;
            if (init2==true){
                checkFlap(listener);
                start();
            }
            else{  
                checkFlap(listener);
                move();
                checkCrash();
                checkWall();
                checkScore();
            }
            if(flap){
                flap();
            }
            if(fall){
                falling();
            }
        }
        else if (inGame=false){
            gameOver();
        }
    }
    public void flap(){
        speed=-20;
        //birdY-=150;
    }
    public void start(){
        speed=0;
        if(flap==true){
            System.out.println("here");
            acceleration=1;
            speed=-10;
            init2=false;
        }
    }
    public void move(){
        speed+=acceleration;
        time++;
        birdY+=speed;
        wallX-=5;
        wallX2-=5;
    }
    public void falling(){
        if(fallCount<10 && fallCount%10!=0){
            speed=0;
            fallCount++;
        }
        else{
            speed+=acceleration;  
        }
        birdY+=speed;
        if(birdY>screenHeight+1000){
            inGame=false;
        }
    }
    public void gameOver(){
        replay=false;
        //replay=listener.getReplay(); //checks if replay is true from the leap
        if(replay==true){
            score=0;
            inGame=true;
            replay=false;
        }
    }
    public void checkCrash(){
        if(((wallX<100&&wallX+170>30) && (birdY<wallHeight || (birdY+70>=wallHeight+space)))||((wallX2<100&&wallX2+170>30)&& (birdY<wallHeight2 || (birdY+70>=wallHeight2+space)))){
            fall=true;
        }
        if(birdY+100>screenHeight || birdY<0){
            fall=true;
        }
    }
    public void checkScore(){
        if(time>153 && time%153==0){
            score++;
        }
    }
    public void checkFlap(SampleListener listener) {
        flap=listener.getFlap();
    }
    public void locateWall() {
        height=(int)(Math.random()*screenHeight/3)+200;
    }
    private void checkWall(){
        if(wallX+170<=0){
            wallX=screenWidth;
            locateWall();
            wallHeight=height;
        }
        if(wallX2+170<=0){
            wallX2=screenWidth;
            locateWall();
            wallHeight2=height;
        }
    }
}
