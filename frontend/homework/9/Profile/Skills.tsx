import './Skills.css'
interface ISkills{
    id:number
    skill:String
}

interface SkillSet{
    skills:ISkills[]
}

export function Skills({skills}:SkillSet){
    return(
        <>
        <div className="skill-set grid">
        <p id="headings">Skills</p>
        <ul>
            {
                skills.map((item) => (
                    <li key = {item.id}>{item.skill}</li>
            ))}
        </ul>
        </div>
        </>
    )
}