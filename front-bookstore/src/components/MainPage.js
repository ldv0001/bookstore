import React, {useEffect, useState} from 'react';
import './Books'
import Books from "./Books";
import axios from "axios";
import '../styles/styles.css';

function MainPage() {

    var [resp, setResp] = useState([])
    var [searchedWord, setSearchedWord] = useState('')

    async function getBooks() {
        const response = await axios.get('http://127.0.0.1:8080/').catch(err => console.log(err.response.data.errorMessage))
        setResp(response.data)
    }

    useEffect(() => {
        getBooks()
    }, [])

    const filtered = resp.filter(x => x.nameOfTheBook.toLowerCase().includes(searchedWord.toLowerCase()))

    const addToBasket = () => {
        console.log('In APP :: ' + localStorage.getItem('currentBook'))
        axios.post('http://127.0.0.1:8080/basket', {
                username: localStorage.getItem('user'),
                book:
                    {id: localStorage.getItem('currentBook')}
            },
            {headers: {authorization: 'Bearer ' + localStorage.getItem('jwt')}})
    }

    return (
        <div className="App">
            <div className='form-outline'>
                <input type="text"
                       id="search"
                       className='form-control'
                       placeholder='Search'
                       required
                       onChange={(e) => setSearchedWord(e.target.value)}/>
            </div>

            {filtered.map(r =>
                <Books key={r.id}
                       id={r.id}
                       imageName={r.image}
                       book={r.nameOfTheBook}
                       author={r.author.name}
                       description={r.description}
                       price={r.price}
                       addToBasket={addToBasket}
                       getBooks={getBooks}
                />
            )}
        </div>
    );
}

export default MainPage;
