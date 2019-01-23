$.ajax({
	type : "POST",
    url : '/getValSize',
    success : function(data) {
        $('#valQtd').html(data);
    }
});