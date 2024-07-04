import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Deck {
    public List<Card> deck = new ArrayList<>();
    private int[] cards = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    private String[] types = {"trefla", "romb", "Inima", "Neagra"};


    public void initializeDeck(){

        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < cards.length; j++) {
                deck.add(new Card(cards[j], types[i]));
                // System.out.println(deck.get(j).toString());
            }
        }
        Collections.shuffle(deck);
//        System.out.println(deck.toString());
    }

    public Card removeCardFromDeck(Deck deck){
      return   this.deck.remove(deck.deck.size()-1);
    }


}
