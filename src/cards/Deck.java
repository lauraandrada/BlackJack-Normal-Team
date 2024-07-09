package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    public List<Card> cards = new ArrayList<>();
    private int[] numbers = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    private String[] types = {"diamonds", "hearts", "clubs", "spades"};


    public void initializeDeck(){

        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                cards.add(new Card(numbers[j], types[i]));
            }
        }
        Collections.shuffle(cards);
    }

    public Card removeCardFromDeck(){
        Card card = this.cards.removeFirst();
        return card;
    }


}
