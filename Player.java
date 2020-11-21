import java.util.*;

public class Player {
    private String name;
    public int initBet;
    private ArrayList<Stream> cardStreams;
    private int streamPointer;

    public int turnsDone = 0;

    public Player(String name, int bet) {
        this.name = name;
        this.initBet = bet;
    }

    public boolean hasNext(){
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
            if (!cardStreams.get(streamPointer).isStarted){
                System.out.println("2 - Double");
                if (cardStreams.get(streamPointer).checkSame()){
                    System.out.println("3 - Split");
                }
            }
            int choice = new Scanner(System.in).nextInt();
            if (choice == 0){
                this.stop();
            }
            else if (choice == 1){
                this.hit(Game.deck);
            }
            else if (choice == 2 && !cardStreams.get(streamPointer).isStarted){
                this.doubl(Game.deck);
            }
            else if (choice == 3 && !cardStreams.get(streamPointer).isStarted && cardStreams.get(streamPointer).checkSame()){
                this.split();
            }
            else{
                System.out.println("Invalid Operation; Retry using the given options!");
            }
        }
    }

    public void split() {
        if (cardStreams.get(streamPointer).checkSame()){
            cardStreams.add(streamPointer + 1, new Stream(initBet));
            cardStreams.add(streamPointer + 1, new Stream(initBet));
            
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
