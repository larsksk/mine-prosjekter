import React from "react";
import { useDataQuery } from "@dhis2/app-runtime";
import { CircularLoader, Menu, MenuItem } from "@dhis2/ui";
import classes from "./App.module.css";
import { DataSetsTable } from "./DataSetsTable";

// Both relevant queries
const dataQuery = {
  dataSets: {
    resource: "dataSets/",
    params: {
      fields: [
        "id",
        "displayName",
        "created",
      ],
    },
    paging: "false",
  },
  metaDataSets: {
    resource: "dataSets/",
    params: {
      fields: [
        "dataSetElements[dataElement[id, displayName,created]]",
      ],
    },
    paging: "false",
  }
}

export function DataSets(props) {
  const { loading, error, data } = useDataQuery(dataQuery);

  if (error) {
    return <span>ERROR: {error.message}</span>
  }

  if (loading) {
    return <CircularLoader large />
  }

  if (data) {
    let elements = data.dataSets.dataSets;
    let metaElements = data.metaDataSets.dataSets;

    // Creates the menu items with onCLick function to change the active table
    return <div className={classes.menu}>
      <Menu>
        {elements.map(element => {
          return <MenuItem
            key = {element.id}
            label = {element.displayName}
            onClick={() => {
              props.activeTableHandler(
                <DataSetsTable
                elements={elements}
                name={element.displayName}
                metaElements={metaElements} />
              );
            }}/>
        })}
      </Menu>
      <div>
        {props.activeTable}
      </div>
    </div>
  }
}
