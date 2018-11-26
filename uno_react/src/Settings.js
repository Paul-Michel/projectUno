import React, { Component } from 'react';
import './css/Settings.css';
import Footer from './footer.js';
import Header from './header.js';
import './css/materialize.min.css';
import './css/animation.css';

class Settings extends Component {
    state = {
        games: [],
        players: []
    }

    async componentDidMount() {
        const response1 = await fetch('http://localhost:8002/playedgames')
        const games = await response1.json();
        this.setState({ games })
        const response2 = await fetch('http://localhost:5001/players')
        const players = await response2.json();
        this.setState({ players })
    }

    seeHistory = () => {
        document.getElementById('history').classList.toggle('hidden');
        document.getElementById('stats').classList.toggle('hidden');

    }
    seeStats = () => {
        document.getElementById('stats').classList.toggle('hidden');
        document.getElementById('history').classList.toggle('hidden');
    }

    seeDesc = (idgame) => {
        document.getElementById(idgame).classList.toggle('hidden')
    }

    pseudoStat = () => {
        const divstat = document.getElementById("divstat")
        while (divstat.firstChild) {
            divstat.removeChild(divstat.firstChild);
        }

        const statPseudo = document.getElementById("statpseudo").value
        const h1 = document.createElement("h1")
        const statdetails = document.createElement("p")

        this.state.players.forEach(player => {
            if (player.pseudo == statPseudo) {
                h1.innerHTML = player.pseudo
                divstat.appendChild(h1)
                statdetails.innerHTML = "Nombre de parties :" + player.playedNb + "<br></br>" +
                    "Nombre de victoires :" + player.winNb + "<br></br>" +
                    "Ratio victoires/défaites :" + player.winrate + "%";
                divstat.appendChild(statdetails)
            }
        })
        if (!divstat.firstChild) {
            h1.innerHTML = "Player not found"
            divstat.appendChild(h1)
        }
    }

    render() {

        const { games } = this.state
        const { players } = this.state

        return (
            <div>
                <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"></link>
                <link href="https://fonts.googleapis.com/css?family=Righteous" rel="stylesheet"></link>

                <Header />

                <div id="component">
                    <div id="menu">
                        <ul class="collection with-header">
                            <a href="#" class="collection-item red" onClick={this.seeHistory}><h4>HISTORY</h4></a>
                            <a href="#" class="collection-item red" onClick={this.seeStats}><h4>STATS</h4></a>
                        </ul>
                    </div>
                    <div id="history">
                        <center><h1>FLACYPE</h1></center>
                        <div id="listeGame">
                            Your games :
                            <ul> {games.map((game) => (
                                <li key={game.id}> Game {game.id}, {game.datePlayed.substring(0, 10)} <a div="seeMore" href="#" onClick={() => this.seeDesc(game.id)}>See more</a>
                                    <div id={game.id} class="hidden histocontent">
                                        Première place : {game.firstWinner} <br></br>
                                        Deuxième place : {game.secondWinner} <br></br>
                                        Troisième place : {game.thirdWinner}
                                    </div>
                                </li>
                            ))}
                            </ul>
                        </div>
                    </div>
                    <div id="stats" class="hidden">
                        <h1>Entrer nom joueur : </h1> <i class="material-icons prefix">textsms</i>
                        <input id="statpseudo" type="text" class="autocomplete" name="pseudo" /> <button onClick={this.pseudoStat}> Rechercher </button>
                        <div id="divstat">
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        )
    }

}

export default Settings;