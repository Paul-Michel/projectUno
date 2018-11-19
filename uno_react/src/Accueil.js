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
                    <Link to="/Game">
                        <a className="button a" >NEW GAME</a>
                    </Link>
                    <br></br>
                    <br></br>
                    <Link to='/settings'>
                        <a className="button"> STATS </a>
                    </Link>
                </div>
            </div>
        );
    }
}

export default Accueil;