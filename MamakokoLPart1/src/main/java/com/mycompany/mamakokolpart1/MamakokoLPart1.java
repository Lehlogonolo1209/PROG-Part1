
package com.mycompany.mamakokolpart1;

import java.util.Scanner;
import java.util.ArrayList;

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

        // Create MessageManager object
        MessageManager messageManager = new MessageManager();

        // REGISTRATION
        System.out.println("====================Registration==========================");

        System.out.print("Please enter your first name: ");
        firstName = input.nextLine();

        System.out.print("Please enter your last name: ");
        lastName = input.nextLine();

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

        System.out.print("Please enter your password: ");
        password = input.nextLine();

        // Check password
        while (!Login.checkPasswordComplexity(password)) {

            System.out.println("Password is not correctly formatted; "
                    + "please ensure that the password contains at "
                    + "least eight characters, a capital letter, "
                    + "a number, and a special character.");

            System.out.print("Please enter your password again: ");
            password = input.nextLine();
        }

        System.out.println("Password successfully captured.");

        System.out.print("Please enter your cellphone Number: ");
        cellPhoneNumber = input.nextLine();

        // Check cellphone number
        while (!Login.checkCellPhoneNumber(cellPhoneNumber)) {

            System.out.println("Cell number is incorrectly formatted or "
                    + "does not contain an international code.");

            System.out.print("Please enter your cellphone Number again: ");
            cellPhoneNumber = input.nextLine();
        }

        System.out.println("Cell number successfully captured.");

        // Register user
        System.out.println(
                Login.registerUser(
                        userName,
                        password,
                        cellPhoneNumber));

        // LOGIN
        System.out.println();
        System.out.println("====================Login==========================");

        System.out.print("Please enter your username: ");
        registerUserName = input.nextLine();

        System.out.print("Please enter your password: ");
        registerPassword = input.nextLine();

        // Check login
        boolean loginSuccessful = Login.loginUser(
                registerUserName,
                registerPassword,
                userName,
                password);

        System.out.println(
                Login.returnLoginStatus(
                        loginSuccessful,
                        firstName,
                        lastName));

        // QUICKCHAT
        if (loginSuccessful) {

            System.out.println();
            System.out.println("====================QuickChat App====================");
            System.out.println("Welcome to QuickChat.");

            int messageNumber = 0;
            int menuOption;

            // Main menu
            do {
                System.out.println();
                System.out.println("Option 1) Send Messages");
                System.out.println("Option 2) Show recently sent messages");
                System.out.println("Option 3) Stored Messages");
                System.out.println("Option 4) Quit");
                System.out.print("Please enter an option: ");

                menuOption = readWholeNumber(input);

                switch (menuOption) {

                    case 1:

                        // Ask how many messages to enter
                        System.out.println();
                        System.out.print("How many messages do you want to enter? ");

                        int numberOfMessages = readWholeNumber(input);

                        while (numberOfMessages <= 0) {

                            System.out.print("Please enter a number bigger than 0: ");
                            numberOfMessages = readWholeNumber(input);
                        }

                        // Enter messages
                        for (int i = 0; i < numberOfMessages; i++) {

                            System.out.println();
                            System.out.println("===============Message "
                                    + (messageNumber + 1)
                                    + "===============");

                            enterMessage(
                                    input,
                                    messageNumber,
                                    registerUserName,
                                    messageManager);

                            messageNumber++;
                        }

                        System.out.println();
                        System.out.println(
                                "Total number of messages sent: "
                                + messageManager.returnTotalMessages());

                        break;

                    case 2:

                        // Display sent messages
                        System.out.println();
                        System.out.println("===============Sent Messages===============");

                        System.out.println(messageManager.printMessages());

                        break;

                    case 3:

                        // Open Part 3 menu
                        storedMessagesMenu(input, messageManager);

                        break;

                    case 4:

                        System.out.println(
                                "Total number of messages sent: "
                                + messageManager.returnTotalMessages());

                        System.out.println("Goodbye.");

                        break;

                    default:

                        System.out.println(
                                "Invalid option; please choose 1, 2, 3 or 4.");
                }

            } while (menuOption != 4);
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

        // Ask for recipient
        System.out.print("Please enter the recipient's cellphone number: ");
        String recipient = input.nextLine();

        // Check recipient
        while (!Login.checkCellPhoneNumber(recipient)) {

            System.out.println(Message.checkRecipientCell(recipient));

            System.out.print("Please enter the recipient's cellphone number again: ");
            recipient = input.nextLine();
        }

        System.out.println(Message.checkRecipientCell(recipient));

        // Ask for message text
        System.out.print("Please enter your message: ");
        String messageText = input.nextLine();

        // Check message length
        while (messageText.trim().isEmpty()
                || messageText.length() > 250) {

            System.out.println(Message.checkMessageLength(messageText));

            System.out.print("Please enter your message again: ");
            messageText = input.nextLine();
        }

        System.out.println(Message.checkMessageLength(messageText));

        // Create Message object
        Message message = new Message(
                sender,
                recipient,
                messageText,
                messageNumber);

        System.out.println(
                Message.messageIDCreated(message.getMessageID()));

        // Message options
        System.out.println();
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");
        System.out.print("Please enter an option: ");

        int option = readWholeNumber(input);

        // Check option
        while (option < 1 || option > 3) {

            System.out.println("Invalid option.");

            System.out.print("Please enter an option again: ");
            option = readWholeNumber(input);
        }

        // Send message
        if (option == 1) {

            messageManager.sendMessage(message);

            System.out.println(Message.sentMessage(option));

            System.out.println();
            System.out.println(message.displayMessage());

        } else if (option == 2) {

            // Disregard message
            System.out.print("Press 0 to disregard the message: ");
            String confirmation = input.nextLine();

            while (!confirmation.equals("0")) {

                System.out.print("Press 0 to disregard the message: ");
                confirmation = input.nextLine();
            }

            messageManager.disregardMessage(message);

            System.out.println("Message disregarded.");

        } else {

            // Store message in JSON
            boolean saved = messageManager.storeMessage(message);

            if (saved) {
                System.out.println(Message.sentMessage(option));
            } else {
                System.out.println("Message could not be stored.");
            }
        }
    }

    // PART 3 - Stored Messages Menu
    public static void storedMessagesMenu(
            Scanner input,
            MessageManager messageManager) {

        int option;

        do {
            System.out.println();
            System.out.println("====================Stored Messages====================");

            System.out.println("1) Display sender and recipient");
            System.out.println("2) Display longest stored message");
            System.out.println("3) Search for message ID");
            System.out.println("4) Search messages by recipient");
            System.out.println("5) Delete message using message hash");
            System.out.println("6) Display stored messages report");
            System.out.println("7) Display sent messages report");
            System.out.println("8) Return to main menu");

            System.out.print("Please enter an option: ");
            option = readWholeNumber(input);

            switch (option) {

                case 1:

                    // Display sender and recipient
                    System.out.println(
                            messageManager.displayStoredContacts());

                    break;

                case 2:

                    // Display longest stored message
                    Message longest = messageManager.longestStoredMessage();

                    if (longest == null) {

                        System.out.println("No stored messages.");

                    } else {

                        System.out.println("Longest stored message:");
                        System.out.println(longest.getMessageText());
                    }

                    break;

                case 3:

                    // Search message ID
                    System.out.print("Enter message ID: ");
                    String id = input.nextLine();

                    Message found = messageManager.findByID(id);

                    if (found == null) {

                        System.out.println("Message ID not found.");

                    } else {

                        System.out.println("Recipient: "
                                + found.getRecipient());

                        System.out.println("Message: "
                                + found.getMessageText());
                    }

                    break;

                case 4:

                    // Search by recipient
                    System.out.print("Enter recipient cellphone number: ");
                    String recipient = input.nextLine();

                    ArrayList<Message> results =
                            messageManager.searchByRecipient(recipient);

                    if (results.isEmpty()) {

                        System.out.println("No matching messages.");

                    } else {

                        for (Message message : results) {
                            System.out.println(message.getMessageText());
                        }
                    }

                    break;

                case 5:

                    // Delete message using hash
                    System.out.print("Enter message hash: ");
                    String hash = input.nextLine();

                    Message deleted = messageManager.deleteByHash(hash);

                    if (deleted == null) {

                        System.out.println(
                                "Message not found or could not be deleted.");

                    } else {

                        System.out.println(
                                "Message: \"" + deleted.getMessageText()
                                + "\" successfully deleted.");
                    }

                    break;

                case 6:

                    // Stored messages report
                    System.out.println(messageManager.storedReport());

                    break;

                case 7:

                    // Sent messages report
                    System.out.println(messageManager.sentReport());

                    break;

                case 8:

                    System.out.println("Returning to main menu.");

                    break;

                default:

                    System.out.println("Invalid option; choose 1 to 8.");
            }

        } while (option != 8);
    }

    // Read a whole number from the user
    public static int readWholeNumber(Scanner input) {

        String number = input.nextLine().trim();

        while (!number.matches("-?[0-9]{1,9}")) {

            System.out.print("Please enter a whole number: ");
            number = input.nextLine().trim();
        }

        return Integer.parseInt(number);
    }
}
