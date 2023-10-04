function formValidation() {
    const loginIdRegexp = /^[a-z0-9]{6,12}$/;
    const pwdRegexp = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;

    if($("#loginId").val() === "") {
        alert("아이디를 입력해주세요.");
        $("#loginId").focus();
        return false;
    } else if(!loginIdRegexp.test($("#loginId").val())) {
        alert("아이디는 영문 소문자, 숫자를 포함해서 6~12자리 이내로 입력해주세요.");
        $("#loginId").focus();
        return false;
    } else if($("#pwd") === "") {
        alert("비밀번호를 입력해주세요.");
        $("#pwd").focus();
        return false;
    } else if(!pwdRegexp.test($("#pwd").val())) {
        alert("비밀번호는 특수문자를 포함하여 8~16자리 이내로 입력해주세요.");
        $("#pwd").focus();
        return false;
    } else if($("#pwd").val() !== $("#pwdChk").val()) {
        alert("비밀번호가 일치하지 않습니다.");
        $("#pwdChk").focus();
        return false;
    } else if($("#name").val() === "") {
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
    // } else if($("#openTime").val() === "") {
    //     alert("매장 오픈 시간을 입력해주세요.");
    //     $("#openTime").focus();
    //     return false;
    // } else if($("#closeTime").val() === "") {
    //     alert("매장 마감 시간을 입력해주세요.");
    //     $("#closeTime").focus();
    //     return false;
    } else {
        return true;
    }
}

function fn_idChk(){
    let loginId = $('#loginId').val();
    const loginIdRegexp = /^[a-z0-9]{6,12}$/;

    if(!loginIdRegexp.test($("#loginId").val())){
        alert('ID 형식이 올바르지 않습니다.');
        return false;
    } else if (!loginId) {
        alert('ID를 입력해주세요.')
        return false;
    } else {
        $.ajax({
            type : "post" ,
            url : "/api/member/verifyduplicateloginid" ,
            data : {"loginId" : loginId} ,
            success : function(suc) {
                if(!suc) {
                    alert("사용 가능한 아이디입니다.");
                } else {
                    alert("이미 사용중인 아이디입니다.");
                }
            } ,
            error : function (err) {
                console.log(err);
            }
        })
    }
}

function fn_openNap(val){

}



const birthYearEl = document.querySelector("#birth-year");
const birthMonthEl = document.querySelector("#birth-month");
const birthdayEl = document.querySelector("#birth-day");
isYearOptionExisted = false;
isMonthOptionExisted = false;
isdayOptionExisted = false;

const now = new Date();
const year = now.getFullYear();

birthYearEl.addEventListener('focus', function () {
    if(!isYearOptionExisted) {
        isYearOptionExisted = true;
        for(let i = (year-100); i <= year; i++) {
            const yearOption = document.createElement('option');
            yearOption.setAttribute('value', i);
            yearOption.setAttribute('name', 'year');
            yearOption.innerText = i;
            this.appendChild(yearOption);
        }
    }
})

birthMonthEl.addEventListener('focus', function () {
    if(!isMonthOptionExisted) {
        isMonthOptionExisted = true;
        for(let i = 1; i <= 12; i++) {
            const monthOption = document.createElement('option');
            monthOption.setAttribute('value', i);
            monthOption.setAttribute('name', 'month');
            monthOption.innerText = i;
            this.appendChild(monthOption);
        }
    }
})

birthdayEl.addEventListener('focus', function () {
    if(!isdayOptionExisted) {
        isdayOptionExisted = true;
        for(let i = 1; i <= 31; i++) {
            const dayOption = document.createElement('option');
            dayOption.setAttribute('value', i);
            dayOption.setAttribute('name', 'day');
            dayOption.innerText = i;
            this.appendChild(dayOption);
        }
    }
})

function fn_openKakaoMap(){
    new daum.Postcode({
        oncomplete: function(data) {

            var addr = '';
            var extraAddr = '';
            if (data.userSelectedType === 'R') {
                addr = data.roadAddress;
            } else {
                addr = data.jibunAddress;
            }
            if(data.userSelectedType === 'R'){
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                document.getElementById("storeAddres").value = extraAddr;
            } else {
                document.getElementById("storeAddres").value = '';
            }
            document.getElementById("storeAddres").value = addr;
        }
    }).open();
}