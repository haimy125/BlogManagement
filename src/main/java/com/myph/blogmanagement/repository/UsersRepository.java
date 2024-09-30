package com.myph.blogmanagement.repository;

import com.myph.blogmanagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    Users findByAccounts_AccountId(String accountId);
}
