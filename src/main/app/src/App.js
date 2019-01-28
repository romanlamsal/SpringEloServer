import React, { Component } from 'react';
import logo from './logo.svg';
import LastMatches from './components/LastMatches'
import EloLadder from './components/EloLadder'
import './App.css';

class App extends Component {
  render() {
    return (
        <div className="App">
            <EloLadder />
            <LastMatches />
        </div>
    );
  }
}

export default App;
