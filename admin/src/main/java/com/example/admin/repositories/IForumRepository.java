package com.example.admin.repositories;

import com.example.admin.entities.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IForumRepository extends JpaRepository<Forum,Long> {
}
