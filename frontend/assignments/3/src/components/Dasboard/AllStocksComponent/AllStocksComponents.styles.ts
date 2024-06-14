import { CSSProperties } from "react";

export const styles: { [key: string]: CSSProperties } = {
  main: {
    fontFamily: "Poppins",
  },
  allStocksFromStore: {
    margin: "0 4rem",
  },
  
  row: {
    display: "flex",
    borderBottom: "1px solid grey",
    padding: "5px",
  },
  stockCell: {
    display: "flex",
    fontSize: "18px",
    flexWrap: "wrap",
    width: "80%",
    padding: "1rem",
    justifyContent: "space-between",
  },
  add: {
    width: "20%",
  },
  
  exploreWatchlist: {
    fontSize: "18px",
    display: "flex",
    textDecoration: "none",
    color: "inherit",
    padding: "5px ",
  },
  buttonStyle: {
    fontSize: "18px",
    background: "none",
    border: "none",
    margin: "10px",
  },
  doneIcon: {
    borderRadius: "50%",
    backgroundColor: "#1971c2",
    color: "#ffffff", 
    padding: "2px", 
    margin: "10px 0px 0px 70px",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  },
};
