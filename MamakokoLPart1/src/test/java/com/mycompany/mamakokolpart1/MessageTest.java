
package com.mycompany.mamakokolpart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageConstructor() {

        Message message = new Message(
                "Lehlogonolo",
                "+27718693002",
                "Hello Mike!",
                0);

        assertEquals("Lehlogonolo", message.getSender());
        assertEquals("+27718693002", message.getRecipient());
        assertEquals("Hello Mike!", message.getMessageText());
    }

    @Test
    public void testMessageLengthSuccess() {

        String result = Message.checkMessageLength("Hello Mike!");

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
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("00:0:HITONIGHT", hash);
    }

    @Test
    public void testMessageIDSetter() {

        Message message = new Message(
                "Lehlogonolo",
                "+27718693002",
                "Hello Mike!",
                0);

        message.setMessageID("0012345678");

        assertEquals("0012345678", message.getMessageID());
        assertEquals("00:0:HELLOMIKE", message.getMessageHash());
    }

    @Test
    public void testSenderSetter() {

        Message message = new Message(
                "Lehlogonolo",
                "+27718693002",
                "Hello Mike!",
                0);

        message.setSender("John");

        assertEquals("John", message.getSender());
    }

    @Test
    public void testMessageTextSetter() {

        Message message = new Message(
                "Lehlogonolo",
                "+27718693002",
                "Hello Mike!",
                0);

        message.setMessageID("0012345678");
        message.setMessageText("Goodbye Mike!");

        assertEquals("Goodbye Mike!", message.getMessageText());
        assertEquals("00:0:GOODBYEMIKE", message.getMessageHash());
    }

    @Test
    public void testSendMessage() {

        MessageManager manager = new MessageManager();

        Message message = new Message(
                "Lehlogonolo",
                "+27718693002",
                "Hello Mike!",
                0);

        manager.sendMessage(message);

        assertEquals(1, manager.returnTotalMessages());
    }

    @Test
    public void testPrintMessages() {

        MessageManager manager = new MessageManager();

        Message message = new Message(
                "Lehlogonolo",
                "+27718693002",
                "Hello Mike!",
                0);

        manager.sendMessage(message);

        String result = manager.printMessages();

        assertTrue(result.contains("Sender: lehlogonolo"));
        assertTrue(result.contains("Message: Hello Mike!"));
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
