import java.io.IOError;
import java.io.IOException;
import java.util.*;

public class Pocket {
    public String user = "defUser";
    public String pass = " ";
    public int pocket;

    public Pocket(boolean exists) throws IOException{
        
        if (exists){
            signin();
        }
        else{
            register();
        }
        
    }

    public boolean register() throws IOException{
        Scanner scanner = new Scanner(System.in);
        while (Bank.exists(this.user)){
            System.out.println("Enter a new username: (To sign in instead, leave empty and press Enter.)");
            this.user = scanner.nextLine();

            try{
                if (this.user.equals("")){
                    this.user = "defUser";
                    return signin();
                }
            }
            
            catch(Exception e){
                this.user = "defUser";
                return signin();
            }

            if (Bank.exists(this.user)){
                System.out.println("This username already exists.");
            }

        }

        while (this.pass.length() < 8){
            System.out.println("Enter a new Password (To sign in instead, leave empty and press Enter.)");
            this.pass = scanner.nextLine();

            try{
                if (this.pass.equals("")){
                    this.user = "defUser";
                    return signin();
                }
            }
            
            catch(Exception e){
                this.user = "defUser";
                return signin();
            }

            if (this.pass.length() < 8){
                System.out.println("This password is too short!");
            }
        }
        Bank.register(this);
        this.pocket = 500;
        return true;

    }

    public boolean signin() throws IOException{
        Scanner scanner = new Scanner(System.in);
       
        while (!Bank.authenticate(this)){
            boolean isSignedIn = false;
            System.out.println("Enter your username: (To register instead, leave empty and press Enter.)");
            this.user = scanner.nextLine();


            try{
                if (this.user.equals("")){
                    this.user = "defUser";
                    return register();
                }
            }
            
            catch(Exception e){
                this.user = "defUser";
                return register();
            }

            for (Player h: Game.players){
                if (h.pocket.user.equals(this.user)){
                    isSignedIn = true;
                }
            }

            if (isSignedIn){
                System.out.println("This user is already signed in!");
                continue;
            }


            System.out.println("Enter your password: (To register instead, leave empty and press Enter.)");
            this.pass = scanner.nextLine();

            try{
                if (this.pass.equals("")){
                    this.user = "defUser";
                    return register();
                }
            }
            
            catch(Exception e){
                this.user = "defUser";
                return register();
            }

            if (!Bank.authenticate(this)){
                System.out.println("Wrong Username / Password");
            }

            
        }

        return true;

    }


}
