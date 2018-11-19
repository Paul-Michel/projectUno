import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom'
import Accueil from './Accueil'
import './App.css';
import Settings from './Settings';

const App = () => (
  <Router>
    <div>
      <Route path='/accueil' component={Accueil} />
      <Route path='/settings' component={Settings}/>
    </div>
  </Router>
)
export default App;