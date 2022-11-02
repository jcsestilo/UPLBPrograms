-module(asumenestilo).
-import(string,[substr/3,len/1]).
-compile(export_all).

init_chat() -> 
	Name = io:get_line("Enter your name: "),
	register(chatPerson1,spawn(asumenestilo,chatPerson1,[substr(Name,1,len(Name)-1)])).
	% spawn(asumenestilo,chatPerson1,[substr(Name,1,len(Name)-1)]).

chatPerson1(Name) ->
	receive
		%Start the chat
		{SenderNode, startChat} -> 
			io:format("Chat has started!~n"),
			sendMessages(self(), SenderNode, Name);
		{SenderPID, SenderName, ReplyTrimmed} ->
			io:format("~s: ~s~n", [SenderName, ReplyTrimmed]),
			sendMessages(self(), SenderPID, Name)
	end.

init_chat2(ChatPerson1_Node) ->
	Name = io:get_line("Enter your name: "),
	spawn(asumenestilo,chatPerson2,[ChatPerson1_Node,substr(Name,1,len(Name)-1)]).

chatPerson2(ChatPerson1_Node,Name) ->
	{chatPerson1,ChatPerson1_Node} ! {self(), startChat},
	% io:format("Dumaan Dito~n"),
	Trash = io:get_line("Trash: "),
	Trash2 = io:get_line("Trash: "),
	sendMessages(self(), ChatPerson1_Node, Name),
	receive
		% {Name, Reply} -> 
		% 	io:format("~s: ~s~n", [Name, Reply]);
		{SenderPID, SenderName, ReplyTrimmed} ->
			io:format("~s: ~s~n", [SenderName, ReplyTrimmed]),
			sendMessages(self(), SenderPID, Name)
	end.

sendMessages(SenderPID, ReceiverPID, SenderName) ->
	Reply = io:get_line("You: "),
	ReplyTrimmed = substr(Reply,1,len(Reply)-1),
	ReceiverPID ! {SenderPID, SenderName, ReplyTrimmed}.