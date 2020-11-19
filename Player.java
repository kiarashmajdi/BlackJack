import java.util.*;

public class Player {

    private String name;
    private ArrayList<Card> cards;

    public Player(String name) {

    }

    public void split() {

    }

    public void doubl() {

    }

    public void hit() {

    }

    public boolean checkBurn() {
        int totalPower = powerSum();
        while (totalPower < 21){
            boolean isChanged = false;
            for (int h = 0; h < this.cards.size(); h++){
                if (this.cards.get(h).power == 11){
                    this.cards.get(h).power = 1;
                    totalPower -= 10;
                    isChanged = true;
                    break;
                }
            }
            if (!isChanged){
                return true;
            }

        }
        return false;
    }

    public void checkSame() {
        if ()

    }

    public void checkBlackJack() {
        if (powerSum() == 21){
            return true;
        }
        return false;
    }

    public int powerSum() {
        int sum = 0;
        for (int h = 0; h < this.cards.size(); h++) {
            sum += this.cards.get(h).power;
        }
        return sum;
    }

    public int[] powerArray(){
        for
    }
}
