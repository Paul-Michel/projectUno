import React from 'react';
import { BrowserRouter as Router, Route, Redirect } from 'react-router-dom'
import Accueil from './Accueil'
import './App.css';

const App = () => (
  <Router>
    <div>
      <Redirect from="/" to='/Accueil'/>
      <Route path='/Accueil' component={Accueil} />
    </div>
  </Router>
)
export default App;