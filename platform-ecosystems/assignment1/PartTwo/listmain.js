function addLi() {
  let text = document.getElementById("country").value;
  if(text.length > 0) {
    let li = document.createElement("li");
    let btn = document.createElement("button");
    btn.id = "x-button";
    btn.innerHTML = "X";
    btn.onclick = function() {
      removeElement(this);
    };
    li.textContent = text[0].toUpperCase() + text.substring(1) + " ";
    li.appendChild(btn);
    document.getElementById("list").appendChild(li);
    document.getElementById("country").value = "";
  };
}

function enterKey(e) {
  if (e.keyCode === 13) {
    e.preventDefault();
    addLi();
  }
}

function removeElement(el) {
  el.parentElement.remove();
}

function search(element, searchWord) {
  if (element.startsWith(searchWord)) {
    return true;
  }
  else {
    return false;
  }
}

function listSearch(array, searchWord) {
  let arr = [];
  for (let element of array) {
    if (search(element, searchWord)) {
      arr.push(element);
    }
  }
  return arr;
}

function searching() {
  let input = document.getElementById("search");
  input = input.value.toUpperCase();
  let list = document.getElementById("list").childNodes;
  let listArr = [];
  for (let i = 0; i < list.length; i++) {
    let arrValue = list[i].innerHTML.split(" ")[0];
    listArr.push(arrValue.toUpperCase());
  }

  let arr = listSearch(listArr, input);
  for (i = 0; i < list.length; i++) {
    if (!arr.includes(listArr[i])) {
      list[i].style.display="none";
    }
    else {
      list[i].style.display="list-item";
    }
  }
}
