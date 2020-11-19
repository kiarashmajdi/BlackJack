public class Util {
    public static void swapCards(Deck deck, int h, int i){
        Card temp = deck.cards[h];
        deck.cards[h] = deck.cards[i];
        deck.cards[i] = temp;
    }
}
