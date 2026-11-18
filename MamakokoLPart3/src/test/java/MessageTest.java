/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 *
 * @author lehlogonolo
 */
public class MessageTest {
      // Message length 

    @Test
    public void checkMessageLengthSuccess() {
        assertEquals(
                "Message ready to send.",
                Message.checkMessageLength(
                        "Hi Mike, can you join us for dinner tonight?"
                )
        );
    }

    @Test
    public void checkMessageLengthFailure() {
        String messageText = "a".repeat(260);

        assertEquals(
                "Message exceeds 250 characters by 10; please reduce the size.",
                Message.checkMessageLength(messageText)
        );
    }

    @Test
    public void checkMessageLengthExactly250() {
        assertEquals(
                "Message ready to send.",
                Message.checkMessageLength("a".repeat(250))
        );
    }

    // Recipient number 

    @Test
    public void checkRecipientCorrectlyFormatted() {
        assertEquals(
                "Cell phone number successfully captured.",
                Message.checkRecipientCell("+27718693002")
        );
    }

    @Test
    public void checkRecipientIncorrectlyFormatted() {
        assertEquals(
                "Cell phone number is incorrectly formatted or does "
                        + "not contain an international code. Please "
                        + "correct the number and try again.",
                Message.checkRecipientCell("08575975889")
        );
    }

    //  Message hash 

    @Test
    public void checkMessageHashTestCase1() {
        assertEquals(
                "00:0:HITONIGHT",
                Message.createMessageHash(
                        "0012345678",
                        0,
                        "Hi Mike, can you join us for dinner tonight?"
                )
        );
    }

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
            assertEquals(
                    expectedHashes[index],
                    Message.createMessageHash(
                            "0012345678",
                            index,
                            messages[index]
                    )
            );

            index++;
        }
    }

    @Test
    public void checkMessageHashOneWordMessage() {
        assertEquals(
                "12:3:HIHI",
                Message.createMessageHash(
                        "1234567890",
                        3,
                        "Hi!"
                )
        );
    }

    @Test
    public void checkRemovePunctuation() {
        assertEquals(
                "Hello1",
                Message.removePunctuation("Hello!1")
        );
    }

    // Message ID 

    @Test
    public void checkMessageIDCreated() {
        String messageID = Message.generateMessageID();

        assertEquals(
                "Message ID generated: " + messageID,
                Message.messageIDCreated(messageID)
        );
    }

    @Test
    public void checkMessageIDHasTenDigits() {
        String messageID = Message.generateMessageID();

        assertTrue(messageID.matches("[0-9]{10}"));
    }

    @Test
    public void checkMessageIDCorrectLength() {
        assertTrue(
                Message.checkMessageID(
                        Message.generateMessageID()
                )
        );
    }

    @Test
    public void checkMessageIDTooLong() {
        assertFalse(
                Message.checkMessageID("12345678901")
        );
    }

    @Test
    public void checkMessageIDEmpty() {
        assertFalse(
                Message.checkMessageID("")
        );
    }

    // Send, disregard, store 

    @Test
    public void checkSendMessage() {
        assertEquals(
                "Message successfully sent.",
                Message.sentMessage(1)
        );
    }

    @Test
    public void checkDisregardMessage() {
        assertEquals(
                "Press 0 to delete the message.",
                Message.sentMessage(2)
        );
    }

    @Test
    public void checkStoreMessage() {
        assertEquals(
                "Message successfully stored.",
                Message.sentMessage(3)
        );
    }

    @Test
    public void checkInvalidOption() {
        assertEquals(
                "Invalid option; please choose 1, 2 or 3.",
                Message.sentMessage(5)
        );
    }

    // Message object 

    @Test
    public void checkMessageObjectDetails() {
        Message m = new Message(
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals("+27718693002", m.getRecipientCell());
        assertEquals(0, m.getNumMessageSent());
        assertEquals(10, m.getMessageID().length());

        assertEquals(
                m.getMessageID().substring(0, 2)
                        + ":0:HITONIGHT",
                m.getMessageHash()
        );
    }

    @Test
    public void checkMessageObjectStatus() {
        Message m = new Message(
                1,
                "+27718693002",
                "Hi Keegan, did you receive the payment?"
        );

        m.setMessageStatus(1);
        assertEquals("Sent", m.getMessageStatus());

        m.setMessageStatus(2);
        assertEquals("Disregarded", m.getMessageStatus());

        m.setMessageStatus(3);
        assertEquals("Stored", m.getMessageStatus());
    }

    // Totals and printing

    @Test
    public void checkTotalMessagesSent() {
        Message.clearMessages();

        Message.sendMessage(
                "0012345678",
                "00:0:HITONIGHT",
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(1, Message.returnTotalMessages());
    }

    @Test
    public void checkPrintMessages() {
        Message.clearMessages();

        Message.sendMessage(
                "0012345678",
                "00:0:HITONIGHT",
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        String expected =
                "Message ID: 0012345678\n"
                + "Message Hash: 00:0:HITONIGHT\n"
                + "Recipient: +27718693002\n"
                + "Message: Hi Mike, can you join us for dinner tonight?\n\n";

        assertEquals(expected, Message.printMessages());
    }

    // JSON storage 

    @Test
    public void checkStoreMessageCountsStoredMessages() {
        Message.clearMessages();

        Message.storeMessage(
                "0012345678",
                "00:0:HITONIGHT",
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(1, Message.returnTotalStoredMessages());
    }
}