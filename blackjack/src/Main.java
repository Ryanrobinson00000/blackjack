

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	//creates a cardlayout to switch panels
	CardLayout cardLayout = new CardLayout();
	//creates a conatiner to store panels using hte cardlayout
    JPanel container = new JPanel(cardLayout);
    //creates a guicontrol to control the cardlayout using the container
    GuiControl mmc = new GuiControl(container);
	
	//main
	public static void main(String[] args) {
		new Main();

	}

	//performs main, setting up gui frame
	public  Main()
	{
		//makes jframe exit on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1000, 1000));

	    // Add the card layout container to the JFrame.
	    this.add(container, BorderLayout.CENTER);
	    this.setTitle("Blackjack");
	    // Show the JFrame.
	    this.pack();
	    this.setVisible(true);
	    
	   mmc.setWins(0);
	   mmc.setLosses(0);
	    
	   //create and add homepanel to gui.  is used for settings
	    HomePanel view1=new HomePanel(mmc, container);
	    container.add(view1, "1");
	    
	    game view2=new game(mmc, container);
  	    container.add(view2, "2");
	    //view2 is game.java
	    
	    
	    //revlidate and repaint panel to ensure it shows up properly without needing to resize
	    validate();
	    repaint();
	    
	    
	    
	    
	    
	 
	
				
				
		
		
		
	}
	
}
