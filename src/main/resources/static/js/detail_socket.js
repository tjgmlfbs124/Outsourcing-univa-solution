var chatForm = document.getElementById("chat-form");
var stompClient = null;

console.log("user : " , username);
console.log("solution_id : " , solution_id);
console.log("type : " , type);
function connect(event) {
    if(username) {
        var socket = new SockJS('/solution/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}

function onConnected() {
    $("#isConnect-chat-row").show();
    stompClient.subscribe('/subs/'+solution_id, onMessageReceived);
    //(Object) subscribe(destination, callback, headers = {})
    //명명된 목적지"/topic/public"을 구독합니다.


    stompClient.send("/app/addUser/"+solution_id,
        {},
        JSON.stringify({
          sender: type,
          type: 'JOIN'
        })
    )

}

function onError(error) {

}


function sendMessage(event) {
    var messageInput = $("#chat-message").val();
    console.log("messageInput : " , messageInput);

    if(messageInput && stompClient) {
        var chatMessage = {
            sender: type,
            content: messageInput,
            type: 'CHAT'
        };
        stompClient.send("/app/sendMessage/"+solution_id, {}, JSON.stringify(chatMessage));
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    console.log("message : " , message);
    if(message.type === 'JOIN') {

    }
    else if (message.type === 'LEAVE') {

    }
    else {

    }
}


// 시스템 알리미
function addSystemChatRow(msg){
  $("chat-row").append(""+
    "<a class=\"tt-item\" id=\"isConnect-chat-row\" style=\"display:none;\">"+
      "<div class=\"tt-col-avatar\">"+
         "<svg class=\"tt-icon\">"+
            "<use xlink:href=\"#icon-ava-s\"></use>"+
         "</svg>"+
      "</div>"+
      "<div class=\"tt-col-description\">"+
         "<div class=\"tt-message\">"+msg+"</div>"+
      "</div>"+
    "</a>"
  );
}

// 채팅 문장 추가
function addSystemChatRow(msg){
  $("chat-row").append(""+
    "<a class=\"tt-item\" id=\"isConnect-chat-row\" style=\"display:none;\">"+
      "<div class=\"tt-col-avatar\">"+
         "<svg class=\"tt-icon\">"+
            "<use xlink:href=\"#icon-ava-s\"></use>"+
         "</svg>"+
      "</div>"+
      "<div class=\"tt-col-description\">"+
         "<div class=\"tt-message\">"+msg+"</div>"+
      "</div>"+
    "</a>"
  );
}

// 채팅 추가
function addChatRow(icon, sender, msg, date){
  $("chat-row").append(""+
    "<a class=\"tt-item\">"+
        "<div class=\"tt-col-avatar\">"+
          "<svg class=\"tt-icon\">"+
            "<use xlink:href=\""+ icon + "\"></use>"+
          "</svg>"+
        "</div>"+
        "<div class=\"tt-col-description\">"+
          "<h4 class=\"tt-title\">"+
            "<span>"+sender+"</span>"+
            "<span class=\"time\">"+date+"</span>"+
          "</h4>"+
          "<div class=\"tt-message\">"+
            msg +
          "</div>"+
        "</div>"+
    "</a>"
  );
}


// 채팅 이미지 추가
function addImageRow(icon, sender, url, date){
  $("#chat-row").append(""+
    "<a class=\"tt-item\">"+
      "<div class=\"tt-col-avatar\">"+
        "<svg class=\"tt-icon\">"+
          "<use xlink:href=\""+icon+"\"></use>"+
        "</svg>"+
      "</div>"+
      "<div class=\"tt-col-description\">"+
        "<h4 class=\"tt-title\">"+
          "<span>"+sender+"</span>"+
          "<span class=\"time\">"+date+"</span>"+
        "</h4>"+
        "<div class=\"tt-message\">"+
          "<p>"+
            "<img class=\"tt-offset-11\" src=\""+url+"\" alt=\"\" style=\"max-width:200px;\">"+
          "</p>"+
        "</div>"+
      "</div>"+
    "</a>"
  );
}

function writerToicon(writer){
  if(writer == 0)
    return "<use xlink:href=\"#icon-ava-q\"></use>";
  else
    return "<use xlink:href=\"#icon-ava-a\"></use>";
}

function writerToname(writer){
  if(writer == 0 ) return "질문자";
  else return "관리자";
}

function dateTotime(date){
  var temp = (date.split("T")[1]).split(":");
  return temp[0]+":"+temp[1];
}

chatForm.addEventListener('submit',sendMessage, true);
