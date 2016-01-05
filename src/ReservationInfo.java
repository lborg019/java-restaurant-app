/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Scanner;
/**
 *
 * @author Lukas Borges
 * 
 * This class details the data part of each node.
 * Each node in the Reservation linked list shall
 * have this same set of data; which contains all
 * the details for each reservation.
 * 
 */
public class ReservationInfo
{
    String rName;
    String rTime;

    public ReservationInfo(String rName, String rTime)
    {
        this.rName = rName;
        this.rTime = rTime;
    }
    
    //Getters
    public String getrName(){ return rName;   }
    public String getrTime(){ return rTime;   }
    
    //Setters
    public void setrName(String rName){ this.rName    = rName;   }
    public void setrTime(String rTime){ this.rTime    = rTime;   }    
}
