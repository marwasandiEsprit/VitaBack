package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.ChatMessage;
import tn.esprit.vitanova.entities.ChatRoom;

import java.util.List;

public interface IChatRoomService {
    public ChatRoom ajouterChatRoom (ChatRoom chatRoom);
    public List<ChatRoom> getChatRoomByNutionisteId(Long nutionisteId);
    public List<ChatRoom> getChatRoomByClientId(Long clientId);
    public List<ChatMessage> getMessages(Long idChatRoom);
    public void supprimerPlat (Long idChatRoom);
    public ChatMessage saveChatMessage(ChatMessage chatMessage);
}
