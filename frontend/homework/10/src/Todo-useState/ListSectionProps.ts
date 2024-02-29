export interface ListSectionProps{
    list:{
        id: number,
        todo: string,
    }[],
    searchItem:string,
    addItem: (todo:string)=>void
    deleteItem: (id:number)=>void
}