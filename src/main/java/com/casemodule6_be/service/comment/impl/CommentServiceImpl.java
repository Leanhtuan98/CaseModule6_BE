package com.casemodule6_be.service.comment.impl;

import com.casemodule6_be.common.constant.Constant;
import com.casemodule6_be.common.enums.EnumSSWException;
import com.casemodule6_be.common.exception.SSWException;
import com.casemodule6_be.common.utils.ObjectMapperUtils;
import com.casemodule6_be.dto.comment.CommentRequest;
import com.casemodule6_be.dto.comment.CommentResponse;
import com.casemodule6_be.model.Account;
import com.casemodule6_be.model.Comment;
import com.casemodule6_be.model.Room;
import com.casemodule6_be.repository.AccountRepository;
import com.casemodule6_be.repository.CommentRepository;
import com.casemodule6_be.repository.RoomRepository;
import com.casemodule6_be.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final RoomRepository roomRepository;


    @Override
    public List<CommentResponse> findCommentByRoomId(Long id) {
        return ObjectMapperUtils.mapAll(commentRepository.getAllCommentByRoomId(id),CommentResponse.class);
    }

    @Override
    public CommentResponse save(CommentRequest commentRequest) {
        Optional<Account> account = accountRepository.findById(commentRequest.getAccountId());
        if(!account.isPresent()){
            throw  new SSWException(EnumSSWException.ACCOUNT_NOT_EXISTED);
        }
        Optional<Room> room = roomRepository.findById(commentRequest.getRoomId());
        if(!room.isPresent()){
            throw  new SSWException(EnumSSWException.ROOM_NOT_EXISTED);
        }
        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .status(Constant.DEFAULT_STATUS)
                .rating(commentRequest.getRating())
                .status(Constant.DEFAULT_STATUS)
                .build();
        comment.setAccount(account.get());
        comment.setRoom(room.get());
        comment = commentRepository.save(comment);
        return ObjectMapperUtils.map(comment,CommentResponse.class);
    }

    @Override
    public CommentResponse update(CommentRequest commentRequest) {
        Optional<Comment> comment = commentRepository.findById(commentRequest.getId());
        if(!comment.isPresent()){
            throw  new SSWException(EnumSSWException.COMMENT_NOT_EXISTED);
        }

//        Optional<Account> account = accountRepository.findById(commentRequest.getAccountId());
//        if(!account.isPresent()){
//            throw  new SSWException(EnumSSWException.ACCOUNT_NOT_EXISTED);
//        }
//        Optional<Room> room = roomRepository.findById(commentRequest.getRoomId());
//        if(!room.isPresent()){
//            throw  new SSWException(EnumSSWException.ROOM_NOT_EXISTED);
//        }
        Comment updateComment = comment.get();
        updateComment.setContent(commentRequest.getContent());
        updateComment = commentRepository.save(updateComment);


        return ObjectMapperUtils.map(updateComment,CommentResponse.class);
    }

    @Override
    public void delete(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(!comment.isPresent()){
            throw  new SSWException(EnumSSWException.COMMENT_NOT_EXISTED);
        }
        Comment deleteComment = comment.get();
        deleteComment.setStatus(Constant.DELETE_STATUS);
        commentRepository.save(deleteComment);

    }
}
