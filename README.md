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

## Tools I Used

- Java
- NetBeans
- Maven
- JUnit 5
- GitHub
