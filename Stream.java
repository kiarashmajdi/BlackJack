import java.util.*;

public class Stream {
    public ArrayList<Card> cards;
    private int[] powerList;
    public boolean isStarted = false;
    public boolean isFinalized = false;


    public Stream(){
        this.cards = new ArrayList<Card>();
    }

    public void addCard(Card card){
        cards.add(card);
        powerArray();
    }
    


    public void powerArray(){
        this.powerList = new int[cards.size()];
        for (int h = 0; h < this.cards.size(); h++) {
            powerList[h] = this.cards.get(h).power;
        }
    }

    

    public void checkBurn() {
        int totalPower = powerSum();
        while (totalPower > 21){
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
                isFinalized = true;
                break;
            }

        }
    }


    public void checkBlackJack() {
        if (powerSum() == 21){
            isFinalized = true;
        }
    }

    public int powerSum() {
        int sum = 0;
        for (int h = 0; h < cards.size(); h++){
            sum += cards.get(h).power;
        }
        return sum;
    }
}
