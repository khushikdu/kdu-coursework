let keys=[]
let values=[]

function keyAndValue(object){
    for(let key in object){
        keys.push(key)
        if(typeof(object[key])==="object"){
            keyAndValue(object[key])
        }else{
        values.push(object[key])
        }
    }
}


const player = {
    firstName: "Leo",
    lastName: "Messi",
    address: {
        country: "Spain",
        city: "Barcelona",
    },
    careerInfo: {
        fcBarcelona: {
            appearances: 780,
            goals: {
                premierLeagueGoals: 590,
                championsLeagueGoals: 50,
            },
        },
    },
};

keyAndValue(player);
console.log("Keys : ")
console.log(keys)
console.log("Values : ")
console.log(values)