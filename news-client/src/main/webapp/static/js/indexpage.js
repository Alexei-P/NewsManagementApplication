/**
 * 
 */



$(document).ready(function() {
		$.get("/news-client/getNews", function(data, status) {
			$.each(data, function(index, value){
					var newsId = value.id;
					var mainTitle = value.mainTitle;
					var shortTitle = value.shortTitle;
					var content = value.content;
					var author = value.author.name;
					var publicationDate = value.publicationDate.month+" "+value.publicationDate.dayOfMonth+" "+value.publicationDate.year;
					var modificationDate = value.modificationDate;
					var views = value.views;
					var tagList = value.tagList;
					var commentsList = value.commentsList;					
				$(".news-portal").append("<div class=\"jumbotron\"><h1>"+mainTitle+"</h1>"+
						  "<p>"+content+"</p>"+
						  "<p><a class=\"btn btn-primary btn-lg read-news\" href=\""+"/news-client/getNews/"+newsId+"\" role=\"button\">Read</a></p>"+
						"</div>");
			})
		});
		
		$(document).on("click", ".btn", function(e){
			e.preventDefault();			
			var href = $(this).attr('href');
			/*var newsTemplate = $("#news-content").html();
			$("body").html(newsTemplate);*/
			
			var newsId;
			var mainTitle;
			var shortTitle;
			var content;
			var author;
			var publicationDate;
			var modificationDate;
			var views;
			var tagList;
			var commentsList;
			$.get(href, function(data, status){
				newsId = data.id;
				mainTitle = data.mainTitle;
				shortTitle = data.shortTitle;
				content = data.content;
				author = data.author.name;
				publicationDate = data.publicationDate.month+" "+data.publicationDate.dayOfMonth+" "+data.publicationDate.year;
				modificationDate = data.modificationDate;
				views = data.views;
				tagList = data.tagList;
				commentsList = data.commentsList;
				$.get("static/views/templates/NewsPage.html").then(function(template){
					$(".news-portal").empty();
					$(".news-portal").html(template);
					$("#main-title").text(mainTitle);
					$("#short-title").text(shortTitle);
					$("#content").text(content);
					var tagsString="";
					$.each(tagList, function(index, tagObject){
						if (index != tagList.length-1){
						$("#tags").append(tagObject.tag+", ");
						}else{
							$("#tags").append(tagObject.tag);
						}
					});
					$.get("static/views/templates/CommentTemplate.html").then(
					function(templateComment){
						var i = 0;
						var templateCommentHtml = $(templateComment).html();
						var templateCompiled = Handlebars.compile(templateCommentHtml);
						var placeHolder = $(".blog-post");
						while (i<commentsList.length){
							var html = templateCompiled(commentsList[i]);
							placeHolder.append(html);
							i++;
						}
					})					
				});
			});
		});
});


	

