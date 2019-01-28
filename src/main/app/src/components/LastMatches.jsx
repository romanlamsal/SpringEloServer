import React from 'react'

const getData = () => fetch(process.env.REACT_APP_REPO_URL + "/lastMatches", { mode: "cors" }).then(res => res.json())

const formatDate = utcString => {
    const date = new Date(utcString)

    let day = date.toDateString(),
        time = date.toTimeString().split("GMT")[0]
    return day + ", " + time
}
const numLastMatches = 10
class LastMatches extends React.Component {
    constructor(props) {
        super(props)
        this.state = {matches: [], currPage: 0}
    }

    componentDidMount() {
        getData(this.state.currPage)
            .then(res => {
                this.setState({matches: res.slice(0, numLastMatches).map(match => ({timestamp: match.timestamp, winner_name: match.winner.id, loser_name: match.loser.id}))})
            }).catch(err => console.error("Error fetching ladder:", err))
    }

    render() {
        return <div>
            <div id="last-matches-header">Last {numLastMatches} Matches</div>
            {this.state.matches.map((e, i) => <div key={i}>{formatDate(e.timestamp) + ": " + e.winner_name + " vs " + e.loser_name}</div>)}
        </div>
    }
}
export default LastMatches
