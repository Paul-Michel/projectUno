import React from 'react';
import { BrowserRouter as Router, Route, Redirect } from 'react-router-dom'
import Accueil from './Accueil'
import './App.css';
import Settings from './Settings';
import inGame from './inGame';

const App = () => (
  <Router>
    <div class="main">
      <Route exact path='/' 
      component={() => window.location = 'http://localhost:1337/login'}/>
      <Route path='/accueil' component={Accueil} />
      <Route path='/settings' component={Settings}/>
      <Route path='/ingame' component={inGame}/>
    </div>
  </Router>
)
export default App;