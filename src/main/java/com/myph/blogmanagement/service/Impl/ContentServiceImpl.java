package com.myph.blogmanagement.service.Impl;

import com.myph.blogmanagement.model.Contents;
import com.myph.blogmanagement.model.Users;
import com.myph.blogmanagement.payload.request.ContentRequestDTO;
import com.myph.blogmanagement.repository.ContentsRepository;
import com.myph.blogmanagement.repository.UsersRepository;
import com.myph.blogmanagement.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashSet;
import java.util.Optional;

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
}
