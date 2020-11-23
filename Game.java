import java.util.*;
import java.io.*;

public class Game{
    public static Deck deck;
    public static Dealer dealer;
    public static ArrayList<Player> players;
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException{
        new Bank();

        players = new ArrayList<Player>();
        deck = new Deck();
        dealer = new Dealer();


        int event = 2;

        while (event != 2 || players.size() == 0){

            if (event == 0){
                players.add(new Player());
                event = -2;
            }
            else if (event == 1 && players.size() != 0){
                int user = 0;
                while (user <= 0){
                    System.out.println("Enter the number for the user that you want to sign out. \nTo return to the main menu, press Enter having anything else in this field.");
                    for (int h = 0; h < players.size(); h++){
                        
                        System.out.println((h + 1) + " - " + players.get(h).pocket.user);
                    }
                    try{
                        user = scanner.nextInt();
                    }
                    catch (Exception e){
                        System.out.println("BUG2");
                        event = -2; 
                        break;
                    }

                    if (user < 0){
                        System.out.println("Choice invalid. Please retry.");
                    }
                    else{
                        try{
                            System.out.println("RemovingProcess");
                            players.remove(user - 1);
                            System.out.println("Removed!");
                            event = -2;
                            break;
                        }
                        catch(Exception e){
                            System.out.println("BUG");
                            user = -1;
                        }
                    }
                    
                }
            }

            else if (event == 1 && players.size() == 0){
                System.out.println("There are no players to sign out!");
                event = -2;
            }
            else if (event == 2 && players.size() != 0){
                System.out.println("Game started!");
                break;
            }
            else if (event == 2 && players.size() == 0){
                System.out.println("You need at least one player to start a game!");
                event = -2;

            }
            else{
                System.out.println("Try again, please choose one of the choices");
                event = -2;

            }
            System.out.println("0 - Add another player\n1 - Sign a current player out\n2 - Start a round with current player(s)");
            try{
                event = scanner.nextInt();
            }
            catch (Exception e){
                event = -2;
            }

        }
        
        
        while (1 == 1){
        
            for (int h = 0; h < players.size(); h++){
                int bet = 0;
                System.out.println(players.get(h).pocket.user + ", Please enter your bet amount!");
                while (bet <= 0){
                    bet = 0;
                    try{
                        bet = scanner.nextInt();
                        if (bet <= 0){
                            System.out.println("Amount not sufficient");
                        }
                        else if (bet > players.get(h).pocket.pocket){
                            System.out.println("Not sufficient amount in your pocket for this much bet.");
                            bet = 0;
                            
                        }
                    }

                    catch (Exception e){
                        System.out.println("Please enter a valid amount.");
                        scanner.nextLine();

                    }

                }
                
                players.get(h).init(bet);

            }

            for (int h = 0; h < players.size(); h++ ){
                System.out.println("Pass the device to " + players.get(h).pocket.user + " and press Enter!");
                scanner.nextLine();

                takeTurn(players.get(h));

            }


            System.out.println("Press Enter for The Dealer to start their turn!");
            new Scanner(System.in).nextLine();

            dealer.getBets();
            takeTurn(dealer);

            dealer.transactions();

            for (Player h: players){
                System.out.println(h.pocket.user + ": " + h.pocket.pocket);
            }


            Bank.updatePockets();
            Bank.structureToData();

            event = -2;

            while (event != 2 || players.size() == 0){

                if (event == 0){
                    players.add(new Player());
                    event = -2;
                }
                else if (event == 1 && players.size() != 0){
                    int user = 0;
                    while (user <= 0){
                        System.out.println("Enter the number for the user that you want to sign out. \nTo return to the main menu, press Enter having anything else in this field.");
                        for (int h = 0; h < players.size(); h++){
                            
                            System.out.println((h + 1) + " - " + players.get(h).pocket.user);
                        }
                        try{
                            user = scanner.nextInt();
                        }
                        catch (Exception e){
                            System.out.println("BUG2");
                            event = -2; 
                            break;
                        }
    
                        if (user < 0){
                            System.out.println("Choice invalid. Please retry.");
                        }
                        else{
                            try{
                                System.out.println("RemovingProcess");
                                players.remove(user - 1);
                                System.out.println("Removed!");
                                event = -2;
                                break;
                            }
                            catch(Exception e){
                                System.out.println("BUG");
                                user = -1;
                            }
                        }
                        
                    }
                }
    
                else if (event == 1 && players.size() == 0){
                    System.out.println("There are no players to sign out!");
                    event = -2;
                }
                else if (event == 2 && players.size() != 0){
                    System.out.println("Game started!");
                    break;
                }
                else if (event == 2 && players.size() == 0){
                    System.out.println("You need at least one player to start a game!");
                    event = -2;
    
                }
                else{
                    System.out.println("Try again, please choose one of the choices");
                    event = -2;
    
                }
                System.out.println("0 - Add another player\n1 - Sign a current player out\n2 - Start a round with current player(s)");
                try{
                    event = scanner.nextInt();
                }
                catch (Exception e){
                    event = -2;
                }
    
            }
            
        }

    }

    public static void takeTurn(Player player){

        

        System.out.println("Pass the device to " + player.name + " and press Enter!");
        new Scanner(System.in).nextLine();

        System.out.println(player.cardStreams.get(player.streamPointer).powerSum());


        while (player.hasNext()){
            
            for (int h = 0; h < player.cardStreams.get(player.streamPointer).cards.size(); h++){
                System.out.print("[" + player.cardStreams.get(player.streamPointer).cards.get(h).toString() + "] ");
            }
            System.out.println("");

            player.doTurn();
            try{
                System.out.println(player.cardStreams.get(player.streamPointer).powerSum());
            }
            catch (Exception e){
                System.out.println("Done");
            }
            
        }
    }


    public static void takeTurn(Dealer dealer){

        

        System.out.println("Dealer is starting their turn!");

        System.out.println(dealer.cardStream.powerSum());


        while (dealer.hasNext()){
            
            for (int h = 0; h < dealer.cardStream.cards.size(); h++){
                System.out.print("[" + dealer.cardStream.cards.get(h).toString() + "] ");
            }
            System.out.println("");

            dealer.doTurn();
            System.out.println(dealer.cardStream.powerSum());

            
            
        }
    }

    public static void endRound(){
        
    }
}