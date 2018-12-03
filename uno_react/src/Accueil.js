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

        if (this.state.toInGame === true) {
            return <Redirect to={{
                pathname: '/ingame',
                state: { toInGame: this.state.toInGame},
                socket: { socket: this.state.socket}
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
                            <a className="waves-effect waves-light btn-small red colorBody centerbtn" onClick={() => this.newGame()}>NEW GAME</a>
                        </div>
                        <div className="valign-wrapper boutonJoin">
                            <a className="waves-effect waves-light btn-small red colorBody centerbtn" onClick={() => this.joinGame()}>JOIN GAME</a>
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        );
    }


    newGame(){
        this.state.socket.emit('create', 3, this.props.match.params.id, this.props.match.params.pseudo)
        this.state.socket.on('message', function(response){
            if(response.res === true){
                console.log("ok")
                this.setState(() =>({ toInGame : true }))
            }
            alert(response.message)
        }.bind(this));
        this.state.socket.on('info',function(message){
            alert(message)
        })
    }

    async joinGame(){
        await this.state.socket.emit('join', this.props.match.params.id, this.props.match.params.pseudo)
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