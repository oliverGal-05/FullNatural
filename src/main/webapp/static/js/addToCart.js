let cart = "";
console.log(cart);
let productName;
let productPrice;

function setupAddToCartButtons() {
    let addToCartButtons = document.querySelectorAll(".addToCartButton");
    addToCartButtons.forEach(addToCartButton => {
        addToCartButton.addEventListener("click", function () {
            productName = addToCartButton.parentElement.parentElement.parentElement.getElementsByClassName("card-title")[0].innerHTML;
            productPrice = addToCartButton.parentElement.parentElement.firstElementChild.firstElementChild.innerHTML;
            let tableRef = document.getElementById('cart-table').getElementsByTagName('tbody')[0];
            let newRow = tableRef.insertRow(tableRef.rows.length);
            let newCell = newRow.insertCell(0);
            let newText = document.createTextNode(productName+"  ");
            newCell.appendChild(newText);
            let newCell2 = newRow.insertCell(1);
            let newText2 = document.createTextNode(productPrice+"  ");
            newCell2.appendChild(newText2);
            let newDeletebutton = document.createElement("BUTTON");
            newDeletebutton.innerHTML = "🗑";
            newDeletebutton.setAttribute('class',"btn-danger");
            newRow.appendChild(newDeletebutton)
        })
    })
}


window.onload = function () {
    setupAddToCartButtons()
};
