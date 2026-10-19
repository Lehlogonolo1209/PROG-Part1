# PROG-Part1
Java registration and login system with input validation and JUnit tests.
# MamakokoLPart1

## About My Project

MamakokoLPart1 is a Java console-based registration and login program that I created as part of my Java practical work.
The main idea of the program is to allow a user to create an account and then use the account details to log in. The program asks the user to enter their first name, last name, username, password, and cellphone number.
The program also checks whether the information entered is correct. If the information does not follow the required rules, the user is asked to enter it again.
After the user has successfully registered, they can enter their username and password to log in. The program then checks whether the login details match the details used during registration.

## What My Program Does
My program can:

- Ask the user to enter their first and last name.
- Check if the username has an underscore and is not more than five characters.
- Check if the password has at least eight characters.
- Check if the password has a capital letter, number, and special character.
- Check if the cellphone number has the correct international format.
- Register the user when all the information is correct.
- Allow the user to log in using their username and password.
- Show a message when the login is successful or unsuccessful.
- Use JUnit tests to check if the program is working correctly.

   ## FOR PART 2
  **My program can:**
- Check whether a message is within the allowed length.
- Check whether a recipient's cellphone number has the correct international format.
- Generate a message ID containing 10 digits.
- Create a message hash to identify a message.
- Remove punctuation from message text when required.
- Create message objects containing message information.
- Set and check the status of a message.
- Allow a message to be sent, disregarded, or stored.
- Count messages and display message information.
- Store message information in JSON format.
- Use JUnit 5 tests to check whether the program works correctly.

  ## Message Length
- The program checks that a message does not exceed 250 characters.
- Messages containing 250 characters or fewer can be accepted.
- Messages containing more than 250 characters are rejected.
- The program indicates how many characters must be removed when a message is too long.
  

## Recipient Cellphone Number
The program checks whether the recipient's cellphone number follows the required international format.

Example:

+27718693002

A number that does not follow the required format should be rejected with an appropriate error message.

## Message ID
Each message must have a message ID containing 10 digits. The program generates a message ID and checks whether it has the correct length and format.

Example:

0012345678

## Message Hash
The program creates a message hash using information from the message ID, the message number, and the message text. This helps identify individual messages.

Example:

00:0:HITONIGHT

The expected hash format depends on the message number and the first and last words of the message.

## Message Status
The program supports different message statuses:

-Sent: The message has been sent.
-The message has been rejected or disregarded.
-Stored: The message has been saved for later use.
The program also returns the appropriate response when an invalid message option is selected.

## Message Storage and Display
The program can manage message information, count messages, and display details such as:
Message ID
-Message hash
-Recipient cellphone number
-Message text
-The program also includes functionality for storing messages in JSON format so that the information can be saved.
  

## Username

The username must:
- Have an underscore `_`.
- Be five characters or less.

Example:
`kyl_1`

## Password

The password must:
- Have at least eight characters.
- Have a capital letter.
- Have a number.
- Have a special character.

Example:
`Ch&&sec@ke99!`

## Cellphone Number

The cellphone number must include an international country code.

Example:
`+27838968976`

## Testing

I created JUnit tests to check that the different parts of my program are working correctly.

The tests check:
- Correct and incorrect usernames.
- Correct and incorrect passwords.
- Correct and incorrect cellphone numbers.
- Successful login.
- Failed login.
  
 ## FOR PART2**
 The tests check:
- Correct and incorrect message lengths.
- Valid and invalid cellphone numbers.
- Message hash creation.
- Removing punctuation from message text.
- Message ID generation and validation.
- Sending, disregarding, and storing messages.
- Creating message objects and checking their details.
- Checking message statuses.
- Counting messages.
- Displaying message information.
- Counting stored messages.
- These tests help me find mistakes and improve the reliability of my program.
  
 
## Tools I Used

- Java
- NetBeans
- Maven
- JUnit 5
- GitHub



