const router = require('express').Router()
const _ = require('lodash')

function sendData(){
  let data = fetch('l_url_de_vincent')
  return data
}

router.get('/newgame', (req, res) => {
    res.redirect('l_url_de_vincent').then((games) => res.json(games)).catch((err) => {
    return res.status(404).send(err)
  })
})
router.post('/newgame', (req, res) => {
  res.sendData().then((games) => res.json(games)).catch((err) => {
    return res.status(404).send(err)
  })
})

router.get('/newturn', (req, res) => {
  res.redirect('l_url_de_vincent').then((games) => res.json(games)).catch((err) => {
      return res.status(404).send(err)
  })
})
router.post('/newturn', (req, res) => {
  res.sendData().then((games) => res.json(games)).catch((err) => {
    return res.status(404).send(err)
  })
})

router.get('/play', (req, res) => {
  res.redirect('l_url_de_vincent').then((games) => res.json(games)).catch((err) => {
      return res.status(404).send(err)
  })
})
router.post('/play', (req, res) => {
  res.sendData().then((games) => res.json(games)).catch((err) => {
    return res.status(404).send(err)
  })
})

module.exports = router