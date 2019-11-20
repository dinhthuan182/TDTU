// # Place all the behaviors and hooks related to the matching controller here.
// # All this logic will automatically be available in application.js.
// # You can use CoffeeScript in this file: http://coffeescript.org/

function showImagePhoto(){
  if(this.files && this.files[0])
  {
    var obj = new FileReader();
    obj.onload = function(data){
      $('.image').prepend('<div class="img float-left m-2"><div ng-repeat="file in imagefinaldata" class="img-wrp"><img src="' + data.target.result + '" class="imgResponsiveMax size-my-photos" alt="" /><img class="btn-close m-1" src="/assets/close.png" onclick="clickClose.call(this)" /></div></div>');
      $('.upload-btn-wrapper').css({ display: "none" });
    }
    obj.readAsDataURL(this.files[0]);
  }
};

function clickClose(){
  $(this).parent().remove();
  $('.upload-btn-wrapper').css({ display: "block" });
};