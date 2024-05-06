package tn.esprit.vitanova.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.Community;
import tn.esprit.vitanova.entities.Posts;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.repository.CommunityRepository;
import tn.esprit.vitanova.repository.PostsRepository;
import tn.esprit.vitanova.repository.UserRepo;
import tn.esprit.vitanova.security.services.UserDetailsImpl;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService implements IPostsService {

    private final CommunityRepository communityRepository;
    private final PostsRepository postsRepository;
    private final UserRepo userRepo;

    @Override
    public Posts addNewPost(Posts post, Long communityId) {
        UserDetailsImpl connectedPrincipal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.findByUsername(connectedPrincipal.getUsername()).orElseThrow(NullPointerException::new);

        Community community = communityRepository.findById(communityId).orElseThrow(NullPointerException::new);
        post.setCommunity(community);
        post.setIdOwner(user.getId());
        return postsRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postsRepository.deleteById(postId);
    }

    @Override
    public Posts updatePost(Posts postRequest, Long postId) {
        Posts currentPost = postsRepository.findById(postId).orElseThrow(NullPointerException::new);
        currentPost.setPost(postRequest.getPost());
        currentPost.setDescription(postRequest.getPost());
        currentPost.setImageP(postRequest.getPost());
        return postsRepository.save(currentPost);
    }

    @Override
    public List<Posts> getCommunityPosts(Long communityId) {
        return postsRepository.findByCommunity_IdCommunityOrderByCreatedDateDesc(communityId);
    }

    @Override
    public List<Posts> getUserPosts(Long userId) {
        return postsRepository.findByIdOwner(userId);
    }

    @Override
    public Posts getPostDetail(long communityId, long postId) {
        return postsRepository.findByIdPostsAndCommunity_IdCommunity(postId, communityId).orElseThrow(NullPointerException::new);
    }
}
