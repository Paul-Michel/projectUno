import React, { Component } from 'react';
import './css/inGame.css';
import pioche from './ressources/pioche.png';
import testcard from './ressources/b3.png';
import swaped from './ressources/swaped.png';
import './css/animation.css';
import Header from './header.js';


class Test extends Component {

    state = {
        socket : null
    }

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
        console.log("bonjour")
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

    sendServerChat(){
        let message = document.getElementById('message').value
        this.props.location.socket.socket.emit('chat', message, "moi")
    }

    handleDragStart(event) {
        event.dataTransfer.setData("text/plain", event.target.id);
        const draggable = document.getElementById(event.target.id)
        draggable.classList.remove("in-hand")
        draggable.classList.add("in-stack")
     }
     
    handleDrop(event) {
       event.preventDefault();
       const id = event.dataTransfer.getData("text");
       event.target.appendChild(document.getElementById(id));
     }
     
    handleDragOver(event) {
       event.preventDefault();
     }

    //const xhr = new XMLHttpRequest();
    sendPost = () => {
        /* xhr.open("POST", "url", true);
         xhr.setRequestHeader('Content-Type', 'application/json');
         xhr.send(JSON.stringify({
             value: 'value'
         }));*/
        const draggableHidden1 = document.getElementById('draggableHidden1')
        const visibleDraggable1 = document.getElementById('visibleDraggable1')
        const draggableHidden2 = document.getElementById('draggableHidden2')
        const visibleDraggable2 = document.getElementById('visibleDraggable2')
        const draggableHidden3 = document.getElementById('draggableHidden3')
        const visibleDraggable3 = document.getElementById('visibleDraggable3')
        visibleDraggable1.classList.toggle('hidden')
        draggableHidden1.classList.toggle('hidden')
        visibleDraggable2.classList.toggle('hidden')
        draggableHidden2.classList.toggle('hidden')
        visibleDraggable3.classList.toggle('hidden')
        draggableHidden3.classList.toggle('hidden')
    }


    render() {

        this.props.location.socket.socket.on('chat', (message,author) => {
            this.sendChat(message, author)
        })


        return (
            <div id="body">
                <Header/>
                <div class="row">
                    <div class="col s6 conatinLeftBox">
                        <div id="box_left" onDrop={this.handleDrop} 
                                            onDragOver={this.handleDragOver}>
                        </div>
                    </div>
                    
                    <div class="col s6">
                        <div id="box_right">
                            <img class="imgPioche" src={pioche} draggable="false"></img>
                        </div>
                    </div>
                    <div class="col s12">
                        <div id="draggableContent">
                            <div id="draggableHidden1" class="animated slideInUp in-hand " draggable="false">
                                <div class="hiddenFace1">
                                    <img class="hiddenFaceImg" src={swaped} draggable="false"></img>
                                </div>
                            </div>
                            <div id="visibleDraggable1" class="animated flipInY draggable in-hand hidden"  draggable="true" onDragStart={this.handleDragStart}>
                                <div class="visibleFace">
                                    <img class="visibleImg" id="1" src={testcard} draggable="false"></img>
                                </div>
                            </div>

                            <div id="draggableHidden2" class="animated slideInUp in-hand"  draggable="false">
                                <div class="hiddenFace2">
                                    <img class="hiddenFaceImg" src={swaped} draggable="false"></img>
                                </div>
                            </div>
                            <div id="visibleDraggable2" class="animated flipInY draggable in-hand hidden"  draggable="true" onDragStart={this.handleDragStart}>
                                <div class="visibleFace">
                                    <img class="visibleImg" id="2" src={testcard} draggable="false"></img>
                                </div>
                            </div>

                            <div id="draggableHidden3" class="animated slideInUp in-hand"  draggable="false">
                                <div class="hiddenFace3">
                                    <img class="hiddenFaceImg" src={swaped} draggable="false"></img>
                                </div>
                            </div>
                            <div id="visibleDraggable3" class="animated flipInY draggable in-hand hidden" draggable="true" onDragStart={this.handleDragStart}>
                                <div class="visibleFace">
                                    <img class="visibleImg" id="3" src={testcard} draggable="false"></img>
                                </div>
                            </div>
                    </div>
                    <div class="col s12">
                        <div id="montour">
                            <button className="waves-effect waves-light btn-small unoColor centerbtn" onClick={this.sendPost}> Mon tour </button>
                        </div>
                    </div>
                    <div id="chat">
                    <div id="openChat" className="animated rotateInUpRight positionChat">
                        <div id="messageChat" class="positionMessageChat"></div>
                        <div id="inputChat" class="inputChat">
                            <i class="material-icons prefix positionIconChat">textsms</i>
                            <input id="message" type="text" class="autocomplete positionInputChat" name="pseudo" /> 
                            <button onClick={() =>this.sendServerChat()}className="waves-effect waves-light btn-small red colorBody"> Send </button>
                        </div>
                    </div>
                    <button id="openChatBtn" className="waves-effect waves-light btn-small red colorBody openChatBtn" onClick={this.openChat}> Messagerie </button>
                    <button id="closeChatBtn" className="waves-effect waves-light btn-small red colorBody closeChatBtn" onClick={this.closeChat}> Fermer </button>
                <div className="animated zoomIn row container">
                </div>
                </div>
                </div>
            </div>
        </div>
        )
    }
}

export default Test;