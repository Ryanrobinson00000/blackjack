import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class game extends JPanel {
	
	
private JPanel border;
	
	//upper section of border panel
	private JPanel north=new JPanel();
	//middle section of border panel
	private JPanel center=new JPanel();
	private JPanel south=new JPanel();


	
	
	
	public game(GuiControl mmc, JPanel container) {
		///////////////////////////////////////
		//display
		///////////////////////////////////////
		//cosmetic dvidier
		JLabel divider=new JLabel("**********************************");
		//notifies user of important game detail
		JLabel details=new JLabel("Dealer must draw to 16 and stand on all 17's");
		north.add(details);
		
		//gets the number of wins for session
		int winCount=mmc.getWins();
		//gets number of losses for session
		int lossCount=mmc.getLosses();
		//makes label for number of wins for this session
		JLabel wins=new JLabel("Wins:"+winCount);
		//makes label for number of losses for this session
		JLabel losses=new JLabel("Losses:"+lossCount);
		//adds win label
		north.add(wins);
		//adds loss label
		north.add(losses);
		//adds cosmetic divider
		north.add(divider);
		
		
		//label denoting dealer space
		JLabel enemyLabel=new JLabel("Dealer");
		//label for opponents hand with first card hidden
		JLabel enemyCardsDisplayHidden=new JLabel("");
		//display for visible oppoent score
		JLabel enemyScoreLabelHidden=new JLabel("Showing:0");
		//flips facedown card displaying all opponents cards
		JLabel enemyCardsDisplay=new JLabel("");
		//displays score for all cards, including face down
		JLabel enemyScoreLabel=new JLabel("Score:?");

		//user label denoting player
		JLabel userLabel=new JLabel("You");
		//cosmetic divider
		JLabel divider2=new JLabel("**********************************");
		//shows user cards
		JLabel userCardsDisplay=new JLabel("");
		//shows user total score
		JLabel userScoreLabel=new JLabel("Score:0");
		//will show whether the player won or lost
		JLabel winLoss=new JLabel("");
		//adds components to center part of panel
		center.add(enemyLabel);
		center.add(enemyCardsDisplayHidden);
		center.add(enemyScoreLabelHidden);
		center.add(enemyScoreLabel);
		center.add(enemyCardsDisplay);
		center.add(divider2);
		center.add(userLabel);
		center.add(userCardsDisplay);
		center.add(userScoreLabel);
		center.add(winLoss);
		
		///////////////////////////////////////////
		
	
		
		
		//starting number of cards in a standard deck
		int cardsInDeck=52;
		//sets the number of cards to the standard size
		mmc.setCardsInDeck(cardsInDeck);
		
		//stores list of cards user has
		ArrayList<Integer> userList=new ArrayList<>();
		//setes userlist as empty arraylist
		mmc.setUserList(userList);
		//holds enemys list of cards held
		ArrayList<Integer> enemyCardList=new ArrayList<>();
		//keeps track of all cards being used
		ArrayList<Integer> cardsBeingUsed=new ArrayList<>();
		//sets cards being used to empty
		mmc.setCardsBeingUsed(cardsBeingUsed);
		//geneartes random number
		Random rn = new Random();
		//user score
		int userScore=0;
		//score when not considering face down card
		int enemyHiddenScore=0;
		//score when flipping up face down card
		int enemyScore=0;
		
		
		//opponent  1st card
		//gets card based off of rng and cards in deck
		  int cardID=drawACard(rn, cardsInDeck);
		  //deincrements deck size
		  cardsInDeck=cardsInDeck-1;
		  //sets new amount of cards in deck
		  mmc.setCardsInDeck(cardsInDeck);
		  //sets enemy card list
		  setCardList(cardID, cardsBeingUsed, enemyCardList);

		  //opponent 2nd card
		  //draws a card and places id in cardId
		  cardID=drawACard(rn, cardsInDeck);
		  //deincrements size of deck
		  cardsInDeck=cardsInDeck-1;
		  //sets number of cards in deck
		  mmc.setCardsInDeck(cardsInDeck);
		  //sets opponents card listbased off of cardID, cards beingused and sets in enemyCardList
		  setCardList(cardID, cardsBeingUsed, enemyCardList);
		  //sets enemy hidden score using enemy card list
		  enemyHiddenScore=hiddenCheckScore(enemyCardList);
		  //System.out.println("***"+enemyHiddenScore+"***");
		  enemyScoreLabelHidden.setText("Showing:"+enemyHiddenScore);
		  
		  //function to display cards.  takes cardlist where to place them and 0 means visible 1 means hidden
		  displayCards(enemyCardList, enemyCardsDisplayHidden, 1);
		  System.out.print(checkScore(enemyCardList));
		  
		  /////////////////////////////
		 //player 1st card
		 /// //////////////////////////
		  //draws a card based off of random number generator and cardsindeck
		  cardID=drawACard(rn, cardsInDeck);
		  //decreases cards left in deck
		  cardsInDeck=cardsInDeck-1;
		  //sets cards in deck
		  mmc.setCardsInDeck(cardsInDeck);
		  //sets list of cards for user
		  setCardList(cardID, cardsBeingUsed, userList);
		  /////////////////////////////////////
		  //player 2nd card
		  /////////////////////////////////////
		  //gets card id
		  cardID=drawACard(rn, cardsInDeck);
		  //deincrements deck size
		  cardsInDeck=cardsInDeck-1;
		 
		  
		  //sets cards in deck
		  mmc.setCardsInDeck(cardsInDeck);
		  //sets userlist using card generated, cards beingused, and list of cards
		  setCardList(cardID, cardsBeingUsed, userList);
		
		  
		  //sets user score to based on list of user cards 
		  userScore=checkScore(userList);
		  //sets score label for user
		  userScoreLabel.setText("Score:"+userScore);
		  //sets enemy score based off of enemy card list
		  enemyScore=checkScore(enemyCardList);

		  //jbutton to activate draw for user
			 JButton draw=new JButton("Draw");
		  //user stands, preventing additional card draws and passing priority to dealer
			 JButton stay=new JButton("Stand");
			 //button to restart game, but with updated win/loss ratio
			 JButton restart=new JButton("Restart");
			 //disable restart button
			 restart.setEnabled(false);
			 
			 //checks if naturalwin occured, where a player gets exactly 21 points off of 2 cards 
		  int checkNaturalWin=(checkwin(userScore, enemyScore));
		  //if a natural win has occurred displays winner
		  if(checkNaturalWin!=0)
		  {
			  //sets card display for visible cards for enemy
			  displayCards(enemyCardList, enemyCardsDisplay, 0);
			  //counts the score for the enemy card list
			 enemyScore= checkScore(enemyCardList);
			 //sets enemy score based off of cards list
			 enemyScoreLabel.setText("Score:"+enemyScore);
			 //disables draw and stay while enabling restart since game has ended
			  gameOver(draw, stay, restart);
			  
			  //perform is user lost round.  display loss and append loss to loss ratio
			  if(checkNaturalWin==1)
			  {				
				  //display that user lost
				  displayLoss(winLoss);
				  //append user loss to loss counter
				  addLoss(mmc);

			  }
			  //perform if user won round.  display win and append win to win counter
			  if(checkNaturalWin==2)
			  {
				  //display that user won
				  displayWin(winLoss);
				  //append user win to counter
				addWin(mmc);

			  }
			  //perform is user tied this round.  displays user tied.  no counter occurs here
			  if(checkNaturalWin==3)
			  {
				  displayTie(winLoss);
			  }
		  }
		  
		  //displays user cards based off of userlist and no cards are hidden
		  displayCards(userList, userCardsDisplay, 0);

		  
		  

		 //adds to bottom part of panel in order
		south.add(draw);
		south.add(stay);
		south.add(restart);
		
		restart.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  //creates new game to replace current game copy
				game view2=new game(mmc, container);  
		
		        //adds new panel to the panel with same name to overwrite
				container.add(view2,"2");
				
				//ensures graphics update properly
				container.repaint();
				container.revalidate();
				
				//gets the cardlayout to allow for transition of screen
				CardLayout copy=mmc.getCardLayout();
				//changes screen to new login panel with new profile added
				copy.show(container, "2");
			  }
			  
			  });
		//performs when user presses draw button
		draw.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  //generates random values
					Random rn = new Random();
					//gets number of cards in deck
					int cardsInDeck=mmc.getCardsInDeck();
					//draws card from deck using random value
				  int cardID=drawACard(rn, cardsInDeck);
				  //sets cards in deck
				  mmc.setCardsInDeck(cardsInDeck-1);
				  
				  //adds card to userlist
				
				  
				 //adds card to list of cards being used
				 ArrayList<Integer> cardsBeingUsed=mmc.getCardsBeingUsed();
				 //sets new cardid to userlist and cardsbeingused
				setCardList(cardID, cardsBeingUsed, userList);
				//updates user score
				 int userScore=checkScore(userList);
				 //displays updated user score after draw
				  userScoreLabel.setText("Score:"+userScore);
				//checks if users score exceeds 21
				boolean loss=  checkBust(userScore);
				
				 // System.out.println("***"+userScore+"***");
				  displayCards(userList, userCardsDisplay, 0);
		
				
				  //if uers score exceeds 21 perform since they lost
				if(loss)
				{
					//display taht user lost
					displayLoss(winLoss);
					//add loss to loss counter
					addLoss(mmc);
					
					//gets display for enemy score with all cards visible
					  displayCards(enemyCardList, enemyCardsDisplay, 0);
					  //checks enemy score based off of enemy card list
					  int enemyScore;
						 enemyScore= checkScore(enemyCardList);
						 //sets text for enemy score label
						 enemyScoreLabel.setText("Score:"+enemyScore);
						 //disables draw and stay while enables restart since game is over
						  gameOver(draw, stay, restart);

				}
				
				//if user gets 21 but it isn't natural(1st 2 cards) perform
				if(nonNatural21(userScore))
				{
					//disables draw and stay buttons and enables restart because game is over
					  gameOver(draw, stay, restart);

					//displays enemy cards with hidden card shown
					 displayCards(enemyCardList, enemyCardsDisplay, 0);
					 //checks enemy score
					  int enemyScore;
						 enemyScore= checkScore(enemyCardList);
						 //sets text with enemy score on it
						 enemyScoreLabel.setText("Score:"+enemyScore); 
						 //dealers score is less than 17
					while(enemyScore<17)
					{
						//generate card id
						   cardID=drawACard(rn, cardsInDeck);
						   //deincrement number of cards left in deck
						  cardsInDeck=cardsInDeck-1;
						  //sets number of cards left in deck
						  mmc.setCardsInDeck(cardsInDeck);
						  //adds cardid to list of cards
						  setCardList(cardID, cardsBeingUsed, enemyCardList);
						  //sets enemy score
						  enemyScore= checkScore(enemyCardList);
						  //displays cards for enemy based off of card list to enemycard display with values being unhidden
						  displayCards(enemyCardList, enemyCardsDisplay, 0);
						  //counts enemy score
							 enemyScore= checkScore(enemyCardList);
					      //sets enemy score text
							 enemyScoreLabel.setText("Score:"+enemyScore);

					}
					//if the enemies score is 21 it is a tie
					if(enemyScore==21)
					{//display that the game was a tie no win/losses are awarded
						displayTie(winLoss);
					}
					//if it is not a loss or a tie it is a win
					else {
						//display that the hand was a winner for user
						displayWin(winLoss);
						//append win to win counter
						addWin(mmc);
					}
						
				}
				
				
				
			  }
			  });
		//lets opponent take turn and finish game when stay button is pressed
		stay.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				 
				
				//disable draw and stay while enableing restart since hand is over
				  gameOver(draw, stay, restart);

						//displays enemy cards based off of list, with no cards being hidden
						 displayCards(enemyCardList, enemyCardsDisplay, 0);
						 //gets enemy score
						  int enemyScore;
							 enemyScore= checkScore(enemyCardList);
							 //sets enemy score
							 enemyScoreLabel.setText("Score:"+enemyScore); 
							
							 //gets number of cards in deck
							 int cardsInDeck=mmc.getCardsInDeck();
							 //draws a card
							 int  cardID=drawACard(rn, cardsInDeck);
							 //returns player score
							 int userScore=checkScore(userList);
///////////////////////////////////////////////////////////////////////////////////////////////////

							 //gets user score up to at least 17
						while(enemyScore<17)
						{
							//removes a card from the deck
							  cardsInDeck=cardsInDeck-1;
							  //sets number of cards in the deck
							  mmc.setCardsInDeck(cardsInDeck);
							  //sets the card list with card chosen cards already used and appends to the dealers card list
							  setCardList(cardID, cardsBeingUsed, enemyCardList);
							  //sets enemy score
							  enemyScore= checkScore(enemyCardList);
							  //sets cards displayed for enemies non-hidden cards
							  displayCards(enemyCardList, enemyCardsDisplay, 0);
							  //gets enemies score
								 enemyScore= checkScore(enemyCardList);
								 //displays enemies score
								 enemyScoreLabel.setText("Score:"+enemyScore);

						}
						//if the dealer hits a soft 17 and the enemies score is less than the users score draw another card
						//soft 17
						if(enemyScore==17&& enemyScore<userScore)
						{
							//deincrements cards in deck
							  cardsInDeck=cardsInDeck-1;
							  //sets cards in deck
							  mmc.setCardsInDeck(cardsInDeck);
							  //sets card list using card id cardsbeing used already and the enemies card list
							  setCardList(cardID, cardsBeingUsed, enemyCardList);
								  //sets the enemies score under soft17 conditions, determining whether to go as 1 or 11 for aces
								 enemyScore= soft17(enemyCardList, userScore);
							  //displays enemy cards without hidden card shown
							  displayCards(enemyCardList, enemyCardsDisplay, 0);
							
							  //sets enemy score label
								 enemyScoreLabel.setText("Score:"+enemyScore);
						}
						
						//displays loss if enemy scores higher than player and scores 21 or less
						if(enemyScore>userScore && enemyScore <=21)
						{
							//displays loss
							displayLoss(winLoss);
							//adds loss to counter
							addLoss(mmc);
						}
						//displays tie if enemy scores equal to player 
						else if(enemyScore==userScore)
						{//displays the game was a tie and no win/loss is awarded
							displayTie(winLoss);
						}
						//if game isn't lost or tied it is won
						else {
							//displays that game was won
							displayWin(winLoss);
							//add win to win counter
							addWin(mmc);
						}
					
			  }
		});
	
		//sets layout for center being a 1xY format
		north.setLayout(new GridLayout(0,1));	
		center.setLayout(new GridLayout(0,1));	
		south.setLayout(new GridLayout(0,1));
		border = new JPanel(new BorderLayout());
		//add north panel to border in north position of panel
		border.add(north,BorderLayout.NORTH);
		//add center panel to the border in center position of panel
		border.add(center,BorderLayout.CENTER);
		border.add(south, BorderLayout.SOUTH);
		//adds border to gui
		this.add(border);
		
		//ensures panel updates properly
		this.repaint();
		this.revalidate();
		
	}
	//displays loss
	void displayLoss(JLabel winLoss)
	{
		//display that player lost
		winLoss.setText("You Lose");
	}
	//displays win
	void displayWin(JLabel winLoss) {
		//displays that user won
		winLoss.setText("You Win");

	}
	//displays tie
	void displayTie(JLabel winLoss) {
		//displays that user tied
		winLoss.setText("You Tied");

	}
	//sets buttons when game ends
	void gameOver(JButton draw, JButton stay, JButton restart)
	{
		//disables ability to draw cards
		draw.setEnabled(false);
		//disables ability to choose to stop drawing cards and pass priority
		stay.setEnabled(false);
		//enables restart button
		restart.setEnabled(true);
	}
	//adds win to win display, also allows win to be transferred on restart
	void addWin(GuiControl mmc)
	{
		//gets win from guicontrol
		  int win=mmc.getWins();
		  //increments win
		  win++;
		  //sets win count
		  mmc.setWins(win);
		
	}
	//adds loss to loss display, also allows loss to be transferred on restart
	void addLoss(GuiControl mmc)
	{
		//gets number of losses from guicontrol
		  int loss=mmc.getLosses();
		  //increments number of losses
		  loss++;
		  //sets losses to guicontrol
		  mmc.setLosses(loss);
	}
	//draws a card from deck
	int drawACard(Random rn, int cardsInDeck)
	{
		//gets a index value for card based on number of cards in deck.  Indexes will look over numbers already used
		   int cardId=rn.nextInt(cardsInDeck - 1 + 1) + 1;
		//returns card id generated
		 return cardId;
	}
	//sets list of cards, adding new card
	void setCardList(int cardID,ArrayList cardsBeingUsed, ArrayList cardList)
	{
		//increments for each value used between x and cardindex
		 int cardAlreadyUsedCount=0;
			//  loops for values between 1 and the cards id
			 for(int x=1;x<=cardID;x++)
			 {
				 //if the card is being used
				 if(cardsBeingUsed.contains(x+cardAlreadyUsedCount))
				 {
					 //perform while cards card contains value being used
					 while(cardsBeingUsed.contains(x+cardAlreadyUsedCount))
					 {
						 //adds index value if card is already being used
						 cardAlreadyUsedCount=cardAlreadyUsedCount+1;
					 }
				 }
			 }
			 //adds index to cards already used
			 cardsBeingUsed.add(cardAlreadyUsedCount+cardID);
			 //sorts collection, allowing indexes to be used to find proper index
			 Collections.sort(cardsBeingUsed);
			
			   
			        	
			 //adds proper index to card list
			cardList.add(cardAlreadyUsedCount+cardID);
			
			 
	}
	//sets hidden score, with hidden not showing first card(which is face-down)
	int hiddenCheckScore(ArrayList cardList)
	{
		//declares score
		int score=0;
		//declares score for individual card in list
		int cardScore=0;
		//iterates through card list
		   for (int i = 1; i < cardList.size(); i++) {
			   //gets corresponding value from card.  ace-13 is why it is %3 and +1 to start with Ace
			   cardScore=(((int)cardList.get(i)%13)+1);
			   //if the score is greater than 10, being JQorK, add 10 to the score 
			   if(cardScore>10)
			   {
				   //incerment score by 10
				   score=score+10;
			   }
			   //if cardscore isn't a face card add the value
			   else{
				   //adds card's value 1orA-10
	        	score=(int)cardScore+score;
			   }
			   
			   
	        }
		   //returns score
		   return score;
	}
	/////////////////////////////////////////////////////////////////////////////////////
	//soft17
	
	//calculates best score whenever soft17 is in effect **priotizies 1 over 11 for ace
	int soft17(ArrayList cardList, int userScore)
	{
		//sets score to 0
		int score=0;
		//sets cardscore to 0
		int cardScore=0;
		//counts number of aces
		int aceCount=0;
		//iterates for number of cards
		   for (int i = 0; i < cardList.size(); i++) {
			   //gets value from card
			   cardScore=(((int)cardList.get(i)%13)+1);
			
			   //cardscore 1 is an ace
			   if(cardScore==1)
			   {
				   //increment ace count
				   aceCount++;
				  // if(21 >=(score+11))
				 //  {
				//	   score=score+11;
				 //  }
				//   else {
				//	   score=score+1;
				//   }
			   }
			   //if the value is a face card increment by score by 10
			   else if(cardScore>10)
			   {
				   score=score+10;
			   }
			   //if value is not a face card or a 1 increment by card's value
			   else{
	        	score=(int)cardScore+score;
			   }
			   ////////////////////////
			   //performs after iterated through for loop, allowing for ace values to be properly evaluated
			   ////////////////////////
			   //if the number of aces is 1 or more and if ace being 11 would result in a countless than 21 make ace value 11
			   if(aceCount>0 && (score+10)<21)
			   {
				   //if incrmenting by 10 would make score higher than the players score increment by 10 to win as enemy
				   if((score+10)>=userScore)
				   {
					   //increment by 10
					   score=score+10;
				   }
			   }
			   
	        }
		 //return best score at when previous was 17
		   return score;
	}
	//counts score when soft17 is not in effect **prioritizes 11 over 1 for ace
	int checkScore(ArrayList cardList)
	{
		//sets score to 0
		int score=0;
		//sets cardScore to 0
		int cardScore=0;
		//sets Ace count to 0
		int aceCount=0;
		//iterates through list of cards
		   for (int i = 0; i < cardList.size(); i++) {
			   //gets card value from index position
			   cardScore=(((int)cardList.get(i)%13)+1);
			
			   //if card is ace incrment ace count. added after for loop
			   if(cardScore==1)
			   {
				   aceCount++;
	
			   }
			   //if card is face card Jack queen or king increment by 10
			   else if(cardScore>10)
			   {//increment score by 10
				   score=score+10;
			   }
			   else{
				   //if card isn't face card or ace increment by card's value
	        	score=(int)cardScore+score;
			   }
			   
			   
	        }
		   //adds aces to count
		   for(int x=0;x<aceCount;x++)
		   {
			   //if 21 is less than score when x is the last ace to be counted
			 
				   if(21 >=(score+11)&& x==(aceCount-1))
				   {
					   //increments by 11 if possible
					   score=score+11;
				   }
				   else {//if value cant be incremented by 11 and be 21 or under value is set to 1
					   score=score+1;
				   }
		   }
		   //return the total score of cards
		   return score;
	}
	//checkwin for natural win being first 2  cards
	int checkwin(int userscore, int enemyScore)
	{
		//if the user has 21 perform
		if(userscore==21) {
			//return tie if the enemy also has a natural 21
			if(enemyScore==21)
			{
				return 3;
			}
			//return user win if the enemy doesnt have a natural 21
			else {
				return 2;
			}
			
		}//if user doesn't have a natural 21 and enemy has a natural 21 perform
		else if(enemyScore==21)
		{//return user loss
			return 1;
		}
		//no natural 21 return no winner and continue game
		else {
			return 0;
		}
	}
	//checks if a nonnatural 21 occurs, being beyond the first 2 cards user is dealt
	boolean nonNatural21(int score)
	{
		//only called after first 2 cards are dealth and checked
		//if the score is 21 it is a nonnatural 21 and return true
		if(score ==21)
		{
			
			return true;
		}
		//if it isn't 21 then it isnt a nonnatural 21 and return false
		return false;
	}
	//checks if user busted(got over 21)
	boolean checkBust(int score)
	{
		//if score is over 21 return true, otherwise return false
		if(score >21)
		{
			
			return true;
		}
		return false;
	}
	//displays cards using cardlist, carddisplay area, and whether the card is hidden from player view
	void displayCards(ArrayList cardList, JLabel cardDisplay, int hidden) {
		//stores cards individual score
		int cardScore=0;
		//stores cards value
		String cards="";
		//performs for duration of card list
		 for (int i = 0; i < cardList.size(); i++) {
		//if card isn't 0, connoting hidden and if it is the first card append questionmark to show unknown
		  if(hidden!=0 && i==0)
		  {
			  cards=cards+"?";
		  }
		  //if card's value is 11 out of a total of 13 using %13, append as J or Jack
		  else if((((int)cardList.get(i)%13)+1)==11)
			 {
				 cards=cards+"J";

			 }
		  //if card's value is 12 out of a total of 13 using %13, append as Q or queen
			 else if((((int)cardList.get(i)%13)+1)==12)
			 {
				 cards=cards+"Q";

			 } 
		  //if card's value is 13 out of a total of 13 using %13, append as K or king
			 else if ((((int)cardList.get(i)%13)+1)==13)
			 {
				 cards=cards+"K";

			 }
		  //if card's value is 1 out of a total of 13 using %13, append as A or ace
			 else if ((((int)cardList.get(i)%13)+1)==1)
			 {
				 cards=cards+"A";

			 }
		  //if not hidden, ace or a face card, append card's value
			 else {
			 cardScore=(((int)cardList.get(i)%13)+1);
			 cards=cards+" "+cardScore;
			 }
		  
		  //////////////////////////////////////////////////
		  //set card suit
		  ///////////////////////////////////////////////////
		  //gets the suit by dividing card's id-1(the -1 due to starting with 1 instead of 0 with card values due to 1 being ace) by 13.  the value is then floored
		 double suit= Math.floor(((int)cardList.get(i)-1)/13);
		 //if value is hidden and value is first index append ? to denote card is hidden from user
		  if(hidden!=0 && i==0)
		  {
			  cards=cards+"?";
		  }
		  //if the suit is valued at 0, D or Diamond is appended
		  else if(suit==0)
			 {
				 cards=cards+"D ";

			 }
		  //if the suit is valued at 1, H or heart is appended
		  else  if(suit==1)
			 {
				 cards=cards+"H ";

			 }
		  //if suit is valued at 2, S or spade is appended
		  else  if(suit==2)
			 {
				 cards=cards+"S ";

			 }
		  //if suit is valued at 3, C or club is appended
		  else  if(suit==3)
			 {
				 cards=cards+"C ";

			 }
		  
		 
		 	//display value of all cards in list
		cardDisplay.setText(cards);
		
	}
	}
	
}
