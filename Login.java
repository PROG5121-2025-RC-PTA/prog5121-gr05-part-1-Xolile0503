
package poe1;


import java.util.Scanner;

public class Login {
    //global variables
     static String username;
     static String passW;
     static String name;
     static String surname;
     static int phoneNum;
     
     static Scanner scanner = new Scanner (System.in);
     
     
//sets
    public static void setUsername(String username) {
        Login.username = username;
    }

    public static void setPassW(String passW) {
        Login.passW = passW;
    }
    
    
//contructor
    public Login() {
    }


    
     
    public static void mainData() 
    {
      details();
      checkUser();
      checkPassword();
      loginF();
    }
     
     
    
    public static void details(){         
        System.out.print("Please enter name: ");
        name =  scanner.nextLine();
        
        System.out.print("Enter surname: ");
        surname = scanner.nextLine();
        
        while(true){
        System.out.print("Enter phone number: +27 ");
        String input = scanner.nextLine();
        
        if (input.matches("\\d{9}")){
            phoneNum = Integer.parseInt(input);
            System.out.println("Cellphone number succesfully added: +27" + input);
            break;
        }else{
            System.out.println("Cellphone number incorrectly formatted or does not contain international code.");
        }
        }
    }
     
    
    
//username    
    public static boolean checkUser()
    {
       System.out.print("Enter username: ");
        username = scanner.nextLine();

        if (username.contains("_") && username.length() <= 5) {
            System.out.println("Username successfully captured. ");
            return true;
        } else {
            System.out.println("Username is not correctly formatted.\n"
                    + "Please ensure that your username contains an underscore\n"
                    + "and is no more than 5 characters in length.");
         
            //will repeat if requirements for username are incorrect
            checkUser();
            return false;
        }
              
    }
      
      
      
//password
    public static boolean checkPassword()
    {
               
             boolean pass;
             System.out.print("Enter password: ");
             
             passW = scanner.nextLine();
      
             
               if((passW.length () >=8) && passW.matches(".*[A-Z].*") && passW.matches(".*\\d.*") && passW.matches(".*[!@#$%^&*()-+=_].*"))
               {
               
                      //true password
                   System.out.println("Password succesfully captured. ");
                   return true;
          
               }else{
                   
                      //false password
                      pass = false;
                      System.out.println( "Password is not correctly formatted, please ensure that the password contains at least: "
                                        + "\n-8 characters "
                                        + "\n-a capital letter "
                                        + "\n-a number "
                                        + "\n-and a special character");
           
                      checkPassword();
                    }
       return pass;
    }      
      
     
           
//login
    public static void loginF()
    {
         System.out.println("You have succesfully registered, enter your username to login: ");
         String user2 = scanner.nextLine();
         
         System.out.println("Enter your password to login: "); 
         String password2 = scanner.nextLine();
            
            if(user2.equals(username) && password2.equals(passW))
{
        System.out.println(" Welcome " + name + " " + surname 
                        + ", it is great to see you again.");
            return;
            }else{
                     
                System.out.println( "Username or password incorrect"
                                  + "\nPlease try again");
                
                loginF();
                 }//else
    }//loginF

}
