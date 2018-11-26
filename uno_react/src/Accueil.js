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
                </div>
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