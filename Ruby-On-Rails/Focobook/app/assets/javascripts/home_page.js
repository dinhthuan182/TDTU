// # Place all the behaviors and hooks related to the matching controller here.
// # All this logic will automatically be available in application.js.
// # You can use CoffeeScript in this file: http://coffeescript.org/


$(document).ready(function(){
//nut like
  $('img.btn-like').click(function(){
    if($(this).attr('src')=="/assets/heart.png")
    {
      $(this).removeAttr("src");
      $(this).attr("src","/assets/like.png");
    }
    else
    {
      $(this).removeAttr("src");
      $(this).attr("src","/assets/heart.png");
    }
  });
});


