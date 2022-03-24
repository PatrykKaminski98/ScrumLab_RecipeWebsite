let button = document.getElementById("submit");

button.addEventListener("click", function (e) {
    let pass1 = document.getElementById("pass1").value
    let pass2 = document.getElementById("pass2").value

    if (pass1 === "" || pass1 !== pass2) {
        e.preventDefault();
        alert("Hala nie są takie same lub są puste!")
    } else {
        console.log("sa takie same dupa")
    }
});
