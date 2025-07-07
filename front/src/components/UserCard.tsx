import { User } from "../models/user"

const UserCard = ({user}:{user:User}) => {
  return (
    <div>
        <h4>{user.name}</h4>
        <p>{user.email}</p>
    </div>
  )
}
export default UserCard