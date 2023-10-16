const birth = $("#birth").val();
const splitBirth = birth.split("-");

console.log(splitBirth[0]);
console.log(splitBirth[1]);
console.log(splitBirth[2]);

$("#birthYear").val(splitBirth[0]).text(splitBirth[0]);
$("#birthMonth").val(splitBirth[1]).text(splitBirth[1]);
$("#birthDay").val(splitBirth[2]).text(splitBirth[2]);

function profileFormValidation() {
    if($("#name").val() === "") {
        alert("이름을 입력해주세요.");
        $("#name").focus();
        return false;
    } else if($("#birth-year").val() == null) {
        alert("출생년도를 선택해주세요");
        return false;
    } else if($("#birth-month").val() == null) {
        alert("월을 선택해주세요");
        return false;
    } else if($("#birth-day").val() == null) {
        alert("일을 선택해주세요");
        return false;
    } else if($("#storeName").val() === "") {
        alert("매장명을 입력해주세요.");
        $("#storeName").focus();
        return false;
    } else if($("#storeAddres").val() === "") {
        alert("매장 주소를 입력해주세요.");
        $("#storeAddres").focus();
        return false;
    } else if($("#openTime").val() === "") {
        alert("매장 오픈 시간을 입력해주세요.");
        $("#openTime").focus();
        return false;
    } else if($("#closeTime").val() === "") {
        alert("매장 마감 시간을 입력해주세요.");
        $("#closeTime").focus();
        return false;
    } else {
        return true;
    }
}

function changePwdValidation() {
    const pwdRegexp = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;

    if($("#pwd") === "") {
        alert("비밀번호를 입력해주세요.");
        $("#pwd").focus();
        return false;
    } else if(!pwdRegexp.test($("#pwd").val())) {
        alert("비밀번호는 특수문자를 포함하여 8~16자리 이내로 입력해주세요.");
        $("#pwd").focus();
        return false;
    }  else if($("#pwd").val() !== $("#pwdChk").val()) {
        alert("비밀번호가 일치하지 않습니다.");
        $("#pwdChk").focus();
        return false;
    } else {
        return true;
    }
}