package br.com.api.weplant.services;

import br.com.api.weplant.dto.CommentDTO;
import br.com.api.weplant.entities.Comment;
import br.com.api.weplant.entities.Post;
import br.com.api.weplant.repositories.CommentRepository;
import br.com.api.weplant.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Page<Post> findAllByUserId(Long id, Pageable pageable) {
        return postRepository.findAllByUserId(id, pageable);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).get();
    }

    public Page<CommentDTO> findAllCommentsByPostId(Long id, Pageable pageable) {
        return commentRepository.findAllByPostId(id, pageable).map(CommentDTO::new);
    }
}
