
package com.mycompany.mamakokolpart1;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageManager {

    // PART 3 - Five ArrayLists
    private ArrayList<Message> sentMessages = new ArrayList<>();
    private ArrayList<Message> disregardedMessages = new ArrayList<>();
    private ArrayList<Message> storedMessages = new ArrayList<>();

    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<String> messageIDs = new ArrayList<>();

    // JSON file
    private String fileName = "storedMessages.json";

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    // Constructor
    public MessageManager() {

        // Load previously stored messages
        storedMessages = loadMessages();

        // Add their IDs and hashes
        for (Message message : storedMessages) {
            addMessageDetails(message);
        }
    }

    // Change JSON file name (useful for testing)
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // Add message ID and hash
    private void addMessageDetails(Message message) {

        if (!messageIDs.contains(message.getMessageID())) {

            messageIDs.add(message.getMessageID());
            messageHashes.add(message.getMessageHash());
        }
    }

    // Send a message
    public void sendMessage(Message message) {

        sentMessages.add(message);
        addMessageDetails(message);
    }

    // Disregard a message
    public void disregardMessage(Message message) {

        disregardedMessages.add(message);
        addMessageDetails(message);
    }

    // Store a message
    public boolean storeMessage(Message message) {

        storedMessages.add(message);

        boolean saved = saveMessages();

        if (saved) {
            addMessageDetails(message);
        } else {
            storedMessages.remove(message);
        }

        return saved;
    }

    // Save stored messages to JSON
    public boolean saveMessages() {

        try {

            FileWriter writer = new FileWriter(fileName);

            gson.toJson(storedMessages, writer);

            writer.close();

            return true;

        } catch (Exception e) {

            System.out.println("Error saving messages: "
                    + e.getMessage());

            return false;
        }
    }

    // Read stored messages from JSON
    public ArrayList<Message> loadMessages() {

        ArrayList<Message> messages = new ArrayList<>();

        File file = new File(fileName);

        // Check whether the file exists
        if (!file.exists()) {
            return messages;
        }

        try {

            FileReader reader = new FileReader(file);

            Message[] savedMessages = gson.fromJson(
                    reader, Message[].class);

            reader.close();

            if (savedMessages != null) {

                for (Message message : savedMessages) {
                    messages.add(message);
                }
            }

        } catch (Exception e) {

            System.out.println("Error reading messages: "
                    + e.getMessage());
        }

        return messages;
    }

    // Getters for the five ArrayLists
    public ArrayList<Message> getSentMessages() {
        return sentMessages;
    }

    public ArrayList<Message> getDisregardedMessages() {
        return disregardedMessages;
    }

    public ArrayList<Message> getStoredMessages() {
        return storedMessages;
    }

    public ArrayList<String> getMessageHashes() {
        return messageHashes;
    }

    public ArrayList<String> getMessageIDs() {
        return messageIDs;
    }

    // Count sent messages
    public int returnTotalMessages() {
        return sentMessages.size();
    }

    // Display all sent messages
    public String printMessages() {

        if (sentMessages.isEmpty()) {
            return "No sent messages.";
        }

        String result = "";

        for (Message message : sentMessages) {

            result += message.displayMessage() + "\n\n";
        }

        return result;
    }

    // Display sender and recipient of stored messages
    public String displayStoredContacts() {

        if (storedMessages.isEmpty()) {
            return "No stored messages.";
        }

        String result = "";

        for (Message message : storedMessages) {

            result += "Sender: " + message.getSender()
                    + "\nRecipient: " + message.getRecipient()
                    + "\n\n";
        }

        return result;
    }

    // Find the longest stored message
    public Message longestStoredMessage() {

        if (storedMessages.isEmpty()) {
            return null;
        }

        Message longest = storedMessages.get(0);

        for (Message message : storedMessages) {

            if (message.getMessageText().length()
                    > longest.getMessageText().length()) {

                longest = message;
            }
        }

        return longest;
    }

    // Search for a message by ID
    public Message findByID(String id) {

        // Search sent messages
        for (Message message : sentMessages) {

            if (message.getMessageID().equals(id)) {
                return message;
            }
        }

        // Search stored messages
        for (Message message : storedMessages) {

            if (message.getMessageID().equals(id)) {
                return message;
            }
        }

        return null;
    }

    // Search messages by recipient
    public ArrayList<Message> searchByRecipient(String recipient) {

        ArrayList<Message> results = new ArrayList<>();

        // Search sent messages
        for (Message message : sentMessages) {

            if (message.getRecipient().equals(recipient)) {
                results.add(message);
            }
        }

        // Search stored messages
        for (Message message : storedMessages) {

            if (message.getRecipient().equals(recipient)) {
                results.add(message);
            }
        }

        return results;
    }

    // Delete a message using its hash
    public Message deleteByHash(String hash) {

        // Search stored messages
        for (int i = 0; i < storedMessages.size(); i++) {

            Message message = storedMessages.get(i);

            if (message.getMessageHash().equalsIgnoreCase(hash)) {

                storedMessages.remove(i);

                if (saveMessages()) {

                    removeMessageDetails(message);
                    return message;

                } else {

                    // Put message back if saving failed
                    storedMessages.add(i, message);
                    return null;
                }
            }
        }

        // Search sent messages
        for (int i = 0; i < sentMessages.size(); i++) {

            Message message = sentMessages.get(i);

            if (message.getMessageHash().equalsIgnoreCase(hash)) {

                sentMessages.remove(i);
                removeMessageDetails(message);

                return message;
            }
        }

        return null;
    }

    // Remove message ID and hash
    private void removeMessageDetails(Message deletedMessage) {

        String id = deletedMessage.getMessageID();

        // Check sent messages
        for (Message message : sentMessages) {

            if (message.getMessageID().equals(id)) {
                return;
            }
        }

        // Check stored messages
        for (Message message : storedMessages) {

            if (message.getMessageID().equals(id)) {
                return;
            }
        }

        // Check disregarded messages
        for (Message message : disregardedMessages) {

            if (message.getMessageID().equals(id)) {
                return;
            }
        }

        int index = messageIDs.indexOf(id);

        if (index >= 0) {

            messageIDs.remove(index);
            messageHashes.remove(index);
        }
    }

    // Display stored messages report
    public String storedReport() {

        if (storedMessages.isEmpty()) {
            return "No stored messages.";
        }

        String report = "";

        for (Message message : storedMessages) {

            report += message.displayMessage() + "\n\n";
        }

        return report;
    }

    // Display sent messages report
    public String sentReport() {

        if (sentMessages.isEmpty()) {
            return "No sent messages.";
        }

        String report = "";

        for (Message message : sentMessages) {

            report += "Message Hash: "
                    + message.getMessageHash()
                    + "\nRecipient: "
                    + message.getRecipient()
                    + "\nMessage: "
                    + message.getMessageText()
                    + "\n\n";
        }

        return report;
    }
}
