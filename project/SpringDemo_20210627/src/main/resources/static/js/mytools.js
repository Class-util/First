//获取url的关键字
function getParamValue(key){
    var param = location.search;
    param = param.substring(1);
    var paramArr = param.split("&");
    for(var i = 0; i < param.length; i++){
        var item = paramArr[i].split("=");
        if(item[0] == key){
            return item[1]
        }
    }
    return null;
}