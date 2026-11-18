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
public class LoginTest {
    
    public LoginTest() {
    }

    // Test username correctly formatted
    @Test
    public void checkUserNameCorrectlyFormatted() {
        String userName = "kyl_1";
        boolean expected = Login.checkUserName(userName);

        assertEquals(true, expected, "Username is correctly formatted");
    }

    // Test username incorrectly formatted
    @Test
    public void checkUserNameIncorrectlyFormatted() {

        String userName = "kyle !!!!!!! ";

        boolean expected = Login.checkUserName(userName);

        assertEquals(false, expected,"Username is not correctly formatted; "
                + "please ensure that your username contains an "
                + "underscore and is no more than five characters "
                + "in length.");
    }

    // Test password correctly formatted
    @Test
    public void checkPasswordCorrectlyFormatted() {
        String password = "Ch&&sec@ke99!";

        boolean expected = Login.checkPasswordComplexity(password);

        assertEquals(true, expected,"Password successfully captured.");
    }

    // Test password incorrectly formatted
    @Test
    public void checkPasswordIncorrectlyFormatted() {
        String password = "password";

        boolean expected = Login.checkPasswordComplexity(password);

        assertEquals(false, expected,"Password is not correctly formatted; "
                + "please ensure that the password contains at "
                + "least eight characters, a capital letter, "
                + "a number, and a special character.");
    }

    // Test cellphone correctly formatted
    @Test
    public void checkCellPhoneCorrectlyFormatted() {
        String cellPhoneNumber = "+27838968976";

        boolean expected = Login.checkCellPhoneNumber(cellPhoneNumber);

        assertEquals(true, expected,"Cell number successfully captured.");
    }

    // Test cellphone incorrectly formatted
    @Test
    public void checkCellPhoneIncorrectlyFormatted() {
        String cellPhoneNumber = "08966553";

        boolean expected = Login.checkCellPhoneNumber(cellPhoneNumber);

        assertEquals(false, expected,"Cell number is incorrectly formatted or does "
                + "not contain an international code; please "
                + "correct the number and try again.");
    }

    // Test login successful
    @Test
    public void loginSuccessful() {

        String userName = "kyl_1";
        String password = "Ch&&sec@ke99!";

        boolean expected = Login.loginUser(
                userName,
                password,
                userName,
                password
        );

        assertEquals(true, expected);
    }

    // Test login failed
    @Test
    public void loginFailed() {

        String userName = "wrong";
        String password = "wrong";

        boolean expected = Login.loginUser(
                userName,
                password,
                "kyl_1",
                "Ch&&sec@ke99!"
        );

        assertEquals(false, expected);
    }
}