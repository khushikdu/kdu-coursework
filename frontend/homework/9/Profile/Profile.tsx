import './Profile.css'
import UserDetails from './details.json'


interface IUserProfile{
    name:string,
    fullName:string,
    qualification:string

}

export function Profile(){
    const usersData:IUserProfile=UserDetails
    return(
        <div className='profile'>
            <p>{usersData.name}</p>
            <p className="userdata">{usersData.fullName}</p>
            <p>{usersData.qualification}</p>
        </div>
    );
}