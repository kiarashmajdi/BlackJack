import java.io.IOException;
import java.util.*;

public class Player extends User{

    public int initBet;
    public ArrayList<PlayerStream> cardStreams;
    public int streamPointer;
    public Pocket pocket;


    public Player() throws IOException{
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("1 - Register");
        System.out.println("2 - Sign in");
        while (choice != 1 && choice != 2){

            try{
                choice = scanner.nextInt();
            }

            catch (Exception e){
                System.out.println("Wrong format input.");
                scanner.nextLine();
            }

            if (choice != 1 && choice != 2){
                System.out.println("Choice not available.");
            }
            
        }
        if (choice == 1){
            this.pocket = new Pocket(false);
        }
        else{
            this.pocket = new Pocket(true);
        }
    }
    public void init(int bet){
        this.initBet = bet;
        this.cardStreams = new ArrayList<PlayerStream>();
        this.cardStreams.add(new PlayerStream(this.initBet));
        this.cardStreams.get(0).addCard(Game.deck.nextCard());
        this.cardStreams.get(0).addCard(Game.deck.nextCard());
    }

    public boolean hasNext(){

        if (streamPointer < cardStreams.size() && cardStreams.get(streamPointer).powerSum() == 21){
            return false;
        }
        if (streamPointer >= cardStreams.size()){
            return false;
        }
        for (int h = 0; h < cardStreams.size(); h++){
            if (!cardStreams.get(h).isFinalized){
                return true;
            }
        }
        return false;
    }

    public void doTurn(){
        if (cardStreams.get(streamPointer).isFinalized){
            streamPointer ++;
        }
        else{
            System.out.println("1 - Hit");
            if (!cardStreams.get(streamPointer).isStarted && cardStreams.size() == 1 && (2 * initBet < pocket.pocket)){
                System.out.println("2 - Double");
            }
            if (cardStreams.get(streamPointer).checkSame() && (2 * initBet < pocket.pocket)){
                System.out.println("3 - Split");
            }
            System.out.println("0 - Stop");
            try{
                int choice = new Scanner(System.in).nextInt();
                if (choice == 0){
                    this.stop();
                }
                else if (choice == 1){
                    this.hit(Game.deck);
                    cardStreams.get(streamPointer).checkBurn();
                    cardStreams.get(streamPointer).checkBlackJack();
                }
                else if (choice == 2 && !cardStreams.get(streamPointer).isStarted && cardStreams.size() == 1 && (2 * initBet < pocket.pocket)){
                    this.doubl(Game.deck);
                    cardStreams.get(streamPointer).checkBurn();
                    cardStreams.get(streamPointer).checkBlackJack();
                }
                else if (choice == 3 && cardStreams.get(streamPointer).checkSame() && (2 * initBet < pocket.pocket)){
                    this.split();
                }
                else{
                    System.out.println("Invalid Operation; Retry using the given options!");
                }
            }

            catch (Exception e){
                System.out.println("Invalid Operation; Retry using the given options!");
            }
        }

    }

    public void split() {
        if (cardStreams.get(streamPointer).checkSame()){
            cardStreams.add(streamPointer + 1, new PlayerStream(initBet));
            cardStreams.add(streamPointer + 1, new PlayerStream(initBet));
            
            cardStreams.get(streamPointer+1).addCard(cardStreams.get(streamPointer).cards.get(0));
            cardStreams.get(streamPointer+2).addCard(cardStreams.get(streamPointer).cards.get(1));
            cardStreams.remove(streamPointer);
            cardStreams.get(streamPointer).isStarted = true;
        }
    }

    public void doubl(Deck deck) {
        cardStreams.get(streamPointer).addCard(deck.nextCard());
        cardStreams.get(streamPointer).bet *= 2;
        cardStreams.get(streamPointer).isStarted = true;

    }

    public void hit(Deck deck) {
        cardStreams.get(streamPointer).addCard(deck.nextCard());
        cardStreams.get(streamPointer).isStarted = true;
    }

    public void stop() {
        cardStreams.get(streamPointer).isFinalized = true;
        cardStreams.get(streamPointer).isStarted = true;

        streamPointer += 1;
        
    }

    
}
