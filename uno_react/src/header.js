import React, { Component } from 'react';
import './css/materialize.min.css';
import { Link } from 'react-router-dom';
import './header.css';

class Header extends Component {

    render() {

        return (

            <nav>
                <div class="animated fadeInDown nav-wrapper colorNav">
                    <a href="/accueil" class=" brand-logo pulse"> <i class=" medium material-icons">home</i> </a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down">
                        <li><Link to='/settings'><a className="waves-effect waves-light btn-small red colorNav"> STATS </a></Link></li>
                    </ul>
                </div>
            </nav>
        )
    }
}
export default Header;