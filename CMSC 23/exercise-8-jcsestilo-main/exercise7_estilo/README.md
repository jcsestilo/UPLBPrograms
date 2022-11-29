## Student information
### Name: Jan Coleen S. Estilo
### Student Number: 2020-05789
### Section: C-3L
## App description: 
- A simple authentication app using Firebase Authentication. With Sign Up and Log In Features.
## Screenshots
## Things you did in the code (logic, solutions)
1. For the form validation, I used the exercise 6 (to-do app) as a reference.
2. For the validation of the email address, I used a package called email validator. This is being used to validate if the email address inputted by the user is in the correct format.
3. For the first problem (check below), I solved the problem by calling a snackbar in the firebase_auth_api.dart. With this, when the user clicks the okay button, the navigator will then pop in the context, which avoids the context not relevant error.
## Challenges faced when developing the app
1. I had a hard time showing the dialog when the sign up or log in successful or if it produces an error. I was trying to pass a message from the firebase authenticator api, then pass it to the authentication provider, which passes the message to the signup.dart or login.dart. However, the context is not relevant anymore since we popped the context in the function call.
## Test Cases
## Happy paths - the expected outputs that your application produces
1. The user inputs the valid information in the fields for sign up and log in.
2. After signing up or logging in, we will go to the to do page. From here, we can logout through the menu and go back to the log in page.
## Unhappy paths - the expected errors/ways that a user can break your app
1. After signing up, the user does not click the "okay" in the snackbar. This makes the popping never happen so we just stay at the sign up page. 
2. The first name and last name is required. It will produce an error when any of the values are missing.