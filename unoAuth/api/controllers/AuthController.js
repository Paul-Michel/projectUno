const passport = require('passport');

module.exports = {
  login: function(req, res){
      passport.authenticate('local', function(err, user, info){
        if(err || !user){
            return res.send({message: info.message,
                user
            });
        }
        req.login(user, function(err){
            if (err) res.send(err);
            sails.log('User '+ user.id+ ' has logged in.');
            return res.redirect('http://localhost:3000/accueil/' + user.id + '/' + user.username);
        })
      })(req, res);
  },
  logout: function(req, res) {
      req.logout();
      res.redirect('/');
  },
  register: function(req, res){
    data = {
        username: req.body.username,
        email: req.body.email,
        password: req.body.password,
        description: req.body.description
    }
    console.log(data);

    User.create(data).fetch().exec(function(err, user){
        if (err) return res.negoiate(err);
        console.log(user);
        req.login(user, function(err){
            if (err) return res.negotiate(err);
            sails.log('User '+ user.id +' has logged in.');
            return res.redirect('/');
        })
    })

  }

};

