package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.ChatMessage;
import tn.esprit.vitanova.entities.ChatRoom;
import tn.esprit.vitanova.repository.ChatMessageRepository;
import tn.esprit.vitanova.repository.ChatRoomRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class ChatRoomService implements  IChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Override
    public ChatRoom ajouterChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    @Override
    public List<ChatRoom> getChatRoomByNutionisteId(Long nutionisteId) {
        return chatRoomRepository.findByNutritionistId(nutionisteId);
    }

    @Override
    public List<ChatRoom> getChatRoomByClientId(Long clientId) {
        return chatRoomRepository.findByClientId(clientId);
    }

    @Override
    public List<ChatMessage> getMessages(Long idChatRoom) {
        return chatMessageRepository.findBychatRoomId(idChatRoom);
    }

    @Override
    public void supprimerPlat(Long idChatRoom) {
        chatRoomRepository.deleteById(idChatRoom);
    }
    @Override
    public ChatMessage saveChatMessage(ChatMessage chatMessage) {
        // Enregistrer le message dans la base de données
        return chatMessageRepository.save(chatMessage);
    }


    @Override
    @Scheduled(fixedRate = 3000)
    public Map<Long, Long> countClientsPerNutritionist() {
        Map<Long, Long> clientsPerNutritionist = new HashMap<>();

        // Fetch all chat rooms
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();

        // Iterate through each chat room
        for (ChatRoom chatRoom : chatRooms) {
            // Get the nutritionist ID associated with the chat room
            Long nutritionistId = chatRoom.getNutritionist().getId();

            // Increment the count for the nutritionist ID
            clientsPerNutritionist.put(nutritionistId,
                    clientsPerNutritionist.getOrDefault(nutritionistId, 0L) + 1);
        }

        // Print the nutritionist IDs and their associated client counts
        for (Map.Entry<Long, Long> entry : clientsPerNutritionist.entrySet()) {
            // System.out.println("Nutritionist ID: " + entry.getKey() + ", Clients Count: " + entry.getValue());
        }
        return clientsPerNutritionist;
    }
}
