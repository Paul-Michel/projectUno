import React, { Component } from 'react';
import './css/materialize.min.css';
import { Link } from 'react-router-dom';
import './header.css';

class Header extends Component {

        
    adaptBtn(){
        if(window.location.href == "http://localhost:3000/ingame"){
            return <li><a href="http://localhost:1337/logout" className="waves-effect waves-light btn-small red colorNav"> LOGOUT </a></li>
        }
        else{
            return <li><Link to='/settings'><a className="waves-effect waves-light btn-small red colorNav"> STATS </a></Link></li>
        }
    }

    render() {

        return (
            <nav>
            <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"></link>
            <link href="https://fonts.googleapis.com/css?family=Righteous" rel="stylesheet"></link>
                <div class="animated fadeInDown nav-wrapper colorNav">
                    <a id="home" href="/accueil" class=" brand-logo pulse "> <i class="medium material-icons positionIconHome">home</i> </a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down">
                        {this.adaptBtn()}
                    </ul>
                </div>
            </nav>
        )
    }
}
export default Header;