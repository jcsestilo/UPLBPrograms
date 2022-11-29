//import necessary packages
import 'file_storage.dart';
import 'package:flutter/material.dart';
// import 'add_dog.dart';
import 'add_todo.dart';

class ShowToDo extends StatefulWidget{
  const ShowToDo({Key? key}) : super(key:key);

    @override
  State<ShowToDo> createState() => _ShowToDoPageState();
}

class _ShowToDoPageState extends State<ShowToDo> {
  //create a future list of string that will store all the dog data from textfile
  late Future<List<String>?> myToDo;
  FileStorage fileHandler = FileStorage('todo.txt');

  @override
  void initState() {
    super.initState();
    //initialize myDogs list by getting data from textfile
    myToDo = fileHandler.readFile();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("To Do List"),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        mainAxisSize: MainAxisSize.min,
        verticalDirection: VerticalDirection.down,
        children: <Widget>[
          myToDoList(context),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          //navigate to AddDogPage page and automatically calls the setState()
          //to render newly added dog in textfile when Navigator.pop is called from AddDogPage page
          Navigator.of(context)
              .push(MaterialPageRoute(
                  builder: (context) =>
                      AddToDoPage(title: 'Add To Do', fileHandler: fileHandler)))
              .then((value) => setState(() {
                    myToDo = fileHandler.readFile();
                  }));
        },
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }

  //Since readFile method will return a Future list, we need a Future builder to build our list
  //using listView.builder
  Widget myToDoList(BuildContext context) {
    return Expanded(
      child: FutureBuilder(
          future: myToDo,
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return buildText(snapshot.data as List<String>, context);
            } else {
              //if textfile has no data
              return const Center(
                child: Text("No tasks found"),
              );
            }
          }),
    );
  }

  void _deleteToDo(myToDo, item) {
    setState(() {
      if (myToDo != null) {
        myToDo.remove(item);
      }
    });
  }

  void _writeStrings(myToDo) {
    String str1 = "";

    for (String str in myToDo) {
      str1 = str1 + str + '\n';
    }

    fileHandler.writeAll(str1);
  }

  //widget that returns listview of myDogs data
  Widget buildText(List<String> myToDo, BuildContext context) {
    return ListView.builder(
      physics: const BouncingScrollPhysics(),
      itemCount: myToDo.length,
      itemBuilder: (context, int index) {
        return Card(
          child: ListTile(
            title: Text(myToDo[index].split(';')[
                0]), //dog name is the first item from returned list of split()
            subtitle: Text(myToDo[index].split(';')[
                1]), //dog age is the second item from returned list of split()
            trailing: const Icon(Icons.delete),
            onTap: () {
              // confirmationDialog(context);
              // _deleteToDo(myToDo, myToDo[index]);
              showDialog(
                context: context,
                builder: (context) {
                  return AlertDialog(
                    title: Text('Are you sure you want to delete?'),
                    actions: <Widget>[
                      TextButton(
                        onPressed: () => {
                          _deleteToDo(myToDo, myToDo[index]),
                          _writeStrings(myToDo),
                          Navigator.pop(context),
                        }, 
                        child: Text('Yes')
                      ),
                      TextButton(
                        onPressed: Navigator.of(context).pop, 
                        child: Text('No')
                      ),
                    ],
                  );
                }
              );
              
            },
          ),
        );
      },
    );
  }
}