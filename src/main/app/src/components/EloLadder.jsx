import React from 'react'

const getData = (page=0) => fetch(process.env.REACT_APP_REPO_URL + "/players?sort=elo,desc&page=" + page, { mode: "cors" }).then(res => res.json())

class EloLadder extends React.Component {
    constructor(props) {
        super(props)
        this.state = {players: [], currPage: 0}
    }

    componentDidMount() {
        getData(this.state.currPage)
            .then(res => {
                this.setState({players: res._embedded.players})
            }).catch(err => console.error("Error fetching ladder:", err))
    }

    render() {
        let rows = JSON.parse(JSON.stringify(this.state.players)).map((e, i) => <div key={i} className="ladder-row">
                <div>{e.name}</div><div>{e.elo}</div>
            </div>)
        for (let i = 5; i < rows.length; i = i + 6) {
            rows.splice(i, 0, <span className="ladder-row-hline"></span>)
        }
        return <div>
            <div id="ladder-header">Elo Ladder</div>
            <div id="ladder">
            {rows}
            </div>
        </div>
    }
}
export default EloLadder
