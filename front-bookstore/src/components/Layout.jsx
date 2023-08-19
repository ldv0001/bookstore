import {NavLink, Outlet} from 'react-router-dom'
import React from "react";
import '../styles/styles.css';


const Layout =(props)=>{

    function logout(){
       localStorage.setItem('role',' ')
       localStorage.setItem('user',' ')
       localStorage.setItem('jwt',' ')
       localStorage.setItem('refresh_jwt',' ')
       localStorage.setItem('expires_date',' ')
    }

    return(
        <>
        <header>
            <div className="header">

                <NavLink to ="/app"><h2>Home</h2></NavLink>


                {props.isAdmin()&& <NavLink to ="/admin"><h2>Admin</h2></NavLink>}


                {!props.isLogged()&&<NavLink  to ="/login"><h2>Login</h2></NavLink>}

                {props.isLogged()&& <a onClick={logout} href="/login"> < h2 className="ms-auto"> Logout</h2></a>}

                {!props.isLogged()&&<NavLink  to ="/signup"><h2>Sign up</h2></NavLink>}

                {props.isLogged()&&<NavLink className="ms-auto" to ="/basket">
                                        <img src="/images/cart.png" width="50" height='50' alt=""/>
                                   </NavLink>}

            </div>
        </header>
        <Outlet/>
       </>
    )
}
export default Layout
