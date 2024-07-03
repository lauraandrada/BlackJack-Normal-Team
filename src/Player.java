import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private int credit;

    public Player(String name, List<Card> hand){
        this.name = name;
        this.hand = hand;
        this.credit = 500;
        
    }
}
