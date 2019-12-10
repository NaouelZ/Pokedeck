import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

public class Main implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String FILE_NAME = "pokedeck.ser";
	
	protected static int index;
	protected static Pokedeck Deck = new Pokedeck();
	protected static final JFrame window = new JFrame("My Pockedeck");
	protected static JPanel pnCard = new JPanel(new BorderLayout());
    protected static JLabel n;
    protected static JLabel h;
    protected static JLabel des;
    protected static JButton addCardButton;
    protected static JButton updateCardButton;
    protected static JButton removeCardButton;
    //protected static JList<String> cardList;
    //protected static JScrollPane displayCardList;
    //protected static ArrayList<String> listCard;
	//private static ObjectOutputStream oos;
    
	public static void main(String[ ] args) {
		Deck.addCard(new PokemonCard("test","test","15"));
		Deck.addCard(new PokemonCard("test2","test2","15"));
		Deck.addCard(new PokemonCard("test3","test3","15"));
	    
		try {
			FileOutputStream fs = new FileOutputStream(FILE_NAME);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(Deck);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        window.setBackground(Color.GREEN);
		window.setSize(500, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//TITLE
        JLabel title = new JLabel("WELCOME TO MY POKEDECK ! ");

		//MY MENU
		JPanel pnMenu = new JPanel(new FlowLayout());
		addCardButton = new JButton("Add card");
		pnMenu.add(addCardButton);
		addCardButton.addActionListener(new AddNewCard());
		updateCardButton = new JButton("Update");
		pnMenu.add(updateCardButton);
		updateCardButton.addActionListener(new EditCard());
		removeCardButton = new JButton("Remove");
		pnMenu.add(removeCardButton);
		removeCardButton.addActionListener(new RemoveCard());

		//Card details
		System.out.println("refresh");
		n = new JLabel(Deck.getCards().get(index).getName());
		h = new JLabel(Deck.getCards().get(index).getHealthPoints());
        n.setPreferredSize(new Dimension(130, 20));
        h.setPreferredSize(new Dimension(20, 20));
		pnCard.add(n, BorderLayout.WEST);
		pnCard.add(h, BorderLayout.EAST);
        des = new JLabel(Deck.getCards().get(index).getDescription());
        des.setPreferredSize(new Dimension(150, 200));
		pnCard.add(des, BorderLayout.SOUTH);
        pnCard.setBackground(Color.YELLOW);
        
        /*listCard = new ArrayList<String>();
        for (int i = 0; i < Deck.getCards().size(); i++) {
            Card selectCard = Deck.getCards().get(i);
            listCard.add(selectCard.getName());
        }*/
        
        //Search Card
        JPanel pnsearch = new JPanel(new FlowLayout());
        JLabel searchName = new JLabel("Search by type and name ");
        JTextField searchByType = new JTextField();
        JTextField searchByName = new JTextField();
        JButton searchButton = new JButton("Search");
        searchByType.setPreferredSize(new Dimension(100, 20));
        searchByName.setPreferredSize(new Dimension(100, 20));
        pnsearch.add(searchName);
        pnsearch.add(searchByType);
        pnsearch.add(searchByName);
        pnsearch.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
        		JLabel noResult = new JLabel("No result");
            	noResult.removeAll();
            	noResult.revalidate();
            	noResult.repaint();
            	String NameType = searchByType.getText();
            	String NameCard = searchByName.getText();
            	Card cardSearch = Deck.searchCard(NameType, NameCard);
            	if(cardSearch != null) {
            		index = Deck.getIndex(cardSearch);
        			n.setText(Deck.getCard(index).getName());
        			h.setText(Deck.getCard(index).getHealthPoints());
        			des.setText(Deck.getCard(index).getDescription());
            	}
            	else {
            		pnsearch.add(noResult);
            		pnsearch.revalidate();
            		pnsearch.repaint();
            	}
            }
        });
        
        //Switch Card 
		JPanel pnSwitch = new JPanel(new GridLayout(1, 2));
		JButton prd = new JButton("  < previous  ");
		JButton nxt = new JButton("     Next >   ");
		prd.addActionListener(new PrescCard());
		nxt.addActionListener(new NextCard());
		pnSwitch.add(prd,BorderLayout.WEST);
		pnSwitch.add(nxt, BorderLayout.EAST);
		
		//cardList = new JList<>(listCard.toArray(new String[0]));
		//displayCardList = new JScrollPane(cardList);
        
		window.setLayout(new FlowLayout());
		window.add(title);
		window.add(pnsearch);
		window.add(pnCard);
		//window.add(displayCardList);
		window.add(pnSwitch);
		window.add(pnMenu);
		window.setResizable(false);
		window.setVisible(true);
		
	}
	

	static class NextCard implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(index < Deck.getCards().size()-1) {
			index++;
			n.setText(Deck.getCard(index).getName());
			h.setText(Deck.getCard(index).getHealthPoints());
			des.setText(Deck.getCard(index).getDescription());
		}
	}
	}
	static class PrescCard implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(index > 0) {
			index--;
			n.setText(Deck.getCard(index).getName());
			h.setText(Deck.getCard(index).getHealthPoints());
			des.setText(Deck.getCard(index).getDescription());
			}
		}
	}
	static class AddNewCard implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            JFrame windowNewCard = new JFrame("Add New Card");
	            windowNewCard.setSize(450, 200);
	 
	            JPanel typePanel = new JPanel();
	            JPanel namePanel = new JPanel();
	            JPanel descriptionPanel = new JPanel();
	            JPanel healthPointsPanel = new JPanel();
	            JPanel addButtonPanel = new JPanel();
	 
	            JLabel typeLabel = new JLabel("Type :");
	            JTextField typeTextField = new JTextField();
	            typeTextField.setPreferredSize(new Dimension(100, 20));
	            typePanel.add(typeLabel);
	            typePanel.add(typeTextField);
	            
	            JLabel nameLabel = new JLabel("Name :");
	            JTextField nameTextField = new JTextField();
	            nameTextField.setPreferredSize(new Dimension(100, 20));
	            namePanel.add(nameLabel);
	            namePanel.add(nameTextField);
	 
           
	            JLabel descriptionLabel = new JLabel("Description :");
	            JTextField descriptionTextField = new JTextField();
	            descriptionTextField.setPreferredSize(new Dimension(100, 20));
	            descriptionPanel.add(descriptionLabel);
	            descriptionPanel.add(descriptionTextField);
	 
		        JLabel healthPointsLabel = new JLabel("Health Points :");
		        JTextField healthPointsTextField = new JTextField();
		        healthPointsTextField.setPreferredSize(new Dimension(100, 20));
		        healthPointsPanel.add(healthPointsLabel);
		        healthPointsPanel.add(healthPointsTextField);
		        
	            JButton addActionButton = new JButton("Add Card");
	            addButtonPanel.add(addActionButton);
	 
	            windowNewCard.setLayout(new FlowLayout());
	            windowNewCard.add(namePanel);
	            windowNewCard.add(healthPointsPanel);
	            windowNewCard.add(typePanel);
	            windowNewCard.add(descriptionPanel);
	            windowNewCard.add(addButtonPanel);
	 
	            windowNewCard.setVisible(true);
	 
	            addActionButton.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent event) {
	                    String nameText = nameTextField.getText();
	                    String healthPointsText = healthPointsTextField.getText();
	                    String typeText = typeTextField.getText();
	                    String descriptionText = descriptionTextField.getText();
	                    Deck.addCard(new Card(nameText, typeText, descriptionText, healthPointsText));
	                    System.out.println("--------Deck size :"+Deck.getCards().size());
	                    System.out.println("Name :"+nameText);
	                    System.out.println("Health Points :"+healthPointsText);
	                    System.out.println("Type :"+typeText);
	                    System.out.println("Description :"+descriptionText);
	                    windowNewCard.setVisible(false);
	                   // listCard.add(nameText);
	                   // cardList = new JList<>(listCard.toArray(new String[0]));
	                   // cardList = new JList<String>(listCard.toArray(new String[0]));
	                    window.revalidate();
	                    window.repaint();
	          }
	     });
	 }
	}
	
	public static class EditCard implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame windowUpdateCard = new JFrame("Update Card");
            windowUpdateCard.setSize(450, 200);
 
            JPanel typePanel = new JPanel();
            JPanel namePanel = new JPanel();
            JPanel descriptionPanel = new JPanel();
            JPanel healthPointsPanel = new JPanel();
            JPanel updateButtonPanel = new JPanel();
 
            JLabel nameLabel = new JLabel("Name :");
            JTextField nameTextField = new JTextField(Deck.getCard(index).getName());
            nameTextField.setPreferredSize(new Dimension(100, 20));
            namePanel.add(nameLabel);
            namePanel.add(nameTextField);
 
            JLabel typeLabel = new JLabel("Type :");
            JTextField typeTextField = new JTextField(Deck.getCard(index).getType());
            typeTextField.setPreferredSize(new Dimension(100, 20));
            typePanel.add(typeLabel);
            typePanel.add(typeTextField);
 
            JLabel descriptionLabel = new JLabel("Description :");
            JTextField descriptionTextField = new JTextField(Deck.getCard(index).getDescription());
            descriptionTextField.setPreferredSize(new Dimension(100, 20));
            descriptionPanel.add(descriptionLabel);
            descriptionPanel.add(descriptionTextField);
 
            
            JLabel healthPointsLabel = new JLabel("Health Points :");
            JTextField healthPointsTextField = new JTextField(Deck.getCard(index).getHealthPoints());
            healthPointsTextField.setPreferredSize(new Dimension(100, 20));
            healthPointsPanel.add(healthPointsLabel);
            healthPointsPanel.add(healthPointsTextField);
            
            JButton updateActionButton = new JButton("Update Card");
            updateButtonPanel.add(updateActionButton);
 
            windowUpdateCard.setLayout(new FlowLayout());
            windowUpdateCard.add(namePanel);
            windowUpdateCard.add(typePanel);
            windowUpdateCard.add(descriptionPanel);
            windowUpdateCard.add(healthPointsPanel);
            windowUpdateCard.add(updateButtonPanel);
 
            updateActionButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    String typeText = typeTextField.getText();
                    String nameText = nameTextField.getText();
                    String descriptionText = descriptionTextField.getText();
                    String healthPointsText = healthPointsTextField.getText();
                    Deck.editCard(index, new Card(typeText, nameText, descriptionText, healthPointsText));
                    window.revalidate();
                    window.repaint();
 
                    windowUpdateCard.setVisible(false);
                }
            });
            windowUpdateCard.setVisible(true);
        }
    }

	
	static class RemoveCard implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Deck.removeCard(index);
		}		
	}
}
