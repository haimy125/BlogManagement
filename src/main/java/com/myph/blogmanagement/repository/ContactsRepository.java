package com.myph.blogmanagement.repository;

import com.myph.blogmanagement.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, String> {
}
