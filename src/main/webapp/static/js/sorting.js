const selectedCategories = new Set();
const categories = document.querySelectorAll(".category");
const selectedSuppliers = new Set();
const suppliers = document.querySelectorAll(".supplier");


function resetCategories() {
    categories.forEach(category => {
            if (!selectedCategories.has(category.firstElementChild.innerHTML)) {

                selectedCategories.add(category.firstElementChild.innerHTML)
            }
        }
    )
}

function sortCategories() {
    for (let category of categories) {
        category.addEventListener('click', function () {
            const cards = document.querySelectorAll("[data-label='card']");
            if (!selectedCategories.has(category.firstElementChild.innerHTML)) {
                selectedCategories.add(category.firstElementChild.innerHTML);
                setAttributes(cards);
            } else {
                selectedCategories.delete(category.firstElementChild.innerHTML);
                setAttributes(cards);
                selectedCategories.remove("All categories")
                console.log(selectedCategories)
            }
            if (selectedCategories.has("All categories")) {
                resetCategories();
                setAttributes(cards)
            } else if (selectedCategories.size === 0) {
                resetCategories();
                setAttributes(cards)
            }
        })
    }
}

function resetSuppliers() {
    suppliers.forEach(supplier => {
            if (!selectedSuppliers.has(supplier.lastElementChild.innerHTML)) {

                selectedSuppliers.add(supplier.lastElementChild.innerHTML)
            }
        }
    );
}

function sortSuppliers() {
    for (let category of suppliers) {
        category.addEventListener('click', function () {
            const cards = document.querySelectorAll("[data-label='card']");
            if (!selectedSuppliers.has(category.lastElementChild.innerHTML)) {
                selectedSuppliers.add(category.lastElementChild.innerHTML);
                setAttributes(cards);
            } else {
                selectedSuppliers.delete(category.lastElementChild.innerHTML);
                setAttributes(cards)
                if (selectedSuppliers.has("All suppliers")) {
                    selectedSuppliers.remove("All suppliers")
                }
            }
            if (selectedSuppliers.has("All suppliers")) {
                resetSuppliers();
                setAttributes(cards)

            }
            if (selectedSuppliers.size === 0) {
                resetSuppliers();
                setAttributes(cards)
            }
        })
    }
}

function resetFilters() {
    let resetButton = document.querySelector(".reset-filters");
    console.log(resetButton);
    resetButton.addEventListener('click', function () {
        resetCategories();
        resetCategories();
        showAllCards()

    })

}

function setAttributes(elements) {
    for (let element of elements) {
        if (selectedCategories.has(element.firstElementChild.innerHTML) && selectedSuppliers.has(element.lastElementChild.innerHTML)) {
            console.log(selectedCategories);
            element.setAttribute("class", "card");
            console.log("Shown the following: " + element.firstElementChild.innerHTML + " " + element.lastElementChild.innerHTML)
        } else {
            element.setAttribute("class", "hidden-card");
            console.log("Hidden the following: " + element.firstElementChild.innerHTML + " " + element.lastElementChild.innerHTML)
        }
    }
}


function showAllCards() {
    const hiddenCards = document.querySelectorAll(".hidden-card");
    for (let hiddenCard of hiddenCards) {
        hiddenCard.setAttribute("class", "card");
        console.log("Shown all")
    }
}

window.onload = function () {
    resetSuppliers();
    resetCategories()
};

sortSuppliers();
sortCategories();
resetFilters();
