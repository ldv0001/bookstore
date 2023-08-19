import React, {useEffect, useState} from 'react';
import axios from "axios";

const Admin = () => {
    var [postAuthor,setPostAuthor] = useState('')
    var [postBook,setPostBook] = useState('')
    var [description,setDescription] = useState('')
    var [price,setPrice] = useState('')
    var [imgName,setImageName] = useState('')
    var [imgFile,setImgFile] = useState('')

    useEffect(()=>{checkFields()},[postBook,postAuthor,description,price,imgName])

    function uploadImage(e){
        e.preventDefault()
        let file = e.target.files[0]
        setImgFile(file)
        setImageName(file.name)
    }

    function checkFields(){
        if(postAuthor.length>0 &&
            postBook.length>0 &&
            description.length>0 &&
            price.length >=0 &&
            imgName.length>0
        ){
            return true
        }
        return false
    }

    function createPost() {
        axios
            .post('http://127.0.0.1:8080/admin', {
                nameOfTheBook: postBook,
                description: description,
                price: price,
                image: imgName,
                author: {
                    name: postAuthor
                }
            },
                  {headers:{authorization: 'Bearer '+localStorage.getItem('jwt')}})

        const imageData = new FormData()
        imageData.append('imageFile',imgFile)
        axios.post('http://127.0.0.1:8080/image',imageData)
    }

    return (
        <div className="container">
            <form>
              <div className="col-5">
                  <label htmlFor="Admin">Author's name</label>
                    <input type="text"
                       className="form-control"
                       id="Author"
                       required
                       value = {postAuthor}
                       onChange={(e)=>setPostAuthor(e.target.value)}/>

                  <label htmlFor="Book">Name of the book</label>
                     <input type="text"
                       className="form-control"
                       id="Book"
                       required
                       value = {postBook}
                       onChange={(e)=>setPostBook(e.target.value)}/>

                  <label htmlFor="Description">Description</label>
                     <textarea className="form-control"
                            id="Description"
                            required
                            value = {description}
                            onChange={(e)=>setDescription(e.target.value)}
                            rows="3">
                     </textarea>

                  <label>Price
                    <input type="number"
                           className="form-control"
                           id="Price"
                           required
                           value = {price}
                           onChange={(e)=>setPrice(e.target.value)}/>
                  </label>

                <div className='row-5 mt-3'>
                     <input type="file" name ='file' className='form-control' onChange={uploadImage}/>
                </div>
                  {checkFields()&& <h1><button className='btn bg-secondary m-0 text-white' onClick={createPost}>Add</button></h1>}
                  {!checkFields()&& <h2>Please fill all fields</h2>}
              </div>
            </form>
           </div>

    );
};

export default Admin;
