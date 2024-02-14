const os=require('os')
const fs=require('fs')

//function to get os information and return in a json format
function systemInfo(){
    return{
        HostName:os.hostname(),
        OperatingSystem:os.type(),
        Architecture:os.arch(),
        OSRelease:os.release(),
        Uptime:os.uptime(),
        NumberofCPUCores:os.cpus().length,
        TotalMemory:os.totalmem(),
        FreeMemory:os.freemem(),
        CurrentWorkingdirectory:process.cwd()
    }
}

//function to write the details to a json file
function writeToJSON(data) {
    const stringData= JSON.stringify(data,null,4)
    fs.writeFileSync('systemInfo.json',stringData)
}

const sysInfo=systemInfo()
writeToJSON(sysInfo)
