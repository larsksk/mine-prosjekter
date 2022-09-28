import React from "react";
import classes from "./App.module.css";
import { useState } from "react";

import { Browse } from "./Browse";
import { Insert } from "./Insert";
import { DataSets } from "./DataSets";
import { Navigation } from "./Navigation";

function MyApp() {
  const [activePage, setActivePage] = useState("Browse");
  const [activeTable, setActiveTable] = useState();

  function activePageHandler(page) {
    setActivePage(page);
  }

  /* I don't know if directly using props.setActiveTable is better,
    but since this is how the setActivePage is done this is for consistency */
  function activeTableHandler(table) {
    setActiveTable(table);
  }

  return (
    <div className={classes.container}>
      <div className={classes.left}>
        <Navigation
          activePage={activePage}
          activePageHandler={activePageHandler}
        />
      </div>
      <div className={classes.right}>
        {activePage === "Browse" && <Browse />}
        {activePage === "Insert" && <Insert />}
        {activePage === "DataSets" && <DataSets
        activeTable={activeTable}
        activeTableHandler={activeTableHandler}
        />}
      </div>
    </div>
  );
}

export default MyApp;
