Ali mainly worked on Players, Requests, and AI-based features and SMTP Gmail.
Implemented endpoints:

Add request to join a tournament → /add

Get join requests of a specific player → /player-request{player_id}

Withdraw a player from a tournament → /withdraw/{tournamentId}/{playerId}

AI recommendations for recruitment opportunities → /ai-recommendations/{opportunity_id}

Get player details → /get-player-by-id/{player_id}

Get all players without a club → /get-all-players-without-club

Get all players DTO → /get-all-player-dto

Analyze player strengths & weaknesses → /strengths-weaknesses/{player_id}

Generate a training plan for a player → /training-plan/{player_id}/{days}

Class Diagram:
<img width="3840" height="1603" alt="Kashaf UML" src="https://github.com/user-attachments/assets/96fda48a-2592-4a75-a506-6c54df67172f" />
