# PROG-PEO
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

## Part 3 – Store Data and Display Task Report
My program can:

Create an array containing all sent messages.

Create an array containing all disregarded messages.

Create an array containing all stored messages.

Store message hashes in an array.

Store message IDs in an array.

Read stored messages from a JSON file.

Display the sender and recipient of stored messages.

Find and display the longest stored message.

Search for a message using its message ID.

Search for all stored messages belonging to a particular recipient.

Delete a stored message using its message hash.

Display a report containing the details of stored messages.

I also added a fourth option called Stored Messages to the main menu. This allows users to access the different stored message features.

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
  
 ## FOR PART2
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

## What I Learned

Through Part 1 and Part 2 of my programming practical work, I learned many new things about Java programming. I learned how to write code, test my program, find mistakes, and fix them. These tasks helped me understand how to build a program step by step.

**Part 1: Registration and Login**

In Part 1, I learned how to create a registration and login system using Java. I learned how to ask users to enter their personal details, such as their first name, last name, username, password, and cellphone number.

I also learned how to check if the information entered is correct. For example, the program checks if the username has an underscore and is not more than five characters. It also checks if the password has the required characters and if the cellphone number is in the correct format.

I learned how to allow users to log in using the details they entered during registration. This helped me understand how a registration and login system works.

**Part 2: Sending Messages**

In Part 2, I learned how to create and manage messages using Java. I learned how to check the length of a message and make sure that the recipient's cellphone number is correct.

I also learned how to create a message ID and a message hash. These help identify messages. I learned how to work with different message statuses, such as sent, disregarded, and stored.

I also learned how to count messages, display message details, and save message information in JSON format. This helped me understand how a messaging system can manage information.

**Part 3: Arrays and Stored Messages**

In Part 3, I learned how to create and work with arrays in Java. I learned how to store different messages in separate arrays depending on whether they were sent, disregarded or stored.

I also learned how to add information to arrays, search through arrays and remove information when a message is deleted. This helped me understand how arrays can be used to organise and manage information in a program.

Another important thing I learned was how to work with JSON files to save and read stored messages. This helped me understand how information can be kept even after the program is closed.

I also learned how to create a message report and display important information, such as message hashes, recipients and message content.

**Overall Learning Experience**

Overall, this practical work helped me improve my problem-solving skills and become more confident in Java programming. I learned that writing a program is not only about making the code work but also about testing it, finding errors and improving it.

Each part introduced me to new programming concepts, and I gained a better understanding of how different Java features can work together to create a complete application.

