function tipCalculator(bill){
    let tip=[]
    let totalAmount=[]

    bill.forEach(element => {
        if(element<50) {
            tip.push(0.2*element)
            totalAmount.push(1.2*element)
        }
        else if(element>=50 &&element<200){
            tip.push(0.15*element)
            totalAmount.push(1.15*element)
        }
        else {
            tip.push(0.1*element)
            totalAmount.push(1.1*element)
        }
    });
    console.log("Tip : ",tip)
    console.log("Total amount : ",totalAmount)
}
bill=[140,45,280]
tipCalculator(bill)