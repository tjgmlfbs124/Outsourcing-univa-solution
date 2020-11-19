// 평점 별을 클릭할때 발생하는 이벤트 처리
function onStar(score){
  $("input[name=score]").val(score);
  var list = $("#star-list").children();
  for (var i = 0; i < list.length; i++) {
    if(i < score) list[i].style.color = "#d81159";
    else list[i].style.color = "#666f74";
  }
}
// n 길이가 width보다 아래일때, n 앞에 0을 붙혀줌
function numberPad(n, width) {
    n = n + '';
    return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
}

// 답변쪽 이미지 파일 업로드
function ajaxFileUpload(mIndex) {
  $("#imageFile_" + numberPad(mIndex,2)).click();
}

// 채팅쪽 이미지 파일 업로드
function chatImageUpload() {
  $("#chat-image-form").click();
}

// 이미지 미리보기 지원하는 함수
function imagePreView(target, callback){
  if(!target) return;
  var num = (target.id).split("_")[1];
  var img = new Image();
  target.onchange = function (e) {
    e.preventDefault();
    var file = target.files[0], reader = new FileReader();
    reader.onload = function (event) {
      img.src = event.target.result;
      img.width = 150;
      img.height = 220;
      img.id=target.id;
      $("#image_"+num).attr("src", img.src);
    };
    reader.readAsDataURL(file);
    $(target.id).css("cursor","pointer");
  };
}

// 파일명 미리보기 함수
function fileNamePreView(target, callback){
  if(!target) return;
  var num = (target.id).split("_")[1];
  var img = new Image();
  target.onchange = function (e) {
    e.preventDefault();
    var file = target.files[0], reader = new FileReader();
    reader.onload = function (event) {
      img.src = event.target.result;
      img.id=target.id;
    };
    reader.readAsDataURL(file);
    $("#chat-image-name").val(file.name);
  };
}


function addQuestion(){
  var length = $("#answer-row").children().length;
  $("#answer-row").append(""+
    "<div class=\"row\" style=\"border-bottom:1px solid #e2e7ea;\">"+
    "<div class=\"col-md-3\">"+
        "<ul class=\"pt-edit-btn\">"+
            "<li>"+
              "<button type=\"button\" class=\"btn-icon\" style=\"cursor:\"><h4>사진</h4></button>"+
            "</li>"+
        "</ul>"+
        "<div class=\"form-group\">"+
            "<a href=\"#\" class=\"tt-button-icon\">"+
                "<span class=\"tt-icon\">"+
                  "<img style=\"max-width:120px; max-height:120px; min-width:120px; min-height:120px;\" id=\"image_"+numberPad(length,2)+"\" onclick=\"ajaxFileUpload('"+length+"')\"></img>"+
                  "<input type=\"file\" name=\"answerSub[" + (length-1) +"].file\" id=\"imageFile_" + numberPad(length,2) + "\" style=\"display:none;\"></img>"+
                  "<input type=\"text\" name=\"answerSub[" + (length-1) + "].number\" style=\"display:none;\" value=\"" + (length-1) + "\"/>"+
                "</span>"+
            "</a>"+
        "</div>"+
    "</div>"+
    "<div class=\"col-md-9\">"+
        "<div class=\"col-left\">"+
            "<ul class=\"pt-edit-btn\">"+
                "<li>"+
                  "<button type=\"button\" class=\"btn-icon\" style=\"cursor:\"><h4>질문내용</h4></button>"+
                "</li>"+
            "</ul>"+
        "</div>"+
        "<textarea name=\"answerSub[" + (length-1) + "].text\" class=\"form-control\" rows=\"5\" placeholder=\"사진에 대한 설명 및 답변을 입력하세요.\"></textarea>"+
    "</div>"+
    "</div>");
  imagePreView(document.getElementById("imageFile_"+numberPad(length,2)));
}

function addChatRow(writer, date, content){
  $("#chat-row").append(""+
  "<a class=\"tt-item\">"+
    "<div class=\"tt-col-avatar\">"+
       "<svg class=\"tt-icon\">"+
          writerToicon(writer)+
       "</svg>"+
    "</div>"+
    "<div class=\"tt-col-description\">"+
       "<h4 class=\"tt-title\"><span>"+writerToname(writer)+"</span> <span class=\"time\">"+dateTotime(date)+"</span></h4>"+
       "<div class=\"tt-message\">"+content+"</div>"+
       "<div class=\"tt-message\">"+
          "<p>"+
             // "<img class=\"tt-offset-11\" src=\"../images/single-topic-img01.jpg\" alt=\"\" style=\"max-width:200px;\">"+
          "</p>"+
       "</div>"+
    "</div>"+
  "</a>");
  document.getElementById("chat-row").scrollHeight;
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

function screenShot(index, div, callback){
  html2canvas(div).then(function(canvas){
      var el = document.getElementById("screenShot");
      el.href = canvas.toDataURL("image/jpeg");
      el.download=$("#nickname").text()+"_문제_"+index+".jpg";
      el.click();
      callback(index, canvas.toDataURL("image/jpeg"));
  });
}

function on(index, data){
  var div = $("#questionSlider").children()[2].children;
  div[index].style.display="block";
  screenShot(index, div[index], function(idx){
    if(div.length-1 > idx){
      index++;
      on(index);
    }
    else{
      window.location.reload();
    }
  })
}
