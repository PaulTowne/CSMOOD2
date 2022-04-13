import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GameObject
{
  protected int xposition,yposition;
   Color object;
   
   
   public GameObject(int xposition,int yposition,Color object)
   {
      this.xposition = xposition;
      this.yposition = yposition;
      this.object = object; 
      
   }
   public boolean collides(GameObject other)
   {
     
      int topthis = yposition-12;
      int bottomthis = yposition +12;
      int leftthis = xposition-12;
      int rightthis= xposition +12;
      
      int topother = other.ypos()-12;
      int bottomother = other.ypos()+12;
      int leftother = other.xpos()-12;
      int rightother = other.xpos()+12;
      if((bottomthis<topother || topthis>bottomother || leftthis> rightother || rightthis<leftother))
      {
      
      return false;
      }
      else
      {
       
      return true;
      }
      
   }
   
      
      public void draw(Graphics g) 
      {
          g.setColor(object);
         g.fillRect((xposition),(yposition),25,25);
         g.setColor(Color.BLACK);
         g.drawRect((xposition),(yposition),25,25);
      }
      public void drawVictory(Graphics g)
      {
         g.setColor(new Color(218,165,32));
         g.fillRect((xposition),(yposition),25,25);
         g.setColor(Color.YELLOW);
         g.drawRect((xposition),(yposition),25,25);
      }
         
      
      
   
   public int xpos()
   {
      return xposition;
   }
   public int ypos()
   {
      return yposition;
   }
   public Color C()
   {
      return object;
   }
}