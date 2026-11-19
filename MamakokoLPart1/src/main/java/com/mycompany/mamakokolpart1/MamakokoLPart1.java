
package com.mycompany.mamakokolpart1;

import java.util.Scanner;

public class MamakokoLPart1 {

    public static void main(String[] args) {

        // Declarations
        String firstName;
        String lastName;
        String userName;
        String password;
        String cellPhoneNumber;
        String registerUserName;
        String registerPassword;

        Scanner input = new Scanner(System.in);

        // Create a manager to keep track of messages
        MessageManager messageManager = new MessageManager();

        // REGISTRATION
        System.out.println("====================Registration==========================");

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
                    + "correct the number and try again.");

            System.out.print("Please enter your cellphone Number again: ");
            cellPhoneNumber = input.nextLine();
        }

        System.out.println("Cell number successfully captured.");

        // Registration method
        System.out.println(
                Login.registerUser(
                        userName,
                        password,
                        cellPhoneNumber));

        // LOGIN
        System.out.println();
        System.out.println("====================Login==========================");

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
                        lastName));

        // QUICKCHAT

        // Only users who logged in successfully can send messages
        if (loginSuccessful) {

            System.out.println();
            System.out.println("====================Quickchat App==========================");
            System.out.println("Welcome to QuickChat.");

            int messageNumber = 0;
            int menuOption;

            // Menu runs until the user selects quit
            do {
                System.out.println();
                System.out.println("Option 1) Send Messages");
                System.out.println("Option 2) Show recently sent messages");
                System.out.println("Option 3) Quit");
                System.out.print("Please enter an option: ");

                menuOption = readWholeNumber(input);

                switch (menuOption) {

                    case 1:

                        // Ask how many messages the user wants to send
                        System.out.println();
                        System.out.print("Please enter how many messages you want to send: ");
                        int numberOfMessages = readWholeNumber(input);

                        // Check number of messages
                        while (numberOfMessages <= 0) {

                            System.out.print("Please enter a number bigger than 0: ");
                            numberOfMessages = readWholeNumber(input);
                        }

                        // Enter the requested number of messages
                        for (int i = 0; i < numberOfMessages; i++) {

                            System.out.println();
                            System.out.println("===============Message "
                                    + (messageNumber + 1)
                                    + "===============");

                            // Enter one message
                            enterMessage(
                                    input,
                                    messageNumber,
                                    registerUserName,
                                    messageManager);

                            messageNumber++;
                        }

                        // Display total messages successfully sent
                        System.out.println();
                        System.out.println(
                                "Total number of messages sent: "
                                + messageManager.returnTotalMessages());

                        break;

                    case 2:

                        // Display recently sent messages
                        if (messageManager.returnTotalMessages() == 0) {

                            System.out.println("No messages have been sent yet.");

                        } else {

                            System.out.println();
                            System.out.println("===============Sent Messages===============");

                            System.out.println(messageManager.printMessages());
                        }

                        break;

                    case 3:

                        System.out.println(
                                "Total number of messages sent: "
                                + messageManager.returnTotalMessages());

                        System.out.println("Goodbye.");
                        break;

                    default:

                        System.out.println(
                                "Invalid option; please choose 1, 2 or 3.");
                }

            } while (menuOption != 3);
        }

        input.close();
    }

    // Enter one message
    public static void enterMessage(
            Scanner input,
            int messageNumber,
            String sender,
            MessageManager messageManager) {

        System.out.println();

        // Ask user for recipient
        System.out.print("Please enter the recipient's cellphone number: ");
        String recipient = input.nextLine();

        // Check recipient
        while (!Login.checkCellPhoneNumber(recipient)) {

            System.out.println(Message.checkRecipientCell(recipient));

            System.out.print("Please enter the recipient's cellphone number again: ");
            recipient = input.nextLine();
        }

        System.out.println(Message.checkRecipientCell(recipient));

        // Ask user for message
        System.out.print("Please enter your message: ");
        String messageText = input.nextLine();

        // Check message
        while (messageText.trim().isEmpty()
                || messageText.length() > 250) {

            if (messageText.trim().isEmpty()) {

                System.out.println("Message cannot be empty.");

            } else {

                System.out.println(Message.checkMessageLength(messageText));
            }

            System.out.print("Please enter your message again: ");
            messageText = input.nextLine();
        }

        System.out.println(Message.checkMessageLength(messageText));

        // Create a Message object using the constructor
        Message message = new Message(
                sender,
                recipient,
                messageText,
                messageNumber);

        // Display generated message ID
        System.out.println(
                Message.messageIDCreated(message.getMessageID()));

        System.out.println();

        // Ask what to do with the message
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");
        System.out.print("Please enter an option: ");

        int option = readWholeNumber(input);

        // Check option
        while (option < 1 || option > 3) {

            System.out.println(Message.sentMessage(option));

            System.out.print("Please enter an option again: ");
            option = readWholeNumber(input);
        }

        System.out.println();

        if (option == 1) {

            // Send message using MessageManager
            messageManager.sendMessage(message);

            System.out.println(Message.sentMessage(option));

            // Display the message details
            System.out.println();
            System.out.println(message.displayMessage());

        } else if (option == 2) {

            // Disregard message
            System.out.println(Message.sentMessage(option));

            String deleteInput = input.nextLine();

            while (!deleteInput.equals("0")) {

                System.out.println("Press 0 to delete the message.");
                deleteInput = input.nextLine();
            }

            System.out.println("Message deleted.");

        } else if (option == 3) {

            // Store message in the JSON file
            messageManager.storeMessage(message);

            System.out.println(Message.sentMessage(option));
        }
    }

    // Read a whole number from the user
    public static int readWholeNumber(Scanner input) {

        String number = input.nextLine().trim();

        // Check number
        while (!number.matches("-?[0-9]{1,9}")) {

            System.out.print("Please enter a whole number: ");
            number = input.nextLine().trim();
        }

        return Integer.parseInt(number);
    }
}
