import React, {useEffect, useState} from 'react';
import axios from "axios";
import * as qs from 'qs';

const Login = ()=>{
    var [login,setLogin] = useState('')
    var [password,setPassword] = useState('')
    var [isLogged,setLogged] = useState(true)

    useEffect(()=>{

    },[isLogged])

    function logIn(e){
        e.preventDefault();
        console.log(login)

        axios({
            method: 'post',
            url: 'http://127.0.0.1:8080/login',
            data: {
                 username: login,
                 password: password
            },
            headers: {
                'content-type': 'application/json'
            }
        }).then((headers) => {

                localStorage.setItem('role',headers.data.role)
                localStorage.setItem('user',headers.data.user)
                localStorage.setItem('jwt',headers.data.access_token)
                localStorage.setItem('refresh_jwt',headers.data.refresh_token)
                localStorage.setItem('expires_date',headers.data.expires_date)
                if(localStorage.getItem('role')==='ROLE_USER'||localStorage.getItem('role')==='ROLE_ADMIN') {
                    window.location.href = "/app"
                }else{
                   setLogged(false)
                }
            })
    }
    function wrongPassword(e) {
        if (e === false) {
            return <h1 className="text-danger">wrong password</h1>
        }
    }

    return (
        <div className="login">
            {wrongPassword(isLogged)}

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

                <h1><button className='btn bg-secondary m-0 text-white' onClick={logIn}>Log in</button></h1>
                </div>
            </form>
        </div>
)

}
export default Login;
