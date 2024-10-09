package com.myph.blogmanagement.service.Impl;

import com.myph.blogmanagement.model.Users;
import com.myph.blogmanagement.payload.response.ContactsResponseDTO;
import com.myph.blogmanagement.payload.response.UserResponseDTO;
import com.myph.blogmanagement.repository.UsersRepository;
import com.myph.blogmanagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * @return
     */
    @Override
    public UserResponseDTO getUser(String id) {
        Optional<Users> users = usersRepository.findById(id);
        if (users.isPresent()) {
            Users user = users.get();
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            BeanUtils.copyProperties(user, userResponseDTO);
            userResponseDTO.setUsername(user.getAccounts().getUsername());

            Set<ContactsResponseDTO> contactsResponseDTOSet = user.getContacts().stream()
                    .map(contacts -> {
                        ContactsResponseDTO contactsResponseDTO = new ContactsResponseDTO();
                        BeanUtils.copyProperties(contacts, contactsResponseDTO);
                        return contactsResponseDTO;
                    })
                    .collect(Collectors.toSet());

            userResponseDTO.setContactsResponseDTOSet(contactsResponseDTOSet);
            return userResponseDTO;
        }
        return null;
    }
}
