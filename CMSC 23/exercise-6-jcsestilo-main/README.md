# Student information
## Name : Jan Coleen Estilo
## Student Number: 2020-05789
## Section: C-3L
# App description: Todo List app using persistence
# Screenshots
1. Starting point of the application
![Screenshot 2022-10-18 124025](https://user-images.githubusercontent.com/100185394/196338431-d3c6772b-ad40-427c-a828-27440ffc8647.png)
2. Adding a task
![Screenshot 2022-10-18 124139](https://user-images.githubusercontent.com/100185394/196338461-5cd53be8-8e75-4ca3-be95-567a8ab02e4b.png)
![Screenshot 2022-10-18 124151](https://user-images.githubusercontent.com/100185394/196338474-03dd9169-2d6e-40b4-b5ef-f70b4e43518c.png)
![Screenshot 2022-10-18 124215](https://user-images.githubusercontent.com/100185394/196338484-66583c18-64a8-4b27-83b2-7b1983892713.png)
3. When a user clicks the save button while having no input in either of the text fields.
![Screenshot 2022-10-18 124231](https://user-images.githubusercontent.com/100185394/196338535-6cebb302-a220-4f31-88b0-a33d021a485e.png)
4. User deleting a task
![Screenshot 2022-10-18 124248](https://user-images.githubusercontent.com/100185394/196338553-30aa36e9-f3a5-4bcf-b26c-666c9b380cf9.png)
![Screenshot_1666068123](https://user-images.githubusercontent.com/100185394/196338561-0ac74495-9c01-4f4c-868e-fc4d8e476a9e.png)
# Things you did in the code (logic, solutions)
- For the validation, I created a Form widget and changed the TextFields from the example into a TextFormField. With this, I was able to use the validate function in the Form widget.
- For the deletion of the to do, I showed an alert dialog on the onPressed method of the icon and we confirm the deletion of the task if the user confirms the action in the alert dialog.
# Challenges faced when developing the app
- I had a hard time in showing the alert dialog when the user wants to delete a task.
    - For some reason, the alert dialog is not disappearing when the user clicks the Yes option. However, the to do is being deleted properly.
    - I also had a hard time on how to implement the appearing of the alert dialog since the code is having an error when I create a new method that will return true/false whether the user confirms the deletion.
        - I was able to solve this problem by putting the showing of the dialog directly to the onTap method of the delete icon in the ListTile.
# Test Cases
## Happy paths - the expected outputs that your application produces
- A user can delete, add, and view tasks in the application.
- When a user clicked the save button but there are no inputs in either the task textfield or the description textfield, the application will again ask for the user input in the empty fields.
- When a user decides to delete a to do, an alert will pop up asking the user if he/she is sure on deleting that certain to do. If the user clicked yes, the to do will be deleted in the text file and it will disappear in the list of to dos in the application. However, if a user clicks No, the alert dialog will disappear and no changes will happen in the text file and the list of to dos.
## Unhappy paths - the expected errors/ways that a user can break your app
- 
