/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package leapkiosk;
import com.leapmotion.leap.*;

/**
 *
 * @author bensu
 */
public class SampleListener extends Listener{
    public int screenHeight=740;
    public int screenWidth=1366;
    public int appY,appX;
    public int a=2;
    public float vectorX;
    public float vectorY;
    public int appY_2, appY_1;
    public boolean inGame=true;
    public boolean replay=false;
    public boolean click=false;
    public boolean quit=false;
    public boolean flap;
    public boolean isMenu,isPong,isFlappy;
    public void onInit(Controller controller) {
        System.out.println("Initialized");
    }
    public void onConnect(Controller controller) {
        System.out.println("Connected"); //to indicate whether the leap motion connection is successful
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE); //to accept circle gesture
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }
    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }
    public void onExit(Controller controller) {
        System.out.println("Exited");
    }
    public void onFrame(Controller controller) { //to get the most recent frame and information
        Frame frame = controller.frame(); 
        //GestureList gestures = frame.gestures();
        replay=false;
        quit=false;
        click=false;
        flap=false;
        for(Gesture gesture : frame.gestures()){
            switch (gesture.type()) {
                case TYPE_CIRCLE:
                    float seconds = gesture.durationSeconds();
                    if(seconds>=0.5){
                        quit=true; //drawing a circle enables replay
                    }
                /*case TYPE_SWIPE:
                    SwipeGesture swipe = new SwipeGesture(gesture);
                    Vector swipeDirection = swipe.direction();
                    float dirX = swipeDirection.get(0);
                    float dirY = swipeDirection.get(1);
                    float dirZ = swipeDirection.get(2)*10;
                    System.out.println(dirX + " " + dirY + " " + dirZ);
                    if(dirZ<0 && Math.abs(dirZ)>1){
                        click=true;
                    }
                    else if(dirY<0 && Math.abs(dirY)>Math.abs(dirX)){
                        flap=true;
                    }*/
                case TYPE_SCREEN_TAP:
                    ScreenTapGesture screentap = new ScreenTapGesture(gesture);
                    Vector pokeLocation = screentap.position();
                    float dirZ = pokeLocation.get(2);
                    if(Math.abs(dirZ)>30){
                        click=true;
                    }
                case TYPE_KEY_TAP:
                    KeyTapGesture keytap = new KeyTapGesture(gesture);
                    Vector keyLocation = keytap.direction();
                    float dirY = keyLocation.get(1)*10;
                    System.out.println(dirY);
                    if(Math.abs(dirY)>4){
                        flap=true;
                    }
                break;
            }
            System.out.println(flap);
        }   
        InteractionBox iBox = controller.frame().interactionBox();
            Pointable pointL = controller.frame().fingers().leftmost(); //takes the leftmost finger into account
            Pointable pointR = controller.frame().fingers().rightmost(); //takes the rightmost finger into account
            Vector leapPointL = pointL.stabilizedTipPosition(); //gets the position vector of fingers
            Vector leapPointR = pointR.stabilizedTipPosition();
            Vector normalizedPointL = iBox.normalizePoint(leapPointL, true);
            Vector normalizedPointR = iBox.normalizePoint(leapPointR, true);
            appY_2 = (int)((1 - normalizedPointR.getY()) * screenHeight); //gets Y coordinates of fingers changed according to the height of screen
            appY_1 = (int)((1 - normalizedPointL.getY()) * screenHeight);
            appX = (int)((1-normalizedPointL.getX()) * screenWidth);
    }
    public boolean getClick(){
        return click;
    }
    public boolean getFlap(){
        return flap;
    }
    public int getCoorY(int a){
        if (a==1){
            return appY_1; //returns left finger's Y coordinate
        }
        else{
            return appY_2; //returns right finger's Y coordinate
        }
    }
    public boolean getQuit(){
        return quit;
    }
    public int checkMenuY(){
        return appY_1;
    }
    public int checkMenuX(){
        return appX;
    }
}
