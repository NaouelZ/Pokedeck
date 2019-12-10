import java.io.Serializable;
import java.util.ArrayList;

public class Pokedeck implements Serializable {
	private static final long serialVersionUID = 1L;
	ArrayList<Card> deck = new ArrayList<Card>();
	
	public Pokedeck(){
		this.deck = new ArrayList<Card>();
	}
	
	public ArrayList<Card> getCards() {
		return this.deck;
	}
	
	public Card getCard(int index) {
		return deck.get(index);
	}
	
	public void addCard(Card newCard) {
		deck.add(newCard);
	}
	
	public void editCard(int index, Card newCard) {
		deck.set(index, newCard);
	}
	
	public void removeCard(int index) {
		deck.remove(index);
	}

	public Card searchCard(String type, String name) {
		for(int i = 0; i < deck.size(); i++) {
			Card theCard = deck.get(i);
			String cardType = theCard.getType();
			String cardName = theCard.getName();
			if(type.equals(cardType)) {
				if(name.equals(cardName)) {
					return theCard;
				}
			}
		}
		return null;
	}
	
	public int getIndex(Card cardIndex) {
		return deck.indexOf(cardIndex);
	}
	
}
