import {Link} from "react-router-dom";
import axios from "axios";

const Books = (props) => {
    const imageURL ='http://localhost:8080/image/'

    const add =(e)=>{
        e.preventDefault()
        localStorage.setItem('currentBook',props.id)
        props.addToBasket(props.book)
    }

    const deleteBook = (e)=>{
        e.preventDefault()
        axios.delete(`http://127.0.0.1:8080/deleteBook/${props.id}`,
                {headers: {authorization: 'Bearer ' + localStorage.getItem('jwt')}})
            .then((response) => {
                props.getBooks()
            });
    }

    return (
        <div className="book shadow-lg p-1 rounded ">
            <div className='container-fluid border-primary  '>
                <div className='row'>

                    <div className='col-md-4 shadow mb-5 p-1 rounded'>
                           <img  src={imageURL+props.id} alt="" className='img-fluid img-thumbnail'/>
                    </div>

                    <div className='col-8 '>
                           <div  style={{color: "black", fontFamily: "Goudy Old Style"  }}>
                                 <h1><b>                                {props.book}</b></h1>
                                 <h1 style={{color: "#669999"}}>        {props.author} </h1>
                           </div>

                           <div className="trunc">
                                 {props.description}
                           </div>
                    </div>


                    <div  style={{color: "red", fontFamily: "Brush Script MT"  }}>
                        <h2>Price:                           {props.price} ₽ </h2>
                    </div>

                    </div>

                <div className='row '>
                    <div className='col '>
                        <Link to ={`/app/${props.id}`}
                             style={{ textDecoration: 'none',
                                      color: "#669999",
                                      textShadow: "1px 1px 2px,0 0 0.1em blue, 0 0 0.1em blue" }}>
                           <h2>read more</h2>
                        </Link>
                    </div>

                    <div className='col text-right'>
                        { !(localStorage.getItem('user')===' ')&&  <button className='btn bg-secondary m-2 text-white'
                                onClick={add}>Add to cart</button>}
                        { (localStorage.getItem('role')==='ROLE_ADMIN')&&  <button className='btn bg-danger m-2 text-white'
                                                                           onClick={deleteBook}>Delete</button>}
                    </div>

                </div>

            </div>

         </div>

    );
};

export default Books;
