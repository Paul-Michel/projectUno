module.exports = function(req, res, next) {
    'user strict';

    
    if(req.isSocket)
    {
        if(req.session &&
        req.session.passport &&
        req.session.passport.user)
        {
            return next();
        }

        res.json(401);
    }
    else
    {
        if(req.isAuthenticated())
        {
            return next();
        }
    }


};