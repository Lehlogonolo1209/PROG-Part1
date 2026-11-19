/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mamakokolpart1;

/**
 *
 * @author Lehlogonolo
 */
import java.util.Random;

public class Message {

    // Properties
    private String messageID;
    private String messageHash;
    private String sender;
    private String recipient;
    private String messageText;
    private int messageNumber;

    // Constructor
    public Message(String sender, String recipient,
                   String messageText, int messageNumber) {

        this.sender = sender;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;

        messageID = generateMessageID();

        messageHash = createMessageHash(
                messageID, messageNumber, messageText);
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    // Setters
    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;

        messageHash = createMessageHash(
                messageID, messageNumber, messageText);
    }

    // Allows a known ID to be used in unit tests
    public void setMessageID(String messageID) {
        if (checkMessageID(messageID)) {
            this.messageID = messageID;

            messageHash = createMessageHash(
                    messageID, messageNumber, messageText);
        }
    }

    // Generate a random 10-digit message ID
    public static String generateMessageID() {

        Random random = new Random();
        String id = "";

        for (int i = 0; i < 10; i++) {
            id += random.nextInt(10);
        }

        return id;
    }

    // Check message ID
    public static boolean checkMessageID(String id) {
        return id != null && id.matches("[0-9]{10}");
    }

    public static String messageIDCreated(String id) {
        return "Message ID generated: " + id;
    }

    // Check recipient number
    public static String checkRecipientCell(String recipient) {

        if (Login.checkCellPhoneNumber(recipient)) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does "
                + "not contain an international code. Please "
                + "correct the number and try again.";
    }

    // Check message length
    public static String checkMessageLength(String text) {

        if (text == null || text.trim().isEmpty()) {
            return "Message cannot be empty.";
        }

        if (text.length() <= 250) {
            return "Message ready to send.";
        }

        int extra = text.length() - 250;

        return "Message exceeds 250 characters by "
                + extra + "; please reduce the size.";
    }

    // Remove punctuation from a word
    public static String removePunctuation(String word) {
        return word.replaceAll("[^a-zA-Z0-9]", "");
    }

    // Create message hash
    public static String createMessageHash(
            String id, int number, String text) {

        if (text == null || text.trim().isEmpty()) {
            return "";
        }

        String[] words = text.trim().split("\\s+");

        String firstWord = removePunctuation(words[0]);
        String lastWord = removePunctuation(words[words.length - 1]);

        String hash = id.substring(0, 2)
                + ":" + number
                + ":" + firstWord + lastWord;

        return hash.toUpperCase();
    }

    // Message option response
    public static String sentMessage(int option) {

        switch (option) {
            case 1:
                return "Message successfully sent.";

            case 2:
                return "Press 0 to delete the message.";

            case 3:
                return "Message successfully stored.";

            default:
                return "Invalid option; please choose 1, 2 or 3.";
        }
    }

    // Display message information
    public String displayMessage() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nSender: " + sender
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }
}
