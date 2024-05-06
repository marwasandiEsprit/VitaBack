package tn.esprit.vitanova.controllers;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vitanova.entities.ChatMessage;
import tn.esprit.vitanova.entities.ChatRoom;
import tn.esprit.vitanova.services.ChatRoomService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {
ChatRoomService chatRoomService ;
    @PostMapping("/addChatRoom")
    public ChatRoom ajouterChatRoom(@RequestBody ChatRoom ChatRoom) {
        return chatRoomService.ajouterChatRoom(ChatRoom);
    }
    @PostMapping("/addMessage")
    public ChatMessage ajouterChatRoom(@RequestBody ChatMessage chatMessage) {
        return chatRoomService.saveChatMessage(chatMessage);
    }
    @GetMapping("/getChatRoomByClientId/{id}")
    public List<ChatRoom> getChatRoomByClientId(@PathVariable Long id) {
        return chatRoomService.getChatRoomByClientId(id);
    }
    @GetMapping("/getChatRoomByNutionisteId/{id}")
    public List<ChatRoom> getChatRoomByNutionisteId(@PathVariable Long id) {
        return chatRoomService.getChatRoomByNutionisteId(id);
    }
    @GetMapping("/getMessages/{id}")
    public List<ChatMessage> getMessages(@PathVariable Long id) {
        return chatRoomService.getMessages(id);
    }
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        // Enregistrer le message dans la base de données
        chatRoomService.saveChatMessage(chatMessage);
//    you
        return chatMessage;
    }

}
