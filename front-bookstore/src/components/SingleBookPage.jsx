import {useParams} from 'react-router-dom'
import React, {useEffect, useState} from "react";
import axios from "axios";

const SingleBookPage =()=>{
    const imageURL ='http://localhost:8080/image/'
    const {id}=useParams();
    var [resp, setResp] =useState({})
    var [authname, setAuth] =useState('')

    function addToCart() {
        axios.post('http://127.0.0.1:8080/basket', {
                    username: localStorage.getItem('user'),
                    book:
                        {id: id}
                },
                {headers:{authorization: 'Bearer '+localStorage.getItem('jwt')}})
    }

    useEffect(()=>{
        getBook()
    },[])

    async function getBook(){
        const response = await axios.get(`http://127.0.0.1:8080/${id}`)
        setResp(response.data)
        setAuth(response.data.author.name)
    }

    return(

        <div className='container'>

            <div className='row'>

            <div className='col-md-3 '>
                <img  src={imageURL+id} alt="" className='img-fluid img-thumbnail'/>
            </div>
            <div className='col-md-4 '>
                <div  style={{color: "black", fontFamily: "Goudy Old Style"  }}>

                    <h2><b>{resp.nameOfTheBook}</b></h2>
                    <h2>   {authname} </h2>
                </div>

                <div className="">
                    {resp.description}
                </div>

            </div>
                <div  style={{color: "red", fontFamily: "Brush Script MT"  }}>
                    <h2 >Price:                           {resp.price} ₽ </h2>
                </div>
            </div>

            <div className='row-1'>
                { !(localStorage.getItem('user')===' ')&& <button className='btn bg-secondary m-2 text-white'
                        onClick={addToCart}>Add to cart</button>}
            </div>
        </div>
    )
}
export default SingleBookPage
