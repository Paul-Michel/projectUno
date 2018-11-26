import React, { Component } from 'react';
import logo from './ressources/uno.png';
import Footer from './footer.js';
import Header from './header.js';
import { Link } from 'react-router-dom';
import './css/Accueil.css';
import './css/materialize.min.css';
import './css/animation.css';

class Accueil extends Component {

    openChat = () => {
        document.getElementById('openChat').style.display = "block"
        document.getElementById('openChatBtn').style.display = "none"
        document.getElementById('closeChatBtn').style.display = "block"
    }

    closeChat = () => {
        document.getElementById('openChat').style.display = "none"
        document.getElementById('openChatBtn').style.display = "block"
        document.getElementById('closeChatBtn').style.display = "none"
    }

    render() {

        return (

            <div className="div">
                <Header />
                <div class="animated zoomIn container">
                    <img src={logo} className="tailleimg" />

                    <div className="centrage">
                        <div className="valign-wrapper">
                            <Link to="/ingame"><a className="waves-effect waves-light btn-small red colorBody centerbtn" >NEW GAME</a></Link>
                        </div>
                        <div className="valign-wrapper boutonJoin">
                            <Link to="/ingame"><a className="waves-effect waves-light btn-small red colorBody centerbtn" >JOIN GAME</a></Link>
                        </div>
                    </div>
                </div>
                <div id="chat">
                    <div id="openChat" className="animated rotateInUpRight">
                        <div class=" chatContainer">
                            <img src="" alt="Avatar" />
                            <p>Hello. How are you today?</p>
                            <span class="time-right">11:00</span>
                        </div>

                        <div class="chatContainer darker">
                            <img src="" alt="Avatar" class="right" />
                            <p>Hey! I'm fine. Thanks for asking!</p>
                            <span class="time-left">11:01</span>
                        </div>
                    </div>
                    <button id="openChatBtn" className="waves-effect waves-light btn-small red colorBody openChatBtn" onClick={this.openChat}> Messagerie </button>
                    <button id="closeChatBtn" className="waves-effect waves-light btn-small red colorBody closeChatBtn" onClick={this.closeChat}> Fermer </button>
                </div>

                <Footer />
            </div>

        );
    }
}

export default Accueil;