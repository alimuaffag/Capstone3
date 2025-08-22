package seu.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import seu.capstone3.Model.*;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SimpleMailMessage mailMessage = new SimpleMailMessage();


    public void sendAcceptedEmail(Player player, Club club) {
        mailMessage.setFrom("alimuaffag@gmail.com");
        mailMessage.setTo(player.getEmail());
        mailMessage.setSubject("Accepted Email");
        String emailBody =   "Dear " + player.getName() + ",\n\n"
                + "Congratulations \n"
                + "We are pleased to inform you that your joining request has been ACCEPTED.\n\n"
                + "Club: " + club.getName() + "\n\n"
                + "We look forward to seeing your contribution and wish you great success in your journey with us.\n\n"
                + "Best regards,\n"
                + club.getName() + " Management Team";
        mailMessage.setText(emailBody);
        mailSender.send(mailMessage);
    }

    public void sendRejectedEmail(Player player, Club club) {
        mailMessage.setFrom("alimuaffag@gmail.com");
        mailMessage.setTo(player.getEmail());
        mailMessage.setSubject("Rejected Email");
        String emailBody =   "Dear " + player.getName() + ",\n\n"
                + "Thank you for your interest in joining "
                + club.getName()+ " our club" + ".\n"
                + "After careful consideration, we regret to inform you that your joining request "
                + "has not been accepted this time.\n\n"
                + "We truly appreciate your effort and encourage you to apply again in the future.\n\n"
                + "Best regards,\n"
                + club.getName() + " Management Team";
        mailMessage.setText(emailBody);
        mailSender.send(mailMessage);
    }

    public void sendTournamentWelcome(Tournament tournament, Player player){
        mailMessage.setFrom("alimuaffag@gmail.com");
        mailMessage.setTo(player.getEmail());
        mailMessage.setSubject("Welcome to " + tournament.getName() + " Tournament!");
        String emailBody = "Dear " + player.getName() + ",\n\n"
                + "Welcome to the " + tournament.getName() + " tournament!\n\n"
                + "Tournament Details:\n"
                + "Start Date: " + tournament.getStartDate() + "\n"
                + "End Date: " + tournament.getEndDate() + "\n"
                + "Location: " + tournament.getLocation() + "\n"
                + "Sponsor: " + tournament.getSponsor().getName() + "\n\n"
                + "We are excited to have you participate and wish you the best of luck!\n\n"
                + "Best regards,\n"
                + tournament.getName() + " Management Team";

        mailMessage.setText(emailBody);
        mailSender.send(mailMessage);
    }

    public void qualifiedEmail(RecruitmentOpportunity recruitmentOpportunity, Player player,Club club) {
        mailMessage.setFrom("alimuaffag@gmail.com");
        mailMessage.setTo(player.getEmail());
        mailMessage.setSubject( "You’re qualified for " + recruitmentOpportunity.getTitle());
        String emailBody = "Dear " + player.getName() + ",\n\n"
                + "Good news! You’re qualified for the " + recruitmentOpportunity.getTitle() + " role at " + club.getName() + ".\n\n"
                + "Next step:\n"
                + "- Find this opportunity in our page in the system." + "\n"
                + "- Job ID: " + recruitmentOpportunity.getId() + "\n"
                + "- If you’re interested, please submit your application there.\n\n"
                + "Best regards,\n"
                + club.getName() + " Talent Team";
        mailMessage.setText(emailBody);
        mailSender.send(mailMessage);
    }

    public void updateTournamentDate(Tournament tournament, Sponsor sponsor, List<Player> players) {
        for (Player player : players) {
            if (player.getEmail() != null) {
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom("alimuaffag@gmail.com");
                mailMessage.setTo(player.getEmail());
                mailMessage.setSubject("Postponement of " + tournament.getName());
                mailMessage.setText("Dear " + player.getName() + ",\n\n"
                        + "We would like to inform you that the " + tournament.getName()
                        + " has been postponed. The new date for the tournament is from "
                        + tournament.getStartDate() + " to " + tournament.getEndDate() + ".\n\n"
                        + "We appreciate your understanding and look forward to seeing you at the tournament.\n\n"
                        + "Best regards,\n" + sponsor.getName());

                mailSender.send(mailMessage);
            }
        }
    }




}
