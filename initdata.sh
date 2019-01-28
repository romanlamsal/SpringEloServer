#/bin/bash
curl -X POST -H 'Content-Type: application/json' -d '{"winner": "player1", "loser": "player2"}' http://localhost:8080/api/match
curl -X POST -H 'Content-Type: application/json' -d '{"winner": "player2", "loser": "player1"}' http://localhost:8080/api/match
curl -X POST -H 'Content-Type: application/json' -d '{"winner": "player1", "loser": "player4"}' http://localhost:8080/api/match
curl -X POST -H 'Content-Type: application/json' -d '{"winner": "player2", "loser": "player3"}' http://localhost:8080/api/match
curl -X POST -H 'Content-Type: application/json' -d '{"winner": "player3", "loser": "player4"}' http://localhost:8080/api/match
curl -X POST -H 'Content-Type: application/json' -d '{"winner": "player5", "loser": "player1"}' http://localhost:8080/api/match
curl -X POST -H 'Content-Type: application/json' -d '{"winner": "player6", "loser": "player1"}' http://localhost:8080/api/match
curl -X POST -H 'Content-Type: application/json' -d '{"winner": "player5", "loser": "player6"}' http://localhost:8080/api/match

