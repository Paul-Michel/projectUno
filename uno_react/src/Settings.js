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
        const response1 = await fetch('http://localhost:5002/playedgames')
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
 
    toggleHiddenOnGame= () => {
        const liGame1 = document.getElementById('game1')
        liGame1.classList.toggle("hidden")
        const liGame2 = document.getElementById('game2')
        liGame2.classList.toggle("hidden")
        const liGame3 = document.getElementById('game3')
        liGame3.classList.toggle("hidden")
        const liGame4 = document.getElementById('game4')
        liGame4.classList.toggle("hidden")
    }
 
    histoPseudo = () => {
        const listeGame = document.getElementById('listeGame')
        while (listeGame.firstChild) {
            console.log('ok')
            listeGame.removeChild(listeGame.firstChild);
        }
        const histoPseudo = document.getElementById('histoPseudo').value
        const h1 = document.createElement('h1')
        listeGame.appendChild(h1)
        h1.innerHTML = histoPseudo
        h1.style.textAlign = "center"
        const ulHisto = document.createElement('ul')
        listeGame.appendChild(ulHisto)
       
        this.state.games.forEach(game => {
            const descGame = document.createElement('div')
            listeGame.appendChild(descGame)
            const heurePlayed = game.datePlayed.slice(14, 19)
            listeGame.innerHTML = " Game du " + game.datePlayed.slice(0, 10) + " à " + heurePlayed
            const seeDescGame = document.createElement('a')
            listeGame.appendChild(seeDescGame)
            seeDescGame.innerHTML = " See more .."
            seeDescGame.onclick = this.toggleHiddenOnGame
            if(histoPseudo === game.firstWinner || histoPseudo === game.secondWinner || histoPseudo === game.thirdWinner || histoPseudo === game.fourthWinner){
                const liGame1 = document.createElement('li')
                liGame1.id = "game1"
                const liGame2 = document.createElement('li')
                liGame2.id = "game2"
                const liGame3 = document.createElement('li')
                liGame3.id = "game3"
                const liGame4 = document.createElement('li')
                liGame4.id = "game4"
                liGame1.classList.add('hidden')
                liGame2.classList.add('hidden')
                liGame3.classList.add('hidden')
                liGame4.classList.add('hidden')
                liGame1.innerHTML = " Première place : " + game.firstWinner
                liGame2.innerHTML= " Deuxième place : " + game.secondWinner
                liGame3.innerHTML= " Troisième place : " + game.thirdWinner
                liGame4.innerHTML= " Quatrième place (nul à chier) : " + game.fourthWinner
                descGame.appendChild(liGame1)
                descGame.appendChild(liGame2)
                descGame.appendChild(liGame3)
                descGame.appendChild(liGame4)
            }
        })
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
                        <h1>History</h1>
                        <h3>Entrer nom joueur : </h3> <i class="material-icons prefix">textsms</i>
                        <input id="histoPseudo" type="text" class="autocomplete" name="pseudo" /> <button onClick={this.histoPseudo} className="waves-effect waves-light btn-small red colorBody" > Rechercher </button>
                        <div id="listeGame">
                           
                        </div>
                    </div>
                    <div id="stats" class="hidden">
                        <h1> Stats </h1>
                        <h3>Entrer nom joueur : </h3> <i class="material-icons prefix">textsms</i>
                        <input id="statpseudo" type="text" class="autocomplete" name="pseudo" /> <button onClick={this.pseudoStat} className="waves-effect waves-light btn-small red colorBody" > Rechercher </button>
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