import React, { Component } from 'react';
import './css/inGame.css';
import pioche from './ressources/pioche.png';
import testcard from './ressources/b3.png';
import swaped from './ressources/swaped.png';
import './css/animation.css';
import Header from './header.js';
import { cpus } from 'os';


class Test extends Component {

    state = {
        images: null,
        turn: null,
        skipTurn: { bool: false, turn: null },
        cardInfo: null
    }

    importAllImages(r) {
        let images = {};
        r.keys().map((item, index) => { images[item.replace('./', '')] = r(item); });
        this.setState(() =>({  images }))
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
     
    handleDrop = (event) => {
        event.preventDefault();
        const id = event.dataTransfer.getData("text");
        event.target.appendChild(document.getElementById(id));
        this.effect(id)
     }
     
    handleDragOver(event) {
       event.preventDefault();
     }

    sendPost = () => {
        const hand = document.getElementById('draggableContent').children
        for(let i = 1 ; i <= hand.length / 2 ; i ++){
            document.getElementById('draggableHidden' + i).classList.toggle('hidden')
            document.getElementById('visibleDraggable' + i).classList.toggle('hidden')
        }          
    }

    skipTurn = () => {
        if(this.state.skipTurn.bool === true){
            this.props.location.socket.socket.emit('newTurn', this.state.skipTurn.turn)
            var myNode = document.getElementById("draggableContent");
            while (myNode.firstChild) {
                myNode.removeChild(myNode.firstChild);
            }
            this.setState(() => ({ skipTurn: {bool: false, turn: null}}))
        }    
    }

    updateCard = (currentCard) => {
        const stack = document.getElementById('box_left')
        if(stack.firstChild)
            stack.removeChild(stack.firstChild)
        const img = document.createElement('img')
        img.src = this.state.images[currentCard.color.toLowerCase() + "_" + currentCard.value.toLowerCase() + ".png"]
        stack.appendChild(img)
    }
    
    effect(id){
        let cardInfo = document.getElementById(id).firstChild.firstChild.alt;
        let cardColor = cardInfo.split(' ')[1]
        console.log(1)
        if(cardColor == 'BLACK'){
            console.log(2)
            this.setState(() =>({ cardInfo }))
            this.blackCard()
        }else{
            this.props.location.socket.socket.emit('effect', cardInfo, this.state.turn)
            const myNode = document.getElementById("draggableContent");
            while (myNode.firstChild) {
                myNode.removeChild(myNode.firstChild);
            }
        } 
    }

    blackEffect(color){
        let cardInfo = this.state.cardInfo.split(' ')
        cardInfo[1] = color
        cardInfo = cardInfo.join(' ')
        this.props.location.socket.socket.emit('effect', cardInfo, this.state.turn)
        const myNode = document.getElementById("draggableContent");
        const table = document.getElementById('tableColor')
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild);
        }
        while(table.firstChild){
            table.removeChild(table.firstChild)
        }
        document.body.removeChild(table)
    }
    createHand = (info) => {

        const hand = document.getElementById('hand')
        let draggableContent = document.getElementById("draggableContent")
        if(!document.getElementById("draggableContent")){
            draggableContent = document.createElement('div')
            draggableContent.id = 'draggableContent'
            hand.appendChild(draggableContent)
        }

        let i = 1
        info.hand.forEach(element => {
            const draggableHidden = document.createElement('div')
            draggableHidden.id = 'draggableHidden' + i
            draggableHidden.classList.add('animated', 'slideInUp', 'in-hand')
            draggableHidden.draggable = false
            draggableContent.appendChild(draggableHidden)
    
            const hiddenFace = document.createElement('div')
            hiddenFace.classList.add('hiddenFace' + i)
            draggableHidden.appendChild(hiddenFace)
    
            const hiddenFaceImg = document.createElement('img')
            hiddenFaceImg.classList.add('hiddenFaceImg')
            hiddenFaceImg.src = swaped 
            hiddenFaceImg.draggable = false
            hiddenFace.appendChild(hiddenFaceImg)
    
            const visibleDraggable = document.createElement('div')
            visibleDraggable.id = 'visibleDraggable' + i
            visibleDraggable.classList.add('animated', 'flipInY', 'draggable', 'in-hand', 'hidden')
            if(element.playable === true)
                visibleDraggable.setAttribute("draggable", true) 
            visibleDraggable.addEventListener("dragstart", this.handleDragStart)
            draggableContent.appendChild(visibleDraggable)
    
            const visibleFace = document.createElement('div')
            visibleFace.classList.add('visibleFace')
            visibleDraggable.appendChild(visibleFace)
    
            const visibleImg = document.createElement('img')
            visibleImg.classList.add('visibleImg')
            visibleImg.id = i 
            i = i + 1
            visibleImg.src = this.state.images[element.color.toLowerCase() + "_" + element.value.toLowerCase() + ".png"]
            if(element.playable === false)
                visibleImg.classList.add('notPlayable')
            visibleImg.setAttribute('alt', element.id + " " + element.color + " " + element.value)
            visibleImg.draggable = false
            visibleFace.appendChild(visibleImg)
        });
            
        
    }

    blackCard(){
        const table = document.createElement('table')
        const tr = document.createElement('tr')
        const tr2 = document.createElement('tr')
        const tr3 = document.createElement('tr')
        const tdRed = document.createElement('td')
        const tdBlue = document.createElement('td')
        const tdYellow = document.createElement('td')
        const tdGreen = document.createElement('td')

        table.id = 'tableColor'

        tdRed.innerHTML = "RED"
        tdBlue.innerHTML = "BLUE"
        tdGreen.innerHTML = "GREEN"
        tdYellow.innerHTML = "YELLOW"
        tr3.innerHTML = 'Pick a color !'

        table.classList.add('buttonColor')
        tdRed.classList.add('waves-effect', 'waves-light', 'btn-small', 'redButton', 'colorBody')
        tdBlue.classList.add('waves-effect', 'waves-light', 'btn-small', 'blue', 'colorBody')
        tdYellow.classList.add('waves-effect', 'waves-light', 'btn-small', 'yellow', 'colorBody')
        tdGreen.classList.add('waves-effect', 'waves-light', 'btn-small', 'green', 'colorBody')

        tdRed.addEventListener('click', () => this.blackEffect('RED'))
        tdBlue.addEventListener('click', () => this.blackEffect('BLUE'))
        tdYellow.addEventListener('click', () => this.blackEffect('YELLOW'))
        tdGreen.addEventListener('click', () => this.blackEffect('GREEN'))


        tr.appendChild(tdRed)
        tr.appendChild(tdYellow)
        tr2.appendChild(tdBlue)
        tr2.appendChild(tdGreen)
        table.appendChild(tr3)
        table.appendChild(tr)
        table.appendChild(tr2)
        document.body.appendChild(table)
    }

    async componentDidMount() {
        await this.importAllImages(require.context('./ressources/cards', false, /\.(png|jpe?g|svg)$/))

        this.props.location.socket.socket.on('chat', (message,author) => {
            this.sendChat(message, author)
        })
        this.props.location.socket.socket.on('newTurn', (info, turn) => {
            this.setState(() =>({ turn }))
            this.createHand(info)
            
        })
        this.props.location.socket.socket.on('update', (info) => {
            console.log(info)
            this.updateCard(info.currentCard)
        })
        this.props.location.socket.socket.on('skipTurn', (turn) => {
            console.log('ok')
            this.setState(() => ({ skipTurn: {bool: true, turn: turn}}))
        })
    }

    render() {

        

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
                    <div id="hand" class="col s12">
                        
                    <div class="col s12">
                        <div id="montour">
                            <button className="waves-effect waves-light btn-small unoColor centerbtn tour" onClick={this.sendPost}> Mon tour </button>
                        </div>
                        <div id="montour">
                            <button className="waves-effect waves-light btn-small unoColor centerbtn tour" onClick={this.skipTurn}> Passer tour </button>
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