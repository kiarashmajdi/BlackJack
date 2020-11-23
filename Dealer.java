public class Dealer extends User{
    
    public DealerStream cardStream;
    public int[] allBets;
    public int[] allPowers;
    public int currentPower;

    public int owe;
    public int gain;

    public Dealer() {
        this.name = "Dealer";
        cardStream = new DealerStream();
        cardStream.addCard(Game.deck.nextCard());
        cardStream.addCard(Game.deck.nextCard());


    }

    public boolean hasNext(){
        owe = 0;
        gain = 0;
        currentPower = cardStream.powerSum();

        if (currentPower > 21){
            currentPower = 0;
            return false;
        }
        else{
            
            for (int i = 0; i < allPowers.length; i++){
                if (currentPower > allPowers[i]){
                    gain += allBets[i];
                }
                else if (currentPower < allPowers[i]){
                    owe += allBets[i];
                }
            }

            if (gain < owe){
                return true;
            }
            else{
                return false;
            }

        }


        
    }

    public void transactions(){
        for (int h = 0; h < Game.players.size(); h++){
            for (int i = 0; i < Game.players.get(h).cardStreams.size(); i++){
                int pSum = Game.players.get(h).cardStreams.get(i).powerSum();
                if (pSum > 21){
                    pSum = 0;
                }
                if (pSum < currentPower){
                    Game.players.get(h).pocket.pocket -= Game.players.get(h).cardStreams.get(i).bet;
                }
                else if (pSum > currentPower){
                    Game.players.get(h).pocket.pocket += Game.players.get(h).cardStreams.get(i).bet;
                }
            }
            
        }
    }

    public void doTurn(){
        this.hit(Game.deck);
        this.cardStream.checkBurn();
    }

    public void hit(Deck deck) {
        cardStream.addCard(Game.deck.nextCard());

    }

    public void stop() {

    }

    public static int numOfStreams(){
        int streamCounter = 0;
        for (int h = 0; h < Game.players.size(); h++){
            streamCounter += Game.players.get(h).cardStreams.size();
        }
        return streamCounter;
    }

    public void getBets(){
        allPowers = new int[numOfStreams()];
        allBets = new int[numOfStreams()];

        int indexCounter = 0;
        for (int h = 0; h < Game.players.size(); h++){
            for (int i = 0; i < Game.players.get(h).cardStreams.size(); i++){
                if (21 < Game.players.get(h).cardStreams.get(i).powerSum()){
                    allBets[indexCounter] = Game.players.get(h).cardStreams.get(i).bet;
                    allPowers[indexCounter] = 0;
                    indexCounter += 1;
                }
                else{
                    allBets[indexCounter] = Game.players.get(h).cardStreams.get(i).bet;
                    allPowers[indexCounter] = Game.players.get(h).cardStreams.get(i).powerSum();
                    indexCounter += 1;
                }
                
                
            }   
        }
    }
}
