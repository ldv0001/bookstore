import React, {useEffect, useState} from 'react';
import axios from "axios";

const SignUpPage =()=>{
    var [login,setLogin] = useState('')
    var [password,setPassword] = useState('')
    var [passConfirm,setPassConfirm] = useState('')
    var [check,setCheck] = useState(true)
    var [response,setResponse] = useState(null)

    function checkFields(){
        if(login.length>0 &&
            password.length>0 &&
            passConfirm.length>0
        ){
            return true
        }
        return false
      }

    function checkPass(){
        if(password === passConfirm || passConfirm===''){
            setCheck(true)
        }else{
            setCheck(false)
        }
    }

    useEffect(()=>{
        checkPass()},[passConfirm]
    )

    async function signUp(e){

         axios.post('http://127.0.0.1:8080/signup',{
            username: login,
            password: password
         }).then(res =>{if(res.status !==400)window.location.reload()})
           .catch(err => setResponse(err.response.data.errorMessage))
           e.preventDefault()
    }

    return( <div>
            <form >
                <div className='col-5'>
                    <label htmlFor={login}>Login</label>
                    <input type="text"
                           id ="login"
                           className="form-control"
                           required
                           value = {login}
                           onChange={(e)=>setLogin(e.target.value)}/>
                    <label htmlFor={password}>Password</label>
                    <input type="password"
                           id ="password"
                           className="form-control"
                           required
                           value = {password}
                           onChange={(e)=>setPassword(e.target.value)}/>
                    <label htmlFor={passConfirm}>Confirm Password</label>
                    <input type="passConfirm"
                           id ="passConfirm"
                           className="form-control"
                           required
                           value = {passConfirm}
                           onChange={(e)=>setPassConfirm(e.target.value)}/>
                    {!check && <h4 className = "text-danger">Pass do not match</h4>}

                    {checkFields()&&check && <h1><button className='btn bg-secondary m-0 text-white' onClick={signUp}>Sign Up</button></h1>}
                    {!checkFields()&& <h1 className="text-danger">Please fill all fields</h1>}

                </div>
            </form>
            <h2 className="text-danger">{response}</h2>
    </div>
    )

}
export default SignUpPage;
