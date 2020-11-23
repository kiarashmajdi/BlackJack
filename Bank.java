import java.io.*;
import java.util.*;

public class Bank {
    public static ArrayList<String[]> userPass;
    
    
    public Bank() throws IOException{
        dataToStructure();
        System.out.println(userPass.get(0)[0] + " " + userPass.get(0)[1] + " " + userPass.get(0)[2]);
    }



    public static void dataToStructure() throws IOException{
        Scanner reader = new Scanner(new File("bank.csv"));
        userPass = new ArrayList<String[]>();

        while (reader.hasNextLine()){
            String line = reader.nextLine();
            userPass.add(line.split(","));
        }
    }

    public static void structureToData() throws IOException{
        File file = new File("bank.csv");
        file.createNewFile();
        PrintWriter pw = new PrintWriter(file);
        for (int h = 0; h < userPass.size(); h++){
            pw.println(userPass.get(h)[0] + "," + userPass.get(h)[1] + "," + userPass.get(h)[2]);
            
        }
        pw.close();
    }

    public static int binarySearch(String user, int left, int right){

        while (left <= right){
            int mid = left + (right - left) / 2;

            if (userPass.get(mid)[0].equals(user)){
                return mid;
            }
            if (userPass.get(mid)[0].compareTo(user) < 0){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        return -1;

    }

    public static boolean authenticate(Pocket pocket){
        int loc = binarySearch(pocket.user, 0, userPass.size() - 1);
        if  (loc == -1){
            return false;
        }
        
        if (!pocket.pass.equals(userPass.get(loc)[1])){
            return false;
        }

        pocket.pocket = Integer.parseInt(userPass.get(loc)[2]);

        return true;

    }

    public static boolean exists(String user){
        if (binarySearch(user, 0, userPass.size() - 1) == -1){
            return false;
        }
       
        return true;

        

    }


    public static void register(Pocket pocket) throws IOException{
        String[] newLine = new String[]{pocket.user, pocket.pass, "500"};
        for (int h = 0; h < userPass.size(); h++){
            if (pocket.user.compareTo(userPass.get(h)[0]) < 0){
                userPass.add(h, newLine);
                break;
            }
        } 
        structureToData();
    }

    public static void updatePockets(){
        for (int h = 0; h < Game.players.size(); h++){
            int loc = binarySearch(Game.players.get(h).pocket.user, 0, userPass.size() - 1);
            userPass.get(loc)[2] =  Integer.toString(Game.players.get(h).pocket.pocket);
        }
    }
}