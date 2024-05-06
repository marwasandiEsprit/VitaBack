package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.Comments;
import tn.esprit.vitanova.models.PagedResponse;

import java.util.List;

public interface ICommentsService {

    Comments addNewComment(Comments newComment, Long postId);

    void deleteComment(Long idComment);

    Comments updateComment(Comments commentRequest, Long idComment);

    List<Comments> getAllCommentsByPostId(Long postId);

    PagedResponse<Comments> getAllCommentsByPage(int pageNumber, int pageSize, Long postId, String sort);
}
