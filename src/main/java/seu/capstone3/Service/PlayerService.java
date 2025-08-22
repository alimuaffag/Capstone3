package seu.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import seu.capstone3.Api.ApiException;
import seu.capstone3.DTOIN.PlayerDTOIn;
import seu.capstone3.DTOOUT.PlayerDTOOut;
import seu.capstone3.DTOOUT.PlayerSWAnalysisDTOOut;
import seu.capstone3.DTOOUT.TrainingPlanSimpleDTOOut;
import seu.capstone3.Model.Category;
import seu.capstone3.Model.Player;
import seu.capstone3.Repository.CategoryRepository;
import seu.capstone3.Repository.PlayerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final CategoryRepository categoryRepository;
    private final MinioService minioService;
    private final AiScoutingService aiScoutingService;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }


    public void addPlayer(PlayerDTOIn playerDTOIn) {
        Category category = categoryRepository.findCategoryById(playerDTOIn.getCategory_id());
        if(category == null) {
            throw new ApiException("Category not found");
        }

        Player existingPlayer = playerRepository.findPlayerByEmail(playerDTOIn.getEmail());
        if (existingPlayer != null) {
            throw new ApiException("Player with this email: "+ playerDTOIn.getEmail()+" already exists");
        }

        Player player = new Player(null,
                playerDTOIn.getName(),
                playerDTOIn.getEmail(),
                playerDTOIn.getPhoneNumber(),
                playerDTOIn.getAge(),
                playerDTOIn.getLocation(),
                playerDTOIn.getHeight(),
                playerDTOIn.getWeight(),
                playerDTOIn.getDescription(),
                playerDTOIn.getSkills(),
                null,
                null,
                null,
                category,
                null,
                null);

        playerRepository.save(player);
    }

    public void updatePlayer(Integer id, PlayerDTOIn playerDTOIn) {
        Player existing = playerRepository.findPlayerById(id);
        if (existing == null) {
            throw new ApiException("Player not found");
        }

        Category category = categoryRepository.findCategoryById(playerDTOIn.getCategory_id());
        if (category == null) {
            throw new ApiException("Category not found");
        }

        existing.setName(playerDTOIn.getName());
        existing.setAge(playerDTOIn.getAge());
        existing.setEmail(playerDTOIn.getEmail());
        existing.setPhoneNumber(playerDTOIn.getPhoneNumber());
        existing.setLocation(playerDTOIn.getLocation());
        existing.setHeight(playerDTOIn.getHeight());
        existing.setWeight(playerDTOIn.getWeight());
        existing.setDescription(playerDTOIn.getDescription());
        existing.setSkills(playerDTOIn.getSkills());
        existing.setCategory(category);

        playerRepository.save(existing);
    }



    public void deletePlayer(Integer id) {
        Player player = playerRepository.findPlayerById(id);
        if (player == null) {
            throw new ApiException("Player not found");
        }

        player.getTournaments().forEach(t -> t.getPlayers().remove(player));
        player.getTournaments().clear();
        playerRepository.delete(player);
    }



    // upload player CV
    public void uploadCv(Integer playerId, MultipartFile cvFile) throws Exception {
        Player player = playerRepository.findPlayerById(playerId);
        if (player == null) {
            throw new ApiException("Player not found");
        }

        String objectName = "player-" + playerId + "-cv-" + cvFile.getOriginalFilename();

        String fileUrl = minioService.uploadFile(cvFile, objectName);

        player.setCvUrl(fileUrl);
        playerRepository.save(player);
    }


    // this to convert player to dto
    public PlayerDTOOut convertToDTO(Player player) {
        PlayerDTOOut dto = new PlayerDTOOut();
        dto.setName(player.getName());
        dto.setEmail(player.getEmail());
        dto.setPhoneNumber(player.getPhoneNumber());
        dto.setAge(player.getAge());
        dto.setLocation(player.getLocation());
        dto.setHeight(player.getHeight());
        dto.setWeight(player.getWeight());
        dto.setDescription(player.getDescription());
        dto.setSkills(player.getSkills());
        dto.setCvUrl(player.getCvUrl());

        dto.setClubName(player.getClub() != null ? player.getClub().getName() : null);
        dto.setCategoryName(player.getCategory() != null ? player.getCategory().getName() : null);

        return dto;
    }


    // get player by ID with CV
    public PlayerDTOOut getPlayerWithCv(Integer id) throws Exception {
        Player player = playerRepository.findPlayerById(id);
        if (player == null) {
            throw new ApiException("Player not found");
        }
        PlayerDTOOut dto = convertToDTO(player);

        if (player.getCvUrl() != null) {
            String presignedUrl = minioService.getPresignedUrl(player.getCvUrl());
            dto.setCvUrl(presignedUrl);
        }

        return dto;
    }


    // get all players with dto
    public List<PlayerDTOOut> getAllPlayersDto() {
        return playerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    //get all players without club
    public List<PlayerDTOOut> getPlayersWithoutClub() {
        return playerRepository.getPlayersWithoutClub()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public Player getPlayerById(Integer id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    public PlayerSWAnalysisDTOOut analyzePlayerStrengthsWeaknesses(Integer player_id) {
        Player player = getPlayerById(player_id);
        return aiScoutingService.analyzePlayerStrengthsWeaknesses(player);
    }

    public TrainingPlanSimpleDTOOut getTrainingPlanSimpleDto(Integer player_id, Integer days) {
        Player player = getPlayerById(player_id);
        return aiScoutingService.generateAutoTrainingPlan(player,days);
    }
}
