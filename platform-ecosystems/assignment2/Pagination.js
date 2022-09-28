function Pagination(props) {

  // Increase the page number value by 1
  function increasePage(event) {
    props.setPageNumber(parseInt(event.target.value) + 1);
  }

  // Decrease the page number value by 1
  function decreasePage(event) {
    props.setPageNumber(parseInt(event.target.value) - 1);
  }

  if (!props.apiData.results) {
    return <p>Loading...</p>;
  }
  else {
    let active = props.apiData.pager.page;
    let maxPage = props.apiData.pager.pageCount;

    let buttonFor = <button value={props.pageNumber} onClick={increasePage}>Next</button>;
    let buttonBack = <button value={props.pageNumber} onClick={decreasePage}>Prev</button>;
    let pager = "Page " + active + " of " + maxPage;

    // Only rendering buttons when they are needed
    switch (active) {
      case 1:
        if (maxPage === 1) {
          return <nav>{pager}</nav>;
        } else {
          return <nav>{pager} {buttonFor}</nav>;}
      case maxPage:
        return <nav>{buttonBack} {pager}</nav>;
      default:
        return <nav>{buttonBack} {pager} {buttonFor}</nav>;
    }
  }
}

export default Pagination
