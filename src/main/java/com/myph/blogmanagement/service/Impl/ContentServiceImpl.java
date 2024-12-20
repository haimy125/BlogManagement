package com.myph.blogmanagement.service.Impl;

import com.myph.blogmanagement.model.Comments;
import com.myph.blogmanagement.model.Contents;
import com.myph.blogmanagement.model.Users;
import com.myph.blogmanagement.payload.request.ContentRequestDTO;
import com.myph.blogmanagement.payload.response.CommentResponseDTO;
import com.myph.blogmanagement.payload.response.ContentResponseDTO;
import com.myph.blogmanagement.repository.ContentsRepository;
import com.myph.blogmanagement.repository.UsersRepository;
import com.myph.blogmanagement.service.ContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentsRepository contentsRepository;

    @Autowired
    private UsersRepository usersRepository;

    /**
     * @param contentRequestDTO
     * @return
     */
    @Override
    public boolean create(ContentRequestDTO contentRequestDTO) {
        return usersRepository.findById(contentRequestDTO.getUserId())
                .map(user -> {
                    Contents contents = new Contents();
                    contents.setContentId(Base64.getEncoder().encodeToString((contentRequestDTO.getTitle()+ System.currentTimeMillis()).getBytes()));
                    contents.setUsers(user);
                    contents.setTitle(contentRequestDTO.getTitle());
                    contents.setBody(contentRequestDTO.getBody());
                    contents.setDeletedFlag(false);
                    contents.setCommentsSet(new HashSet<>());

                    contentsRepository.save(contents);
                    return true;
                })
                .orElse(false);
    }

    /**
     * @return
     */
    @Override
    public List<ContentResponseDTO> getAllContent(int page, int size) {
        List<ContentResponseDTO> contentResponseDTOList = new ArrayList<>();

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Contents> contentList = contentsRepository.findAll(pageRequest);

        for(Contents content : contentList) {
            Users user = content.getUsers();

            ContentResponseDTO contentResponseDTO = new ContentResponseDTO();
            if (user != null) {
                contentResponseDTO.setUserId(user.getUserId());
                contentResponseDTO.setFullName(user.getFirstName() + " " + user.getLastName());
                contentResponseDTO.setAvatar(user.getAvatar());
            }
            contentResponseDTO.setContentId(content.getContentId());
            contentResponseDTO.setTitle(content.getTitle());
            contentResponseDTO.setBody(content.getBody());
            contentResponseDTO.setCreatedAt(content.getCreatedAt());
            contentResponseDTO.setUpdatedAt(content.getUpdatedAt());

            List<CommentResponseDTO> commentResponseDTOList = new ArrayList<>();

            for(Comments comment : content.getCommentsSet()) {
                CommentResponseDTO commentResponseDTO = new CommentResponseDTO();

                if (comment.getUsers() != null) {
                    commentResponseDTO.setUserId(comment.getUsers().getUserId());
                    commentResponseDTO.setFullName(comment.getUsers().getFirstName() + " " + comment.getUsers().getLastName());
                    commentResponseDTO.setAvatar(comment.getUsers().getAvatar());
                }
                commentResponseDTO.setBody(comment.getBody());
                commentResponseDTO.setImage(comment.getImage());

                commentResponseDTOList.add(commentResponseDTO);
            }
            contentResponseDTO.setCommentResponseDTOList(commentResponseDTOList);
            contentResponseDTOList.add(contentResponseDTO);
        }
        return contentResponseDTOList;
    }

    /**
     * @param id
     */
    @Override
    public ContentResponseDTO getContent(String id) {
        Contents contents = contentsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Content not found with id: " + id));
        return convertToDTO(contents);
    }

    public ContentResponseDTO convertToDTO(Contents contents) {
        ContentResponseDTO contentResponseDTO = new ContentResponseDTO();
        BeanUtils.copyProperties(contents, contentResponseDTO);

        // Copy các thuộc tính khác nếu cần
        contentResponseDTO.setUserId(contents.getUsers().getUserId());
        contentResponseDTO.setFullName(contents.getUsers().getFirstName() + " " + contents.getUsers().getLastName());
        contentResponseDTO.setAvatar(contents.getUsers().getAvatar());

        // Chuyển đổi commentsSet sang commentResponseDTOList
        List<CommentResponseDTO> commentResponseDTOList = contents.getCommentsSet().stream()
                .map(comment -> {
                    CommentResponseDTO dto = new CommentResponseDTO();
                    BeanUtils.copyProperties(comment, dto);
                    return dto;
                })
                .collect(Collectors.toList());

        // Gán danh sách commentResponseDTOList vào contentResponseDTO
        contentResponseDTO.setCommentResponseDTOList(commentResponseDTOList);
        return contentResponseDTO;
    }
}
