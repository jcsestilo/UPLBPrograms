-module(asumenestilo).
-import(string,[substr/3,len/1]).
-compile(export_all).

init_chat() -> 
	Name = io:get_line("Enter your name: "),
	register(chatPerson1,spawn(asumenestilo,chatPerson1,[substr(Name,1,len(Name)-1)])).
	% spawn(asumenestilo,chatPerson1,[substr(Name,1,len(Name)-1)]).

sendMessages(ChatPersonPID, SenderName) ->
	receive
		{ChatPersonPID, Message} ->
			ChatPersonPID ! {Message, SenderName};
		{ChatPersonPID, "bye"} ->
			ChatPersonPID ! bye
	end.


chatPerson1(Name) ->
	receive
		%Start the chat
		{SenderNode, startChat} -> 
			io:format("Chat has started!~n"),
			register(sendMessagesForCP1, spawn(asumenestilo, sendMessages, [SenderNode, Name])),
			Reply = io:get_line("You: "),
			whereis(sendMessagesForCP1) ! {SenderNode, substr(Reply,1,len(Reply)-1)};
		{Message, SenderName} ->
			io:format("~s: ~s~n", [SenderName, Message]);
		bye ->
			ok
	end.

init_chat2(ChatPerson1_Node) ->
	Name = io:get_line("Enter your name: "),
	spawn(asumenestilo,chatPerson2,[ChatPerson1_Node,substr(Name,1,len(Name)-1)]).

chatPerson2(ChatPerson1_Node,Name) ->
	{chatPerson1,ChatPerson1_Node} ! {self(), startChat},
	register(sendMessagesForCP2, spawn(asumenestilo, sendMessages, [ChatPerson1_Node, Name])),
	Reply2 = io:get_line("You: "),
	whereis(sendMessagesForCP2) ! {ChatPerson1_Node, substr(Reply2,1,len(Reply2)-1)},
	receive
		{Message, SenderName} ->
			io:format("~s: ~s~n", [SenderName, Message]);
		_ -> io:format("Received here!~n");
		bye ->
			ok

	end.