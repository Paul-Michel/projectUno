/**
 * GameController
 *
 * @description :: Server-side actions for handling incoming requests.
 * @help        :: See https://sailsjs.com/docs/concepts/actions
 */
const MongoClient = require('mongodb').MongoClient;
var ObjectId = require('mongodb').ObjectID;
const url = "mongodb://localhost:27017/";
module.exports = {

    findAll: function(req,res){
      console.log('test1')
      MongoClient.connect(url, function(err, db) {
        if (err) throw err;
        var dbo = db.db("unoUsers");
        dbo.collection("user").find({}).toArray(function(err, result) {
          if (err) throw err;
          res.send(result);
          db.close();
        });
      }); 
    },

    findById: function(req,res){
        console.log('test1')
        MongoClient.connect(url, function(err, db) {
          if (err) throw err;
          var dbo = db.db("unoUsers")
          dbo.collection("user").find({"_id": ObjectId(req.params.id)}).toArray(function(err, result) {
            if (err) throw err;
            res.send(result);
            db.close();
          });
        }); 
      },
};

