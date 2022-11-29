class ToDo {
  String task;
  String description;

  ToDo(this.task, this.description);

  get todoData {
    return '${task};${description}\n';
  }

}