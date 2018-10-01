//edit participants modal
$('#exampleModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var email = button.data('email') // Extract info from data-* attributes
  var name = button.data('name')
  var child = button.data('child')
  var adult = button.data('adult')
  var total = button.data('total')
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
