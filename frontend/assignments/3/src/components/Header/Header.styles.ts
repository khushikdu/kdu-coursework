import { CSSProperties } from "react";

export const styles: { [key: string]: CSSProperties } = {
  main:{
    // margin: '3rem 5rem 0rem 5rem',
    fontFamily: "Poppins",
    // fontSize:'18px'
    // position: "sticky",
    // top:"0",
    // zIndex: 10
  },
  mainHeader: {
    
    color: "white",
    background: "#1971c2",
    fontFamily: "Poppins",
    fontSize: "2rem",
    display:"flex",
    justifyContent:'space-between',
    padding: '5px',
  },
  leftLogoTitle:{
    
    display:'flex',
    float:'left'
  },
  linkStyle: {
    padding: '5px',
    textDecoration: "none",
    color: "inherit",
  },
  rigthTitle:{
    padding: '5px',
    display:'flex',
    float:'right',
  },
  summariser:{
    paddingRight: '25px',
  },
  
};
