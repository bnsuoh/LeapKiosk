/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package leapkiosk;
//import com.leapmotion.leap.Controller;
import javax.swing.JFrame;

/**
 *
 * @author cem
 */
public class LeapKiosk{
    public static int screenWidth=1366;
    public static int screenHeight=740;
    public static void main(String avg[]){  
        JFrame frame = new JFrame("Leap Motion Kiosk");
        frame.add(new Draw());
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Controller controller = new Controller(); //initializes leap
        //SampleListener listener=new SampleListener();
        //controller.addListener(listener); //starts leap
    }
}


