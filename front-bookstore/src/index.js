import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import ReactDOM from 'react-dom/client';
import {BrowserRouter} from 'react-router-dom';

import Navigate from "./Navigate";


const container = document.getElementById('root');

const root = ReactDOM.createRoot(container);



root.render(    <BrowserRouter>
                <Navigate />
    </BrowserRouter>
)





