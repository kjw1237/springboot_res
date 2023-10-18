$(document).ready(function(){
    console.log("test");
});

function fn_onlyNumber(id) {
    let element = document.getElementById(id);
    element.value = element.value.replace(/[^0-9]/gi, "");
}