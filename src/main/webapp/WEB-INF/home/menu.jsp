
<div id="nav" class="vmenu">
    <a class="menu_link${HOME}" href="<%=request.getContextPath()%>/home">Home Page</a>
    <a class="menu_link${UM}" href="<%=request.getContextPath()%>/UserManagement?action=list">User Management</a>
    <a class="menu_link" href="#">Link 2</a>
    <a class="menu_link" href="#">Link 3</a>
</div>

<script>
    //add active class to the current button (highlight it)
    var header = document.getElementById("nav");
    var links = header.getElementsByClassName("menu_link");

    for(var i = 0; i< links.length; i++){
        links[i].addEventListener("click", function (){
            var current = document.getElementsByClassName("active");
            current[0].className = current[0].className.replace(" active", "");
            this.className += " active";
        });
    }

</script>