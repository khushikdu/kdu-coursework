const input='{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}';

result=JSON.parse(input);

for(let key in result){
    if(key!=="email" && typeof result[key] === "string"){
        result[key]=result[key].toUpperCase();
    }
}
console.log(result);

delete result.email;
console.log(JSON.stringify(result))