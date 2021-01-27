package com.geraud.ocr_webapp.controllers;

import com.geraud.ocr_webapp.model.Booking;
import com.geraud.ocr_webapp.model.Loan;
import com.geraud.ocr_webapp.model.Member;
import com.geraud.ocr_webapp.service.CallLoanApi;
import com.geraud.ocr_webapp.utils.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@Slf4j
public class LoanController {

    @Autowired
    CallLoanApi callLoanApi;

    /**
     * Liste les livres empruntés
     * @param login identifiants de l'emprunteur
     * @param model model pour la vue, regroupe la liste des livres empruntés sous l áttribut "myLoans"
     * @return page ffichant l'historique des livres empruntés
     */
    @RequestMapping("/myLoans")
    public String listLoanFromMember(@ModelAttribute("identifiants")Login login,
                                     Model model){
        try {
            Loan[] myLoans = callLoanApi.getLoanbyMember(login);
            Booking[] myBookings = callLoanApi.getBookingsByMember(login);

            model.addAttribute("myBookings" , myBookings);
            model.addAttribute("myLoans", myLoans);
        }catch (Exception e){
            log.error("Erreur serveur : " + e.getMessage());
            return "redirect:/errorPage";
        }
        return "listMyLoans";
    }

    /**
     * méthode pour prolonger la période de prêt
     * @param id id de l'emprunt à prolonger
     * @param redirectAttributes récupération des identifiantsde l'emprunteur pour ne pas les lui redemander lors de la redirection
     * @return redirection vers l'historique des emprunts mis à jour
     */
    @RequestMapping("/loan/{id}/extend")
    public String extendLoan(@PathVariable(value = "id") Long id ,
                             RedirectAttributes redirectAttributes){

        try {
            Loan patchedLoan = callLoanApi.patchLoan(id);

            //attribution des identifiants de l'emprunt modifié pour envoyer directement au @ModelAttribute de la méthode du controller de l'historique des emprunts
            Login login = new Login();
            login.setCardnumber(patchedLoan.getMember().getCardnumber());
            login.setEmail(patchedLoan.getMember().getEmail());
            redirectAttributes.addFlashAttribute("identifiants", login);
        }catch (Exception e){
            log.error("Erreur serveur : " + e.getMessage());
            return "redirect:/errorPage";
        }
        //redirection vers l'historique des emprunts
       return "redirect:/myLoans";
    }

    /**
     * controller permetant d'annuler une réservation
     * @param id de la réservation à annuler
     * @param redirectAttributes récupération de l'identité du membre annulant une réservation pour le rediriger vers sa page de profil
     * @return page des emprunts et réservation du membre en cas de succès , sinon redirection vers la page d'erreur
     */
    @RequestMapping("/booking/{id}/delete")
    public String deleteBooking(@PathVariable(value = "id") Long id,
                                RedirectAttributes redirectAttributes){
        try {
            Member member = callLoanApi.deleteBooking(id);
            Login login = new Login();
            login.setCardnumber(member.getCardnumber());
            login.setEmail(member.getEmail());
            redirectAttributes.addFlashAttribute("identifiants", login);

        } catch (Exception e){
            log.error("erreur serveur " + e.getMessage());
            return "redirect:/errorPage";
        }
        return "redirect:/myLoans";
    }
}
