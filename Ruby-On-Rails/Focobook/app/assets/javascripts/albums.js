$listImage =[];
count = -1;
function clickCloseItem(){
  $(this).parent().parent().remove();
  $listImage[$(this).attr("id")] = "";
 console.log( $listImage);
};


$(document).ready(function(){
  $(".btn-upload").on("change", function(){
    var preview = document.querySelector('.images');
    var files   = document.querySelector('input[type=file]').files;

    function readAndPreview(file) {
      if ( /\.(jpe?g|png|gif)$/i.test(file.name) ) {
        $listImage.push(file);
        console.log($listImage);

        var reader = new FileReader();
        reader.addEventListener("load", function () {
          count++;
          var image = '<div class="float-left m-2"><div ng-repeat="file in imagefinaldata" class="img-wrp"><img src="' + this.result + '" class="size-my-photos"/><img class="btn-close-album m-1" src="/assets/close.png" id = "'+ count +'" onclick="clickCloseItem.call(this)" /></div></div>';
           $('.images').prepend(image);
        }, false);
        reader.readAsDataURL(file);
      };
    };

    if (files) {
      [].forEach.call(files, readAndPreview);
    };
  });
});

function filterImage(file){
  return file !="";
}

$(document).ready(function(){
  $('#new_album').submit(function(event) {
    event.preventDefault();
    var form = $('#new_album').serializeArray();
    var formData = new FormData();
    for(var i=0;i<form.length;i++){
    formData.append(form[i].name, form[i].value);
    console.log(form[i].name);
  }
  $listImage = $listImage.filter(img => filterImage(img));
  for(var i = 0; i<$listImage.length;i++){
    var file = $listImage[i];
    formData.append('album[images][]', file, file.name);
  }
  console.log(formData.getAll('album[images][]'));
  var u = $('#new_album').attr('action');
  if($listImage.length > 25){
    toastr["error"]("Please upload maximum 25 images!");
  }else{
    $.ajax({
    url: u,
    data:formData,
    type: 'POST',
    contentType: false,
    processData: false
    });
  }
  });


  $('#edit_album').submit(function(event) {
    event.preventDefault();
    var form = $('#new_album').serializeArray();
    var formData = new FormData();
    for(var i=0;i<form.length;i++){
    formData.append(form[i].name, form[i].value);
    console.log(form[i].name);
  }
  $listImage = $listImage.filter(img => filterImage(img));
  for(var i = 0; i<$listImage.length;i++){
    var file = $listImage[i];
    formData.append('album[images][]', file, file.name);
  }
  console.log(formData.getAll('album[images][]'));
  var u = $('#edit_album').attr('action');
  if($listImage.length > 25){
    toastr["error"]("Please upload maximum 25 images!");
  }else{
    $.ajax({
    url: u,
    data:formData,
    type: 'POST',
    contentType: false,
    processData: false
    });
  }
  });
});
