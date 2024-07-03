public class BlackJack {

    Deck deck = new Deck();
    Player andrei = new Player("Andrei" );
    Player dealer = new Player("Dealer");
    public void play(){
        deck.initializeDeck();
        // Am adaugat fiecarui player 2 carti
        andrei.addToHand(deck.deck.remove(deck.deck.size()-1));
        andrei.addToHand(deck.deck.remove(deck.deck.size()-1));
        andrei.checkHand(0);
        andrei.checkHand(1);



//        for (int i=0; i<2;i++){
//            dealer.checkHand();
//
//        }

    }
}
