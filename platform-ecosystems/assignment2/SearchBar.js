import {useState} from "react";

function SearchBar(props) {

  const [searchQuery, setSearchQuery] = useState("");

  function handleInput(event) {
    setSearchQuery(event.target.value);
  }

  // If you press enter in the search box it's the same as pressing the search button
  function handleKeyDown(event) {
    if (event.key === "Enter") {
      handleClick();
    }
  }

  // Changing the page to 1 to never end up on an empty page
  function handleClick() {
    props.setPageNumber(1);
    props.setSearchQuery(searchQuery);
  }

  // Creating the input as a controlled element
  return <section>
    <input type="text" id="input" value={searchQuery} onKeyDown={handleKeyDown} onChange={handleInput} placeholder="Search" />
    <button onClick={handleClick}>Search</button>
  </section>
}

export default SearchBar;
