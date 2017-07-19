/**
 * 
 */
$(document).ready(function(){
    $("getAllNews").click(function(){
        $.get("${home}", function(data, status){
            alert("Data: " + data + "\nStatus: " + status);
        });
    });
});