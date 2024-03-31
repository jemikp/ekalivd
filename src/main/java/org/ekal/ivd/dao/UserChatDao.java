package org.ekal.ivd.dao;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dto.UserChatDTO;
import org.ekal.ivd.entity.UserChat;
import org.ekal.ivd.repository.UserChatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserChatDao {
    private final static Logger logger = LoggerFactory.getLogger(UserChatDao.class);

    @Autowired
    UserChatRepository userChatRepository;

    public void createUserChat(@Valid UserChatDTO userChatDTO) {
        UserChat userChat = new UserChat(userChatDTO);
        userChatRepository.save(userChat);
        userChatDTO.setId(userChat.getId());
    }

    public List<UserChatDTO> getUserChat(Integer projectId,Integer departmentId,Integer taskId){
        List<UserChatDTO> userChatList = new ArrayList<>();
        List<UserChat> userChat = null;
        if(null != taskId && taskId > 0){
            userChat = userChatRepository.getByTaskId(taskId);
        }else if(null != departmentId && departmentId > 0 && null != projectId && projectId > 0){
            userChat = userChatRepository.getByProjectIdAndDepartmentId(projectId,departmentId);
        }else if(null != projectId && projectId > 0 && null == departmentId && null == taskId){
            userChat = userChatRepository.getByProjectId(projectId);
        }else{
            userChat = userChatRepository.findAll();
        }
        userChatList = userChat.stream().map(u -> new UserChatDTO(u)).collect(Collectors.toList());
        return userChatList;
    }

}