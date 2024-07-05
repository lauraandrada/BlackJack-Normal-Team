import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private int credit ;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.credit = 600;

    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit  += credit;
    }

    public String getName() {
        return name;
    }

    public void addToHand(Card card) {
        this.hand.add(card);

    }

    public void checkHand() {
        if (name.equals("Dealer") && hand.size() <3) {
            System.out.println(name + " 's cards are " + hand.get(0) + " ?");
        }else
        System.out.println(name + " 's cards are " + this.hand + " sum of " + getSum());
    }


    public int getSum() {

        int sum = 0;

        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getValue() > 11) {
                sum += 10;
            } else sum += hand.get(i).getValue();
        }

        sum = checkForAcesReturnSum(sum);
//        System.out.println("Suma va fi: " + sum);
        return sum;
    }

    public int checkForAcesReturnSum(int sum) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getValue() == 11 && sum > 21) {
                sum -= 10;
            }
        }
        return sum;
    }

}
