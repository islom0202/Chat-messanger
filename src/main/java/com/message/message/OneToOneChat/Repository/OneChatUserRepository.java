package com.message.message.OneToOneChat.Repository;

import com.message.message.OneToOneChat.Entity.OneChatUser;
import com.message.message.OneToOneChat.Entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneChatUserRepository extends JpaRepository<OneChatUser,Long> {
    List<OneChatUser> findByStatus(UserStatus userStatus);

    OneChatUser findByNikName(String nikName);
}
