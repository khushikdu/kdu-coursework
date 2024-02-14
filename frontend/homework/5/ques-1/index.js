const http=require('http')
const fs=require('fs')

const port=8001
const jsonData='systemInfo.json';
const name='Khushi';

// Creating an HTTP server and Sending a response with name and system information
const server=http.createServer((request,resposne)=>{
    if(request.method==='GET'&&request.url==="/"){
        fs.readFile(jsonData,'utf-8',(error,data)=>{
            if(error){
                console.error('Error parsing JSON data',error.message);
                resposne.writeHead(500)
                resposne.end('Internal server error')
            } else {
                const systemInfo=JSON.parse(data);
                resposne.writeHead(200)
                resposne.end(`Hello my name is ${name}\nHere is my System information ${JSON.stringify(systemInfo)}`)
            }
        });
    } else {
        resposne.writeHead(404)
        resposne.end('Not found');
    }
});

server.listen(port,()=>{
    console.log('Starting process...')
})