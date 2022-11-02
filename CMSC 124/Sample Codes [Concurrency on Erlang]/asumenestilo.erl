-module(asumenestilo).
-import(string,[substr/3,len/1]).
-compile(export_all).

% Initialize charPerson1 process
init_chat() -> 
	Name = io:get_line("Enter your name: "),
	register(chatPerson1,spawn(asumenestilo,chatPerson1,[substr(Name,1,len(Name)-1)])).

% chatPerson1 process
chatPerson1(Name) ->
	receive
		%Start the chat
		{startChat,ChatPerson2PID} ->
			io:format("Chat has started!~n"),
			MsgProcess1PID = spawn(asumenestilo,sendMessage1,[Name,ChatPerson2PID,self()]),
			chatPerson1Receiver(MsgProcess1PID)
	end.

chatPerson1Receiver(MsgProcess1PID) ->
	receive
		bye -> io:format("Input bye!~n");

		{CP2PID, bye} ->
			io:format("Input bye!~n"),
			exit(MsgProcess1PID,kill),
			CP2PID ! bye;
		% Print whatever message it receives from chatPerson2
		{SenderName,Message} -> 
			io:format("~s: ~s~n",[SenderName,Message]),
			chatPerson1Receiver(MsgProcess1PID)
	end.

% Initialize chatPerson2 process
% Needs name of ChatPerson1_Node which contains chatPerson1 process
init_chat2(ChatPerson1_Node) ->
	Name = io:get_line("Enter your name: "),
	spawn(asumenestilo,chatPerson2,[ChatPerson1_Node,substr(Name,1,len(Name)-1)]).

% chatPerson2 process
chatPerson2(ChatPerson1_Node,Name) ->
	{chatPerson1,ChatPerson1_Node} ! {startChat,self()},
	% chatPerson2 process should begin the chat since it messaged chatPerson1 process to start the chat
	MsgProcess2PID = spawn(asumenestilo,sendMessage2,[Name,ChatPerson1_Node,self()]),
	chatPerson2Receiver(MsgProcess2PID).

chatPerson2Receiver(MsgProcess2PID) ->
	receive
		bye -> io:format("Input bye!~n");
		{CP1PID, bye} ->
			io:format("Input bye!~n"),
			exit(MsgProcess2PID,kill),
			CP1PID ! bye;
		{SenderName,Message} -> 
			io:format("~s: ~s~n",[SenderName,Message]),
			chatPerson2Receiver(MsgProcess2PID)
	end.
	

% Spawned by chatPerson1 process so that it can send messages to chatPerson2
% Name is the name of chatPerson1 process
% RecipientPID is the processID of chatPerson2
sendMessage1(Name,RecipientPID, CP1PID) ->
	Message = io:get_line("You: "),
	MessageTrimmed = substr(Message,1,len(Message)-1),
	
	if
		MessageTrimmed =:= "bye" ->
			RecipientPID ! {CP1PID, bye};
		true ->
			RecipientPID ! {Name,MessageTrimmed},	%Send to chatPerson2 process the message
			sendMessage1(Name, RecipientPID, CP1PID)
	end.
	

sendMessage2(Name,RecipientNode, CP2PID) ->
	timer:sleep(300),	%Delay so that the next (input) line gets called properly
	Message = io:get_line("You: "),
	MessageTrimmed = substr(Message,1,len(Message)-1),
	
	if
		MessageTrimmed =:= "bye" ->
			{chatPerson1,RecipientNode} ! {CP2PID, bye};
		true -> 
			{chatPerson1,RecipientNode} ! {Name,MessageTrimmed},
			sendMessage2(Name,RecipientNode,CP2PID)
	end.