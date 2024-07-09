package utils;

import cards.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJack {

    Deck deck = new Deck();
    Player andrei = new Player("Andrei");
    Player dealer = new Player("Dealer");

    Player player2 = new Player("player2");

    Player player3 = new Player("player3");
    List<Player> players = new ArrayList<>();


    private void dealCards(Player player) {
        player.addToHand(deck.removeCardFromDeck());
    }


    public void play() {
        deck.initializeDeck();

        System.out.println("Hello and welcome to blackjack");
        players.add(andrei);
        players.add(player2);
        players.add(player3);
        players.add(dealer);

        for (Player player : players) {
            dealCards(player);
            dealCards(player);
            player.checkHand();
//            player.getSum();

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
            } else {
                while (player.getSum() < 17) {
                    dealCards(player);
                    player.checkHand();
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (players.get(i).getSum() > dealer.getSum() && players.get(i).getSum() < 22 && dealer.getSum() < 22) {//verifica pt <21
                players.get(i).setCredit(100);
                dealer.setCredit(-100);
                System.out.println(players.get(i).getName() + " a castigat!" + "cu valoarea " + players.get(i).getSum() + " are credit: " + players.get(i).getCredit());
            } else if (dealer.getSum() < 22) {
                dealer.setCredit(+100);
                players.get(i).setCredit(-100);
                System.out.println("Dealerul a castigat cu " + dealer.getSum() + " impotriva lui " + players.get(i).getName() + " are credit: " + dealer.getCredit());


            } else if (dealer.getSum() > 21 && players.get(i).getSum() <= 21) {
                players.get(i).setCredit(100);
                dealer.setCredit(-100);
                System.out.println(players.get(i).getName() + " a castigat!" + "cu valoarea " + players.get(i).getSum() + " are credit: " + players.get(i).getCredit());
            }
        }
    }
}

