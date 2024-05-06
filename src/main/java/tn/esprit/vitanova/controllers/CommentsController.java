package tn.esprit.vitanova.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vitanova.entities.Comments;
import tn.esprit.vitanova.models.PagedResponse;
import tn.esprit.vitanova.services.ICommentsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentsController {

    private final ICommentsService commentsService;

    @PostMapping("/post/{postId}")
    @Operation(summary = "add new comment")
    public Comments addNewComment(@RequestBody Comments newComment, @PathVariable Long postId) {
        return commentsService.addNewComment(newComment, postId);
    }

    @DeleteMapping("/{idComment}")
    @Operation(summary = "delete comment")
    public void deleteComment(@PathVariable Long idComment) {
        commentsService.deleteComment(idComment);
    }

    @PutMapping("/{idComment}")
    @Operation(summary = "Update comment")
    public Comments updateComment(@RequestBody Comments commentRequest, @PathVariable Long idComment) {
        return commentsService.updateComment(commentRequest, idComment);
    }

    @GetMapping("/post/{postId}")
    @Operation(summary = "get comments by page")
    public PagedResponse<Comments> getAllCommentsByPage(@RequestParam int pageNumber, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "default") String sortBy, @PathVariable Long postId) {
        return commentsService.getAllCommentsByPage(pageNumber, pageSize, postId, sortBy);
    }
}
