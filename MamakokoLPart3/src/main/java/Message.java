/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lehlogonolo
 */
public class Message {
     // Variables
    private String messageID;
    private String recipientCell;
    private String message;
    private String messageHash;
    private String messageStats;
    private int numberMessageSent;
    
    //Constracter
    public Message(String messageID,int numberMessageSent, String recipient, String message)
    {
        this.messageID = messageID;
        this.numberMessageSent = numberMessageSent;
        this. recipientCell = recipientCell;
        this.messageHash = messageHash;
        this.messageStats =  messageStats;     
    }
    
    //Methods
    // Accessor methods
    public String getMessageID() {
         return this.messageID;
    }

    public int getNumMessageSent() {
         return this.numberMessageSent;
    }

     public String getRecipient() {
         return this.recipientCell;
    }

     public String getMessage() {
         return this.message;
    }

     public String getMessageHash() {
         return this.messageHash;
    }

     public String getMessageStatus() {
         return this.messageStats;
    }
     
     // Mutator methods
     public void setMessageID(String messageID) {
         this.messageID = messageID;
    }

     public void setNumMessageSent(int num) {
         this.numberMessageSent = num;
    }

     public void setRecipient(String recipient) {
          this.recipient = recipient;
    }

     public void setMessage(String message) {
         this.message = message;
    }

     public void setMessageHash(String messageHash) {
         this.messageHash = messageHash;
    }

     public void setMessageStatus(String messageStatus) {
         this.messageStats = messageStatus;
    } 
     
     //Message method
     // Generates a random 10-digit Message ID
    public static String generateMessageID() {

        String messageID = "";

        for (int i = 0; i < 10; i++) {
            messageID += random.nextInt(10);
        }

        return messageID;
    }

    // Checks if Message ID is no more than 10 characters
    public boolean checkMessageID(String messageID) {

        if (messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    // Checks recipient cellphone number
    public boolean checkRecipientCell(String recipientCell) {

        if (recipientCell.matches("\\+27[0-9]{9}")) {
            return true;
        } else {
            return false;
        }
    }

    // Checks message length
    public static String checkMessageLength(String message) {

        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {

            int extraCharacters = message.length() - 250;

            return "Message exceeds 250 characters by "
                    + extraCharacters
                    + "; please reduce the size.";
        }
    }
    
    // Displays message details
    public void DisplayMessage() {

        System.out.println("Message ID: " + this.messageID
                + "\nMessages Sent: " + this.numberMessageSent
                + "\nRecipient: " + this.recipient
                + "\nMessage: " + this.message
        );
    }

    // Creates Message Hash
    public String createMessageHash(String messageID,
            int numMessageSent, String message) {

        String messageHash = "";
        int count = 0;

        // Add first 2 digits of Message ID
        for (int i = 0; i < messageID.length(); i++) {
            char current = messageID.charAt(i);

            if (Character.isDigit(current) && count < 2) {

                messageHash += current;
                count++;
            }
        }

     // Add colon and message number
        messageHash += ":" + numMessageSent + ":";

        // Split message into words
        String[] messageArray =
                message.trim().split("\\s+");

        // Add first word
        messageHash += messageArray[0];

        // Add last word
        messageHash +=
                messageArray[messageArray.length - 1];

        return messageHash.toUpperCase();
    }

    // Sends, disregards or stores the message
    public String sentMessage(int option) {

        if (option == 1) {
            setMessageStatus("Sent");
            sentMessages.add(this);

            return "Message successfully sent.";

        } else if (option == 2) {
            setMessageStatus("Disregarded");

            return "Press 0 to delete the message.";

        } else if (option == 3) {
            setMessageStatus("Stored");
            storedMessages.add(this);
            storeMessage();

            return "Message successfully stored.";

        } else {

            return "Invalid option.";
        }
        
        
        // Stores message in JSON format
    public void storeMessage() {

        try {
            FileWriter file = new FileWriter("messages.json", true);

            file.write("{\n");
            file.write("  \"messageID\": \""
                    + messageID + "\",\n");

            file.write("  \"messageNumber\": "
                    + numberMessageSent + ",\n");
            
            file.write("  \"recipient\": \""
                    + recipientCell + "\",\n");

            file.write("  \"message\": \""
                    + message.replace("\"", "\\\"")
                    + "\",\n");

            file.write("  \"messageHash\": \""
                    + messageHash + "\",\n");

            file.write("  \"status\": \""
                    + messageStats + "\"\n");

            file.write("}\n");

            file.close();

        } catch (IOException e) {

            System.out.println(
                    "Error storing message.");
        }
    }

    // Prints all sent messages
    public static String printMessages() {
        String allMessages = "";

        for (Message currentMessage : sentMessages) {

            allMessages +=
                    "Message ID: "
                    + currentMessage.getMessageID()
                    + "\n";

            allMessages +=
                    "Message Hash: "
                    + currentMessage.getMessageHash()
                    + "\n";

            allMessages +=
                    "Recipient: "
                    + currentMessage.getRecipient()
                    + "\n";

            allMessages +=
                    "Message: "
                    + currentMessage.getMessage()
                    + "\n\n";
        }
        return allMessages;
    }

    // Returns total messages sent
    public static int returnTotalMessagess() {

        return sentMessages.size();
    }
}
    
   


