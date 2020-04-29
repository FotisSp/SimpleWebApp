const elem = document.querySelector('input[name="birthdate"]');
const datepicker = new Datepicker(elem, {
  autohide: true,
  disableTouchKeyboard: true,
  format: 'd M yyyy',
  minDate: '01 01 1900',
  maxDate: setCurrentDate(),
  todayBtn: true,
  todayHighlight: true,
});

function setCurrentDate() {
  var today = new Date();
  var dd = today.getDate();
  var mm = today.getMonth() + 1;
  var yyyy = today.getFullYear();
  if (dd < 10) {
    dd = '0' + dd
  }
  if (mm < 10) {
    mm = '0' + mm
  }

  today = dd + ' ' + mm + ' ' + yyyy;
  return today;
}
