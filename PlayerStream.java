import java.util.*;

public class PlayerStream extends Stream{

    public int bet;

    public PlayerStream(int bet){
        this.cards = new ArrayList<Card>();
        this.bet = bet;
    }

    public boolean checkSame() {
        if (cards.size() == 2 && cards.get(0).power == cards.get(1).power){
            return true;
        }
        return false;

    }

}
