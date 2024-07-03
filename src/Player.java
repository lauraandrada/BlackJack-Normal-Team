import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private int credit;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<>();
        this.credit = 500;
        
    }

    public void addToHand(Card card){
        this.hand.add(card);

    }

    public void checkHand(int i){
        System.out.println(this.hand.get(i));
    }

}
