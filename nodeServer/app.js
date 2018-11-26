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
            let infoSocket = {socket: socket, pseudo: pseudo, id: id}
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
        socket.on('join', function (pseudo, id) {
            let infoSocket = {socket: socket, pseudo: pseudo, id: id}
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
    let turn = 0
    let players = []
    sockets.forEach(s => {
        s.socket.on('effect', function(){
            turn = effect(cardPlayed)
            newTurn(turn)
        })
        players.push(s.id)
    })
    createGame("players=" + players)
    console.log('game created')
}

async function createGame(postData){
    console.log(postData)
    var clientServerOptions = {
        uri: 'http://localhost:7000/newgame'+ '?' + postData,
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    }

    await request(clientServerOptions, function (error, response) {
        newTurn("playerIdx=0")
        return;
    });
    
}

function newTurn(postData){
    console.log(postData)
    var clientServerOptions = {
        uri: 'http://localhost:7000/newturn'+ '?' + postData,
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    }

    request(clientServerOptions, function (error, response) {
        console.log(JSON.parse(response.body))
        socket.emit('newTurn')
        return;
    });
}


function effect(postData, turn){
    console.log(postData)
    var clientServerOptions = {
        uri: 'http://localhost:7000/effect',
        method: 'POST',
        json: postData,
        headers: {
            'Content-Type': 'application/json'
        }
    }

    request(clientServerOptions, function (error, response) {
        console.log(response.body)
        if(response.body.CanRePlay)
            return turn
        else 
            return response.body.nextPlayer
    });
}


start(gameCreateListener)