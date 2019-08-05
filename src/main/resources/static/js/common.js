$(function(){
	//表格行，鼠标放上去变色
	$(".tr:odd").css("background", "#FFFCEA");
	$(".tr:odd").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#00A1DE");
		}, function(){
			$(this).css("background-color", "#FFFCEA");
		});
	});
	$(".tr:even").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#00A1DE");
		}, function(){
			$(this).css("background-color", "#fff");
		});
		//#00A1DE    #FFE1FF
	}); 

	/*ie6,7下拉框美化*/
    if ( $.browser.msie ){
    	if($.browser.version == '7.0' || $.browser.version == '6.0'){
    		$('.select').each(function(i){
			   $(this).parents('.select_border,.select_containers').width($(this).width()+5); 
			 });
    		
    	}
    }
 
});


		//Excel模板下载
   		function downloadTemplate (num) {
   			if (num==1) {
   				if (confirm("下载Excel模板")==true) {
   					//window.open("/static/exceltemplate/gross.xls");
   				 	window.location.href = "/crud/gross/downloadExcel";
   			
   				 	
   				}
   			}
   			
   			if (num==2) {
   				if (confirm("下载Excel模板")==true) {
   					//window.location.href = "";
   					window.location.href = "/crud/regionalprofit/downloadExcel";
		}
   			}
   			if (num==3) {
   				if (confirm("下载Excel模板")==true) {
   					window.open("");
   				}
   			}
   		}
   		
   		//取消按钮
   		function clearBtn (btnNun) {
   			if(btnNun==1){
   				if (confirm("取消文件导入！")==true) {
   					$('#location01').val("");
   				}
   			}
   			if(btnNun==2){
   				if (confirm("取消文件导入！")==true) {
   					$('#location02').val("");
   				}
   			}
   			if(btnNun==3){
   				if (confirm("取消文件导入！")==true) {
   					$('#location03').val("");
   				}
   			}
   		}

   		//数据展示按钮
   		function showBtn (num){
   			if (num==1) {
 				window.location.href="/gross/showList/1/10";
   			}
   			if (num==2) {
 				window.location.href="/regionalprofit/showList/1/10";
   			}
   			if (num==3) {
 				window.location.href="/gross/showList/1/10";
   			}
 	   	}
   		//Excel文件导入上传
   		function uploadExcel (num) {
   			if (confirm("确定导入Excel文件！")==true) {
	   			if(num==1){
	   				if ($('#location01').val()!="") {
	   					alert($("#importFileForm")[0]);
	   					if($("#importFileForm")[0]!=null){
	   						var formData = new FormData($("#importFileForm")[0]);
	   						alert("123456");
	   						$.ajax({
	   							type:"post",
	   							url:"/crud/gross/uploadExcel",
	   							data:formData,
	   							async:false,
	   							cache: false, 
				                contentType: false, 
				                processData: false, 
				                success: function (result) {
				                    alert(result.message);
				                },
				                error : function() {
				                     alert("提交异常!");
				                }
	   						});
	   					}	
	   				} else{
	   					alert("请选择要导入的数据表格！");
	   				}
	   			}
	   			
	   			if(num==2){
	   			
   				if ($('#location02').val()!="") {
   					//alert($("[name='importFileForm']")[1])
   					if($("[name='importFileForm']")[0]!=null){
	   						var formData = new FormData($("[name='importFileForm']")[0]);
	   						alert(formData);
	   						$.ajax({
	   							type:"post",
	   							url:"/regionalprofit/uploadExcel",
	   							data:formData,
	   							async:false,
	   							cache: false, 
				                  contentType: false, 
				                  processData: false, 
				                  success: function (result) {
				                     alert(result.message);
				                  },
				                  error : function() {
				                      alert("提交异常!");
				                  }
	   						});
	   					}
   				} else{
   					alert("请选择要导入的数据表格！");
   				}
   			}
	   			
	   			
	   		if(num==3){
   				if ($('#location03').val()!="") {
   					if($("[name='importFileForm']")[2]!=null){
   						alert("表单三提交成功");
   					}else{
   						alert("请选择要导入的数据表格！");
   					}
   				} else{
   					alert("请选择要导入的数据表格！");
   				}
   			}	
	   			
   			}
   			
   			
   		}