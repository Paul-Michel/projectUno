var http = require('http');
var fs = require('fs');
var request = require('request');



// Chargement du fichier index.html affiché au client
function start(callback){
    var server = http.createServer();
    callback(gameListener, server)
    server.listen(8080);
}

function gameCreateListener(callback,server){
    // Load socket.io
    var io = require('socket.io').listen(server);
    sockets = []
    let nbPlayers;
    let currentPlayers = 0;
    let gameCreated = false;

    io.sockets.on('connection', function (socket) {
        //Create a game : with number of players and check if game not already created.
        socket.on('create', function (n,id,pseudo) {
            let infoSocket = {socket: socket, id: id, pseudo: pseudo}
            if(gameCreated === false){
                console.log('game created, player 1 joined the game.')
                socket.emit('message',{ message : 'game created, '+ infoSocket.pseudo + ' joined the game.', res : true})
                socket.emit('effect')
                sockets.push(infoSocket)
                nbPlayers = n;
                currentPlayers++
                gameCreated = true;
            }else
                socket.emit('message',{message : 'game already created, join it if you want !', res : false})
        });

        //Join the game : check if the game is created and there is still room for one player,
        //then increment player 
        socket.on('join', function (id, pseudo) {
            let infoSocket = {socket: socket, id: id, pseudo: pseudo}
            console.log('player ' + nbPlayers + ' joined the game.');
            if(gameCreated === false)
                socket.emit('message',{ message : "no game created ! Create one if you want", res : false })
            else if(currentPlayers < nbPlayers){
                    currentPlayers++
                    sockets.push(infoSocket)
                    socket.emit('message', { message : infoSocket.pseudo + ' joined the game.', res : true })
                if(currentPlayers == nbPlayers){
                    sockets.forEach(s => {
                        s.socket.emit('info', "let's go !")
                    });
                    callback(server,sockets)
                }
            }else
                socket.emit('message',{ message : 'game is full, sorry...', res : false})    
        });
    });
}

function gameListener(server,sockets){
    var io = require('socket.io').listen(server);
    let players = []
    sockets.forEach(s => {
        s.socket.on('chat',function(message,author){
            console.log("ok")
            sockets.forEach(s2 => {
                s2.socket.emit('chat', message, s.pseudo)
            })
        })
        s.socket.on('effect', function(cardPlayed, turn){
            effect(cardPlayed, sockets, turn)
        })
        s.socket.on('newTurn', function(turn){
            newTurn(turn, sockets)
        })
        players.push(s.id)
    })
    console.log(players)
    createGame("players=" + players, sockets)
    console.log('game created')
}

async function createGame(postData,socket){
    var clientServerOptions = {
        uri: 'http://localhost:7001/newgame'+ '?' + postData,
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    }

    request(clientServerOptions, function (error, response) {
        newTurn("0", sockets)
        return;
    });
    
}

function newTurn(postData, sockets){
    console.log(postData)
    var clientServerOptions = {
        uri: 'http://localhost:7001/newturn/' + postData,
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }
    // ERROR FIX : LES CARTES SE RECHARGENT ON REPLAY ET PAS ON PLAY, IL FAUDRA AUSSI AFFICHER LA CARTE EN COURS ET RECACHER LES CARTES A LA FIN DU TOUR
    request(clientServerOptions, function (error, response) {
        console.log('newTurn : ', JSON.parse(response.body))
        console.log(JSON.parse(response.body).CanPlay)
        if(JSON.parse(response.body).CanPlay == false){
            sockets[postData].socket.emit('skipTurn', JSON.parse(response.body).nextPlayer)
        }
        sockets[postData].socket.emit('newTurn', JSON.parse(response.body), postData)
        sockets.forEach(s => {
                s.socket.emit('update', JSON.parse(response.body))
            if(sockets.indexOf(s) == postData)
                s.socket.emit('chat', 'your turn', 'server')
            else
                s.socket.emit('chat', s.pseudo + ' playing...', 'server')
        })
        return response.body;
    });
}


function effect(postData, sockets, turn){
    postData = postData.split(' ')
    console.log(postData[0], postData[1], postData[2])
    let cardPlayed = {playerIdx: turn,
                    card: {
                        id: postData[0],
                        color: postData[1],
                        value: postData[2]
                    }}
    var clientServerOptions = {
        uri: 'http://localhost:7000/effect',
        method: 'POST',
        json: cardPlayed,
        headers: {
            'Content-Type': 'application/json'
        }
    }

    request(clientServerOptions, function (error, response) {
        console.log('effect : ', response.body)
        sockets.forEach(s => {
            s.socket.emit('update', response.body)
            if(sockets.indexOf(s) == turn)
                s.socket.emit('chat', 'you played ' + response.body.currentCard.color + ' ' + response.body.currentCard.value, 'server')
            else
                s.socket.emit('chat', sockets[turn].pseudo + ' played ' + response.body.currentCard.color + ' ' + response.body.currentCard.value, 'server')
        })
        if(response.body.CanRePlay !== true){
            turn = response.body.nextPlayer
            newTurn(turn,sockets) 
        }else{
            sockets[turn].socket.emit('newTurn', response.body, turn)
        }   
             
    });
}


start(gameCreateListener)