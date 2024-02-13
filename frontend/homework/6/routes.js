const express=require('express');
const uuid = require('uuid')
let accounts=require('./data')

console.log(accounts)
const app=express();
const port=5001;

app.use(express.json());

app.listen(port,()=>{
    console.log(`Application started on port ${port}`)
});

//to fetch the details of all the accounts
app.get('/accounts',(request,response)=>{
    response.json(accounts);
});


//to fetch the details of all the account with the given id
app.get('/accounts/:id',(request,response)=>{
    const accountID=Number(request.params.id)
    const getAccount=accounts.find((account)=>
        account.id===accountID);

    if(!getAccount){
      response.status(500).send('Not found')
    } else {
        response.json(getAccount);
    }
});

//to add a new account
app.post('/accounts',(request,response)=>{
  const {username,position}=request.body;
  let id = uuid.v4();
  const newAccount ={
    id,
    username,
    position
  }
  
    accounts.push(newAccount);
  response.json(accounts);
});