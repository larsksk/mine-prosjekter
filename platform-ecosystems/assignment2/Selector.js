function Selector(props) {

  /* Checking to see if the continent code state contains the continent checkbox
     we clicked, and changing the string thereafter */
  function continentChange(event) {
    let continent = event.target.id;
    let continents = props.continentCode.split(",");
    if (continents.includes(continent)) {
      continents = continents.filter(
        function(value, index, array) {
          return value !== continent;
        }
      );
    } else {
      continents.push(continent);
    }
    props.setPageNumber(1);
    props.setContinentCode(continents.join());
  }

  return <section id="continentSelect">
    <input type="checkbox" id="EU" onChange={continentChange} defaultChecked/>
    <label htmlFor="EU">Europe</label>
    <input type="checkbox" id="AF" onChange={continentChange} defaultChecked/>
    <label htmlFor="AF">Africa</label>
    <input type="checkbox" id="SA" onChange={continentChange} defaultChecked/>
    <label htmlFor="SA">South America</label>
    <input type="checkbox" id="NA" onChange={continentChange} defaultChecked/>
    <label htmlFor="NA">North America</label>
    <input type="checkbox" id="OC" onChange={continentChange} defaultChecked/>
    <label htmlFor="OC">Oceania</label>
    <input type="checkbox" id="AS" onChange={continentChange} defaultChecked/>
    <label htmlFor="AS">Asia</label>}
  </section>
}

export default Selector;
