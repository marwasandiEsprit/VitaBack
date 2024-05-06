package tn.esprit.vitanova.services;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import tn.esprit.vitanova.entities.*;
import tn.esprit.vitanova.repository.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class Allservices implements Allservicesimpl{
    PsychologueRepo pr;
    NotificationssRepo np;
    ChatRepo chatRepo;
    RapportPsyRepo rapportPsyRepo;
    ClientRepo cl;
    ConsultationRepo cr;
    TemplateEngine templateEngine;

   UserRepo userRepository;
   FeedbackRepo fp;

    @Override
    public Psychologue ajouterPsychologue(Psychologue p) {
        return pr.save(p) ;
    }

    @Override
    public void updatepsychologue(Long idpsychologue, Psychologue psychologue) {
     psychologue.setPsychologueId(idpsychologue);
     pr.save(psychologue);
    }

    @Override
    public Psychologue getpsychologuebyId(Long idpsychologue) {
        return pr.findById(idpsychologue).orElse(null);
    }

    @Override
    public List<Psychologue> chercherTousForm() {
        return pr.findAll();
    }

    @Override
    public void supprimerpsychologue(Long idPsychologue) {
        pr.deleteById(idPsychologue);

    }

    @Override
    public RapportPsy ajouterrapportpsychologue(RapportPsy rp) {
        return rapportPsyRepo.save(rp);
    }

    @Override
    public void updateprapportpsychologue(Long idrapportpsychologue, RapportPsy rp) {
        rp.setIdRapportPsy(idrapportpsychologue);
        rapportPsyRepo.save(rp);

    }

    @Override
    public RapportPsy getrapportpsychologuebyId(Long idrapportpsychologue) {
        return rapportPsyRepo.findById(idrapportpsychologue).orElse(null);
    }

    @Override
    public List<RapportPsy> chercherTousrapport() {
        return rapportPsyRepo.findAll();
    }

    @Override
    public void supprimerrapportpsychologue(Long idrapportpsychologue) {
        rapportPsyRepo.deleteById(idrapportpsychologue);

    }

    @Override
    public Notifications ajouternotification(Notifications no) {
        return np.save(no);
    }

    @Override
    public void updatenotification(Long idnotification, Notifications notification) {
     notification.setIdNotifications(idnotification);
     np.save(notification);
     

    }


    @Override
    public Notifications getnotificationbyId(Long idnotication) {
        return np.findById(idnotication).orElse(null);
    }

    @Override
    public List<Notifications> cherchertousnotification() {

        return np.findAll();
    }

    @Override
    public void supprimernotification(Long idnotification) {
        np.deleteById(idnotification);

    }

    @Override
    public Chat ajouterchat(Chat chat) {
        return chatRepo.save(chat);
    }

    @Override
    public void updatechat(Long idchat, Chat chat) {
        chat.setIdChat(idchat);
        chatRepo.save(chat);
    }

    @Override
    public Chat getchatbyId(Long idchat) {
        return chatRepo.findById(idchat).orElse(null);
    }

    @Override
    public List<Chat> cherchertouschat() {
        return chatRepo.findAll();
    }

    @Override
    public void supprimerchat(Long idchat) {
         chatRepo.deleteById(idchat);
    }

    @Override
    public List<Client> cherchertousclient() {
        return cl.findAll();
    }

    @Override
    public Consultation addconsultation(Consultation consultation) {
        Integer availability = isConsultationSlotAvailable(consultation.getConsultationdate(), consultation.getStartTime(), consultation.getPsychiatrist().getId());

        if (availability.equals(0)) {
            return cr.save(consultation);
        } else {
            throw new IllegalArgumentException("Cannot add consultation. The slot is not available.");
        }
    }

    @Override
    public void updateconsultation(Long id, Consultation consultation) {
        consultation.setIdConsultation(id);
        cr.save(consultation);
    }

    @Override
    public Consultation showconsultation(Long idconsulation) {
        return cr.findById(idconsulation).orElse(null);
    }

    @Override
    public List<Consultation> getallconsultation() {
        return cr.findAll();
    }

    @Override
    public void deleteconsultationbyid(Long id) {
        cr.deleteById(id);
    }

    @Override
    public Integer numberconsultation(Long psychologueId) {


        return null;
    }


    //    @Override
//    public Integer numberconsultation(Long psychologueId) {
//        Psychologue psychologue = pr.findById(psychologueId).orElse(null);
//
//        List<Consultation> consultations=psychologue.getConsultations();
//
//        LocalDate today = LocalDate.now();
//        List<Consultation> todaysConsultations = psychologue.getConsultations().stream()
//                .filter(consultation -> consultation.getConsultationdate().equals(today))
//                .collect(Collectors.toList());
//
//        return todaysConsultations.size();
//    }
    /////number of consultation per month

//    public Integer numberConsultationPerMonth(Long psychologueId, YearMonth yearMonth) {
//        Psychologue psychologue = pr.findById(psychologueId).orElse(null);
//
//        if (psychologue == null) {
//            return 0; // Handle the case where the Psychologue is not found
//        }
//
//        List<Consultation> consultations = psychologue.getConsultations();
//
//        List<Consultation> consultationsInMonth = psychologue.getConsultations().stream()
//                .filter(consultation -> {
//                    LocalDate consultationDate = consultation.getConsultationdate();
//                    return consultationDate.getMonth() == yearMonth.getMonth() && consultationDate.getYear() == yearMonth.getYear();
//                })
//                .collect(Collectors.toList());
//
//        return consultationsInMonth.size();
//    }
    public void generatePdf(Long rapportPsyId) {
        Optional<RapportPsy> rapportPsyOptional = rapportPsyRepo.findById(rapportPsyId);
        if (rapportPsyOptional.isPresent()) {
            RapportPsy rapportPsy = rapportPsyOptional.get();
            try {

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("rapport_psy.pdf"));
                document.open();
                Image image = Image.getInstance("D:\\downloads\\im.jpg");
                image.scaleToFit(100, 100); // Adjust width and height as needed
                image.setAlignment(Element.ALIGN_TOP | Element.ALIGN_RIGHT);
                document.add(image);

                // Add content to the PDF
                // Rapport Psy title with big font and centered alignment
                Paragraph rapportPsyTitle = new Paragraph("Rapport Psy", FontFactory.getFont(FontFactory.HELVETICA, 24, Font.BOLD));
                rapportPsyTitle.setAlignment(Element.ALIGN_CENTER);
                document.add(rapportPsyTitle);

                // Add empty line for spacing
                for (int i = 0; i < 8; i++) {
                document.add(new Paragraph("\n"));}
                document.add(new Paragraph("Date: " + rapportPsy.getDateRappPs(), FontFactory.getFont(FontFactory.HELVETICA, 14)));

                // Rest of the elements with medium font and left alignment
                document.add(new Paragraph("psychologue: " + rapportPsy.getPsychiatrist().getUsername(), FontFactory.getFont(FontFactory.HELVETICA, 14)));
                document.add(new Paragraph("client: " + rapportPsy.getClients().getUsername(), FontFactory.getFont(FontFactory.HELVETICA, 14)));
                for (int i = 0; i < 2; i++) {
                    document.add(new Paragraph("\n"));}
                document.add(new Paragraph("" + rapportPsy.getDescription(), FontFactory.getFont(FontFactory.HELVETICA, 14)));
                for (int i = 0; i < 2; i++) {
                    document.add(new Paragraph("\n"));}

                document.add(new Paragraph("most frequent emotions : " + extractFeelingsFromRapport(rapportPsyId), FontFactory.getFont(FontFactory.HELVETICA, 14)));

                Image signatureImage = Image.getInstance("D:\\downloads\\im.png");
                signatureImage.scaleToFit(100, 100); // Adjust width and height as needed

// Calculate the position of the bottom right corner
                float x = document.right() - signatureImage.getScaledWidth();
                float y = document.bottom();

                signatureImage.setAbsolutePosition(x, y);
                document.add(signatureImage);

                document.close();
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("RapportPsy not found with id: " + rapportPsyId);
        }
    }



    @Override
    public List<Consultation> con(LocalDate date, LocalTime startTime, Long psychologueid) {
        return null;
    }

     public Map<LocalDate, Integer> consultationsPerDayInMonth(Long psychologueId, YearMonth yearMonth) {
        User psychologue = userRepository.findById(psychologueId).orElse(null);

        if (psychologue == null) {
            return new HashMap<>(); // Handle the case where the Psychologue is not found
        }

        List<Consultation> consultations = psychologue.getConsultationsAsPsychiatrist();

        Map<LocalDate, Integer> consultationsPerDay = consultations.stream()
                .filter(consultation -> {
                    LocalDate consultationDate = consultation.getConsultationdate();
                    return consultationDate.getMonth() == yearMonth.getMonth() && consultationDate.getYear() == yearMonth.getYear();
                })
                .collect(Collectors.groupingBy(Consultation::getConsultationdate, Collectors.collectingAndThen(Collectors.toList(), List::size)));

        // Fill in days with zero consultations
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
        while (!firstDayOfMonth.isAfter(lastDayOfMonth)) {
            consultationsPerDay.putIfAbsent(firstDayOfMonth, 0);
            firstDayOfMonth = firstDayOfMonth.plusDays(1);
        }

        return consultationsPerDay;
    }


    //// consultation verif
    public Integer isConsultationSlotAvailable(LocalDate date, LocalTime debut, Long psychologueid) {
        // Calculate the end time by adding one hour to the start time

        LocalTime endTime = debut.plusHours(1);

        // Retrieve existing consultations for the selected date and psychologist
        List<Consultation> consultations = cr.findByConsultationdateAndUserId(date,psychologueid);
        List<Consultation> con = new ArrayList<>();
        // Check if the new consultation overlaps with any existing consultations
        for (Consultation consultation : consultations) {
            LocalTime existingStartTime = consultation.getStartTime().plusHours(1);
            LocalTime existingEndTime = existingStartTime;

            if ((existingStartTime.isBefore(endTime) && existingEndTime.isAfter(debut)) ||
                    (existingStartTime.equals(debut) && existingEndTime.isAfter(debut)) ||
                    (existingStartTime.isBefore(endTime) && existingEndTime.equals(endTime)))  {
                con.add(consultation);
            }


        }
        return con.size();}
    public List<LocalTime> findAvailableTimeSlots(LocalDate date, Long psychologueid) {
        List<Consultation> consultations = cr.findByConsultationdateAndUserId(date, psychologueid);
        List<LocalTime> availableSlots = new ArrayList<>();

        // Define the working hours of the psychiatrist
        LocalTime startOfWork = LocalTime.of(9, 0); // Start time (e.g., 9:00 AM)
        LocalTime endOfWork = LocalTime.of(17, 0); // End time (e.g., 5:00 PM)

        // Initialize the current time to the start of the working hours
        LocalTime currentTime = startOfWork;

        // Iterate through the working hours in one-hour increments
        while (currentTime.isBefore(endOfWork)) {
            // Check if the current time slot overlaps with any existing consultations
            boolean isAvailable = true;
            for (Consultation consultation : consultations) {
                LocalTime existingStartTime = consultation.getStartTime();
                LocalTime existingEndTime = existingStartTime.plusHours(1);

                if (currentTime.isBefore(existingEndTime) && existingStartTime.isBefore(currentTime.plusHours(1))) {
                    isAvailable = false;
                    break;
                }
            }

            // If the current time slot is available, add it to the list of available slots
            if (isAvailable) {
                availableSlots.add(currentTime);
            }

            // Move to the next time slot
            currentTime = currentTime.plusHours(1);
        }

        return availableSlots;
    }

        // Slot does not overlap with any existing consultation for the psychologist

  //  }
    /////

    //////





    @Override
    public List<Consultation> getConsultationsByUserId(Long userId) {
        return cr.findByClientId(userId);
    }
    public List<Consultation> getConsultationsBypsyId(Long userId) {
        return cr.findByPsychiatristId(userId);
    }
    public List<RapportPsy> getRapportPsyByPsychiatristId(Long psychiatristId) {
        return rapportPsyRepo.findByPsychiatristId(psychiatristId);
    }
    public List<Feedback> getFeedbackByTherapistId(Long therapistId) {
        return fp.findByTherapistId(therapistId);
    }

    public void saveFeedback(Feedback feedback) {
        // Check if the user has already rated the therapist
        if (fp.existsByTherapistIdAndUserId(feedback.getTherapist().getId(), feedback.getUser().getId())) {
            throw new IllegalStateException("User has already rated this therapist.");
        }

        // Save the feedback
        Feedback savedFeedback = fp.save(feedback);

        // Calculate average rating for the therapist
        User therapist = feedback.getTherapist();
        List<Feedback> therapistFeedbacks = fp.findByTherapistId(therapist.getId());
        int totalRating = therapistFeedbacks.stream().mapToInt(Feedback::getRating).sum();
        double averageRating = totalRating / (double) therapistFeedbacks.size();
        therapist.setRating(averageRating);
        // Update therapist rating in the database
        // TherapistService.updateTherapist(therapist); // You need to implement updateTherapist method in TherapistService

    }
    public double calculateAverageRating(Long therapistId) {
        List<Feedback> therapistFeedbacks = fp.findByTherapistId(therapistId);
        int totalRating = therapistFeedbacks.stream().mapToInt(Feedback::getRating).sum();
        int feedbackCount = therapistFeedbacks.size();
        if (feedbackCount == 0) {
            return 0; // Avoid division by zero
        }
        return (double) totalRating / feedbackCount;
    }
    public List<String> extractFeelingsFromRapport(Long rapportPsyId) {
        List<String> feelings = new ArrayList<>();

        // Define a list of feeling-related words
        List<String> feelingWords = Arrays.asList("sad", "happy", "depressed", "joyful", "anxious", "content", "angry");

        // Retrieve the RapportPsy entity from the database
        Optional<RapportPsy> rapportOptional = rapportPsyRepo.findById(rapportPsyId);

        if (rapportOptional.isPresent()) {
            RapportPsy rapportPsy = rapportOptional.get();

            // Extract feelings from the description
            String description = rapportPsy.getDescription();
            String[] words = description.toLowerCase().split("\\s+");

            // Check if each word is a feeling word
            for (String word : words) {
                if (feelingWords.contains(word)) {
                    feelings.add(word);
                }
            }
        }

        return feelings;
    }
    public Map<String, Long> getConsultationCountPerClientForPsychiatrist(Long psychiatristId) {
        User psychiatrist = userRepository.findById(psychiatristId).orElse(null);
        if (psychiatrist == null) {
            // Handle the case where psychiatrist with given ID is not found
            return null;
        }

        List<Consultation> consultations = cr.findByPsychiatrist(psychiatrist);
        Map<String, Long> consultationCountPerClient = new HashMap<>();

        for (Consultation consultation : consultations) {
            String clientUsername = consultation.getClient().getUsername();
            consultationCountPerClient.put(clientUsername, consultationCountPerClient.getOrDefault(clientUsername, 0L) + 1);
        }

        return consultationCountPerClient;
    }

    public Map<String, Long> getTotalConsultationsPerPsychiatrist() {
        List<Consultation> consultations = cr.findAll();
        Map<String, Long> totalConsultationsPerPsychiatrist = new HashMap<>();

        for (Consultation consultation : consultations) {
            String psychiatristUsername = consultation.getPsychiatrist().getUsername();
            totalConsultationsPerPsychiatrist.put(psychiatristUsername, totalConsultationsPerPsychiatrist.getOrDefault(psychiatristUsername, 0L) + 1);
        }

        return totalConsultationsPerPsychiatrist;
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000) // Execute every 24 hours
    public void countConsultationsAndPrint() {
        long consultationCount = countConsultationsWithinLast24Hours();
        System.out.println("Number of consultations in the last 24 hours: " + consultationCount);
    }

    public long countConsultationsWithinLast24Hours() {
        LocalDate twentyFourHoursAgo = LocalDate.now().minusDays(1);
        return cr.countByConsultationdateAfter(twentyFourHoursAgo);
    }
//    public List<Consultation>getshit(Long id){
//        List<Consultation> consultations = cr.findByPsychiatristId(id);
//        return  consultations;
//    }

//    public static Map<LocalDate, Integer> calculateBusiestConsultationDays(User psychiatrist) {
//        Map<LocalDate, Integer> consultationCountByDay = new HashMap<>();
//        List<Consultation> consultations = psychiatrist.getConsultationsAsPsychiatrist();
//
//        for (Consultation consultation : consultations) {
//            LocalDate consultationDate = consultation.getConsultationdate();
//            consultationCountByDay.put(consultationDate, consultationCountByDay.getOrDefault(consultationDate, 0) + 1);
//        }
//
//        // Sort the map by consultation count in descending order
//        Map<LocalDate, Integer> busiestDays = consultationCountByDay.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//
//        return busiestDays;
//    }

//        public List<String> recommendPsychologists(String genderPreference, char specializeDepression,
//                                                   char specializeRelationship, char specializeAnxiety) {
//            // Example list of psychologists (replace with your actual data)
//            List<Psychologue> psychologists = new ArrayList<>();
//
//            List<String> recommendedPsychologists = new ArrayList<>();
//
//            // Iterate over each psychologist
//            for (Psychologue psychologist : psychologists) {
//                // Check gender preference
//                if (!"none".equals(genderPreference) && !psychologist.getGender().equalsIgnoreCase(genderPreference)){
//                    // If the user has specified a gender preference and the psychologist's gender does not match,
//                    // skip to the next psychologist
//                    continue;
//                }
//
//                // Check specialties based on user's preferences
//                if (specializeDepression == 'Y' && !psychologist.getSpecialty().equals("Depression")) {
//                    // If the user is interested in depression specialty and the psychologist doesn't specialize in it,
//                    // skip to the next psychologist
//                    continue;
//                }
//
//                if (specializeRelationship == 'Y' && !psychologist.getSpecialty().equals("Relationship")) {
//                    // If the user is interested in relationship issues specialty and the psychologist doesn't specialize in it,
//                    // skip to the next psychologist
//                    continue;
//                }
//
//                if (specializeAnxiety == 'Y' && !psychologist.getSpecialty().equals("Anxiety")) {
//                    // If the user is interested in anxiety specialty and the psychologist doesn't specialize in it,
//                    // skip to the next psychologist
//                    continue;
//                }
//
//                // If all criteria are met, add the psychologist to the list of recommended psychologists
//                recommendedPsychologists.add(psychologist.getNom());
//            }
//
//            // Return the list of recommended psychologists
//            return recommendedPsychologists;
//        }
    }





//    public long countConsultationsPerDay(Long psychologueId) {
//        Psychologue psychologue = pr.findById(psychologueId)
//                .orElseThrow(() -> new EntityNotFoundException("Psychologue not found with id: " + psychologueId));
//
//        List<Consultation> consultations = psychologue.getConsultations();
//
//        LocalDate systemDate = LocalDate.now();
//
//        long result = consultations.stream()
//                .filter(consultation -> {
//                    Date consultationDate = consultation.getConsultationdate();
//                    if (consultationDate != null) {
//                        LocalDate dateFromConsultation = consultationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                        return dateFromConsultation.isEqual(systemDate);
//                    }
//                    return false;
//                })
//                .count();
//
//        System.out.println("Total consultations per day: " + result);
//
//        return result;
//



//    public long countConsultationsPerWeek(Long psychologueId, LocalDate date) {
//        Psychologue psychologue = pr.findById(psychologueId)
//                .orElseThrow(() -> new EntityNotFoundException("Psychologue not found with id: " + psychologueId));
//
//        long result = psychologue.countConsultationsPerWeek(date);
//        System.out.println("Consultations per week: " + result);
//
//        return result;
//    }
//
//    public long countConsultationsPerMonth(Long psychologueId, LocalDate date) {
//        Psychologue psychologue = pr.findById(psychologueId)
//                .orElseThrow(() -> new EntityNotFoundException("Psychologue not found with id: " + psychologueId));
//
//        long result = psychologue.countConsultationsPerMonth(date);
//        System.out.println("Consultations per month: " + result);
//
//        return result;
//    }



