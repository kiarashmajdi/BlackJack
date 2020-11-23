public class Card {
    public int power;
    public int number;
    public int coat;

    public String[] numbers = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    public String[] suits = {"C", "D", "S", "H"};
    
    public Card(int number, int coat){
        this.number = number;
        this.coat = coat;
        this.setPower();
    }


    public String toString(){
        return (numbers[this.number - 1] + " " + suits[this.coat - 1]);
    }

    public void setPower(){
        try{
            this.power = Integer.parseInt(numbers[this.number - 1]);
        }

        catch (Exception e){
            if (this.number == 13){
                this.power = 11;
            }
            else{
                this.power = 10;
            }
        }
    }
}
