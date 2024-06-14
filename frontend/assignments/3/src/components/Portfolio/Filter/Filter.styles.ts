import { CSSProperties } from "react";

export const styles: { [key: string]: CSSProperties } = {
  main: {
    fontFamily: "Poppins",
    fontStyle: "normal",
    width: "30%",
    margin: "0 3rem 0 3rem",
    border: "1px solid grey",
    padding: "10px 0",
    height: "max-content",
    backgroundColor: "#e9edee",
    position: "sticky",
    top: 100,
    zIndex: 2,
    color: "grey",
    borderRadius: "15px",
    fontSize: "18px",
  },
  filterSection: {
    zIndex: 3,
    borderBottom: "1px solid grey",
    padding: "10px",
    height: "max-content",
    backgroundColor: "#e9edee",
    color: "grey",
    fontSize: "18px",
    position: "sticky",
    top: 70,
  },
};
