Kashaf – Sport Scouting & Management System

Overview:

Kashaf is a backend application built with Spring Boot that provides a centralized platform for managing players, clubs, scouts, sponsors, and tournaments.

The system goes beyond standard CRUD operations by incorporating:
AI-powered scouting (using OpenAI) to analyze player data, provide recruitment recommendations, and generate personalized training plans.

Email notifications (SMTP Gmail) to automate communication such as invitations, qualification notices, and updates.

Player and club management to streamline requests, registrations, and club-related activities.

Tournament and sponsorship management to organize competitions and handle sponsor interactions.

Recruitment workflows enabling players to submit requests to join tournaments or clubs.

Optional file storage with MinIO, which can be replaced with other cloud storage services.

Contribution – Ali’s Work
My primary focus in Kashaf was on Players, Requests, AI-driven features, and Email integration (SMTP Gmail).
Additionally, I contributed support to Club recruitment workflows, Tournament rescheduling, and Sponsor-related features.

Endpoints Developed:-


Players & Requests:

POST /add → Submit a request to join a tournament

GET /player-request/{player_id} → Retrieve join requests of a specific player

DELETE /withdraw/{tournamentId}/{playerId} → Withdraw a player from a tournament

GET /get-player-by-id/{player_id} → Retrieve player details

GET /get-all-players-without-club → List all players not associated with a club

GET /get-all-player-dto → Retrieve player data in DTO format


AI Features:

GET /ai-recommendations/{opportunity_id} → AI-generated recruitment recommendations

GET /strengths-weaknesses/{player_id} → Analyze player strengths and weaknesses

GET /training-plan/{player_id}/{days} → Generate a personalized training plan


Email (SMTP Gmail):

Automated email notifications for player and request-related events


Endpoints Supported:-


Club Recruitment:

PUT /accept-player-to-club/{recruitmentOpportunity_id}/{requestJoining_id} → Accept a player into a club

PUT /reject-player/{recruitmentOpportunity_id}/{requestJoining_id} → Reject a player from a club

POST /qualified-email/{recruitmentOpportunity_id}/{player_id}/{club_id} → Send a qualification email to a player


Tournaments & Sponsors:

PUT /reschedule-tournaments/{sponsor_id}/{tournamentId} → Reschedule a tournament



Technology Stack:

Java 17+

Spring Boot (Web, Data JPA, Validation, Mail)

Hibernate / JPA

MySQL / PostgreSQL

Lombok

SMTP Gmail (email service)

OpenAI API (AI recommendations and player analysis)

MinIO (optional for file storage)

Kashaf Class Diagram:
<img width="3840" height="1603" alt="Kashaf UML" src="https://github.com/user-attachments/assets/96fda48a-2592-4a75-a506-6c54df67172f" />

Kashaf Presntation:
[Kashaf Presntations.pptx](https://github.com/user-attachments/files/21958234/Kashaf.Presntations.pptx)
