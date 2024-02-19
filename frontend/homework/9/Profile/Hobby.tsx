import './Hobby.css'
interface IHobby{
    id:number
    hobby:string
}

interface Hobbies{
    hobby: IHobby[]
}

export function Hobby({hobby}:Hobbies){
    return(
        <>
        <div className="hobby-items grid">
        <p id="headings">Hobby</p>
        <ul>
            {hobby.map((item)=>(
                <li key={item.id}> {item.hobby}</li>
            ))}
        </ul>
        </div>
        </>
    )
}