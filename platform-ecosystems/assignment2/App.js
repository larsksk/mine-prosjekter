import React, { useState, useEffect } from "react";
import "./App.css";
import Table from "./Table.js";
import SearchBar from "./SearchBar.js"
import Pagination from "./Pagination.js";
import Selector from "./Selector.js";
import PageSize from "./PageSize.js";

function App() {

  const [apiData, setApiData] = useState([]);
  const [searchQuery, setSearchQuery] = useState(""); // Default = No search query
  const [pageNumber, setPageNumber] = useState(1); //Default = Page 1
  const [pageSize, setPageSize] = useState(10);
  const [pageOrder, setPageOrder] = useState("Country:ASC");
  const [continentCode, setContinentCode] = useState("EU,AF,SA,NA,OC,AS");

  useEffect(() => {
    // All parameters are appended to this URL.
    let apiQuery = "https://dhis2-app-course-api.ifi.uio.no/api?";

    // If searchQuery isn't empty add &search=searchQuery to the API request.
    if (searchQuery) {
      apiQuery += "&search=" + searchQuery;
    }

    // Add what page we are requesting to the API request.
    apiQuery += "&page=" + pageNumber
              + "&pageSize=" + pageSize
              + "&order=" + pageOrder
              + "&ContinentCode=" + continentCode;

    // Query data from API.
    console.log("Querying: " + apiQuery);
    fetch(apiQuery)
      .then((results) => results.json())
      .then((data) => {
        // Then add response to state.
        setApiData(data);
      });
  }, [searchQuery, pageNumber, pageSize, pageOrder, continentCode]); // Array containing which state changes that should re-reun useEffect()

  return (
    <section className="App">
      <h1>Country lookup</h1>
      <SearchBar setSearchQuery={setSearchQuery} setPageNumber={setPageNumber} />
      <Selector continentCode={continentCode} setContinentCode={setContinentCode} setPageNumber={setPageNumber}/>
      <Table apiData={apiData} id="table" pageOrder={pageOrder} setPageOrder={setPageOrder} />
      <Pagination apiData={apiData} pageNumber={pageNumber} setPageNumber={setPageNumber} />
      <PageSize pageSize={pageSize} setPageSize={setPageSize} setPageNumber={setPageNumber} />
    </section>
  );
}

export default App;
