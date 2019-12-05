  $("#tiJiao").click(function(){
	  
      if($("#files").val() == ""){
          alert("请选择要上传的文件");
      }else{
//          CommonPerson.Base.LoadingPic.FullScreenShow();
          $("#tkDiv").hide();
          $("#tk1").hide();
          $("#uploadForm").submit();  
      }
 })
 
 //设置提交成功后返回的页面
    function successFun(data,status) {
        $("#showForm").html(data.msg);      //提交表单后从后台接收到的返回来的数据，我保存到了msg里
        // $("#showForm").html("或者这里可以直接写想要显示的内容")
}