function setSessionStorage(name, object) {
    console.log(object);
    sessionStorage.setItem(name, JSON.stringify(object));
}

function getSessionStorage(name) {
    return JSON.parse(sessionStorage.getItem(name));
}

function sortCategories() {
    const selectedCategories = new Set();
    const categories = document.querySelectorAll(".category");
    for (let category of categories) {
        category.addEventListener('click', function () {
            const cards = document.querySelectorAll("[data-label='card']");
            if (!selectedCategories.has(category.firstElementChild.innerHTML)) {
                selectedCategories.add(category.firstElementChild.innerHTML);
                console.log(selectedCategories);
                setAttributes(cards, selectedCategories);
            } else {
                selectedCategories.delete(category.firstElementChild.innerHTML);
                setAttributes(cards, selectedCategories)
            }
            if (selectedCategories.has("All")) {
                showAllCards();
                selectedCategories.clear();
            }
        })
    }
}

function setAttributes(elements, storage) {
    for (let element of elements) {
        if (!storage.has(element.firstElementChild.innerHTML)) {
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


sortCategories();