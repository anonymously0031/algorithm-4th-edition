function sort(arr) {
    if(arr.length<=1){
        return arr;
    }
    var left = arr.slice(0,arr.length/2);
    var right = arr.slice(arr.length/2);
    return merge(sort(left), sort(right));
}
function merge(left,right){
    var temp = [];
    while (left.length > 0 && right.length > 0) {
        var x = left.shift();
        var y = right.shift();
        if(x<=y){
            temp.push(x);
            right.unshift(y);
        }else{
            temp.push(y);
            left.unshift(x);
        }
        if (left.length == 0) {
            return temp.concat(right);
        }else{
            return temp.concat(left);
        }
    }
}