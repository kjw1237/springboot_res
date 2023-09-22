function formValidation() {
    const loginIdRegexp = /^[a-z0-9]{6,12}$/;
    const pwdRegexp = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;


    // if($("#loginId").val() === "") {
    //     alert("아이디를 입력해주세요.");
    //     $("#loginId").focus();
    //     return false;
    // } else if(!loginIdRegexp.test($("#loginId").val())) {
    //     alert("아이디는 영문 소문자, 숫자를 포함해서 6~12자리 이내로 입력해주세요.");
    //     $("#loginId").focus();
    //     return false;
    // } else if($("#pwd") === "") {
    //     alert("비밀번호를 입력해주세요.");
    //     $("#pwd").focus();
    //     return false;
    // } else if(!pwdRegexp.test($("#pwd").val())) {
    //     alert("비밀번호는 특수문자를 포함하여 8~16자리 이내로 입력해주세요.");
    //     $("#pwd").focus();
    //     return false;
    // } else if($("#pwd").val() !== $("#pwdChk").val()) {
    //     alert("비밀번호가 일치하지 않습니다.");
    //     $("#pwdChk").focus();
    //     return false;
    // } else if($("#name").val() === "") {
    //     alert("이름을 입력해주세요.");
    //     $("#name").focus();
    //     return false;
    // } else if($("#nickName").val() === "") {
    //     alert("닉네임을 입력해주세요.");
    //     $("#nickName").focus();
    //     return false;
    // } else if($("#birthdate").val() === "") {
    //     alert("생일을 입력해주세요.");
    //     $("#birthdate").focus();
    //     return false;
    // } else if($("#storeName").val() === "") {
    //     alert("매장명을 입력해주세요.");
    //     $("#storeName").focus();
    //     return false;
    // } else if($("#storeAddres").val() === "") {
    //     alert("매장 주소를 입력해주세요.");
    //     $("#storeAddres").focus();
    //     return false;
    // } else if($("#openTime").val() === "") {
    //     alert("매장 오픈 시간을 입력해주세요.");
    //     $("#openTime").focus();
    //     return false;
    // } else if($("#closeTime").val() === "") {
    //     alert("매장 마감 시간을 입력해주세요.");
    //     $("#closeTime").focus();
    //     return false;
    // } else {
    //     return true;
    // }
}

function fn_idChk(){
    console.log('run function');
    let loginId = $('#loginId').val();
    const loginIdRegexp = /^[a-z0-9]{6,12}$/;

    if(!loginIdRegexp.test($("#loginId").val())){
        alert('ID 형식이 올바르지 않습니다.');
        return false;
    } else if (!loginId) {
        alert('ID를 입력해주세요.')
        return false;
    }

    $.ajax({
        type : "get" ,
        url : "/api/member/check_id" ,
        data : ["loginId" , loginId] ,
        success : function(suc) {

        } ,
        error : function (err) {

        }
    })

}

function fn_openNap(val){

}