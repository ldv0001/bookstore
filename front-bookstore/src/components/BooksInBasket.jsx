import React, {useEffect} from 'react';
import axios from "axios";


const BooksInBasket = (props) => {

    useEffect(()=>{},[props.count])

    function deletePosition(e) {
        e.preventDefault()
        axios
            .delete(`http://127.0.0.1:8080/basket/${props.id}`,
                {headers: {authorization: 'Bearer ' + localStorage.getItem('jwt')}})
            .then((response) => {
                // console.log('delete books : '+ props.id)
                props.getBooks()
            });
    }

    function deleteOneBook() {
        axios
            .delete(`http://127.0.0.1:8080/basket/removeonebook/${props.id}`,
                {headers: {authorization: 'Bearer ' + localStorage.getItem('jwt')}})
            .then((response) => {
                // console.log('delete books : '+ props.id)
                props.getBooks()
            });
        // window.location.reload();
    }

    function addOneBook(e) {
        // e.preventDefault()
        axios.put(`http://127.0.0.1:8080/basket/addonebook/${props.id}`,
                {headers: {authorization: 'Bearer ' + localStorage.getItem('jwt')}})
            .then((response) => {
                props.getBooks()
            });
    }

    return (
        <div className="book shadow-lg p-1 rounded ">
            <div className='container-fluid border-primary  '>

            <form>
                <div className='row'>
                    <div className='col-6 '>
                        <div  style={{color: "black", fontFamily: "Goudy Old Style"  }}>

                            <h2><b>{props.book}</b></h2>
                            <h2>   {props.author}</h2>

                        <div  style={{color: "red", fontFamily: "Brush Script MT"  }}>
                                <h2>Price: {props.price} ₽ </h2>
                        </div>

                    </div>
                </div>

                <div className='col '>
                    <button className='btn m-2 ' onClick={deleteOneBook}>
                        <img src="/images/minus.png" width="40" height='40' alt=""/>
                    </button>
                </div>

                <div className='col '>
                    <button className='btn  m-2 text-white' onClick={addOneBook}>
                        <img src="/images/plus.png" width="40" height='40' alt=""/>
                    </button>
                </div>

                <div className='col '>
                    <button className='btn  m-2 ' onClick={deletePosition}>
                        <img src="/images/remove.png" width="40" height='40' alt=""/>
                    </button>

                </div>
                <div className='col '>
                    <h2>{props.count} pcs</h2>
                </div>
                </div>
            </form>
            </div>
        </div>
    );
};

export default BooksInBasket;
