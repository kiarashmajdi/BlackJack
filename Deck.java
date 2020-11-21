import java.util.*;

public class Deck {
    public static Card[] cards = new Card[52];
    private static int cardPointer = 0;

    

    public Deck(){
        this.deck();
        this.shuffle();
    }

    public void deck(){
        for (int h = 1; h < 14; h++){
            for (int i = 1; i < 5; i++){
                cards[(h-1)*4 + i-1] = new Card(h, i);
            }
        }
        
    }

    public void shuffle(){
        Random rand = new Random();
        for (int h = 0; h < 1000; h++){
            Util.swapCards(this, rand.nextInt(52), rand.nextInt(52));
        }
        
    }

    public Card nextCard(){
        cardPointer++;
        return cards[cardPointer - 1];
    }

}
