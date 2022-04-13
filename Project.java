import javax.swing.*;
import java.awt.*;
//import java.util.*;
import java.util. ArrayList;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.Timer;
import java.util.Scanner;
public class Project extends JFrame
{
   
   public static void main(String [] args)
   {
      
      JFrame frame = new JFrame ("Project"); 
      frame.setSize(820, 620); 
      frame.setContentPane(new Project_Panel());  
      frame.setVisible(true); 
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}