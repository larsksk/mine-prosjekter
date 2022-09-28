function Table(props){


  function tableSort(event) {
    let sortingValue = event.target.id;
    if (props.pageOrder === sortingValue + ":ASC") {
      props.setPageOrder(sortingValue + ":DESC");
    } else {
      props.setPageOrder(sortingValue + ":ASC");
    }
  }

  if (!props.apiData.results) {
    return <p>Loading...</p>;
  } else {
    let results = props.apiData.results;

    // Assigning unique key values to the elements while mapping
    let tableRows = results.map((row) =>
      <tr key={row.Country.toString()}>
        <td key={row.Country.toString()}>{row.Country}</td>
        <td key={row.Continent.toString()}>{row.Continent}</td>
        <td key={row.Population.toString()}>{row.Population}</td>
        <td key={row.PopulationGrowth.toString()}>{row.PopulationGrowth}</td>
      </tr>
    );

    return <table>
      <thead>
        <tr id="headerTable">
          <th id="Country" onClick={tableSort}>Country</th>
          <th id="Continent" onClick={tableSort}>Continent</th>
          <th id="Population" onClick={tableSort}>Population</th>
          <th id="PopulationGrowth" onClick={tableSort}>Population Growth</th>
        </tr>
      </thead>
      <tbody>
      {tableRows}
      </tbody>
    </table>;
  }
}

export default Table
