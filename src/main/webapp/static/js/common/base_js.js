var laypage,layer,layedit,laydate,form,table,laytpl;
layui.use(['laypage', 'form', 'layedit', 'laydate', 'element', 'table' ,'upload' ,'laytpl'], function(){
  	layer = layui.layer,
  	form = layui.form;
  	table  = layui.table;
  	element = layui.element,
  	upload  = layui.upload ,
  	laypage = layui.laypage,
  	layedit = layui.layedit,
  	laydate = layui.laydate,
  	laytpl = layui.laytpl,
  	
  	
  	//自定义验证规则
	form.verify({
	    title: function(value){
	      if(value.length < 5){
	        return '标题至少得5个字符啊';
	      }
	    }
	    ,img:function(value){
	    	if(value == ""){
	    		return '请上传图片';
	    	}
	    }
	    ,max: function(value,ele){
	    	var max = $(ele).data("max");
	    	if(parseFloat(value) > parseFloat(max)){
	    		return '余额不足';
	    	}
	    }
	    ,password: [/(.+){6,12}$/, '密码必须6到12位']
	    ,pwd: function(value){
	    	if(value == ""){
	    		return '请输入确认密码';
	    	}else if(value != $("#pwd").val()){
	    		return '两次输入密码不一致';
	    	}
	    }
	    ,select: function(value,ele){
	    	if(value == 0 || value == ''){
	    		return "请选择"+$(ele).data("name");
	    	}
	    }
	    ,content: function(value,ele){
    		layedit.sync($(ele).data("index"));
    		if($(ele).val() == ""){
    			return "请填写内容";
    		}
	    }
	    ,projectIds: function(value,ele){
	    	var str = "";
	    	$(".project:visible").each(function(i){
	    		if(i > 0){
	    			str += ',';
	    		}
	    		str += $(this).data("val");
	    	});
	    	$(ele).val(str);
		    return "";
	    }
	    ,moduleIds: function(value,ele){
	    	var str = "";
	    	$(".module:checked").each(function(i){
	    		if(i > 0){
	    			str += ',';
	    		}
	    		str += $(this).val();
	    	});
	    	$(".module:checked").parent().each(function(){
	    		str += ','+$(this).data("val");
	    	});
	    	$(ele).val(str);
		    return "";
	    }
	    ,details: function(value,ele){
	    	var str = "[";
	    	$($(ele).data("target")).each(function(i){
	    		if(i > 0){
	    			str += ',{';
    			}else{
	    			str += '{';
    			}
	    		$(this).parents(".detail-item").find("input[data-detail]").each(function(j){
	    			if(j > 0){
		    			str += ',';
	    			}
	    			str += '"'+$(this).data("detail")+'":"'+$(this).val()+'"';
	    		});
	    		str += '}';
	    	});
	    	str += ']';
		    $(ele).val(str);
		    return "";
	    }
	});
	
	//监听顶部菜单
	element.on('nav(topMenu)', function(elem){
		$(".leftMenu").addClass("layui-hide");
		$(".leftMenu"+elem.data("id")).removeClass("layui-hide");
	});
	
	//监听左侧菜单
	element.on('nav(leftMenu)', function(elem){
		var url = elem.data("url");
		var id = elem.data("id");
		if(url && url != ''){
			element.tabDelete('body', id);
			element.tabAdd('body', {
				title: elem.data("name")
			  	,content: '<iframe height="100%" width="100%" style="border:none" src="'+url+'?id='+id+'"></iframe>' //支持传入html
			  	,id: id
			});
			element.tabChange('body', id);
			/*<#--$.ajax({
			  type: 'get',
			  url: url,
			  data: {id:id},
			  dataType: "html",
			  success: function(data){
	  			element.tabDelete('body', id);
				element.tabAdd('body', {
					title: elem.data("name")
				  	,content: data //支持传入html
				  	,id: id
				});
				element.tabChange('body', id);
				listData(id,1);
			  },
			  error: function(){
			  	layer.msg("请求失败", {icon: 5,anim: 6});
			  }
			});	-->*/
		}
	});
	
    form.on('select(provinceId)', function(elem){
		$("#cityId").html($("#citysMap"+elem.value).html());
		form.render('select', 'form');
    });      
    form.on('select(btn-search)', function(elem){
    	$(".btn-search").click();
    });      
    form.on('select(cascade)', function(elem){
    	$($(elem.elem).data("target")).html($($(elem.elem).data("source")+elem.value).html());
		form.render('select', 'form');
    });
    form.on('select(currencyMark)', function(elem){
    	alert($(elem.elem).find("option:selected").data("mark"));
    	$("#currencyMark").val($(elem.elem).find("option:selected").data("mark"));
    	
    });   
    form.on('select(currencyId)', function(elem){
    	var html = "";
    	$(elem.elem).children().each(function(){
	    	if(this.value && this.value !='' && this.value != elem.value){
		        var tpl = $("#tmp-detail").html();
		        laytpl(tpl).render({"id":this.value,"name":this.innerHTML}, function (render) {
		           html += render;
		        });
	    	}
    	});
    	if(html.length > 0){
    		$("#checkboxVal").val("");
    		$("#currencyDiv").html(html);
	    	form.render('checkbox', 'form');
    	}
    });      
    form.on('select(selectItem)', function(elem){
		laytpl($("#selectItem"+elem.value).html()).render({}, function(html){
		  	$("#selectItem").html(html);
		});
	  	form.render('select', 'form');
    });
    form.on('select(linkType)', function(elem){
    	if(elem.value == 1){
	    	laytpl(linkType1.innerHTML).render({}, function(html){
			  	$("#linkType").html(html);
			  	var index  = layedit.build('content',{uploadImage:{url: '/uploadFile', type: 'post'}}); //建立编辑器 
				$("#content").attr("data-index",index);
			});
    	}else if(elem.value == 2){
    		laytpl(linkType2.innerHTML).render({}, function(html){
			  	$("#linkType").html(html);
			});
    	}
    });
    form.on('checkbox(badge)', function(data){
    	var size = $(data.elem).parents(".layui-tab-item").find(":checked").size();
    	if(size > 0){
    		$($(data.elem).data("target")).removeClass("layui-hide").text(size);
    	}else{
    		$($(data.elem).data("target")).addClass("layui-hide");
    	}
    });
    /*<#--form.on('switch(feeFixed)', function(data){
    	var target = $(data.elem).data("target");
    	if(data.elem.checked){
	    	$("#"+target).val(1);
    		$("#label-"+target).addClass("layui-hide");
    	}else{
	    	$("#"+target).val(0);
	    	$("#label-"+target).removeClass("layui-hide");
    	}
	});-->*/
	form.on('switch(switchItem)', function(data){
    	var val = $(data.elem).data("val").split("|");
    	if(data.elem.checked){
    		data.elem.value = val[0];
    	}else{
    		data.elem.value = val[1];
    	}
    });
	form.on('switch(target)', function(data){
    	var target = $(data.elem).data("target");
    	var targetLabel = $(data.elem).data("label");
    	if(data.elem.checked){
	    	$("#"+target).val(1);
	    	if(targetLabel){
    			$("#"+targetLabel).addClass("layui-hide");
	    	}
    	}else{
	    	$("#"+target).val(0);
	    	if(targetLabel){
	    		$("#"+targetLabel).removeClass("layui-hide");
	    	}
    	}
	});
	form.on('switch(feeOpen)', function(data){
		var id = $(data.elem).data("id");
		var feeOpen = 0;
    	if(data.elem.checked){
    		feeOpen = 1;
    	}
		btn("/currency/user/fee",{"id":id,"feeOpen":feeOpen});
	});  
    
    form.on('submit(save)', function(data){
    	if(data.form){
	    	$.ajax({
			  type: data.form.method,
			  url: data.form.action,
			  data: data.field,
			  success: function(data){
			  	if(data > 0){
			  		layer.msg("保存成功", { icon: 1,time: 1000},function(){
				  		iframeClose();
				  		$(".btn-search", window.parent.document).click();
			  		});
			  	}else if(data == -1){
			  		layer.msg("币种总量不足，请修改发行总量", {icon: 5});
			  	}else if(data == -2){
			  		layer.msg("手机号已存在", {icon: 5});
			  	}else if(data == -3){
			  		layer.msg("身份证号已存在", {icon: 5});
			  	}else{
			  		layer.msg("保存失败", {icon: 5});
			  	}
			  },
			  error: function(){
			  	layer.msg("请求失败", {icon: 5,anim: 6});
			  }
			});
    	}
    	return false;
    });      
	
	if(typeof(init) == "function"){
		init();
	}
})


function iframeBtn(title,url,type){
	if(url && url != ''){
		if(!type && type != 0){
			type = 2;
		}
		layer.open({
		  type: type,
		  title: title,
		  shadeClose: true,
		  shade: 0.8,
		  area: ['90%','90%'],
		  content: url
		}); 
	}
}

function iframeBtnChange(title,url,type){
	if(url && url != ''){
		if(!type && type != 0){
			type = 2;
		}
		layer.open({
		  type: type,
		  title: title,
		  shadeClose: true,
		  shade: 0.8,
		  area: ['70%','70%'],
		  content: url,
		  end: function () {
              location.reload();
            }
		}); 
	}
}

function btn(url,data,msg){
	if(!msg){
		msg = '确认要进行该操作?';
	}
	layer.confirm(msg, {icon: 3, title:'提示'}, function(index){
		layer.close(index);
		
		$.ajax({
		  type: 'post',
		  url: url,
		  data: data,
		  success: function(data){
		  	if(data > 0){
		  		layer.msg("成功", { icon: 1,time: 1000},function(){
			  		$(".btn-search").click();
		  		});
		  	}else if(data == -7){
		  	//提币相关
		  		layer.msg("请勿重复提交", {icon: 5});
		  	}else{
		  		layer.msg("失败", {icon: 5});
		  	}
		  },
		  error: function(){
		  	layer.msg("请求失败", {icon: 5,anim: 6});
		  }
		});	
	});
}

function delBtn(url,id,msg){
	if(!msg){
		msg = '确认要进行该操作?';
	}
	layer.confirm(msg, {icon: 3, title:'提示'}, function(index){
		layer.close(index);
		
		$.ajax({
		  type: 'post',
		  url: url,
		  data: {id:id},
		  success: function(data){
		  	if(data > 0){
		  		layer.msg("删除成功", { icon: 1,time: 1000},function(){
			  		$(".btn-search").click();
		  		});
		  	}else{
		  		layer.msg("删除失败", {icon: 5});
		  	}
		  },
		  error: function(){
		  	layer.msg("请求失败", {icon: 5,anim: 6});
		  }
		});	
	});
	
	
	
	
}

function iframeClose(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭   
}

/**
 * 分页js
 * 
 * @param pageNo
 *            页码
 */
function listData(id,pageNo) {
	if (pageNo) {
		// 改变页码
		$("#pageNo"+id).val(pageNo);
	}else{
		$("#pageNo"+id).val(1);
	}
	$("#pageFrom"+id).find("input:text").each(function(){
		$(this).val($.trim($(this).val()));
	});
	$.ajax({
		type : "POST",
		url : $("#pageFrom"+id).attr("action"),
		dataType : "html",
		data : $("#pageFrom"+id).serialize(),
		success : function(data) {
			if (data) {
				$("#pageData"+id).html(data);
			} else {
				$("#pageData"+id).html('');
			}
			$("table tr:even").addClass("odd_bg");
			
			if(typeof(listDataEnd) == "function"){
				listDataEnd();
			}
		},
		error : function(e) {
			alert("网络异常");
		}
	});
}

function toggleFullScreen(){
	document.fullscreenElement || document.mozFullScreenElement || document.webkitFullscreenElement ? document.cancelFullScreen ? document.cancelFullScreen() : document.mozCancelFullScreen ? document.mozCancelFullScreen() : document.webkitCancelFullScreen && document.webkitCancelFullScreen() : document.documentElement.requestFullscreen ? document.documentElement.requestFullscreen() : document.documentElement.mozRequestFullScreen ? document.documentElement.mozRequestFullScreen() : document.documentElement.webkitRequestFullscreen && document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT)
}

 function show(id,msg) {
        layer.tips(msg, "#"+id+"", {
          tips: [4, "#4794ec"]
        });
    }
