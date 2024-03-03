import { CSSProperties } from "react";

export const styles: { [key: string]: CSSProperties } = {
  main: {
    fontFamily: "Poppins",
    display: "flex",
    padding: "5px",
  },
  leftSection: {
    width: "75%",
  },
  rightSection: {
    width: "25%",
  },
  details: {
    border: "1px solid black",
    width: "90%",
    display: "flex",
    margin: "2rem",
    flexWrap: "wrap",
    justifyContent: "space-between",
  },
  commonStyleForDetails: {
    border: "1px solid black",
    padding: "10px 20px",
    margin: "10px 20px",
    display: "flex",
  },
  graph: {
    display: "flex",
    flexDirection: "row",
    width: "90%",
    height: "500px",
    border: "1px solid black",
    margin: "2rem",
    overflowX: "auto",
    flexWrap: "nowrap",
    scrollbarWidth: "thin",
  },

  buy: {
    padding: "1rem",
    border: "1px solid green",
    color: "green",
    margin: "0.5rem",
    background: "#b2f2bb",
  },
  sell: {
    padding: "1rem",
    border: "1px solid red",
    color: "red",
    margin: "0.5rem",
    background: "#ffc9c9",
  },
  history: {
    height: "41.5%",
    border: "1px solid black",
    margin: "1rem 3rem 0rem 1rem",
    padding: "10px",
    overflowY: "auto",
    scrollbarWidth: "thin",
    scrollbarColor: "transparent transparent",
  },
  logo: {
    backgroundColor: "#feec98",
    border: "1px solid #f4a92d",
    color: "#f4a92d",
    padding: "5px",
  },
};
