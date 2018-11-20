import React, { Component } from 'react';
import logo from './ressources/uno.png';
import son from './ressources/son.png';
import { Link } from 'react-router-dom';
import './Accueil.css';
import Redirect from 'react-router-dom/Redirect';


class Accueil extends Component {

    render() {

        return (

            <div>
                <style>
                    @import url('https://fonts.googleapis.com/css?family=Righteous');
                </style>
                <div>
                    <img src={son} className="imgson" />
                </div>
                <div id="photo">
                    <img src={logo} className="tailleimg" />
                </div>
                <div style={{ textAlign: 'center' }}>
                        <a className="button" href="http://localhost:1337/login" >LOGIN</a>
                        <a className="button" href="http://localhost:1337/register" >REGISTER</a>
                    <Link to='/settings'>
                        <a className="button"> STATS </a>
                    </Link>
                </div>
            </div>
        );
    }
}

export default Accueil;