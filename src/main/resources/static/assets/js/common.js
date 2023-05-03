// 학생상세정보창 팝업
function openStudentDetail(stdId) {
    var option = "width: 500px, height: 100px, top:120px, left:20px"
    window.open("/student/" + stdId,"학생상세정보창",option);
}
$(function () {
})