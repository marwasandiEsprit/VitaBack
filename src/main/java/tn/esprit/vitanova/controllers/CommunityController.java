package tn.esprit.vitanova.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vitanova.entities.Community;
import tn.esprit.vitanova.services.ICommunityService;

import java.util.List;

@RestController
@RequestMapping(value = "/communities", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CommunityController {

    private final ICommunityService communityService;

    @PostMapping
    @Operation(summary = "add new community")
    public Community addCommunity(@RequestBody Community community) {
        return communityService.addCommunity(community);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get community by id")
    public Community getCommunityById(@PathVariable Long id) {
        return communityService.getCommunityById(id);
    }

    @GetMapping
    @Operation(summary = "Get all communities")
    public List<Community> getAllCommunities() {
        return communityService.getAllCommunities();
    }

    @PostMapping("/{communityId}/users/{userId}")
    @Operation(summary = "add user to community")
    public Community addUserToCommunity(@PathVariable Long communityId, @PathVariable Long userId) {
        return communityService.addUserToCommunity(communityId, userId);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update community")
    public ResponseEntity<String> updateCommunity(@PathVariable Long id, @RequestBody Community community) {
        community.setIdCommunity(id); // Assurez-vous que l'ID de la communauté est correct
        communityService.updateCommunity(community);
        return new ResponseEntity<>("Community updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete community")
    public ResponseEntity<String> deleteCommunity(@PathVariable Long id) {
        communityService.deleteCommunity(id);
        return new ResponseEntity<>("Community deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "Search community")
    List<Community> findCommunity(@RequestParam String query) {
        return communityService.findCommunity(query);
    }
}
