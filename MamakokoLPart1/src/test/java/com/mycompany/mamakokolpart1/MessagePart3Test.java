/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mamakokolpart1;

/**
 *
 * @author Lehlogonolo
 */

import java.util.ArrayList;
import java.io.File;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessagePart3Test {

    // Test 1 - Sent messages array
    @Test
    public void testSentMessagesArray() {

        MessageManager manager = new MessageManager();

        Message message1 = new Message(
                "Lehlogonolo",
                "+27834557896",
                "Did you get the cake?",
                0);

        Message message4 = new Message(
                "Lehlogonolo",
                "0838884567",
                "It is dinner time!",
                3);

        manager.sendMessage(message1);
        manager.sendMessage(message4);

        assertEquals(2, manager.getSentMessages().size());

        assertEquals(
                "Did you get the cake?",
                manager.getSentMessages().get(0).getMessageText());

        assertEquals(
                "It is dinner time!",
                manager.getSentMessages().get(1).getMessageText());
    }

    // Test 2 - Longest stored message
    @Test
    public void testLongestStoredMessage() {

        MessageManager manager = new MessageManager();

        manager.getStoredMessages().clear();

        Message message2 = new Message(
                "Lehlogonolo",
                "+27838884567",
                "Where are you? You are late! I have asked you to be on time.",
                1);

        Message message5 = new Message(
                "Lehlogonolo",
                "+27838884567",
                "Ok, I am leaving without you.",
                4);

        manager.getStoredMessages().add(message2);
        manager.getStoredMessages().add(message5);

        Message longest = manager.longestStoredMessage();

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                longest.getMessageText());
    }

    // Test 3 - Search by message ID
    @Test
    public void testSearchMessageID() {

        MessageManager manager = new MessageManager();

        Message message4 = new Message(
                "Lehlogonolo",
                "0838884567",
                "It is dinner time!",
                3);

        message4.setMessageID("0838884567");

        manager.sendMessage(message4);

        Message found = manager.findByID("0838884567");

        assertNotNull(found);

        assertEquals(
                "It is dinner time!",
                found.getMessageText());
    }

    // Test 4 - Search by recipient
    @Test
    public void testSearchByRecipient() {

        MessageManager manager = new MessageManager();

        manager.getStoredMessages().clear();

        Message message2 = new Message(
                "Lehlogonolo",
                "+27838884567",
                "Where are you? You are late! I have asked you to be on time.",
                1);

        Message message5 = new Message(
                "Lehlogonolo",
                "+27838884567",
                "Ok, I am leaving without you.",
                4);

        manager.getStoredMessages().add(message2);
        manager.getStoredMessages().add(message5);

        ArrayList<Message> results =
                manager.searchByRecipient("+27838884567");

        assertEquals(2, results.size());

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                results.get(0).getMessageText());

        assertEquals(
                "Ok, I am leaving without you.",
                results.get(1).getMessageText());
    }

    // Test 5 - Delete message by hash
    @Test
    public void testDeleteMessageByHash() {

        MessageManager manager = new MessageManager();

        Message message = new Message(
                "Lehlogonolo",
                "+27838884567",
                "Where are you? You are late! I have asked you to be on time.",
                1);

        manager.sendMessage(message);

        Message deleted =
                manager.deleteByHash(message.getMessageHash());

        assertNotNull(deleted);

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                deleted.getMessageText());

        assertEquals(0, manager.getSentMessages().size());
    }

    // Test 6 - Sent messages report
    @Test
    public void testSentMessagesReport() {

        MessageManager manager = new MessageManager();

        Message message = new Message(
                "Lehlogonolo",
                "+27834557896",
                "Did you get the cake?",
                0);

        manager.sendMessage(message);

        String report = manager.sentReport();

        assertTrue(report.contains("Message Hash:"));
        assertTrue(report.contains("Recipient: +27834557896"));
        assertTrue(report.contains("Message: Did you get the cake?"));
    }

    // Test 7 - JSON storage
    @Test
    public void testJSONStorage() {

        MessageManager manager = new MessageManager();

        // Use a separate file for testing
        manager.setFileName("part3-test-messages.json");

        // Start with an empty stored messages array
        manager.getStoredMessages().clear();

        Message message = new Message(
                "Lehlogonolo",
                "+27838884567",
                "Ok, I am leaving without you.",
                4);

        boolean saved = manager.storeMessage(message);

        assertTrue(saved);

        ArrayList<Message> loaded = manager.loadMessages();

        assertEquals(1, loaded.size());

        assertEquals(
                "Ok, I am leaving without you.",
                loaded.get(0).getMessageText());

        // Delete the test file
        File file = new File("part3-test-messages.json");

        assertTrue(file.delete());
    }

    // Test 8 - Disregarded messages
    @Test
    public void testDisregardedMessages() {

        MessageManager manager = new MessageManager();

        Message message3 = new Message(
                "Lehlogonolo",
                "+27834484567",
                "Yohoooo, I am at your gate.",
                2);

        manager.disregardMessage(message3);

        assertEquals(
                1,
                manager.getDisregardedMessages().size());

        assertEquals(
                "Yohoooo, I am at your gate.",
                manager.getDisregardedMessages().get(0).getMessageText());
    }

    // Test 9 - Message ID and hash arrays
    @Test
    public void testMessageIDsAndHashes() {

        MessageManager manager = new MessageManager();

        Message message = new Message(
                "Lehlogonolo",
                "+27834557896",
                "Did you get the cake?",
                0);

        manager.sendMessage(message);

        assertTrue(
                manager.getMessageIDs().contains(
                        message.getMessageID()));

        assertTrue(
                manager.getMessageHashes().contains(
                        message.getMessageHash()));
    }

    // Test 10 - Stored messages report
    @Test
    public void testStoredMessagesReport() {

        MessageManager manager = new MessageManager();

        manager.getStoredMessages().clear();

        Message message = new Message(
                "Lehlogonolo",
                "+27838884567",
                "Ok, I am leaving without you.",
                4);

        manager.getStoredMessages().add(message);

        String report = manager.storedReport();

        assertTrue(report.contains("Message ID:"));
        assertTrue(report.contains("Message Hash:"));
        assertTrue(report.contains("Sender: Lehlogonolo"));
        assertTrue(report.contains("Recipient: +27838884567"));
        assertTrue(report.contains("Ok, I am leaving without you."));
    }
}
