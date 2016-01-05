/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.NoSuchElementException;

/**
 *
 * @author Lukas Borges
 */

public class LinkedList
{
    //reference to head node:
    private Node head;
    private int listCount;
    
    //default constructor
    public LinkedList()
    {
        //this is an empty list, so the reference to the head mode
        //is set to a new node with no data
        head = new Node(null);
        listCount = 0;
    }
    
    //append object to the end of the list
    public void append(ReservationInfo reservation)
    {
        Node listTemp = new Node(reservation);
        Node listCurrent = head;
        
        //starting at the head node, crawl to the end of the list
        while(listCurrent.getNextNode() != null)
        {
            listCurrent = listCurrent.getNextNode();
        }
        
        listCurrent.setNextNode(listTemp);
        listCount++;
        //System.out.println("Appended from LinkedList");
    }
    
    //checks for empty
    public boolean isEmpty()
    {
        if(listCount == 0)
        {
            return true;
        }
            else
        {
            return false;
        }
    }
    
    //removes element at specified index
    public boolean removeIndex(int index)
    {
        //exit if index > range
        //if(index < 1 || index > size())
            //return false;
        
        Node listCurrent = head;
        for (int i = 1; i < index; i++)
        {
            if(listCurrent.getNextNode() == null)
                return false;
            
            listCurrent = listCurrent.getNextNode();
        }
        //connects current Node with succeding Node
        listCurrent.setNextNode(listCurrent.getNextNode().getNextNode());
        listCount--; //decrements the number of elements in the list
        return true;
    }

    
    //returns the amount of nodes in the list
    public int size()
    {
        return listCount;
    }
    
    //allows me to access elements in the linked list
    public ReservationInfo get(int index)
    {
        // index must be 1 or higher
        if(index < 0)
        return null;
		
        Node current = head.getNextNode();
        for(int i = 0; i < index; i++)
        {
            if(current.getNextNode() == null)
            return null;
            
            current = current.getNextNode();
        }
        return current.getrObject();
    }
    
    //allows access to linked list iterator from class instatiation
    public ListIterator listIterator()
    {
        return new LinkedListIterator();
    }
    
    @Override
    public String toString()
    {
        Node listCurrent = head.getNextNode();
        String output = "";
        while(listCurrent != null)
        {
            output += "[" + listCurrent.getrObject().toString() + "]";
            listCurrent = listCurrent.getNextNode();
        }
        return output;
    }
    

//This class defines the linked
//list iterator, used to insert
//nodes in the correct position
class LinkedListIterator implements ListIterator
{
    //3 instance variables
    private Node position;
    private Node previous;
    private boolean isAfterNext;

    public LinkedListIterator()
    {
        position = null;
        previous = null;
        isAfterNext = false;
    }
    
    @Override
    public ReservationInfo next()
    {  
        if (!hasNext()) { throw new NoSuchElementException(); }
        isAfterNext = true;

        if (position == null)
        {
            position = head;
        }
        else
        {
            position = position.nextNode;
        }

         return position.rObject;
      }
    
    @Override
    public boolean hasNext()
    {  
        if (position == null)
        {
            return head != null;
        }
        else
        {
            return position.nextNode != null;
        }
    }
    
    public void makeNewReservation(ReservationInfo element)
    {   
        if(isEmpty() == true) //if list is empty
        {
            //System.out.println("Added to empty list");
            add(element); //add method has an exception for empty list
        }
        else
        {
            position = head;
            for (int i = 0; i < size(); i++)
            {
                String eTime = element.rTime.replaceAll(":", "");
                String pTime = position.nextNode.rObject.rTime.replaceAll(":", "");
                
                int eTimeInt = Integer.parseInt(eTime);
                int pTimeInt = Integer.parseInt(pTime);
                
//                System.out.println(eTimeInt);
//                System.out.println(pTimeInt);
                
                if(i == (size()-1) && pTimeInt < eTimeInt)
                {
                    append(element);
                    break;
                }
                
                if(pTimeInt >= eTimeInt)
                {
                    //System.out.println("Added by comparison");
                    add(element);
                    break;
                }
                else
                    next();
            }
        }
    }
    
    @Override
    public void add(ReservationInfo element)
    {
        if(position == null) //if list is empty, append new element
        {
            append(element);
            position = head;
        }
        else//otherwise, append according to date/time comparison
        {
            Node newNode = new Node(null);  //create new node
            newNode.rObject = element;      //assign parameter to node's data
            newNode.nextNode = position.nextNode; //node points to next node in chain
            position.nextNode = newNode;          //current pointer points to new node
            position = newNode;                   //current IS the new node
            listCount++;
            //System.out.println("Added from ListIterator");
        }

        isAfterNext = false;
    }
    
    @Override
    public void remove()
    {
        if(!isAfterNext) { throw new IllegalStateException(); }
        if(position == head)
        {
            removeIndex(0);
        }
        else
        {
            previous.nextNode = position.nextNode;
        }
        position = previous;
        isAfterNext = false;
        listCount--;
    }

    @Override
    public void set(ReservationInfo element)
    {
        if(!isAfterNext) { throw new IllegalStateException(); }
        position.rObject = element;
    }
}    

//This class defines each node object 
//which are composed of pointer and data.
class Node
{
    Node nextNode;           //reference to next node in chain. null if none
    ReservationInfo rObject; //data carried by this node

    //Default Constructor
    public Node(ReservationInfo reservation)
    {
        nextNode = null;
        rObject = reservation;
    }

    //another Node constructor if we want to
    //specify the node to point to.
    public Node(ReservationInfo reservation, Node next)
    {
        nextNode = next;
        rObject = reservation;
    }

    //Getters
    public ReservationInfo getrObject(){return rObject;  }
    public Node getNextNode()          {return nextNode; }

    //Setters
    public void setrObject(ReservationInfo rObject){this.rObject = rObject;  }
    public void setNextNode(Node nextNode)         {this.nextNode = nextNode;}

}
}