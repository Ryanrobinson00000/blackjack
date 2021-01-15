import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class HomePanel extends JPanel implements ActionListener{
	private JPanel border;
	
	//upper section of border panel
	private JPanel north=new JPanel();
	//middle section of border panel
	private JPanel center=new JPanel();
	private JPanel south=new JPanel();
	
		//creates container that will be used to pass container from main
	   JPanel container;
		public HomePanel(GuiControl mmc, JPanel container) {
			//passes container values from main
			this.container=container;
			
			//rules for blackjack game
			JLabel rules=new JLabel("Rules");
			JLabel rule1=new JLabel("1.Get closes to 21 than the dealer to win.  Human players lose automatically if they  go over 21.");
			JLabel rule2=new JLabel("2.Aces 'A' are worth either 1 or 11, whichever is more convenient at the moment.");
			JLabel rule3=new JLabel("3.Face cards, being jack, queen and king, are worth 10.");
			JLabel rule4=new JLabel("4.Non-face cards are worth their card number.");
			JLabel rule5=new JLabel("5.The dealer must draw cards until they pass 16 and must stop when they have 17 or more.");
			JLabel rule6=new JLabel("6.If the dealer has 17 with an ace being valued at 11, they can continue to draw cards until they hit a 'hard' 17.");
			JLabel rule7=new JLabel("7.Non-dealer players may draw as many cards as they wish as long as they do not go over 21.");
			JLabel rule8=new JLabel("8.All players start with 2 cards. Player cards are face up while one card is face up and one face down for the dealer.");

			//blackjack label
			JLabel blackJackTitle=new JLabel("Blackjack");
			north.add(blackJackTitle);
			
			//adds all rules to panel individually and in order
			center.add(rules);
			center.add(rule1);
			center.add(rule2);
			center.add(rule3);
			center.add(rule4);
			center.add(rule5);
			center.add(rule6);
			center.add(rule7);
			center.add(rule8);

			JButton submit=new JButton("Start");
			south.add(submit);
			submit.addActionListener(mmc);
			
		
			
			
			
			
			
		//sets layout for center being a 1xY format
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
		


		
		
	}
	
	

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}


		
}
