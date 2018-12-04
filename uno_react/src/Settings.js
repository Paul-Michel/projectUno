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
 
    pseudoStat = async () => {
        const divstat = document.getElementById("divstat")
        while (divstat.firstChild) {
            divstat.removeChild(divstat.firstChild);
        }
 
        const statPseudo = document.getElementById("statpseudo").value
        const h1 = document.createElement("h1")
        const statdetails = document.createElement("p")

        const response1 = await fetch('http://localhost:5003/stats/' + statPseudo)
        const playerStat = await response1.json();
 
        h1.innerHTML = playerStat.username
        divstat.appendChild(h1)
        statdetails.innerHTML = "Nombre de victoires :" + playerStat.wonGames  + "<br></br>" +
            "Nombre de Défaites :" + playerStat.lostGames  + "<br></br>" +
            "Ratio victoires/défaites :" + playerStat.winRate
        divstat.appendChild(statdetails)

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
 
    histoPseudo = async () => {
        
        const listeGame = document.getElementById('listeGame')
        while (listeGame.firstChild) {
            console.log('ok')
            listeGame.removeChild(listeGame.firstChild);
        }
        const histoPseudo = document.getElementById('histoPseudo').value
        const response1 = await fetch('http://localhost:5003/wonbyplayer/' + histoPseudo)
        const games = await response1.json();
        const h1 = document.createElement('h1')
        listeGame.appendChild(h1)
        h1.innerHTML = histoPseudo
        h1.style.textAlign = "center"
        const ulHisto = document.createElement('ul')
        listeGame.appendChild(ulHisto)
       
        games.playedGames.forEach(game => {
            console.log(game)
            const descGame = document.createElement('div')
            const heurePlayed = game.datePlayed.slice(14, 19)
            listeGame.innerHTML = " Game du " + game.datePlayed.slice(0, 10) + " à " + heurePlayed
            const seeDescGame = document.createElement('a')
            listeGame.appendChild(seeDescGame)
            seeDescGame.innerHTML = " See more .."
            seeDescGame.onclick = this.toggleHiddenOnGame
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
            liGame1.innerHTML = " Première place : " + game.firstWinnerId
            liGame2.innerHTML= " Deuxième place : " + game.secondWinnerId
            liGame3.innerHTML= " Troisième place : " + game.thirdWinnerId
            liGame4.innerHTML= " Quatrième place (nul à chier) : " + game.fourthWinnerId
            descGame.appendChild(liGame1)
            descGame.appendChild(liGame2)
            descGame.appendChild(liGame3)
            descGame.appendChild(liGame4)
            listeGame.appendChild(descGame)
            console.log(descGame.firstChild)
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