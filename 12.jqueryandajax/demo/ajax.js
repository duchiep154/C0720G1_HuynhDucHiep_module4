$.ajax({
    type: "POST", //GET, DELETE, PUT...
    url: "/getPosts/",
    data: "{empid: " + empid + "}",
    contentType: "application/json; charset=utf-8",
    dataType: "json",
	// dataType: "text",
    success: function(result){
        console.log(result);
    },
	error: function(result) {
		
	}
});