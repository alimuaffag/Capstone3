package seu.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seu.capstone3.Api.ApiResponse;
import seu.capstone3.DTOIN.TournamentDTOIn;
import seu.capstone3.DTOIN.TournamentRescheduleDTOIn;
import seu.capstone3.Model.Team;
import seu.capstone3.Model.Tournament;
import seu.capstone3.Service.TournamentService;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/tournament")
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllTournaments(){
        return ResponseEntity.status(200).body(tournamentService.getAllTournaments());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTournament(@Valid @RequestBody TournamentDTOIn tournamentDTOIn){
        tournamentService.addTournament(tournamentDTOIn);
        return ResponseEntity.status(200).body(new ApiResponse("Tournament added successfully"));
    }

    @PutMapping("/update/{tournament_id}")
    public ResponseEntity<?> updateTournament(@PathVariable Integer tournament_id,
                                              @Valid @RequestBody TournamentDTOIn tournamentDTOIn){
        tournamentService.updateTournament(tournament_id,tournamentDTOIn);
        return ResponseEntity.status(200).body(new ApiResponse("Tournament updated successfully"));
    }

    @DeleteMapping("/delete/{sponsor_id}/{tournament_id}")
    public ResponseEntity<?> deleteTournament(@PathVariable Integer tournament_id , @PathVariable Integer sponsor_id){
        tournamentService.deleteTournament(sponsor_id,tournament_id);
        return ResponseEntity.status(200).body(new ApiResponse("Tournament deleted successfully"));
    }

    //EX

    @GetMapping("/get-tournamentsOutDTO")
    public ResponseEntity<?> getAllTournamentsOutDTO(){
        return ResponseEntity.status(200).body(tournamentService.getAllTournamentsOutDTO());
    }

    @PutMapping("/assign-player-to-tournament/{tournamentId}/{playerId}")
    public ResponseEntity<?> assignPlayerToTournament(@PathVariable Integer tournamentId , @PathVariable Integer playerId){
        tournamentService.assignPlayerToTournament(tournamentId, playerId);
        return ResponseEntity.status(200).body(new ApiResponse("Tournament assigned successfully"));
    }

    @GetMapping("/get-players-in-tournament/{tournamentId}")
    public ResponseEntity<?> getPlayersInTournament(@PathVariable Integer tournamentId){
        return ResponseEntity.status(200).body(tournamentService.getPlayersInTournament(tournamentId));
    }


    @PostMapping("/{tournamentId}/determine-teams")
    public String drawTeams(@PathVariable Integer tournamentId) {
        tournamentService.determineTeamsRandomly(tournamentId);
        return "Teams have been drawn successfully!";
    }


    @GetMapping("/{tournamentId}/teams")
    public Set<Team> getTeams(@PathVariable Integer tournamentId) {
        return tournamentService.getTeamsInTournament(tournamentId);
    }

    @PostMapping("{sponsorId}/{tournamentId}/close")
    public ResponseEntity<?> closeTournament(@PathVariable Integer sponsorId,@PathVariable Integer tournamentId){
        tournamentService.closeTournament(sponsorId,tournamentId);
        return ResponseEntity.status(200).body(new ApiResponse("Tournament Closed"));
    }

    @GetMapping("/get-player-tournament/{email}")
    public ResponseEntity<?> getPlayerTournament(@PathVariable String email){
        return ResponseEntity.status(200).body(tournamentService.getPlayerTournament(email));
    }

    @GetMapping("/get-open-tournaments")
    public ResponseEntity<?> getOpenTournaments(){
        return ResponseEntity.status(200).body(tournamentService.getOpenTournaments());
    }


    @GetMapping("/get-remaining-players/{tournamentId}")
    public ResponseEntity<?> remainingPlayersToFull(@PathVariable Integer tournamentId){
        return ResponseEntity.status(200).body(tournamentService.remainingPlayersToFull(tournamentId));
    }

    @PutMapping("/withdraw/{tournamentId}/{playerId}")
    public ResponseEntity<?> withdrawPlayerFromTournament(@PathVariable Integer tournamentId,@PathVariable Integer playerId){
        tournamentService.withdrawPlayerFromTournament(tournamentId,playerId);
        return ResponseEntity.status(200).body(new ApiResponse("Withdrawal completed successfully"));
    }

    @GetMapping("/get-by-category/{categoryId}")
    public ResponseEntity<?> getTournamentsByCategory(@PathVariable Integer categoryId){
        return ResponseEntity.status(200).body(tournamentService.getTournamentsByCategory(categoryId));
    }

    @GetMapping("/get-by-sponsor/{sponsorId}")
    public ResponseEntity<?> getTournamentsBySponsorId(@PathVariable Integer sponsorId){
        return ResponseEntity.status(200).body(tournamentService.getTournamentsBySponsorId(sponsorId));
    }


    @PutMapping("/reschedule-tournaments/{sponsor_id}/{tournamentId}")
    public ResponseEntity<?> rescheduleTournament(@PathVariable Integer sponsor_id, @PathVariable Integer tournamentId , @Valid @RequestBody TournamentRescheduleDTOIn tournamentRescheduleDTOIn){
        tournamentService.rescheduleTournaments(sponsor_id,tournamentId,tournamentRescheduleDTOIn);
        return ResponseEntity.status(200).body(new ApiResponse("Tournament rescheduled successfully"));
    }
}
