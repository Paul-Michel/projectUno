import React, { Component } from 'react';
import './inGame.css';
import pioche from './ressources/pioche.png';
import testcard from './ressources/b3.png';


class Test extends Component {

    allowDrop = (ev) => {
        ev.preventDefault();
    }

    drag = (ev) => {
        ev.dataTransfer.setData("text", ev.target.id);
    }

    drop = (ev) => {
        ev.preventDefault();
        var data = ev.dataTransfer.getData("text");
        document.getElementById(data).classList.toggle("in-hand")
        ev.target.appendChild(document.getElementById(data));

    }

    //var xhr = new XMLHttpRequest();
    sendPost = () => {
        /* xhr.open("POST", "url", true);
         xhr.setRequestHeader('Content-Type', 'application/json');
         xhr.send(JSON.stringify({
             value: 'value'
         }));*/
        const facecachée1 = document.getElementsByClassName('facecachée1')[0]
        const facevisible1 = document.getElementsByClassName('facevisible1')[0]
        const facecachée2 = document.getElementsByClassName('facecachée2')[0]
        const facevisible2 = document.getElementsByClassName('facevisible2')[0]
        const facecachée3 = document.getElementsByClassName('facecachée3')[0]
        const facevisible3 = document.getElementsByClassName('facevisible3')[0]
        facevisible1.classList.toggle('hidden')
        facecachée1.classList.toggle('hidden')
        facevisible2.classList.toggle('hidden')
        facecachée2.classList.toggle('hidden')
        facevisible3.classList.toggle('hidden')
        facecachée3.classList.toggle('hidden')
    }


    render() {

        return (
            <div id="body">
                <div>
                    <div id="box">
                        <div id="box_left" onDragOver={(e)=>this.onDragOver(e)}></div>
                        <div id="box_right">
                            <img src={pioche}></img>
                        </div>
                    </div>
                </div>
                <div id="montour">
                    <button onClick={this.sendPost}> Mon tour </button>
                </div>

                <div id="draggableContent">
                    <div id="draggable1" draggable onDragStart = {(e) => this.onDragStart(e)}className="draggable in-hand" draggable="true" ondragstart="drag(event)">
                        <div className="facecachée1">
                            <img src={pioche} draggable="false"></img>
                        </div>
                        <div className="facevisible1 hidden">
                            <img src={testcard} draggable="false"></img>
                        </div>
                    </div>
                    <div id="draggable2" draggable class="draggable in-hand" draggable="true" ondragstart="drag(event)">
                        <div className="facecachée2">
                            <img src={pioche} draggable="false"></img>
                        </div>
                        <div className="facevisible2 hidden">
                            <img src={testcard} draggable="false"></img>
                        </div>
                    </div>
                    <div id="draggable3" draggable className="draggable in-hand" draggable="true" ondragstart="drag(event)">
                        <div className="facecachée3">
                            <img src={pioche} draggable="false"></img>
                        </div>
                        <div className="facevisible3 hidden">
                            <img src={testcard} draggable="false"></img>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default Test;