package tn.esprit.vitanova.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.vitanova.entities.Posts;
import tn.esprit.vitanova.services.CloudinaryService;
import tn.esprit.vitanova.services.IPostsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

    private final IPostsService postsService;
    private final CloudinaryService cloudinaryService;

    @PostMapping("/add")
    @Operation(summary = "Create new post")
    public Posts addNewPost(@RequestBody Posts post, @RequestParam Long communityId) {
        return postsService.addNewPost(post, communityId);
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "delete post")
    public void deletePost(@PathVariable Long postId) {
        postsService.deletePost(postId);
    }

    @PutMapping("/{postId}")
    @Operation(summary = "update post")
    public Posts updatePost(@RequestBody Posts postRequest, @PathVariable Long postId) {
        return postsService.updatePost(postRequest, postId);
    }

    @GetMapping("/community/{communityId}")
    @Operation(summary = "Get posts by community id")
    public List<Posts> getCommunityPosts(@PathVariable Long communityId) {
        return postsService.getCommunityPosts(communityId);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get posts by owner id")
    public List<Posts> getUserPosts(@PathVariable Long userId) {
        return postsService.getUserPosts(userId);
    }

    @GetMapping("/{postId}/community/{communityId}")
    @Operation(summary = "Get post details")
    public Posts getPostDetail(@PathVariable long communityId, @PathVariable long postId) {
        return postsService.getPostDetail(communityId, postId);
    }

    @PostMapping(value = "/media", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload post media")
    public Map<String,String> uploadImage(@RequestPart("file") MultipartFile file) throws Exception {
        Map<String,String> result = new HashMap<>();
        result.put("url", cloudinaryService.uploadFile(file));
        return result;
    }
}
