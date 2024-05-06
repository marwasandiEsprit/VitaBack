package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.Community;

import java.util.List;

public interface ICommunityService {

    Community addCommunity(Community community);
    Community getCommunityById(Long id);
    List<Community> getAllCommunities();
    void updateCommunity(Community community);
    void deleteCommunity(Long id);
    Community addUserToCommunity(Long communityId, Long userId);

    List<Community> findCommunity(String query);
}
