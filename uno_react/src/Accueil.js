import React, { Component } from 'react';
import logo from './ressources/uno.png';
import son from './ressources/son.png';
import Footer from './footer.js';
import Header from './header.js';
import { Link, Redirect } from 'react-router-dom';
import './css/Accueil.css';
import './css/materialize.min.css';
import './css/animation.css';
import io from 'socket.io-client';

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
    state = {
        toInGame : false,
        socket : io.connect('http://localhost:8080')
    }

    componentDidMount(){
        this.state.socket.on('effect',function(){
            if(document.getElementById('visibleDraggable1'))
                console.log('works')
        })
    }
    render() {
        const message = " Hi ! ";
        const author = "Tim";
        
        if (this.state.toInGame === true) {
            return <Redirect to={{
                pathname: '/ingame',
                state: { toInGame: this.state.toInGame }
            }} />
        }

        return (

            <div className="div">

                <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"></link>
                <link href="https://fonts.googleapis.com/css?family=Righteous" rel="stylesheet"></link>

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
                <div className="animated zoomIn row container">
                    <div className="col s4">
                        <div className="waves-effect waves-light btn-small red darken-3" onClick={() => this.newGame()}>NEW GAME</div>
                    </div>
                    <div className="animated zoomIn col s4">
                        <div className="waves-effect waves-light btn-small red darken-3" onClick={() => this.joinGame()}>JOIN GAME</div>
                    </div>
                </div>

                <Footer />
            </div>

        );
    }


    newGame(){
        this.state.socket.emit('create', 2, this.props.match.params.pseudo)
        this.state.socket.on('message', function(response){
            if(response.res){
                this.setState(() =>({ toInGame : true }))
            }
            alert(response.message)
        }.bind(this));
        this.state.socket.on('info',function(message){
            alert(message)
        })
    }

    async joinGame(){
        await this.state.socket.emit('join')
        this.state.socket.on('message', function(response){
            if(response.res){
                this.setState(() =>({ toInGame : true }))
            }
            alert(response.message)
        }.bind(this));
        this.state.socket.on('info',function(message){
            alert(message)
        })
    }
}

export default Accueil;