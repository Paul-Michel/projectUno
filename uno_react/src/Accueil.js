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
    sendChat = (message, author) => {
        console.log(message)
        const chat = document.getElementById('messageChat')
       // const message = document.getElementById('message').value
        const newMessage = document.createElement('div')
        newMessage.classList.add('chatContainer')
        chat.appendChild(newMessage)
        const authorMessage = document.createElement('p')
        authorMessage.innerHTML = author
        authorMessage.style.borderBottom = "2px solid black"
        newMessage.appendChild(authorMessage)
        const messageContent = document.createElement('p')
        messageContent.innerHTML = message
        newMessage.appendChild(messageContent)
        const dateMessage = document.createElement('span')
        dateMessage.classList.add('time-right')
        const dateHours = new Date().getHours()
        const dateSeconds = new Date().getMinutes()
        dateMessage.innerHTML = dateHours + ":" + dateSeconds
        newMessage.appendChild(dateMessage)
    }
    render() {
        const message = " Hi ! ";
        const author = "Tim";
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
                    <div id="openChat" className="animated rotateInUpRight positionChat">
                        <div id="messageChat" class="positionMessageChat"></div>
                        <div id="inputChat" class="inputChat">
                            <i class="material-icons prefix positionIconChat">textsms</i>
                            <input id="message" type="text" class="autocomplete positionInputChat" name="pseudo" /> 
                            <button onClick={() =>this.sendChat(message, author)}className="waves-effect waves-light btn-small red colorBody"> Send </button>
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