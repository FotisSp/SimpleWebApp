function searchUser() {
  var input, filter, table, tr, tdName, tdLName, i, txtValueN, txtValueLN;
  input = document.getElementById("searchField");
  filter = input.value.toUpperCase();
  table = document.getElementById("users");
  tr = table.getElementsByTagName("tr");

  for (i = 0; i < tr.length; i++) {
    tdName = tr[i].getElementsByTagName("td")[0];
    tdLName = tr[i].getElementsByTagName("td")[1];
    if (tdName || tdLName) {
      txtValueN = tdName.textContent || tdName.innerText;
      txtValueLN = tdLName.textContent || tdLName.innerText;
      if (txtValueN.toUpperCase().indexOf(filter) > -1 || txtValueLN.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}
