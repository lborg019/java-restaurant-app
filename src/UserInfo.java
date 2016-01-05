/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Lukas
 * 
 * This class gets the user input
 */

import java.util.Scanner;
import java.io.*;

public class UserInfo
{
    String uName;
    String uTime;
    
//    public UserInfo()
//    {
//        
//    }

    public UserInfo(String uName, String uTime)
    {        
        this.uName = uName;
        this.uTime = uTime;
    }

    /**
     * time follows hh:mm format.
     * fail validation test if:
     * -> less than 5 characters are entered
     * -> : are placed incorrectly
     * -> hh > 24
     * -> mm > 59
     * 
    */
    //check if time is okay
    public boolean checkTime(String time)
    {
        int hours = Integer.parseInt(time.substring(0,2));
        int minutes = Integer.parseInt(time.substring(3,5));
        
        if(time.charAt(2) != ':' || time.length() != 5
           || hours > 24 || minutes > 50)
        {
            return false; //fails
        }
        else
        {
            return true; //succeeds
        }
    }
    
    public void loadInfo()
    {
        String tempDate;
        String tempTime;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in your name:");
        setuName(scanner.nextLine());
        
        System.out.println("Please type in the reservation"
                             + " time [hh:mm continuous format]:");
        tempTime = scanner.nextLine();
        while(checkTime(tempTime) == false) //input validation:
        {
            System.out.println("Invalid time.");
            System.out.println("Please type the reservation time [hh:mm continous])");
            tempTime = scanner.nextLine();
        }
        setuTime(tempTime); //sets validated continous time
    }
    
    //Getters
    public String getuName(){ return uName;   }
    public String getuTime(){ return uTime;   }
    
    //Setters
    public void setuName(String rName){ this.uName    = rName;   }
    public void setuTime(String rTime){ this.uTime    = rTime;   }
    
}
