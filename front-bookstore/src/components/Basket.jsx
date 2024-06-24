import React, {useEffect, useState} from 'react';
import axios from "axios";
import BooksInBasket from "./BooksInBasket";

const Basket = () => {

    var [resp, setResp] =useState([])

     async function getBooks(){
        const response =  await axios.get('http://127.0.0.1:8080/basket',
             {headers:{authorization: 'Bearer '+localStorage.getItem('jwt')}})
        setResp(response.data)
    }

    useEffect(()=>{
        getBooks()
    },[])

    function buy(e) {
         e.preventDefault()
        axios
            .delete(`http://127.0.0.1:8080/basket/buy`,
                {headers: {authorization: 'Bearer ' + localStorage.getItem('jwt')}})
            .then((response) => {
                getBooks()
            });
    }

    return (
            <div className="basket">

                {resp.map(r=>
                    <BooksInBasket key = {r.id}
                                   id ={r.id}
                                   count = {r.booksCount}
                                   book ={r.book.nameOfTheBook}
                                   author ={r.book.author.name}
                                   price ={r.price}
                                   getBooks ={getBooks} />
                )}
                <div  style={{color: "black", fontFamily: "Goudy Old Style"  }}>
                    <h1 align="right">Total price: {resp.reduce((x,y)=>
                        (x+(y.price*y.booksCount)),0).toFixed(2)} </h1>
                </div>

                <button className='btn bg-secondary ml-auto m-2 text-white' onClick={buy}>Buy</button>

            </div>
    );
};

export default Basket;
