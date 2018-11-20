import React, { Component } from 'react';
import logo from './ressources/uno.png';
import son from './ressources/son.png';
import Footer from './footer.js';
import Header from './header.js';
import { Link } from 'react-router-dom';
import './css/Accueil.css';
import './css/materialize.min.css';
import './css/animation.css';

class Accueil extends Component {

    render() {

        return (

            <div className="div">

                <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"></link>
                <link href="https://fonts.googleapis.com/css?family=Righteous" rel="stylesheet"></link>

                <Header />

                <div class="animated zoomIn container">
                    <img src={logo} className="tailleimg" />
                </div>
                <div className="animated zoomIn row container">
                    <div className="col s4">
                        <Link to="/ingame"><a className="waves-effect waves-light btn-small red darken-3" >NEW GAME</a></Link>
                    </div>
                    <div className="animated zoomIn col s4">
                        <Link to="/ingame"><a className="waves-effect waves-light btn-small red darken-3" >JOIN GAME</a></Link>
                    </div>
                </div>

                <Footer />
            </div>

        );
    }
}

export default Accueil;