package com.mycompany.mamakokolpart1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Manages sent messages and messages saved for later. */
public class MessageManager {
    private final ArrayList<Message> sentMessages = new ArrayList<>();
    private final ArrayList<Message> storedMessages = new ArrayList<>();

    public void sendMessage(Message message) { sentMessages.add(message); }
    public int returnTotalMessages() { return sentMessages.size(); }
    public List<Message> getSentMessages() { return List.copyOf(sentMessages); }

    public String printMessages() {
        StringBuilder result = new StringBuilder();
        for (Message message : sentMessages) {
            result.append(message.displayMessage()).append("\n\n");
        }
        return result.toString();
    }

    public void storeMessage(Message message) {
        storedMessages.add(message);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("storedMessages.json")) {
            gson.toJson(storedMessages, writer);
        } catch (IOException e) {
            System.out.println("Error storing the message: " + e.getMessage());
        }
    }

    public void clearMessages() {
        sentMessages.clear();
        storedMessages.clear();
    }
}
