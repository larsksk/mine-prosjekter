function PageSize(props) {

  function pageSizeChange(event) {
    // Changing to page 1 to not get empty pages if the page size increases
    props.setPageNumber(1)
    props.setPageSize(event.target.value);
  }

  return <p>Results per page:
    <select name="pages" id="pages" value={props.pageSize} onChange={pageSizeChange}>
      <option value="10">10</option>
      <option value="20">20</option>
      <option value="50">50</option>
    </select>
  </p>
}

export default PageSize;
