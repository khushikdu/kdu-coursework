function trimAndUpperCase(days){
    let newDays=[]
    days.forEach(element => {
        newDays.push(element.trim().substring(0,3).toUpperCase())
    });
    console.log(newDays)
}

let days=["Sunday","Monday "," Tuesday","Wednesday "," Thursday","Friday","Saturday"]
trimAndUpperCase(days);

function modify(message){
    message=message.trim()
    let newMessage=message.replace(/a/g,'4')
        .replace(/e/g,'3')
        .replace(/i/g,'1')
        .replace(/o/g,'0')
        .replace(/s/g,'5')
    
    console.log(newMessage)
}

modify("javascript is cool ");
modify("programming is fun");
modify("become a coder");