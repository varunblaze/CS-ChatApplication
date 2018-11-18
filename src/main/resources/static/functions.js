	function processResponse(response) { // given the final CS text, converts the parsed response from the CS server into HTML code for adding to the response holder div
	var botSaid = '<strong>' + botName + ': ' + response + "</strong><br>\n";
	update(botSaid);
}
	(function() {

	$('#live-chat header').on('click', function() {

		$('.chat').slideToggle(300, 'swing');
		$('.chat-message-counter').fadeToggle(300, 'swing');

	});

	$('.chat-close').on('click', function(e) {

		e.preventDefault();
		$('#live-chat').fadeOut(300);

	});

}) ();



var botName = 'harry';		// change this to your bot name

// declare timer variables
var alarm = null;
var callback = null;
var loopback = null;
function parseCommands(response){ // Response is data from CS server. This processes OOB commands sent from the CS server returning the remaining response w/o oob commands

	var len  = response.length;
	var i = -1;
	while (++i < len )
	{
		if (response.charAt(i) == ' ' || response.charAt(i) == '\t') continue; // starting whitespace
		if (response.charAt(i) == '[') break;	// we have an oob starter
		return response;						// there is no oob data 
	}
	if ( i == len) return response; // no starter found
	var user = $('#txtUser').val();
     
	// walk string to find oob data and when ended return rest of string
	var start = 0;
	while (++i < len )
	{
		if (response.charAt(i) == ' ' || response.charAt(i) == ']') // separation
		{
			if (start != 0) // new oob chunk
			{
				var blob = response.slice(start,i);
				start = 0;

				var commandArr = blob.split('=');
				if (commandArr.length == 1) continue;	// failed to split left=right

				var command = commandArr[0]; // left side is command 
				var interval = (commandArr.length > 1) ? commandArr[1].trim() : -1; // right side is millisecond count
				if (interval == 0)  /* abort timeout item */
				{
					switch (command){
						case 'alarm':
							window.clearTimeout(alarm);
							alarm = null;
							break;
						case 'callback':
							window.clearTimeout(callback);
							callback = null;
							break;
						case 'loopback':
							window.clearInterval(loopback);
							loopback = null;
							break;
					}
				}
				else if (interval == -1) interval = -1; // do nothing
				else
				{
					var timeoutmsg = {user: user, send: true, message: '[' + command + ' ]'}; // send naked command if timer goes off 
					switch (command) {
						case 'alarm':
							alarm = setTimeout(function(){sendMessage(timeoutmsg );}, interval);
							break;
						case 'callback':
							callback = setTimeout(function(){sendMessage(timeoutmsg );}, interval);
							break;
						case 'loopback':
							loopback = setInterval(function(){sendMessage(timeoutmsg );}, interval);
							break;
					}
				}
			} // end new oob chunk
			if (response.charAt(i) == ']') return response.slice(i + 2); // return rest of string, skipping over space after ] 
		} // end if
		else if (start == 0) start = i;	// begin new text blob
	} // end while
	return response;	// should never get here
 }
 
function update(text){ // text is  HTML code to append to the 'chat log' div. This appends the input text to the response div
	var chatLog = $('#chatmessage').html();
	$('#chatmessage').html(chatLog + '<p>' + text + '</p><hr>');
	var rhd = $('#chatmessage');
	var h = rhd.get(0).scrollHeight;
	debugger;
	rhd.scrollTop(h);
}
function onsubmit1()
{
debugger;
var name = $('#txtUser').val();
    if (name == '') {
		alert('Please provide your name.');
		document.getElementById('txtUser').focus();
    }
	var youSaid = $('#txtMessage').val() ;
	update(youSaid);
	var data = $('#frmchat').serialize();
	console.log(data);
	$.ajax({
	url: 'ui.php',
	dataType: 'text',
	data: data,
    type: 'post',
    success: function(response){
		processResponse(parseCommands(response));
    },
    error: function(xhr, status, error){
		alert('oops? Status = ' + status + ', error message = ' + error + "\nResponse = " + xhr.responseText);
    }
  });
  $('#txtMessage').val('').focus();
}
	//
	



function sendMessage(data){ //Sends inputs to the ChatScript server, and returns the response-  data - a JSON string of input information
$.ajax({
	url: 'ui.php',
	dataType: 'text',
	data: data,
    type: 'post',
    success: function(response){
		processResponse(parseCommands(response));
    },
    error: function(xhr, status, error){
		alert('oops? Status = ' + status + ', error message = ' + error + "\nResponse = " + xhr.responseText);
    }
  });
}
