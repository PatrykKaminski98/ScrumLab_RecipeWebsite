let button = document.getElementById("submit");

button.addEventListener("click", function (e){
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let email = document.getElementById("email").value;

    if(firstName === ""){
        e.preventDefault()
        alert("Imie nie moze byc puste!")
    }
    if(lastName === ""){
        e.preventDefault()
        alert("Nazwisko nie moze byc puste!")
    }
    if(email === ""){
        e.preventDefault()
        alert("Email nie moze byc pusty!")
    }


})