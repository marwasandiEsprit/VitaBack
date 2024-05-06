package tn.esprit.vitanova.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.AchievementSlug;
import tn.esprit.vitanova.entities.Comments;
import tn.esprit.vitanova.entities.Posts;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.models.PagedResponse;
import tn.esprit.vitanova.repository.CommentsRepository;
import tn.esprit.vitanova.repository.PostsRepository;
import tn.esprit.vitanova.repository.UserRepo;
import tn.esprit.vitanova.security.services.UserDetailsImpl;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService implements ICommentsService {

    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;
    private final IAchievementService achievementService;
    private final UserRepo userRepo;

    @Override
    public Comments addNewComment(Comments newComment, Long postId) {
        UserDetailsImpl connectedPrincipal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.findByUsername(connectedPrincipal.getUsername()).orElseThrow(NullPointerException::new);
        Posts post = postsRepository.findById(postId).orElseThrow(NullPointerException::new);
        newComment.setPosts(post);
        newComment.setUser(user);
        commentsRepository.save(newComment);
        achievementService.addAchievementToUser(user.getId(), AchievementSlug.ADD_10_COMMENT);
        achievementService.addAchievementToUser(user.getId(), AchievementSlug.ADD_15_COMMENT);

        return newComment;
    }

    @Override
    public void deleteComment(Long idComment) {
        commentsRepository.deleteById(idComment);
    }

    @Override
    public Comments updateComment(Comments commentRequest, Long idComment) {
        Comments currentComment = commentsRepository.findById(idComment).orElseThrow(NullPointerException::new);
        currentComment.setComment(commentRequest.getComment());
        return commentsRepository.save(currentComment);
    }

    @Override
    public List<Comments> getAllCommentsByPostId(Long postId) {
        return commentsRepository.findByPosts_IdPosts(postId);
    }

    @Override
    public PagedResponse<Comments> getAllCommentsByPage(int pageNumber, int pageSize, Long postId, String sort) {
        Sort sorting = Sort.unsorted().ascending();
        if (sort.equals("date")) {
            sorting = Sort.by("createdDate").descending();
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sorting);
        return new PagedResponse<>(commentsRepository.findByPosts_IdPosts(postId, pageable));
    }
}
