package com.driver;

import java.util.ArrayList;
import java.util.Date;

//receiveMail(Date date, String sender, String message): Receive a mail, move oldest mail to trash if inbox is full.
//deleteMail(String message): Delete a mail by moving it to trash.
//findLatestMessage(): Find the latest message in the inbox.
//findOldestMessage(): Find the oldest message in the inbox.
//findMailsBetweenDates(Date start, Date end): Find the number of mails received between two dates.
//getInboxSize(): Get the size of the inbox.
//getTrashSize(): Get the size of the trash.
//emptyTrash(): Empty the trash.
//getInboxCapacity(): Get the maximum inbox capacity.

class data
{
    Date date;
    String sender;
    String message;

    public data(Date date, String sender, String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }
}
public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    ArrayList<data> Inbox=new ArrayList<>();
    ArrayList<data> Trash=new ArrayList<>();
    public Gmail(String emailId, int inboxCapacity)
    {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message)
    {
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(Inbox.size()<inboxCapacity)
        {
            Inbox.add(new data(date,sender,message));
        }
        else
        {
            Trash.add(Inbox.get(0));
            Inbox.remove(0);
            Inbox.add(new data(date,sender,message));
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0;i<Inbox.size();i++)
        {
            if(Inbox.get(i).message.equals(message))
            {
                Trash.add(Inbox.get(i));
                Inbox.remove(i);
                break;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if (Inbox.isEmpty())
        {
            return  null;
        }
        else
        {
            return Inbox.get(Inbox.size()-1).message;
        }
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if (Inbox.isEmpty())
        {
            return  null;
        }
        else
        {
            return Inbox.get(0).message;
        }
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count=0;
        for(int i=0;i<Inbox.size();i++)
        {
            Date date=Inbox.get(i).date;
            if ((date.after(start) || date.equals(start)) &&
                (date.before(end) || date.equals(end)))
            {
               count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
