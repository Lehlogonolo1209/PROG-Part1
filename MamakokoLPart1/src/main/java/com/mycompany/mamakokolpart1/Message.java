/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mamakokolpart1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author lehlogonolo
 */
public class Message {

    // Lists that keep the details of the sent messages
    // (the same position in each list belongs to the same message)
    private static ArrayList<String> sentIDs = new ArrayList<>();
    private static ArrayList<String> sentHashes = new ArrayList<>();
    private static ArrayList<String> sentRecipients = new ArrayList<>();
    private static ArrayList<String> sentTexts = new ArrayList<>();

    // List that keeps the stored messages for the JSON file
    private static JsonArray storedMessages = new JsonArray();

    // Generate a random ten-digit message ID
    public static String generateMessageID() {
        Random random = new Random();
        String messageID = "";

        int index = 0;

        while (index < 10) {
            messageID = messageID + random.nextInt(10);
            index++;
        }

        return messageID;
    }

    // Check message ID
    public static boolean checkMessageID(String messageID) {
        if (messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    // Message ID created message
    public static String messageIDCreated(String messageID) {
        return "Message ID generated: " + messageID;
    }

    // Check recipient cellphone number
    // (uses the checkCellPhoneNumber method from Part 1)
    public static String checkRecipientCell(String recipient) {
        if (Login.checkCellPhoneNumber(recipient)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does "
                    + "not contain an international code. Please "
                    + "correct the number and try again.";
        }
    }

    // Check message length
    public static String checkMessageLength(String messageText) {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extra = messageText.length() - 250;

            return "Message exceeds 250 characters by "
                    + extra
                    + "; please reduce the size.";
        }
    }

    // Remove punctuation from a word (keeps only letters and numbers)
    public static String removePunctuation(String word) {
        String cleanWord = "";

        int index = 0;

        while (index < word.length()) {
            char current = word.charAt(index);

            if (Character.isLetterOrDigit(current)) {
                cleanWord = cleanWord + current;
            }

            index++;
        }

        return cleanWord;
    }

    // Create message hash
    // Format: first two numbers of the ID : message number : FIRSTWORDLASTWORD
    public static String createMessageHash(
            String messageID,
            int messageNumber,
            String messageText) {

        String[] words = messageText.trim().split(" +");

        String firstWord = removePunctuation(words[0]);
        String lastWord = removePunctuation(words[words.length - 1]);

        String messageHash = messageID.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;

        return messageHash.toUpperCase();
    }

    // Message options: 1 = send, 2 = disregard, 3 = store
    public static String sentMessage(int option) {
        if (option == 1) {
            return "Message successfully sent.";
        } else if (option == 2) {
            return "Press 0 to delete the message.";
        } else if (option == 3) {
            return "Message successfully stored.";
        } else {
            return "Invalid option; please choose 1, 2 or 3.";
        }
    }

    // Send message (adds the details to the sent lists)
    public static void sendMessage(
            String messageID,
            String messageHash,
            String recipient,
            String messageText) {

        sentIDs.add(messageID);
        sentHashes.add(messageHash);
        sentRecipients.add(recipient);
        sentTexts.add(messageText);
    }

    // Display the full details of one message
    public static String displayMessage(
            String messageID,
            String messageHash,
            String recipient,
            String messageText) {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    // Print all the messages sent while the program is running
    public static String printMessages() {
        String allMessages = "";

        int index = 0;

        while (index < sentIDs.size()) {
            allMessages = allMessages
                    + displayMessage(
                            sentIDs.get(index),
                            sentHashes.get(index),
                            sentRecipients.get(index),
                            sentTexts.get(index))
                    + "\n\n";

            index++;
        }

        return allMessages;
    }

    // Return total number of messages sent
    public static int returnTotalMessages() {
        return sentIDs.size();
    }

    // Store message in a JSON file (storedMessages.json in the project folder)
    public static void storeMessage(
            String messageID,
            String messageHash,
            String recipient,
            String messageText) {

        // Build one JSON object for this message
        JsonObject message = new JsonObject();
        message.addProperty("messageID", messageID);
        message.addProperty("messageHash", messageHash);
        message.addProperty("recipient", recipient);
        message.addProperty("message", messageText);

        storedMessages.add(message);

        // Write all the stored messages to the file
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("storedMessages.json")) {
            gson.toJson(storedMessages, writer);
        } catch (IOException e) {
            System.out.println("Error storing the message: " + e.getMessage());
        }
    }

    // Clear all messages (used by the unit tests)
    public static void clearMessages() {
        sentIDs.clear();
        sentHashes.clear();
        sentRecipients.clear();
        sentTexts.clear();
        storedMessages = new JsonArray();
    }
}
