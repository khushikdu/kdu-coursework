import { CSSProperties } from "react";

export const styles: { [key: string]: CSSProperties } = {
    roomSelection:{
        margin: '1rem 1rem 0rem 1rem',
        display: 'flex',
      },
      select:{
        backgroundColor:'#f08a5d',
        color: 'white'
      },
    indivData:{
        display: 'flex',
        border:'1px solid black',
        margin: '1rem',
        padding: "1rem"
    },
    dataBtn:{
        backgroundColor:'white',
        border:'none'
    },
    dates:{
      margin:'1rem',
      display:'flex',
      justifyContent:'center'
    }
}