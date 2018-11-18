CHAT_NODE=" <li class=\"left clearfix\"><span class=\"chat-img pull-left\">\r\n <img src=\"http:\/\/placehold.it\/50\/55C1E7\/fff&text=U\" alt=\"User Avatar\" class=\"img-circle\" \/>\r\n <\/span>\r\n<div class=\"chat-body clearfix\">\r\n<div class=\"header\">\r\n<strong class=\"primary-font\">Jack Sparrow<\/strong> <small class=\"pull-right text-muted\">\r\n<span class=\"glyphicon glyphicon-time\"><\/span>12 mins ago<\/small>\r\n <\/div>\r\n<p>@response@<\/p>\r\n<\/div>\r\n<\/li>"
RESP_CHAT_NODE=" <li class=\"right clearfix\"><span class=\"chat-img pull-right\">\r\n <img src=\"http:\/\/placehold.it\/50\/55C1E7\/fff&text=WE\" alt=\"User Avatar\" class=\"img-circle\" \/>\r\n <\/span>\r\n<div class=\"chat-body clearfix\">\r\n<div class=\"header\">\r\n<strong class=\"primary-font\">Web Support<\/strong> <small class=\"pull-right text-muted\">\r\n<span class=\"glyphicon glyphicon-time\"><\/span>12 mins ago<\/small>\r\n <\/div>\r\n<p>@response@<\/p>\r\n<\/div>\r\n<\/li>"

function updateresponse(cont)
{
	
var html= RESP_CHAT_NODE.replace("@response@",cont);
updateChatRecord(htmlToElement(html));
objDiv = document.getElementById("init");
objDiv.value="false";
return cont;	
	
}
function updatechatwindow()
{
	var obj=document.getElementById('uiMessage');
	document.getElementById('message').value=obj.value;
	var html= CHAT_NODE.replace("@response@", obj.value);
   	 updateChatRecord(htmlToElement(html));
	obj.value="";
}

function htmlToElement(html) {
    var template = document.createElement('template');
    html = html.trim(); // Never return a text node of whitespace as the result
    template.innerHTML = html;
    return template.content.firstChild;
}

function updateChatRecord(node)
{
	
	var m=document.getElementById('chatrecord');
	m.append(node);
	var objDiv = document.getElementById("chathistory");
objDiv.scrollTop = objDiv.scrollHeight;
}
function submitform()
{
	updatechatwindow();
	var m=document.getElementById('frmchat');
	JsonSubmit(m,updateresponse);
return false;
}

