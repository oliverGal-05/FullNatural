function setSessionStorage(name, object) {
    console.log(object);
    sessionStorage.setItem(name, JSON.stringify(object));
}

function getSessionStorage(name) {
    return JSON.parse(sessionStorage.getItem(name));
}

function sortSuppliers() {
    const selectedSuppliers = new Set();
    console.log(selectedSuppliers)
    const suppliers = document.querySelectorAll(".supplier");
    for (let category of suppliers) {
        category.addEventListener('click', function () {
            const cards = document.querySelectorAll("[data-label='card']");
            if (!selectedSuppliers.has(category.lastElementChild.innerHTML)) {
                selectedSuppliers.add(category.lastElementChild.innerHTML);
                console.log(category.lastElementChild.innerHTML);
                console.log(selectedSuppliers)
                setAttributes(cards, selectedSuppliers);
            } else {
                selectedSuppliers.delete(category.lastElementChild.innerHTML);
                setAttributes(cards, selectedSuppliers)
            }
            if (selectedSuppliers.has("All")) {
                showAllCards();
                selectedSuppliers.clear();
            }
            if (selectedSuppliers.size === 0) {
                showAllCards();
                selectedSuppliers.clear();
            }

        })
    }
}

function setAttributes(elements, storage) {
    for (let element of elements) {
        if (!storage.has(element.lastElementChild.innerHTML) && !storage.has(element.firstElementChild.innerHTML)) {
            element.setAttribute("class", "hidden-card")
            console.log(element + "Hidden")
        } else {
            element.setAttribute("class", "card");
            console.log(element + "Shown")
        }
    }
}


function showAllCards() {
    const hiddenCards = document.querySelectorAll(".hidden-card");
    for (let hiddenCard of hiddenCards) {
        hiddenCard.setAttribute("class", "card")
    }
}

sortCategories();
sortSuppliers();