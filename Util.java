public class Util {
    public static void swapCards(Deck deck, int h, int i){
        Card temp = deck.cards[h];
        deck.cards[h] = deck.cards[i];
        deck.cards[i] = temp;
    }

    public static int intListSum(int[] array){
        int sum = 0;
        for (int h = 0; h < array.length; h++){
            sum += array[h];
        }
        return sum;
    }

}
