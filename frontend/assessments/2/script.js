//to create grids in the graph
document.addEventListener("DOMContentLoaded", () => {
    const gridsContainer = document.getElementById("grids");
    for (let row = 0; row < 6; row++) {
        for (let col = 0; col < 4; col++) {
            const gridItem = document.createElement("div");
            gridItem.classList.add("grid-item");
            gridsContainer.appendChild(gridItem);
        }
    }
});

const socket = io("http://localhost:3000");

fetch("/stock")
    .then((response)=> response.json())
    .then((data)=>{
        console.log("Initial Stock Data:", data);
    })
        .catch((error)=>console.error("Error fetching initial stock data:", error)
);

fetch("/history")
    .then((response)=>response.json())
    .then((history)=> {
        console.log("Stock History:", history);
    })
.catch((error) => console.error("Error fetching stock history:", error));

socket.on("initialData",(data)=>
  console.log("Initial Data from Server:", data)
);

socket.on("realTimeUpdate",(update) => {
    console.log("Real-Time Update:", update);
});
