import 'package:flutter/material.dart';
import 'todo_model.dart';
import 'file_storage.dart';

class AddToDoPage extends StatefulWidget {
  const AddToDoPage({Key? key, required this.title, required this.fileHandler})
      : super(key: key);
  final String title;
  final FileStorage fileHandler;
  @override
  State<AddToDoPage> createState() => _AddToDoPageState();
}

class _AddToDoPageState extends State<AddToDoPage> {
  final TextEditingController _task = TextEditingController();
  final TextEditingController _description = TextEditingController();

  final _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Form(
            key: _formKey,
            child: Column(
              children: [
                buildTextField('Enter task', _task),
                const Divider(),
                buildTextField('Enter description', _description),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    buildSaveButton(),
                    buildViewButton(),
                  ],
                )
              ],
            ),
          ),
         
        ],
      ),
    );
  }

  //function for creating a textfield widget which accepts the label and controller as parameter
  Widget buildTextField(String label, TextEditingController _controller) {
    return TextFormField(
      controller: _controller,
      validator: (value) {
        if (value == null || value.isEmpty) {
          return 'Please enter some text.';
        }
      },
      decoration: InputDecoration(
        border: const OutlineInputBorder(),
        labelText: label,
      ),
    );
  }

  //function for creating save button
  Widget buildSaveButton() {
    return ElevatedButton(
        child: const Text('Save'),
        onPressed: () {
          if(_formKey.currentState!.validate()){
            //create an instance of the data you want to save
            ToDo todo = ToDo(_task.text, _description.text);
            //write formatted data to textfile
            widget.fileHandler.writeString(todo.todoData);
            ScaffoldMessenger.of(context).showSnackBar(
              const SnackBar(content: Text('Processing Data')),
            );
            //clear text on controllers after successful insert of data to database
            _task.clear();
            _description.clear();
          }
          
        });
  }

  Widget buildViewButton() {
    return ElevatedButton(
      onPressed: () {
        //go back to list of dogs page
        Navigator.pop(context);
      },
      child: const Text("View"),
    );
  }
}