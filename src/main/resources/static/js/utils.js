/**
  * postApi
  *   ajax를 사용하여 서버에 post를 요청함.
  */
function postAPI(url, data, successCallback){
  $.ajax({
      type : 'POST',
      url : url,
      dataType : 'json',
      data : data,
      contentType : "application/json; charset=UTF-8",
      processData: false,
      success : function(result) {
        // TODO: result
        successCallback(result);
      },
      error: function(request, status, error) {
        console.log("Error post Api state : " + status);
        console.log("Error post Api error : " + error);
      }
  }) // End Ajax Request
}

/**
  *  sortBy
  *   객체단위에 날짜가 있는 배열을 정렬하여 배열로 리턴.
  */
var sortBy = (function () {
  var toString = Object.prototype.toString,
      parse = function (x) { return x; },
      getItem = function (x) {
        var isObject = x != null && typeof x === "object";
        var isProp = isObject && this.prop in x;
        return this.parser(isProp ? x[this.prop] : x);
      };
  return function sortby (array, cfg) {
    if (!(array instanceof Array && array.length)) return [];
    if (toString.call(cfg) !== "[object Object]") cfg = {};
    if (typeof cfg.parser !== "function") cfg.parser = parse;
    cfg.desc = !!cfg.desc ? -1 : 1;
    return array.sort(function (a, b) {
      a = getItem.call(cfg, a);
      b = getItem.call(cfg, b);
      return cfg.desc * (a < b ? -1 : +(a > b));
    });
  };
}());

function convertDate(date){
  console.log("date : ",  date);

}
