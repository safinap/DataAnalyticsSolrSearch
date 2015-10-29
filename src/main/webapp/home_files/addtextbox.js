   var id=2,txt_box;
	$('#button_pro').on('click','.add',function(){
		  $(this).remove();
		  txt_box='<div class="space" id="input_'+id+'" ><input type="text" name="val[]" class="left txt"/><img src="images/remove.png" class="remove"/><img class="add right" src="images/add.png" /></div>';
		  $("#button_pro").append(txt_box);
		  id++;
	});
	
	$('#button_pro').on('click','.remove',function(){
	        var parent=$(this).parent().prev().attr("id");
			var parent_im=$(this).parent().attr("id");
			$("#"+parent_im).slideUp('medium',function(){
				$("#"+parent_im).remove();
				if($('.add').length<1){
					$("#"+parent).append('<img src="images/add.png" class="add right"/> ');
				}
	        });
   });
   
   