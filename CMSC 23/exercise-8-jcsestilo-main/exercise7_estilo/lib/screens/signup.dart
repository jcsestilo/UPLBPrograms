// import 'dart:html';

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:week7_networking_discussion/providers/auth_provider.dart';
import 'package:email_validator/email_validator.dart';

class SignupPage extends StatefulWidget {
  const SignupPage({super.key});
  @override
  _SignupPageState createState() => _SignupPageState();
}

class _SignupPageState extends State<SignupPage> {
  final _formKey = GlobalKey<FormState>();
  @override
  Widget build(BuildContext context) {
    TextEditingController emailController = TextEditingController();
    TextEditingController passwordController = TextEditingController();
    TextEditingController firstNameController = TextEditingController();
    TextEditingController lastNameController = TextEditingController();

    final email = TextFormField(
      controller: emailController,
      validator: (value) {
        if(value==null||value.isEmpty){
          return "Please enter some text";
        }
        else if(!EmailValidator.validate(value)) {
          return "Please enter a valid email address";
        }
      },
      decoration: const InputDecoration(
        border: OutlineInputBorder(),
        hintText: 'Email'
      ),
    );


    final password = TextFormField(
      controller: passwordController,
      validator: (value){
        if(value==null||value.isEmpty){
          return 'Please enter some text.';
        }
      },
      obscureText: true,
      decoration: const InputDecoration(
        border: OutlineInputBorder(),
        hintText: 'Password'
      ),
    );

    final firstname = TextFormField(
      controller: firstNameController,
      validator: (value){
        if(value==null||value.isEmpty){
          return 'Please enter some text.';
        }
      },
      decoration: const InputDecoration(
        border: OutlineInputBorder(),
        hintText: 'First Name'
      ),
    );

    final lastname = TextFormField(
      controller: lastNameController,
      validator: (value){
        if(value==null||value.isEmpty){
          return 'Please enter some text.';
        }
      },
      decoration: const InputDecoration(
        border: OutlineInputBorder(),
        hintText: 'Last Name'
      ),
    );

    final SignupButton = Padding(
      padding: const EdgeInsets.symmetric(vertical: 16.0),
      child: ElevatedButton(
        onPressed: () {
          if(_formKey.currentState!.validate()){
            //call the auth provider here
            context
              .read<AuthProvider>()
              .signUp(emailController.text, passwordController.text, firstNameController.text, lastNameController.text, context);
            // Navigator.pop(context);
            
          }

        },
        child: const Text('Sign up', style: TextStyle(color: Colors.white)),
      ),
    );

    final backButton = Padding(
      padding: const EdgeInsets.symmetric(vertical: 16.0),
      child: ElevatedButton(
        onPressed: () async {
          Navigator.pop(context);
        },
        child: const Text('Back', style: TextStyle(color: Colors.white)),
      ),
    );


    return Scaffold(
      backgroundColor: Colors.white,
      body: Center(
        child: ListView(
          shrinkWrap: true,
          padding: const EdgeInsets.only(left: 40.0, right: 40.0),
          children: <Widget>[
            const Text(
              "Sign Up",
              textAlign: TextAlign.center,
              style: TextStyle(fontSize: 25),
            ),
            Form(
              key: _formKey,
              child: Column(
                children: [
                  firstname,
                  const Divider(),
                  lastname,
                  const Divider(),
                  email,
                  const Divider(),
                  password,
                  const Divider(),
                  SignupButton,
                ]
              ),
            ),
            backButton
          ],
        ),
      ),
    );
  }
}
