import 'package:email_validator/email_validator.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:week7_networking_discussion/providers/auth_provider.dart';
import 'package:week7_networking_discussion/screens/signup.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final _formKey = GlobalKey<FormState>();
  @override
  Widget build(BuildContext context) {
    TextEditingController emailController = TextEditingController();
    TextEditingController passwordController = TextEditingController();

    final email = TextFormField(
      key: const Key('emailField'),
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

    final loginButton = Padding(
      key: const Key('loginButton'),
      padding: const EdgeInsets.symmetric(vertical: 16.0),
      child: ElevatedButton(
        onPressed: () {
          if(_formKey.currentState!.validate()){
            context
              .read<AuthProvider>()
              .signIn(emailController.text, passwordController.text, context);
          }

        },
        child: const Text('Log In', style: TextStyle(color: Colors.white)),
      ),
    );

    final signUpButton = Padding(
      key: const Key('signUpButton'),
      padding: const EdgeInsets.symmetric(vertical: 16.0),
      child: ElevatedButton(
        onPressed: () async {
          Navigator.of(context).push(
            MaterialPageRoute(
              builder: (context) => const SignupPage(),
            ),
          );
        },
        child: const Text('Sign Up', style: TextStyle(color: Colors.white)),
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
              "Login",
              textAlign: TextAlign.center,
              style: TextStyle(fontSize: 25),
            ),
            Form(
              key: _formKey,
              child: Column(
                children: [
                  email,
                  const Divider(),
                  password,
                  const Divider(),
                  loginButton,
                ],
              ),
            ),
            signUpButton,
          ],
        ),
      ),
    );
  }
}
