import React, { Component } from 'react';
import './css/inGame.css';
import pioche from './ressources/pioche.png';
import testcard from './ressources/b3.png';
import swaped from './ressources/swaped.png';
import './css/animation.css';
import Header from './header.js';


class Test extends Component {


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
                </div>
            </div>
        </div>
        )
    }
}

export default Test;