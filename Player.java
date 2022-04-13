import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class Player extends GameObject
{
   public Player(int xposition,int yposition,Color object)
   {
     super(xposition,yposition,object);
   }
   public void set(int x, int y)
   {
      xposition = x; 
      yposition = y;
   }
   public boolean isOnGround(ArrayList<ArrayList<GameObject>> x)
   {
      if(move(0,1,x))
      return true;
      return false;
      
   }
   public boolean win(int dirX, int dirY, int x,int y,ArrayList<ArrayList<GameObject>> array)

   {
         xposition = xposition +dirX; 
         yposition = yposition +dirY;
         double tempX =(xposition+16)/25;
         double tempY = (yposition+16)/25;
         int indexX = (int)tempX;
         int indexY = (int)tempY;
         for(int i=indexY-1;i<indexY+1;i++)
         {
            for(int k=indexX-1;k<indexX+1;k++)
            {
               
               if(array.get(i).get(k)!=null)
               {
                  if(super.collides(array.get(i).get(k)))
                  {
                     if(i==y && k==x)
                     {
                          return true;
                     }

                  }
               }
            
            }
            
       }
         xposition = xposition -dirX; 
         yposition = yposition -dirY;
        
       return false;
   
   }
   public boolean ceiling(ArrayList<ArrayList<GameObject>> array)
   {
     
            yposition = yposition-1; 
      double tempX =(xposition+16)/25;
      double tempY = (yposition+16)/25;
      int indexX = (int)tempX;
      int indexY = (int)tempY;
      for(int i=indexY-1;i<indexY;i++)
      {
         for(int k=indexX-1;k<indexX+1;k++)
         {
            
            if(array.get(i).get(k)!=null)
            {
               if(super.collides(array.get(i).get(k)))
               {
                  
                  yposition = yposition+1;
                return true;
               }
            }
         
         }
      }
     
         
      
      return false;

   }
   
   public boolean move(int x, int y, ArrayList<ArrayList<GameObject>> array)
   {
      xposition = xposition+x;
      yposition = yposition+y; 
      double tempX =(xposition+16)/25;
      double tempY = (yposition+16)/25;
      int indexX = (int)tempX;
      int indexY = (int)tempY;
      for(int i=indexY-1;i<indexY+1;i++)
      {
         for(int k=indexX-1;k<indexX+1;k++)
         {
            if(array.get(i).get(k)!=null)
            {
               if(super.collides(array.get(i).get(k)))
               {
                  xposition = xposition-x;
                  yposition = yposition-y;
                return true;
               }
            }
         }
      }
      return false;
   }  
   public int xpos()
   {
      return super.xpos();
   }
   public int ypos()
   {
      return super.ypos();
   }

}