// 학생상세정보창 팝업
function openStudentDetail(stdId) {
    var option = "width=1600,height=800,top=120,left=20";
    window.open("/student/" + stdId, "학생상세정보창", option);
}