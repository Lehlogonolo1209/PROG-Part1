/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


package com.mycompany.mamakokolpart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mamakoko
 */
public class MessageTest {

    public MessageTest() {
    }

    // Test message is not more than 250 characters (success)
    @Test
    public void checkMessageLengthSuccess() {
        String messageText = "Hi Mike, can you join us for dinner tonight?";

        String actual = Message.checkMessageLength(messageText);

        assertEquals("Message ready to send.", actual);
    }

    // Test message is more than 250 characters (failure)
    @Test
    public void checkMessageLengthFailure() {
        String messageText = "";

        int index = 0;

        while (index < 260) {
            messageText = messageText + "a";
            index++;
        }

        String actual = Message.checkMessageLength(messageText);

        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", actual);
    }

    // Test recipient number correctly formatted
    @Test
    public void checkRecipientCorrectlyFormatted() {
        String recipient = "+27718693002";

        String actual = Message.checkRecipientCell(recipient);

        assertEquals("Cell phone number successfully captured.", actual);
    }

    // Test recipient number incorrectly formatted
    @Test
    public void checkRecipientIncorrectlyFormatted() {
        String recipient = "08575975889";

        String actual = Message.checkRecipientCell(recipient);

        assertEquals("Cell phone number is incorrectly formatted or does "
                + "not contain an international code. Please "
                + "correct the number and try again.", actual);
    }

    // Test message hash (Test Case 1)
    @Test
    public void checkMessageHashTestCase1() {
        String actual = Message.createMessageHash(
                "0012345678",
                0,
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("00:0:HITONIGHT", actual);
    }

    // Test the remaining message hashes in a loop
    @Test
    public void checkMessageHashesInLoop() {
        String[] messages = {
            "Hi Mike, can you join us for dinner tonight?",
            "Hi Keegan, did you receive the payment?"
        };

        String[] expectedHashes = {
            "00:0:HITONIGHT",
            "00:1:HIPAYMENT"
        };

        int index = 0;

        while (index < messages.length) {
            String actual = Message.createMessageHash(
                    "0012345678",
                    index,
                    messages[index]);

            assertEquals(expectedHashes[index], actual);
            index++;
        }
    }

    // Test message ID is created
    @Test
    public void checkMessageIDCreated() {
        String messageID = Message.generateMessageID();

        String actual = Message.messageIDCreated(messageID);

        assertEquals("Message ID generated: " + messageID, actual);
    }

    // Test message ID is not more than ten characters
    @Test
    public void checkMessageIDCorrectLength() {
        String messageID = Message.generateMessageID();

        boolean expected = Message.checkMessageID(messageID);

        assertEquals(true, expected);
    }

    // Test message ID is too long
    @Test
    public void checkMessageIDTooLong() {
        boolean expected = Message.checkMessageID("12345678901");

        assertEquals(false, expected);
    }

    // Test user selected send message
    @Test
    public void checkSendMessage() {
        String actual = Message.sentMessage(1);

        assertEquals("Message successfully sent.", actual);
    }

    // Test user selected disregard message
    @Test
    public void checkDisregardMessage() {
        String actual = Message.sentMessage(2);

        assertEquals("Press 0 to delete the message.", actual);
    }

    // Test user selected store message
    @Test
    public void checkStoreMessage() {
        String actual = Message.sentMessage(3);

        assertEquals("Message successfully stored.", actual);
    }

    // Test total number of messages sent
    // (Test data: message 1 is sent, message 2 is disregarded)
    @Test
    public void checkTotalMessagesSent() {
        Message.clearMessages();

        Message.sendMessage(
                "0012345678",
                "00:0:HITONIGHT",
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals(1, Message.returnTotalMessages());
    }

    // Test printing the sent messages
    @Test
    public void checkPrintMessages() {
        Message.clearMessages();

        Message.sendMessage(
                "0012345678",
                "00:0:HITONIGHT",
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        String expected = "Message ID: 0012345678"
                + "\nMessage Hash: 00:0:HITONIGHT"
                + "\nRecipient: +27718693002"
                + "\nMessage: Hi Mike, can you join us for dinner tonight?\n\n";

        assertEquals(expected, Message.printMessages());
    }
}
