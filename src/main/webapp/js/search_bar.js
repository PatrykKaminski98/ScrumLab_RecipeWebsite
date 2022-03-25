let searchBarInput = document.querySelector(".search-input");
let recipeNames = document.querySelectorAll(".recipe-name");

searchBarInput.addEventListener("input", function (e) {

    if (searchBarInput.value === "") {
        recipeNames.forEach(function (element) {
            element.parentElement.classList.remove("hidden");
            element.parentElement.classList.add("d-flex");
        });
    } else {
        recipeNames.forEach(function (element) {
            let input = element.innerHTML
            if (!input.includes(searchBarInput.value)) {
                element.parentElement.classList.add("hidden");
                element.parentElement.classList.remove("d-flex");
            } else {
                element.parentElement.classList.remove("hidden");
                element.parentElement.classList.add("d-flex");
            }
        });
    }

});