package tn.esprit.vitanova.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.Community;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.repository.CommunityRepository;
import tn.esprit.vitanova.repository.UserRepo;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService implements ICommunityService {

    private final CommunityRepository communityRepository;

    private final UserRepo userRepository; // Supposons que vous avez un repository pour les utilisateurs

    @Override
    public Community addCommunity(Community community) {
        return communityRepository.save(community);
    }

    @Override
    public Community getCommunityById(Long id) {
        return communityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    @Override
    public void updateCommunity(Community community) {
        communityRepository.save(community);
    }

    @Override
    public void deleteCommunity(Long id) {
        communityRepository.deleteById(id);
    }

    @Override
    public Community addUserToCommunity(Long communityId, Long userId) {
        Community community = communityRepository.findById(communityId).orElseThrow(NullPointerException::new);
        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        user.setCommunity(community);
        userRepository.save(user);
        community.getUsers().add(user);
        return communityRepository.save(community);

    }

    @Override
    public List<Community> findCommunity(String query) {
        return communityRepository.findByNameContainsIgnoreCase(query);
    }
}
