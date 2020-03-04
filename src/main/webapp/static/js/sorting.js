const selected = new Set();

function setSessionStorage(name, object) {
    console.log(object);
    sessionStorage.setItem(name, JSON.stringify(object));
}

function getSessionStorage(name) {
    return JSON.parse(sessionStorage.getItem(name));
}

function sortCategories() {
    const categories = document.querySelectorAll(".category");
    for (let category of categories) {
        category.addEventListener('click', function () {
            const cards = document.querySelectorAll("[data-label='card']");
            if (!selected.has(category.firstElementChild.innerHTML)) {
                selected.add(category.firstElementChild.innerHTML);
                console.log(selected);
                setAttributes(cards, selected);
            } else {
                selected.delete(category.firstElementChild.innerHTML);
                setAttributes(cards, selected)
            }
            if (selected.has("All")) {
                showAllCards();
                selected.clear();
            }
            if (selected.size === 0) {
                showAllCards();
                selected.clear();
            }

        })
    }
}

function setAttributes(elements, storage) {
    for (let element of elements) {
        if (!storage.has(element.lastElementChild.innerHTML || !storage.has(element.firstElementChild.innerHTML))) {
            element.setAttribute("class", "hidden-card")
        } else {
            element.setAttribute("class", "card");
        }
    }
}


function showAllCards() {
    const hiddenCards = document.querySelectorAll(".hidden-card");
    for (let hiddenCard of hiddenCards) {
        hiddenCard.setAttribute("class", "card")
    }
}

function sortSuppliers() {
    const suppliers = document.querySelectorAll(".supplier");
    for (let category of suppliers) {
        category.addEventListener('click', function () {
            const cards = document.querySelectorAll("[data-label='card']");
            if (!selected.has(category.lastElementChild.innerHTML)) {
                selected.add(category.lastElementChild.innerHTML);
                console.log(selected)
                setAttributes(cards, selected);
            } else {
                selected.delete(category.lastElementChild.innerHTML);
                setAttributes(cards, selected)
            }
            if (selected.has("All")) {
                showAllCards();
                selected.clear();
            }
            if (selected.size === 0) {
                showAllCards();
                selected.clear();
            }

        })
    }
}


sortCategories();
sortSuppliers();