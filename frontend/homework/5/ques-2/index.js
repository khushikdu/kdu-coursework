const path=require('path');

//function that takes a file path as an argument and returns the file details
function extractFileInfo(filePath){
    return{
        extension:path.extname(filePath),
        baseName: path.basename(filePath),
        directoryName: path.dirname(filePath)
    };
}

//function that takes an array of file paths as an argument and returns an array of objects
function processFilePaths(arrayFilePath){
    return arrayFilePath.map(extractFileInfo);
}

const filePaths = [
    'dir1/dir2/file1.txt',
    'dir1/dir3/file2.txt',
    'dir1/dir3/file3.md',
    'dir4/file4.jpg',
    'dir4/file5.pdf',
];

const fileInfo=processFilePaths(filePaths)
console.log(fileInfo)