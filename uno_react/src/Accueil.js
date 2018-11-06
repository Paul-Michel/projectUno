import React, { Component } from 'react';
import logo from './ressources/uno.png';
import son from './ressources/son.png';
import { Link } from 'react-router-dom';
import './Accueil.css';
import './ressources/css/bootstrap.css'

class Accueil extends Component {

    render() {

        return (
            <body>
                <div id="photo">
                    <ul class="nav nav-fill">
                        <li class="nav-item">
                            <Link to='/Settings' style={{color: 'white'}}>
                                <a class="myButton"> Stats du profil</a>
                            </Link>
                        </li>
                        <li class="nav-item">
                            <img src={son} className="imgson" />
                        </li>
                    </ul>
                    <img src={logo} className="tailleimg" />
                </div>
                <div class="center">
                    <Link to="/Game" style={{color: 'white'}}>
                        <a class="myButton e">Nouvelle partie</a>
                    </Link>
                </div>
            </body>
        );
    }
}

export default Accueil;