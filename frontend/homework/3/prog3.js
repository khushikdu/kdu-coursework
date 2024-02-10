const shoe1={type:"sneakers",color:"blue",size:"6",price:2000};
const shoe2={type:"bellies", color:"pink", size:"6",price:1500};

const shirt1={type:"semi-formal",color:"blue", size:"42", price:2000};
const shirt2={type:"formal", color:"white", size:"42", price:1800};
const shirt3={type:"casula", color:"black", size:"44", price:5000};

const shoes=[shoe1,shoe2];
const shirts=[shirt1,shirt2,shirt3];

let warehouse=[...shoes,...shirts]
console.log("----------------------------------------------------------");
console.log("Warehouse : ",warehouse);

let price=0
warehouse.forEach(element => {
    price=price+element.price
});

console.log("----------------------------------------------------------");
console.log("Worth : Rs.",price);

warehouse.sort((a,b)=>b.price-a.price);
console.log("----------------------------------------------------------");
console.log("Sorted Warehouse : ",warehouse);

const warehouse1=warehouse.filter(element=>element.color==="blue")
console.log("----------------------------------------------------------");
console.log("Filtered Warehouse : ",warehouse1);