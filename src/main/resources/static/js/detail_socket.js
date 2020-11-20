var chatForm = document.getElementById("chat-form");
var stompClient = null;

var socket = new SockJS('/solution/ws');
stompClient = Stomp.over(socket);
stompClient.connect({}, onConnected, onError);

function connect(event) {
    if(username) {
        var socket = new SockJS('/solution/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }

    
    event.preventDefault();
}

function onConnected() {
    console.log("onConnected");
    addSystemChatRow("채팅이 연결되었습니다.");
    stompClient.subscribe('/subs/'+solution_id, onMessageReceived);
    stompClient.send("/app/addUser/"+solution_id,
        {},
        JSON.stringify({
          sender: type,
          type: 'JOIN'
        })
    );
}

function onError(error) {

}

function sendMessage(event) {
    var messageInput = $("#chat-message").val();

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

function sendImage(url) {
    if(url && stompClient) {
        var chatMessage = {
            sender: type,
            content: url,
            type: 'IMG'
        };
        stompClient.send("/app/sendMessage/"+solution_id, {}, JSON.stringify(chatMessage));
    }
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var type = message.type;
    var writer = message.sender;
    var content = message. content;
    var date = message.date;

    if(writer == "2"){
      icon = "#icon-ava-a";
      writer = "운영자";
    }
    else{
    icon = "#icon-ava-q";
    writer = "질문자";
    }

    switch(type){
      case "CHAT" :
        addChatRow(icon, writer, content, date)
        break;
      case "IMG" :
        addImageRow(icon, writer, content, date)
        break;
    }

    // if(message.type === 'JOIN') {
    //
    // }
    // else if (message.type === 'LEAVE') {
    //
    // }
    // else {
    //
    // }
}

// 시스템 알리미
function addSystemChatRow(msg){
  console.log("msg : " + msg);
  $("#chat-row").append(""+
    "<a class=\"tt-item\">"+
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
  $("#chat-row").append(""+
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
            "<img class=\"tt-offset-11\" src=\"" + "img?id=" +url+"\" alt=\"\" style=\"max-width:200px;\">"+
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

function dateFomatter(date){
  var year = date.getFullYear();
  var month = date.getMonth()+1;
  var day = date.getDate();
  var hour = date.getHours();
  var minute = date.getMinutes();
  return year+"-"+month+"-"+day+" "+hour+":"+minute;

}

chatForm.addEventListener('submit',sendMessage, true);
