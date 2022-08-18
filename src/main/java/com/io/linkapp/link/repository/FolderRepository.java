package com.io.linkapp.link.repository;

import com.io.linkapp.link.domain.Folder;
import com.io.linkapp.user.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FolderRepository extends JpaRepository<Folder, UUID>{

    @EntityGraph(attributePaths = "articles")
    @Query("SELECT DISTINCT folder FROM Folder folder where folder.user =:user")
    List<Folder> findByUser(User user);
}
