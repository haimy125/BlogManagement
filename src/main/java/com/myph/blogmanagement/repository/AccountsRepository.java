package com.myph.blogmanagement.repository;

import com.myph.blogmanagement.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, String> {
    Accounts findByUsername(String username);
}
