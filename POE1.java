
package poe1;

import java.util.Scanner;


public class POE1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           
       Login Login1 = new Login();
       Login1.mainData(); //Handles registration and login
       
        // After successful login
        Message.Part2();   // Opens message sending menu
    }
}
//ST10488461
//Xolile Chilli