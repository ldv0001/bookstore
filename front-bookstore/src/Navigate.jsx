import Admin from "./components/Admin";
import SingleBookPage from "./components/SingleBookPage";
import MainPage from "./components/MainPage";
import axios from "axios";
import './styles/styles.css';
import {Route, Routes} from 'react-router-dom';
import Basket from "./components/Basket";
import Login from "./components/Login";
import Layout from "./components/Layout"
import SignUpPage from "./components/SignUpPage";


function Navigate(){

    async function refreshJWT(){

        let expiresAt = Number(localStorage.getItem('expires_date'))

        if (Date.now()>=(expiresAt-60*1000)||Number.isNaN(expiresAt)) {

            const response = await axios.get('http://127.0.0.1:8080/refresh',
                {headers: {authorization: 'Bearer ' + localStorage.getItem('refresh_jwt')}})

            localStorage.setItem('jwt', response.data.access_token)
            localStorage.setItem('expires_date',response.data.expires_date)

        }
    }

    function isAdmin(){
        if(localStorage.getItem('role')==='ROLE_ADMIN'){
            return true
        }else{
            return false
        }
    }
    function isLogged(){
        if(localStorage.getItem('user')!==' '){
            return true
        }else{
            return false
        }
    }

    function checkAuthentication(){
        if(localStorage.getItem('user') === null) {
            localStorage.setItem('role',' ')
            localStorage.setItem('user',' ')
        }else{
            refreshJWT()
        }
    }

     checkAuthentication()

    return(
        <div>
            <Routes>
                <Route path ="/" element={<Layout isAdmin ={isAdmin} isLogged = {isLogged}/>}>
                     <Route path ="/" element={<MainPage/>}/>
                     <Route path ="*" element={<MainPage/>}/>
                     <Route path ="/login" element={<Login/>}/>
                     <Route path ="/app" element={<MainPage />}/>
                     <Route path ="/app/:id" element={<SingleBookPage />}/>
                     <Route path ="/admin" element={isAdmin()?<Admin/>:<SignUpPage/>}/>
                     <Route path ="/basket" element={isLogged()?<Basket/>:<SignUpPage/>}/>
                     <Route path ="/signup" element={<SignUpPage/>}/>
                </Route>
            </Routes>

        </div>
    )

}
export default Navigate
