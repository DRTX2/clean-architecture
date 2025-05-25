interface ChangeState{
    changeState: ()=>void;
}

const Button:React.FC<ChangeState> = ({changeState}) => {
    
  return (
    <button onClick={changeState}>Pulsar</button>
  )
}
export default Button