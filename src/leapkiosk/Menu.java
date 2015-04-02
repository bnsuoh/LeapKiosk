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
public class Menu {
    public static int screenWidth=1366;
    public static int screenHeight=740;
    public int angle=45;
    public int menuX,menuY;
    public boolean click;
    public boolean isPong, isMenu, isFlappy;
    public void menuRun(SampleListener listener){
        menuX=listener.checkMenuX();
        menuY=listener.checkMenuY();
        click=listener.getClick();
        isPong=false;
        isMenu=true;
        isFlappy=false;
        if(menuY<screenHeight/2 && menuX<screenWidth/2+100 && menuX>screenWidth/2-100){
            angle=45;
        }
        else if(menuY>screenHeight/2 && menuX<screenWidth/2+100 && menuX>screenWidth/2-100){
            angle=225;
        }
        else if(menuX>screenWidth/2 && menuY<screenHeight/2+200 && menuY>screenHeight/2-200){
            angle=135;
        }
        else if(menuX<screenWidth/2 && menuY<screenHeight/2+200 && menuY>screenHeight/2-200){
            angle=315;
        }
        if(click){
            if(angle==45){
                isMenu=false;
                isPong=true;
                click=false;
            }
            else if(angle==225){
                isMenu=false;
                isFlappy=true;
                click=false;
                //score1=0;
                //replay=false;
                //inGame=true;
                //locateWall();
                //wallHeight=height;
                //locateWall();
                //wallHeight2=height;
            }
        }
    }
}
