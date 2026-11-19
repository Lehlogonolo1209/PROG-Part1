package com.mycompany.mamakokolpart1;

import java.util.Random;

/** Represents one QuickChat message. */
public class Message {
    private String messageID;
    private String messageHash;
    private String sender;
    private String recipient;
    private String messageText;
    private int messageNumber;

    public Message(String sender, String recipient, String messageText, int messageNumber) {
        this(generateMessageID(), sender, recipient, messageText, messageNumber);
    }

    // This constructor also makes tests repeatable by allowing a known ID.
    public Message(String messageID, String sender, String recipient, String messageText, int messageNumber) {
        if (!checkMessageID(messageID)) throw new IllegalArgumentException("Message ID must contain exactly 10 digits.");
        this.messageID = messageID;
        this.sender = sender;
        this.recipient = recipient;
        this.messageNumber = messageNumber;
        setMessageText(messageText);
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getSender() { return sender; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public int getMessageNumber() { return messageNumber; }

    public void setSender(String sender) { this.sender = sender; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public void setMessageText(String messageText) {
        if (messageText == null || messageText.trim().isEmpty() || messageText.length() > 250) {
            throw new IllegalArgumentException("Message must contain 1 to 250 characters.");
        }
        this.messageText = messageText;
        this.messageHash = createMessageHash(messageID, messageNumber, messageText);
    }

    public static String generateMessageID() {
        Random random = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) id.append(random.nextInt(10));
        return id.toString();
    }

    public static boolean checkMessageID(String id) {
        return id != null && id.matches("[0-9]{10}");
    }

    public static String messageIDCreated(String id) { return "Message ID generated: " + id; }

    public static String checkRecipientCell(String recipient) {
        return Login.checkCellPhoneNumber(recipient)
                ? "Cell phone number successfully captured."
                : "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public static String checkMessageLength(String text) {
        if (text != null && text.length() <= 250) return "Message ready to send.";
        int extra = text == null ? 0 : text.length() - 250;
        return "Message exceeds 250 characters by " + extra + "; please reduce the size.";
    }

    public static String removePunctuation(String word) {
        return word.replaceAll("[^\\p{L}\\p{N}]", "");
    }

    public static String createMessageHash(String id, int number, String text) {
        String[] words = text.trim().split("\\s+");
        return (id.substring(0, 2) + ":" + number + ":"
                + removePunctuation(words[0]) + removePunctuation(words[words.length - 1])).toUpperCase();
    }

    public static String sentMessage(int option) {
        return switch (option) {
            case 1 -> "Message successfully sent.";
            case 2 -> "Press 0 to delete the message.";
            case 3 -> "Message successfully stored.";
            default -> "Invalid option; please choose 1, 2 or 3.";
        };
    }

    public String displayMessage() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nSender: " + sender
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }
}
