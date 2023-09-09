package com.prograo.repository;

import com.prograo.domain.Chat;
import com.prograo.dto.ChatDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query(value = "SELECT c.id AS chatId, c.message AS chatMessage, c.send_date AS chatDate, u.id AS userId, u.profile_photo AS userProfilePhoto " +
            "FROM chat c " +
            "LEFT JOIN user u ON c.user_id = u.id " +
            "WHERE c.project_id = :projectId", nativeQuery = true)
    List<ChatDTO> getAllChatFromProject(@Param("projectId") Long projectId);
}
