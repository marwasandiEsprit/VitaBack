package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.Posts;

import java.util.List;

public interface IPostsService {

    Posts addNewPost(Posts post, Long communityId);

    void deletePost(Long postId);

    Posts updatePost(Posts postRequest, Long postId);

    List<Posts> getCommunityPosts(Long communityId);

    List<Posts> getUserPosts(Long userId);

    Posts getPostDetail(long communityId, long postId);
}
