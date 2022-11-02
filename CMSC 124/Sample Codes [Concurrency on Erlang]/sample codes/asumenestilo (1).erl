-module(asumenestilo).
-import(string,[substr/3,len/1]).
-compile(export_all).

% Initialize charPerson1 process
init_chat() -> 
	Name = io:get_line("Enter your name: "),
	register(chatPerson1,spawn(asumenestilo,chatPerson1,[substr(Name,1,len(Name)-1)])).
	% spawn(asumenestilo,chatPerson1,[substr(Name,1,len(Name)-1)]).

% chatPerson1 process
chatPerson1(Name) ->
	receive
		%Start the chat
		{startChat,ChatPerson2PID} ->
			io:format("Chat has started!~n"),
			spawn(asumenestilo,sendMessage1,[Name,ChatPerson2PID]);
		% Print whatever message it receives from chatPerson2
		{Name,Message} -> io:format("~s: ~s~n",[Name,Message])
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
	% sendMessage2(Name,ChatPerson1_Node),
	receive
		{sentMessage,Name,Message} -> io:format("~s: ~s~n",[Name,Message]);
		_ -> io:format("Received here!~n")
	end.

% Spawned by chatPerson1 process so that it can send messages to chatPerson2
% Name is the name of chatPerson1 process
% RecipientPID is the processID of chatPerson2
sendMessage1(Name,RecipientPID) ->
	Message = io:get_line("You: "),
	RecipientPID ! {sentMessage,Name,Message}.	%Send to chatPerson2 process the message

sendMessage2(Name,RecipientNode) ->
	% Message = io:get_line("You: "),
	% {chatPerson1,ChatPerson1_Node} ! {Name,Message}.
	true.