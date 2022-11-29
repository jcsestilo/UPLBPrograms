/*
    Author: Jan Coleen S. Estilo
    Section: C-3L
    Date created: October 18, 2022
    Exercise number: Exercise 5
    Program Description: To-do list application
*/

import 'package:flutter/material.dart';
import 'show_todo.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'To-Do List',
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
      home: ShowToDo(),
    );
  }
}