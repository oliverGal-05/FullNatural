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
            // console.log(cards);
            if (!selectedCategories.has(category.firstElementChild.innerHTML)) {
                selectedCategories.add(category.firstElementChild.innerHTML);
                console.log(category.firstElementChild.innerHTML);
                setAttributes(cards, selectedCategories);
            } else {
                selectedCategories.delete(category.firstElementChild.innerHTML);
                setAttributes(cards, selectedCategories)
            }
            if (selectedCategories.has("All")) {
                showAllCards();
                selectedCategories.clear();
            }
            if (selectedCategories.size === 0) {
                showAllCards();
                selectedCategories.clear();
            }

        })
    }
}

function setAttributes(elements, storage) {
    for (let element of elements) {
        console.log(element.firstElementChild)
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