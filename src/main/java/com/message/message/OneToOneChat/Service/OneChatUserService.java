package com.message.message.OneToOneChat.Service;

import com.message.message.OneToOneChat.Entity.OneChatUser;
import com.message.message.OneToOneChat.Entity.UserStatus;
import com.message.message.OneToOneChat.Repository.OneChatUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OneChatUserService {

    private final OneChatUserRepository oneChatUserRepository;

    public void saveChatUser(OneChatUser user) {
        user.setStatus(UserStatus.ONLINE);
        oneChatUserRepository.save(user);
    }

    public void disconnectChatUser(OneChatUser user) {
        OneChatUser chatUser = oneChatUserRepository.findByNikName(user.getNikName());
        if (chatUser != null) {
            user.setStatus(UserStatus.OFFLINE);
            oneChatUserRepository.save(user);
        }
    }

    public List<OneChatUser> findConnectedUsers() {
        return oneChatUserRepository.findByStatus(UserStatus.ONLINE);
    }
}
