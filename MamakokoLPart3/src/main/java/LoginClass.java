/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lehlogonolo
 */
public class LoginClass {
     //Check username
    public static boolean checkUserName(String userName) {
        if (userName.contains("_")
                && userName.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

    //  Check password complexity
    public static boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        int index = 0;

        while (index < password.length()) {
            char current = password.charAt(index);
            if (Character.isUpperCase(current)) {
                hasCapitalLetter = true;
            } else if (Character.isDigit(current)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(current)) {
                hasSpecialCharacter = true;
            }

            index++;
        }

        if (hasCapitalLetter
                && hasNumber
                && hasSpecialCharacter) {
            return true;
        } else {
            return false;
        }
    }

    // Check cellphone number
    public static boolean checkCellPhoneNumber(
            String cellPhoneNumber) {

        if (cellPhoneNumber.matches("\\+27[0-9]{9}")) {
            return true;
        } else {
            return false;
        }
    }

    // Registration method
    public static String registerUser(
            String userName,
            String password,
            String cellPhoneNumber) {

        if (!checkUserName(userName)) {

            return "Username is not correctly formatted; "
                    + "please ensure that your username contains an "
                    + "underscore and is no more than five characters "
                    + "in length.";

        } else if (!checkPasswordComplexity(password)) {

            return "Password is not correctly formatted; "
                    + "please ensure that the password contains at "
                    + "least eight characters, a capital letter, "
                    + "a number, and a special character.";

        } else if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell number is incorrectly formatted or does "
                    + "not contain an international code; please "
                    + "correct the number and try again.";
        } else {
            return "User registered successfully.";
        }
    }

    // Login method
    public static boolean loginUser(
            String enteredUserName,
            String enteredPassword,
            String userName,
            String password) {
        
        if (enteredUserName.equals(userName)
                && enteredPassword.equals(password)) {

            return true;
        } else {
            return false;
        }
    }

    // Return login status
    public static String returnLoginStatus(
            boolean login,
            String firstName,
            String lastName) {

        if (login) {
            return "Welcome " + firstName + ", "+ lastName + " it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}


    
