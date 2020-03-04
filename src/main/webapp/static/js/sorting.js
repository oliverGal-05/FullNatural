const selected = new Set();
const selectedCategories = new Set();
selected.add(selectedCategories);
const selectedSuppliers = new Set();
selected.add(selectedSuppliers);

function sortCategories() {
    const categories = document.querySelectorAll(".category");
    for (let category of categories) {
        category.addEventListener('click', function () {
            const cards = document.querySelectorAll("[data-label='card']");
            if (!selectedCategories.has(category.firstElementChild.innerHTML)) {
                selectedCategories.add(category.firstElementChild.innerHTML);
                setAttributes(cards, selectedCategories);
            } else {
                selectedCategories.delete(category.firstElementChild.innerHTML);
                setAttributes(cards, selectedCategories)
            }
            if (selectedCategories.has("All categories")) {
                selectedCategories.clear();
                setAttributes(cards, selectedCategories)
            }
            if (selectedCategories.size === 0) {
                selectedCategories.clear();
                setAttributes(cards, selectedCategories)
            }
            if (selectedCategories.size === 0 && selectedSuppliers.size === 0) {
                showAllCards();
                selectedSuppliers.clear();
                selectedCategories.clear()
            }
            console.log(selected);
        })
    }
}

function sortSuppliers() {
    const suppliers = document.querySelectorAll(".supplier");
    for (let category of suppliers) {
        category.addEventListener('click', function () {
            const cards = document.querySelectorAll("[data-label='card']");
            if (!selectedSuppliers.has(category.lastElementChild.innerHTML)) {
                selectedSuppliers.add(category.lastElementChild.innerHTML);
                setAttributes(cards, selectedSuppliers);
            } else {
                selectedSuppliers.delete(category.lastElementChild.innerHTML);
                setAttributes(cards, selectedSuppliers)
            }
            if (selectedSuppliers.has("All suppliers")) {
                selectedSuppliers.clear();
                setAttributes(cards, selectedSuppliers)
            }
            if (selectedSuppliers.size === 0) {
                selectedSuppliers.clear();
                setAttributes(cards, selectedSuppliers)
            }
            if (selectedCategories.size === 0 && selectedSuppliers.size === 0) {
                showAllCards();
                selectedSuppliers.clear();
                selectedCategories.clear()
            }
            console.log(selected)
        })
    }
}


function setAttributes(elements, storage) {
    for (let element of elements) {
        if (storage.has(element.lastElementChild.innerHTML || storage.has(element.firstElementChild.innerHTML))) {
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

sortSuppliers();
sortCategories();
