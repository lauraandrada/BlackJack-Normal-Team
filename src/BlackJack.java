import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJack {

    Deck deck = new Deck();
    Player andrei = new Player("Andrei" );
    Player dealer = new Player("Dealer");

    Player player2 = new Player("player2");

    Player player3 = new Player("player3");
    List<Player> players = new ArrayList<>();

    private void dealCards(Player player) {
        player.addToHand(deck.removeCardFromDeck(deck));
    }


    public void play() {
        deck.initializeDeck();
        // Am adaugat fiecarui player 2 carti
//        for (int i = 0; i < 2; i++) {
//            dealCards(andrei);
//            dealCards(dealer);
//        }
//        andrei.checkHand();
//        dealer.checkHand();

//        andrei.getSum();
        System.out.println("Hello and welcome to blackjack");
        players.add(andrei);
        players.add(player2);
        players.add(player3);
        players.add(dealer);

        for (Player player : players) {
            dealCards(player);
            dealCards(player);
            player.checkHand();
            player.getSum();

        }
        Scanner scanner = new Scanner(System.in);

        for (Player player : players) {
            int input;
            if (player.getName().equals("Andrei")) {
                do {
                    System.out.println("Would you like to hit or stay?  ");
                    System.out.println("1- Hit");
                    System.out.println("2- Stay");
                    input = scanner.nextInt();
                    if (input == 1) {
                        dealCards(player);
                        player.checkHand();
                    }
                } while (input == 1 && player.getSum() <= 21);
            }else
            while (player.getSum() <17){
                dealCards(player);
                player.checkHand();
            }
        }
        for (int i =0;i<3;i++){
            if (players.get(i).getSum() > dealer.getSum() && players.get(i).getSum() < 22) {
                System.out.println(players.get(i).getName() + " a castigat!");
            }else System.out.println("Dealerul a castigat");

        }
        // Dealer va da hit pana cand sumDealer > sumAndrei // sumDealer >21

//        andrei.getSum();


    }

}

