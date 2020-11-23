public class Util {
    public static void swap(Deck deck, int h, int i){
        Card temp = deck.cards[h];
        deck.cards[h] = deck.cards[i];
        deck.cards[i] = temp;
    }

    public static void swap(int[] array, int h, int i){
        int temp = array[h];
        array[h] = array[i];
        array[i] = temp;
    }

    public static int intListSum(int[] array){
        int sum = 0;
        for (int h = 0; h < array.length; h++){
            sum += array[h];
        }
        return sum;
    }

    public static void bubbleSortPowersAndBets(){
        for (int h = 0; h < Game.dealer.allPowers.length; h++){
            for (int j = h ; j < Game.dealer.allPowers.length - 1; j++){
                if (Game.dealer.allPowers[j] > Game.dealer.allPowers[j+1]){
                    swap(Game.dealer.allPowers, j, j + 1);
                    swap(Game.dealer.allBets, j, j + 1);
                }
            }
        }
    }

}
