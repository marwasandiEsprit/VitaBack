package tn.esprit.vitanova.services;

import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.vitanova.entities.ChatMessage;
import tn.esprit.vitanova.entities.ChatRoom;

import java.util.List;
import java.util.Map;

public interface IChatRoomService {
    public ChatRoom ajouterChatRoom (ChatRoom chatRoom);
    public List<ChatRoom> getChatRoomByNutionisteId(Long nutionisteId);
    public List<ChatRoom> getChatRoomByClientId(Long clientId);
    public List<ChatMessage> getMessages(Long idChatRoom);
    public void supprimerPlat (Long idChatRoom);
    public ChatMessage saveChatMessage(ChatMessage chatMessage);

    @Scheduled(fixedRate = 3000)
    Map<Long, Long> countClientsPerNutritionist();
}
