import 'package:firebase_auth/firebase_auth.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class FirebaseAuthAPI {
  static final FirebaseAuth auth = FirebaseAuth.instance;
  static final FirebaseFirestore db = FirebaseFirestore.instance;

  Stream<User?> getUser() {
    return auth.authStateChanges();
  }

  void signIn(String email, String password, BuildContext context) async {
    UserCredential credential;
    String message="";
    try {
      final credential = await auth.signInWithEmailAndPassword(
          email: email, password: password);
      message = 'Log In Successful';
    } on FirebaseAuthException catch (e) {
      if (e.code == 'user-not-found') {
        //possible to return something more useful 
        //than just print an error message to improve UI/UX
        print('No user found for that email.');
        message = 'No user found for that email.';
      } else if (e.code == 'wrong-password') {
        print('Wrong password provided for that user.');
        message = 'Wrong password provided for that user.';
      }
    }
    final snackBar = SnackBar(
      content: Text(message),
      action: SnackBarAction(
        label: 'Okay',
        onPressed: () {
          Navigator.pop(context);
        },),
    );
    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }

  signUp(String email, String password, String firstname, String lastname, BuildContext context) async {
    UserCredential credential;
    String message="";
    try {
      credential = await auth.createUserWithEmailAndPassword(
        email: email,
        password: password,
      );
      if (credential.user != null) {
        saveUserToFirestore(credential.user?.uid, email, firstname, lastname);
        print("Sign Up Successful");
        message = "Sign Up Successful";
      }
    } on FirebaseAuthException catch (e) {
        //possible to return something more useful 
        //than just print an error message to improve UI/UX
      if (e.code == 'weak-password') {
        print('The password provided is too weak.');
        message = "The password provided is too weak.";
      } else if (e.code == 'email-already-in-use') {
        print('The account already exists for that email.');
        message = "The account already exists for that email.";
      }
    } catch (e) {
      print(e);
    } 
    final snackBar = SnackBar(
      content: Text(message),
      action: SnackBarAction(
        label: 'Okay',
        onPressed: () {
          Navigator.pop(context);
        },),
    );
    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }

  void signOut() async {
    auth.signOut();
  }

  void saveUserToFirestore(String? uid, String email, String firstname, String lastname) async {
    try {
      await db.collection("users").doc(uid).set({"email": email, "firstname": firstname, "lastname": lastname});
    } on FirebaseException catch (e) {
      print(e.message);
    }
  }


}
