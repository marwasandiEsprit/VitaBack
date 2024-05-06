package tn.esprit.vitanova.controllers;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.vitanova.entities.*;
import tn.esprit.vitanova.repository.UserRepo;
import tn.esprit.vitanova.services.Allservices;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor


public class controller {
    UserRepo user;

    Allservices allservices;

    @PostMapping("/psychologue/addpsychologue")
    public Psychologue ajouterPsychologue(@RequestBody Psychologue p) {
        return allservices.ajouterPsychologue(p) ;
    }

    @PutMapping("/psychologue/updatepsychologue/{idpsychologue}")
    public void updatepsychologue(@PathVariable Long idpsychologue,@RequestBody Psychologue psychologue){
         allservices.updatepsychologue(idpsychologue,psychologue);
    }
    @GetMapping("/psychologue/getpsychologueId/{idpsychologue}")
    public Psychologue getpsychologuebyId(@PathVariable Long idpsychologue){
        return allservices.getpsychologuebyId(idpsychologue);
    }
    @GetMapping("/psychologue/getAll")
    public List<Psychologue> getAllForm(){
        return allservices.chercherTousForm();
    }
    @DeleteMapping("/psychologue/deletepsychologue/{idpsychologue}")
    public  void  supprimerpsychologue(@PathVariable Long idpsychologue) {
        allservices.supprimerpsychologue(idpsychologue);}
        @PostMapping("/rapportpsy/addrapportpsy")
        public RapportPsy ajouterrapportpsychologue(@RequestBody RapportPsy rp) {
            return allservices.ajouterrapportpsychologue(rp);
        }
    @PutMapping("/rapportpsy/updaterapportpsy/{idrapportpsychologue}")
    public void updateprapportpsychologue(@PathVariable Long idrapportpsychologue,@RequestBody RapportPsy rp){
        allservices.updateprapportpsychologue(idrapportpsychologue,rp);
    }
    @GetMapping("/rapportpsy/getrapportpsychologueId/{idrapportpsychologue}")
    public RapportPsy getrapportpsychologuebyId(@PathVariable Long idrapportpsychologue){
        return allservices.getrapportpsychologuebyId(idrapportpsychologue);
    }
    @GetMapping("/rapportpsy/getAll")
    public List<RapportPsy> chercherTousrapport() {
    return allservices.chercherTousrapport();}
    @DeleteMapping("/rapportpsy/deleterapportpsychologue/{idrapportpsychologue}")
    public void supprimerrapportpsychologue(@PathVariable Long idrapportpsychologue) {
        allservices.supprimerrapportpsychologue(idrapportpsychologue);
    }

    @PostMapping("/notification/addnotification")
    public Notifications ajouternotification(@RequestBody Notifications no) {

        return allservices.ajouternotification(no);
    }
    @PutMapping("/notification/updatenotification/{idnotification}")
    public void updatenotification(@PathVariable Long idnotification,@RequestBody Notifications notification) {
        allservices.updatenotification(idnotification,notification);
    }
    @GetMapping("/notification/getnotificationbyid/{idnotication}")
    public Notifications getnotificationbyId(@PathVariable Long idnotication){
       return  allservices.getnotificationbyId(idnotication);
    }
    @GetMapping("/notification/getall")
            public List<Notifications> cherchertousnotification(){return allservices.cherchertousnotification();}

    @DeleteMapping("/notification/deletenotification/{idnotification}")
    public void supprimernotification(@PathVariable Long idnotification){allservices.supprimernotification(idnotification);}
    @PostMapping("/chat/addchat")
    public Chat ajouterchat(@RequestBody Chat chat){return allservices.ajouterchat(chat);}
    @PutMapping("/chat/updatechat/{idchat}")
    public void updatechat(@PathVariable Long idchat, @RequestBody Chat chat){allservices.updatechat(idchat,chat);}
    @GetMapping("/chat/getchatbyid/{idchat}")
    public Chat getchatbyId(@PathVariable Long idchat){return allservices.getchatbyId(idchat);}
    @GetMapping("/chat/getall")
    public List<Chat> cherchertouschat(){return allservices.cherchertouschat();}
    @DeleteMapping("/chat/deletechat/{idchat}")
    public void supprimerchat(@PathVariable Long idchat){allservices.supprimerchat(idchat);}
    @GetMapping("/client/getall")
    public List<Client> cherchertousclient(){
        return allservices.cherchertousclient();
    }
    @PostMapping("/consultation/addconsultation")
    public Consultation addconsultation(@RequestBody Consultation consultation){return allservices.addconsultation(consultation);}
    @PutMapping("/consultation/updateconsultation/{id}")
    public void updateconsultation(@PathVariable Long id, @RequestBody Consultation consultation){
        allservices.updateconsultation(id,consultation);

    }
    @GetMapping("/consultation/getall")
            public List<Consultation> getallconsultation(){
        return allservices.getallconsultation();}
    @GetMapping("/consultation/show/{id}")
    public Consultation showconsultation(@PathVariable Long id){return allservices.showconsultation(id);}
    @DeleteMapping("/consultation/deleteconsultation/{id}")
    public void deleteconsultationbyid(@PathVariable Long id){allservices.deleteconsultationbyid(id);}
//    @GetMapping("/countPerDay/{psychologueId}")
//    public Integer numberconsultation(@PathVariable Long psychologueId) {
//
//        return allservices.numberconsultation(psychologueId);
//    }
//    @GetMapping("/consultation/{psychologueId}/{year}/{month}")
//    public Integer numberConsultationPerMonth(  @PathVariable Long psychologueId,
//                                              @PathVariable int year,
//                                              @PathVariable String month
//    ) {
//        Month monthEnum = Month.valueOf(month.toUpperCase());
//        return  allservices.numberConsultationPerMonth(psychologueId,YearMonth.of(year, monthEnum));
//    }
    @GetMapping("/generate-pdf/{rapportPsyId}")
    public String generatePdf(@PathVariable Long rapportPsyId) {
        allservices.generatePdf(rapportPsyId);
        return "PDF generated successfully mate";
    }
    @GetMapping("/consultations/{psychologueId}/{year}/{month}")
    public Map<LocalDate, Integer> consultationsPerDayInMonth(
            @PathVariable Long psychologueId,
            @PathVariable int year,
            @PathVariable String month
    ) {
        Month monthEnum = Month.valueOf(month.toUpperCase());
        YearMonth yearMonth = YearMonth.of(year, monthEnum);
        return allservices.consultationsPerDayInMonth(psychologueId, yearMonth);
    }
    @GetMapping("/check-consultation-slot/{date}/{startTime}/{psychologistId}")
    public Integer isConsultationSlotAvailable(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @PathVariable Long psychologistId) {

        return allservices.isConsultationSlotAvailable(date,startTime,psychologistId);
    }
    @GetMapping("/ab/{date}/{startTime}/{psychologistId}")
    public List<Consultation> con(
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
        @PathVariable Long psychologistId) {

            return allservices.con(date,startTime,psychologistId);
    }


///////


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Consultation>> getConsultationsByUserId(@PathVariable Long userId) {
        List<Consultation> consultations = allservices.getConsultationsByUserId(userId);
        return ResponseEntity.ok(consultations);
    }
    @GetMapping("/psy/{userId}")
    public ResponseEntity<List<Consultation>> getConsultationsBypsyId(@PathVariable Long userId) {
        List<Consultation> consultations = allservices.getConsultationsBypsyId(userId);
        return ResponseEntity.ok(consultations);
    }
    @GetMapping("/psychiatrist/{psychiatristId}")
    public List<RapportPsy> getRapportPsyByPsychiatristId(@PathVariable Long psychiatristId) {
        return allservices.getRapportPsyByPsychiatristId(psychiatristId);
    }
    @GetMapping("/{psychologistId}/{date}")
    public ResponseEntity<List<LocalTime>> getPsychiatristAvailability(
            @PathVariable("psychologistId") Long psychologistId,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Optional<User> psychiatrist = user.findById(psychologistId);
        if (psychiatrist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<LocalTime> availableTimeSlots = allservices.findAvailableTimeSlots(date, psychologistId);

        return ResponseEntity.ok(availableTimeSlots);
    }


    @GetMapping("/therapist/{therapistId}")
    public ResponseEntity<List<Feedback>> getFeedbackByTherapistId(@PathVariable Long therapistId) {
        List<Feedback> feedbackList = allservices.getFeedbackByTherapistId(therapistId);
        return ResponseEntity.ok(feedbackList);
    }

    @PostMapping("rating")
    public void saveFeedback(@RequestBody Feedback feedback) {

            allservices.saveFeedback(feedback);}



    @GetMapping("/{therapistId}/average-rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long therapistId) {
        double averageRating = allservices.calculateAverageRating(therapistId);
        return ResponseEntity.ok(averageRating);
    }

    @GetMapping("/{rapportPsyId}/feelings")
    public ResponseEntity<List<String>> extractFeelingsFromRapport(@PathVariable Long rapportPsyId) {
        List<String> feelings = allservices.extractFeelingsFromRapport(rapportPsyId);
        return ResponseEntity.ok(feelings);
    }
//  @GetMapping("/{id}")
//  public List<Consultation>getshit(@PathVariable Long id){
//        return allservices.getshit(id);
//  }
@GetMapping("/psychiatrist/{psychiatristId}/consultationCountPerClient")
public ResponseEntity<Map<String, Long>> getConsultationCountPerClientForPsychiatrist(@PathVariable Long psychiatristId) {
    Map<String, Long> consultationCountPerClient = allservices.getConsultationCountPerClientForPsychiatrist(psychiatristId);
    if (consultationCountPerClient == null) {
        // Handle the case where psychiatrist with given ID is not found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(consultationCountPerClient);
}
    @GetMapping("/totalConsultationsPerPsychiatrist")
    public ResponseEntity<Map<String, Long>> getTotalConsultationsPerPsychiatrist() {
        Map<String, Long> totalConsultationsPerPsychiatrist = allservices.getTotalConsultationsPerPsychiatrist();
        if (totalConsultationsPerPsychiatrist == null) {
            // Handle the case where data is not available
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(totalConsultationsPerPsychiatrist);
    }

}




