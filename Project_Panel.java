import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
public class Project_Panel extends JPanel
{
   static int row;
   static int column;
   static int playerx;
   static int playery;
   static int xIndex;
   static int yIndex;
   static boolean w;
   static boolean a;
   static boolean s;
   static boolean d; 
   static boolean standard =true;
   static int N=1;
   static double J=0.0;
   static int count=0;
   static boolean spam = false;
   static boolean endGame = false;
   boolean jump = false;
   Color color = new Color(100,50,0);
   // victory
   static int x1;
   static int y1;
   Player player;
   static ArrayList<ArrayList<GameObject>> data = new ArrayList<ArrayList<GameObject>>();
   
   public ArrayList<ArrayList<GameObject>> access()
   {
      return data;
   }
      private class MouseClass extends MouseAdapter 
      {  
         
         public void mousePressed(MouseEvent me)
         {
            //System.out.println("X,Y = "+me.getX()+" "+me.getY());
            //System.out.println("position ="+player.xpos()+" "+player.ypos());
         }
         public void mouseMoved(MouseEvent me)
         {
           
         }
         public void mouseDragged(MouseEvent me)
         {
            
         }
      }
   public Project_Panel(int x)
   {
      
   }
   public Project_Panel()
   {
      try 
      {
         Scanner scan = new Scanner(new File("projectfile.txt"));
          playerx = scan.nextInt()*25+10;
          playery = scan.nextInt()*25+10;
          row = scan.nextInt();
          column = scan.nextInt();
          player = new Player(playerx,playery,Color.RED);        
         for(int i=0;i<row;i++)
         {
            data.add(new ArrayList<GameObject>());
            for(int k=0;k<column;k++)
            {
               int temp = scan.nextInt();
               if(temp==1)
               data.get(i).add(k,new GameObject((k*25)+10,(i*25)+10,color));
               else if(temp==0)
               data.get(i).add(k,null);
               else if(temp==2)
               {
                data.get(i).add(k,new Block((k*25)+10,(i*25)+10,Color.BLUE));
                x1 = k;
                y1 = i;          
               }
               
            }
         }
         addKeyListener(new KeyEventDemo());
         addMouseListener(new MouseClass());

         setFocusable(true);

         Timer t = new Timer(1, new TimeListener());
        t.start();
         player = new Player(playerx,playery,Color.RED);
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not Found");
      }
   }
         public void paintComponent(Graphics g)
         { 
            super.paintComponent(g);
            
            g.fillRect(0,0,820,620);
            g.setColor(Color.BLACK);
            g.fillRect(10,10,780,580);            
            for(int i=0;i<row;i++)
            {
               for(int k=0;k<column;k++)
               {
                  if(data.get(i).get(k) != null)
                  {
                     GameObject block = data.get(i).get(k);
                     if(i==y1 && k==x1)
                     {
                        block.drawVictory(g);
                     }
                     else
                     block.draw(g);
                  }
                  
   
               }
           }
            player = new Player(playerx,playery,Color.RED);
           player.draw(g);
           

           

         }
         
         
   public class KeyEventDemo  implements KeyListener 
   {
      public void keyTyped(KeyEvent e) {}
      public void keyReleased(KeyEvent e) 
      {
         if(e.getKeyCode() == KeyEvent.VK_W)
         {

         }
         if(e.getKeyCode() == KeyEvent.VK_A)
         {
            a = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_S)
         {
            s = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_D)
         {
            d = false;
         }
      
      }
      public void keyPressed(KeyEvent e) 
      {
         
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
         if(spam!=true)
         {
          w=true;  
          J=7;
         }
         spam=true;
    
         }
         if(e.getKeyCode() == KeyEvent.VK_A)
         {
           a=true;

         }
         if(e.getKeyCode() == KeyEvent.VK_S)
         {
            s=true;
         }
         if(e.getKeyCode() == KeyEvent.VK_D)
         {
            d=true;
         }
         
      }
   }
    public int winX()
   {
      return x1;
   }
   public int winY()
   {
      return y1;
   }
   
   public class TimeListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(w==true)
         {
           jump=true;     
         }
         if(a==true)
         {
           for(int i=0;i<3;i++)
           {
            if(player.win(-1,0,x1,y1,data))
            {
               endGame = true;
            }
            else
            {
              player.move(-1,0,data);
              playerx = player.xpos();
              repaint();
            }
           }
           

         }
         if(d==true)
         {
            
            for(int i=0;i<3;i++)
            {
                if(player.win(1,0,x1,y1,data))
                {
                 
                  endGame = true;
                }
                else
                {

                  player.move(1,0,data);
                  playerx = player.xpos();
                  repaint();
                }
            }
         }
         
         if(s==true)
         {
           
         }
             if(jump==false)
             {
             if(player.win(0,1,x1,y1,data))
             {
               endGame = true;
             }
             else
             {

               if(!player.isOnGround(data))
               {
                  playery = player.ypos();
                  count++;
                 if(count==10)
                 {
                    count=0;
                 if(N<8)
                 N=N+2;
                 }
                 for(int i=0;i<(N+1);i++)
                 {
                   player.isOnGround(data);
                   playery = player.ypos();
                 } 
              }
              else
               N=0;
            }
           }
           else
           {
            if(player.win(0,-1,x1,y1,data))
            {
               endGame = true;
            }
            else
            {


              if(J>0)
              {
                     for(int i=0;i<6;i++)
                     {
                     if(player.ceiling(data))
                     J=0;
                     playery = player.ypos();
                     repaint();
                     }
                     J = J-.3;
               }
                 else
                 {
                  jump=false;
                  w=false;
          
                  
                 }
              }
           }   
           if(player.isOnGround(data))
           {
               spam = false;
           }
            if(endGame==true)
            {
               String victory = "VICTORY";
               JOptionPane.showMessageDialog(null, victory);
               System.exit(1);
               
            }
            
         repaint();
      }
   }



}