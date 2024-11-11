package com.example.admin.services;

import com.example.admin.entities.Forum;

import java.util.List;

public interface IForumService {
    public Forum addForum(Forum f);
    public Forum getForumById(Long id);
    public List<Forum> getAllForum();
    public Forum updateForum(Forum f);
    public void deleteForum(Long id);
}
