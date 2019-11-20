// # Place all the behaviors and hooks related to the matching controller here.
// # All this logic will automatically be available in application.js.
// # You can use CoffeeScript in this file: http://coffeescript.org/

//nut change anh dai dien
document.querySelector("html").classList.add('js');
function showImage(){
  if(this.files && this.files[0])
  {
    var obj = new FileReader();
    obj.onload = function(data){
      var image = document.getElementById("image");
      image.src = data.target.result;
      image.style.display = "block"
    }
    obj.readAsDataURL(this.files[0]);
  }
}