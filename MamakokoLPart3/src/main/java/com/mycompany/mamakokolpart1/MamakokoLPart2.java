/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mamakokolpart1;

/**
 *
 * @author lehlogonolo
 */
import java.util.Scanner;
public class MamakokoLPart1 {

    public static void main(String[] args) {
        //Declarations
        String firstName;
        String lastName;
        String userName;
        String password;
        String cellPhoneNumber;
        String registerUserName;
        String registerPassword;

        Scanner input = new Scanner(System.in);

        // REGISTRATION

        // Ask user for first name
        System.out.print("Please enter your first name: ");
        firstName = input.nextLine();

        // Ask user for last name
        System.out.print("Please enter your last name: ");
        lastName = input.nextLine();

        // Ask user for username
        System.out.print("Please enter your username: ");
        userName = input.nextLine();

        // Check username
        while (!Login.checkUserName(userName)) {

            System.out.println("Username is not correctly formatted; "
                    + "please ensure that your username contains an "
                    + "underscore and is no more than five characters "
                    + "in length.");

            System.out.print("Please enter your username again: ");
            userName = input.nextLine();
        }

        System.out.println("Username successfully captured.");

        // Ask user for password
        System.out.print("Please enter your password: ");
        password = input.nextLine();

        // Check password
        while (!Login.checkPasswordComplexity(password)) {

            System.out.println("Password is not correctly formatted; "
                    + "please ensure that the password contains at "
                    + "least eight characters, a capital letter, "
                    + "a number, and a special character");

            System.out.print("Please enter your password again: ");
            password = input.nextLine();
        }

        System.out.println("Password successfully captured.");

        // Ask user for cellphone number
        System.out.print("Please enter your cellphone Number: ");
        cellPhoneNumber = input.nextLine();

        // Check cellphone number
        while (!Login.checkCellPhoneNumber(cellPhoneNumber)) {
            System.out.println(
                    "Cell number is incorrectly formatted or does "
                    + "not contain an international code; please "
                    + "correct the number and try again."
            );
            System.out.print("Please enter your cellphone Number again: ");
            cellPhoneNumber = input.nextLine();
        }

        System.out.println("Cell number successfully captured.");

        // Registration method
        System.out.println(
                Login.registerUser(
                        userName,
                        password,
                        cellPhoneNumber ));

        // LOGIN

        // Ask for username
        System.out.print("Please enter your username: ");
        registerUserName = input.nextLine();

        // Ask for password
        System.out.print("Please enter your password: ");
        registerPassword = input.nextLine();

        // Check login
        boolean loginSuccessful =
                Login.loginUser(
                        registerUserName,
                        registerPassword,
                        userName,
                        password);

        // Display login status
        System.out.println(
                Login.returnLoginStatus(
                        loginSuccessful,
                        firstName,
                        lastName) );

        input.close();
    }
}

public class Login {

    //Check username
    public static boolean checkUserName(String userName) {
        if (userName.contains("_")
                && userName.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

    //  Check password complexity
    public static boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        int index = 0;

        while (index < password.length()) {
            char current = password.charAt(index);
            if (Character.isUpperCase(current)) {
                hasCapitalLetter = true;
            } else if (Character.isDigit(current)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(current)) {
                hasSpecialCharacter = true;
            }

            index++;
        }

        if (hasCapitalLetter
                && hasNumber
                && hasSpecialCharacter) {
            return true;
        } else {
            return false;
        }
    }

    // Check cellphone number
    public static boolean checkCellPhoneNumber(
            String cellPhoneNumber) {

        if (cellPhoneNumber.matches("\\+27[0-9]{9}")) {
            return true;
        } else {
            return false;
        }
    }

    // Registration method
    public static String registerUser(
            String userName,
            String password,
            String cellPhoneNumber) {

        if (!checkUserName(userName)) {

            return "Username is not correctly formatted; "
                    + "please ensure that your username contains an "
                    + "underscore and is no more than five characters "
                    + "in length.";

        } else if (!checkPasswordComplexity(password)) {

            return "Password is not correctly formatted; "
                    + "please ensure that the password contains at "
                    + "least eight characters, a capital letter, "
                    + "a number, and a special character.";

        } else if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell number is incorrectly formatted or does "
                    + "not contain an international code; please "
                    + "correct the number and try again.";
        } else {
            return "User registered successfully.";
        }
    }

    // Login method
    public static boolean loginUser(
            String enteredUserName,
            String enteredPassword,
            String userName,
            String password) {
        
        if (enteredUserName.equals(userName)
                && enteredPassword.equals(password)) {

            return true;
        } else {
            return false;
        }
    }

    // Return login status
    public static String returnLoginStatus(
            boolean login,
            String firstName,
            String lastName) {

        if (login) {
            return "Welcome " + firstName + ", "+ lastName + " it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    
        
    // QUICKCHAT PART 2
       if (loginSuccessful) {
           Scanner input = new Scanner (System.in);

        System.out.println();
        System.out.println("Welcome to QuickChat.");
            int menuChoice = 0;

        while (menuChoice != 3) {

        System.out.println();
        System.out.println("1. Send Messages");
        System.out.println("2. Show recently sent messages");
        System.out.println("3. Quit");
        System.out.print("Please choose an option: ");
        menuChoice = input.nextInt();
                

    // SEND MESSAGES
        if (menuChoice == 1) {
            
        System.out.print("How many messages would you like to send? ");
        int numberOfMessages = input.nextInt();
        int messageNumber = 0;

        while (messageNumber < numberOfMessages) {
        System.out.println();
        System.out.println("Message " + (messageNumber + 1));

    // Generate Message ID
        String messageID = Message.generateMessageID();
        System.out.println("Message ID generated: "+ messageID);

     // Recipient
        System.out.print( "Enter recipient cellphone number: ");
        String recipientCell =input.nextLine();
         Message temporaryMessage =new Message(messageID,messageNumber,recipientCell,"");

     // Check recipient
        while (!temporaryMessage.checkRecipientCell(recipientCell)) {
        System.out.println("Cell phone number is incorrectly "+ "formatted or does not contain "
                            + "an international code.");
        
        System.out.print("Enter recipient cellphone number "+ "again: ");
         recipientCell = input.nextLine();
                        }

    // Message
        System.out.print( "Enter your message: ");
        String message =input.nextLine();

    // Check message length
        String messageCheck = Message.checkMessageLength(message);

        while (!messageCheck.equals("Message ready to send.")) {
         System.out.println(messageCheck);
         System.out.print("Please enter your message again: ");
         message = input.nextLine();
         messageCheck = Message.checkMessageLength(message);
    }
        System.out.println(messageCheck);

    // Create message object
         Message newMessage =new Message(messageID,messageNumber,recipientCell, message);

   // Create message hash
     String messageHash =newMessage.createMessageHash(messageID,messageNumber,message);
      newMessage.setMessageHash(messageHash);
     System.out.println("Message Hash: "+ newMessage.getMessageHash());

    // Message options
       System.out.println();
       System.out.println("1. Send Message");
       System.out.println("2. Disregard Message");
       System.out.println("3. Store Message");
       System.out.print( "Please choose an option: ");

         int messageOption = input.nextInt();
         

    // Send message
    String result =newMessage.sentMessage( messageOption);
        System.out.println(result);

    // Display sent message
        if (messageOption == 1) {

       System.out.println();
       System.out.println("Message ID: "+ newMessage.getMessageID());
       System.out.println("Message Hash: "+ newMessage.getMessageHash());
       System.out.println("Recipient: " + newMessage.getRecipient());
       System.out.println("Message: "+ newMessage.getMessage()); messageNumber++;

       } else if (messageOption == 3) {
                            messageNumber++;

        } else if (messageOption == 2) {

        System.out.print("Press 0 to delete the message: ");
         int deleteMessage =input.nextInt();

        if (deleteMessage == 0) {

        System.out.println("Message deleted."); messageNumber++;
                }
            }
         }

   // SHOW RECENTLY SENT MESSAGES
    } else if (menuChoice == 2) {
        System.out.println();
        System.out.println("Coming Soon.");

    // QUIT
    } else if (menuChoice == 3) {
         System.out.println();
         System.out.println("Thank you for using QuickChat.");

    } else {
        System.out.println("Invalid option. Please try again.");
            }
        }

   // Display total messages
        System.out.println();
        System.out.println("Total messages sent: "
                    + Message.returnTotalMessagess());

    } else {
        System.out.println();
        System.out.println("You cannot use QuickChat because your "
                    + "login was unsuccessful.");
        }

        input.close();
    }
}
        
        

    



    
