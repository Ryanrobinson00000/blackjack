
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GuiControl implements ActionListener{
private JPanel container;
	//holds cardlayout, used to switch panels
	CardLayout cardLayout;
	//holds number of cards in deck
	int cardsInDeck;
	//holds the userlist of cards
	ArrayList<Integer> userList;
	//holds the cards being used
	ArrayList<Integer> cardsBeingUsed;
	//holds number of wins between games
	int wins;
	//holds number of losses between games
	int losses;
	//sets guicontrol when originally called in main
	GuiControl(JPanel container)
		{
			this.container=container;
			cardLayout = (CardLayout)container.getLayout();

		}
	//returns cardlayout
	CardLayout getCardLayout()
		{
			return this.cardLayout;
		}
	//returns container
	JPanel getContainer()
		{
			return container;
		}
	//returns number of cards in deck
	int getCardsInDeck()
	{
		return cardsInDeck;
	}
	void setCardsInDeck(int cardsInDeck)
	{
		this.cardsInDeck=cardsInDeck;
	}
	//resturns the users list of cards
	ArrayList<Integer> getUserList()
	{
		return userList;
	}
	//sets the users list of cards
	void setUserList(ArrayList<Integer> userList) {
		this.userList=userList;
	}
	//sets the cards being used currently in hands
	void setCardsBeingUsed(ArrayList<Integer> cardsBeingUsed)
	{
		this.cardsBeingUsed=cardsBeingUsed;
	}
	//gets the cards being used
	ArrayList<Integer> getCardsBeingUsed()
	{
		return cardsBeingUsed;
	}
	//sets the number of wins for user
	void setWins(int wins)
	{
		this.wins=wins;
	}
	//returns the number of wins for user
	int getWins()
	{
		return wins;
	}
	//sets the number of user losses
	void setLosses(int losses)
	{
		this.losses=losses;
	}
	//returns the number of user losses
	int getLosses()
	{
		return losses;
	}
	
	//used to change visible panel
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
			    

		
		//starts game
		if (command.contentEquals("Start"))
			{
				cardLayout.show(container, "2");
			}


		 }
				
		 

			
		





 }
		
 

	
