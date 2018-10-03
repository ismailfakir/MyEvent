//edit participants modal
$('#exampleModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var email = button.data('email') // Extract info from data-* attributes
  var name = button.data('name')
  var child = button.data('child')
  var adult = button.data('adult')
  var total = button.data('total')
  var confirm = button.data('confirm')
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var modal = $(this)
  //modal.find('.modal-title').text('New message to ' + recipient)
  //modal.find('.modal-body input').val(recipient)
  modal.find('.modal-body #modalParticipantEmail').val(email)
  modal.find('.modal-body #modalParticipantName').val(name)
  modal.find('.modal-body #modalParticipantChild').val(child)
  modal.find('.modal-body #modalParticipantAdult').val(adult)
  modal.find('.modal-body #modalParticipantTotal').val(total)


  //set confirm checkbox
  if(confirm){
    modal.find('.modal-body #modalConfirmCheckBox').prop('checked', true);
    modal.find('.modal-body #modalParticipantConfirmHidden').val("true")
  }else{
    modal.find('.modal-body #modalConfirmCheckBox').prop('checked', false);
    modal.find('.modal-body #modalParticipantConfirmHidden').val("false")
  }

});

//delete participants modal
$('#deleteModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var email = button.data('email') // Extract info from data-* attributes

  var modal = $(this)
  modal.find('.modal-title').text('Delete ' + email)
  //modal.find('.modal-footer #deleteModalParticipantEmail').href="@CSRF(routes.HomeController.deleteParticipant(\""+email+"\"))";
  modal.find('.modal-footer #deleteModalParticipantEmail').attr("href","/dashboard/participant/delete/"+email);

});

//change hidden value of checkbox element edit participants modal
$("#modalConfirmCheckBox").change(function() {
   if($(this).prop('checked')) {
       $('.modal-body #modalParticipantConfirmHidden').val("true");
   } else {
       $('.modal-body #modalParticipantConfirmHidden').val("false");
   }
});


