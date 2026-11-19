
package com.mycompany.mamakokolpart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageConstructor() {

        Message message = new Message(
                "Lehlogonolo",
                "+27718693002",
                "Are we still meeting at the library this afternoon?",
                0);

        assertEquals("Lehlogonolo", message.getSender());
        assertEquals("+27718693002", message.getRecipient());
        assertEquals(
                "Are we still meeting at the library this afternoon?",
                message.getMessageText());
    }

    @Test
    public void testMessageLengthSuccess() {

        String result = Message.checkMessageLength(
                "Please let me know when you arrive at campus.");

        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {

        String text = "";

        for (int i = 0; i < 260; i++) {
            text += "a";
        }

        String result = Message.checkMessageLength(text);

        assertEquals(
                "Message exceeds 250 characters by 10; please reduce the size.",
                result);
    }

    @Test
    public void testEmptyMessage() {

        assertEquals(
                "Message cannot be empty.",
                Message.checkMessageLength(""));
    }

    @Test
    public void testRecipientSuccess() {

        assertEquals(
                "Cell phone number successfully captured.",
                Message.checkRecipientCell("+27718693002"));
    }

    @Test
    public void testRecipientFailure() {

        assertEquals(
                "Cell phone number is incorrectly formatted or does "
                + "not contain an international code. Please "
                + "correct the number and try again.",
                Message.checkRecipientCell("0712345678"));
    }

    @Test
    public void testMessageID() {

        String id = Message.generateMessageID();

        assertEquals(10, id.length());
        assertTrue(Message.checkMessageID(id));
    }

    @Test
    public void testInvalidMessageID() {

        assertFalse(Message.checkMessageID("12345"));
    }

    @Test
    public void testMessageHash() {

        String hash = Message.createMessageHash(
                "0012345678",
                0,
                "Please remember to bring your laptop tomorrow.");

        assertEquals("00:0:PLEASETOMORROW", hash);
    }

    @Test
    public void testMessageIDSetter() {

        Message message = new Message(
                "Lehlogonolo",
                "+27718693002",
                "The project meeting starts at nine.",
                0);

        message.setMessageID("0012345678");

        assertEquals("0012345678", message.getMessageID());

        assertEquals(
                "00:0:THENINE",
                message.getMessageHash());
    }

    @Test
    public void testSenderSetter() {

        Message message = new Message(
                "Lehlogonolo",
                "+27718693002",
                "I have sent you the updated project files.",
                0);

        message.setSender("Thabo");

        assertEquals("Thabo", message.getSender());
    }

    @Test
    public void testMessageTextSetter() {

        Message message = new Message(
                "Lehlogonolo",
                "+27718693002",
                "The meeting is scheduled for Monday.",
                0);

        message.setMessageID("0012345678");

        message.setMessageText(
                "The meeting has been moved to Friday.");

        assertEquals(
                "The meeting has been moved to Friday.",
                message.getMessageText());

        assertEquals(
                "00:0:THEFRIDAY",
                message.getMessageHash());
    }

    @Test
    public void testSendMessage() {

        MessageManager manager = new MessageManager();

        Message message = new Message(
                "Lehlogonolo",
                "+27718693002",
                "I have completed my part of the assignment.",
                0);

        manager.sendMessage(message);

        assertEquals(1, manager.returnTotalMessages());
    }

    @Test
    public void testPrintMessages() {

        // Create MessageManager object
        MessageManager manager = new MessageManager();

        // Create a message
        Message message = new Message(
                "Lehlogonolo",
                "+27718693002",
                "Can you please review the changes I made to the project?",
                0);

        // Send the message
        manager.sendMessage(message);

        // Get the displayed messages
        String result = manager.printMessages();

        // Display the result
        System.out.println(result);

        // Check that the message information appears
        assertTrue(result.contains(message.getSender()));

        assertTrue(result.contains(message.getRecipient()));

        assertTrue(result.contains(message.getMessageText()));
    }

    @Test
    public void testMessageOptions() {

        assertEquals(
                "Message successfully sent.",
                Message.sentMessage(1));

        assertEquals(
                "Press 0 to delete the message.",
                Message.sentMessage(2));

        assertEquals(
                "Message successfully stored.",
                Message.sentMessage(3));
    }
}
