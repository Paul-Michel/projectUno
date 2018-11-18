module.exports = {
    renderIndex: function(req, res){
        return res.view('pages/dashboard', {user: req.user});
    }

};

