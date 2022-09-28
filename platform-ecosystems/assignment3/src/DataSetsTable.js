import React from "react";
import classes from "./App.module.css";

import {
  Table,
  TableBody,
  TableCell,
  TableCellHead,
  TableFoot,
  TableHead,
  TableRow,
  TableRowHead,
} from "@dhis2/ui";

// Creates table of meta elements from relevant MenuItem
export function DataSetsTable(props) {
  let elements = props.elements;
  let metaElements = props.metaElements;
  let name = props.name;

  // Finds the right index value for the menuItem
  for (let i in elements) {
    if (elements[i].displayName == name) {
      return <Table className={classes.table}>
        <TableHead>
          <TableRowHead>
            <TableCellHead>Display Name</TableCellHead>
            <TableCellHead>Created</TableCellHead>
            <TableCellHead>ID</TableCellHead>
          </TableRowHead>
        </TableHead>
        <TableBody>
          {metaElements[i].dataSetElements.map((element) => (
            <TableRow key={element.dataElement.id}>
              <TableCell>{element.dataElement.displayName}</TableCell>
              <TableCell>{element.dataElement.created}</TableCell>
              <TableCell>{element.dataElement.id}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    }
  }
  return <Table></Table>
}
