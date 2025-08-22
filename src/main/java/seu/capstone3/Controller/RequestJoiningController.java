package seu.capstone3.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seu.capstone3.Api.ApiResponse;
import seu.capstone3.DTOIN.RequestJoiningDTOIn;
import seu.capstone3.Service.RequestJoiningService;

@RestController
@RequestMapping("/api/v1/request-joining")
@RequiredArgsConstructor
public class RequestJoiningController {
    private final RequestJoiningService requestJoiningService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllRequests() {
        return ResponseEntity.status(200).body(requestJoiningService.getAllRequestJoining());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addRequest(@RequestBody RequestJoiningDTOIn requestJoiningDTOIn) {
        requestJoiningService.addRequestJoining(requestJoiningDTOIn);
        return ResponseEntity.status(200).body(new ApiResponse("Request joined successfully"));
    }

    @GetMapping("/player-request{player_id}")
    public ResponseEntity<?> getPlayerRequestJoining(@PathVariable Integer player_id) {
        return ResponseEntity.status(200).body(requestJoiningService.playerApplayedRequestJoining(player_id));
    }

}
