package com.techproed.schoolmanagementbackendb326.service.helper;

import com.techproed.schoolmanagementbackendb326.entity.concretes.business.ContactMessage;
import com.techproed.schoolmanagementbackendb326.entity.concretes.user.User;
import com.techproed.schoolmanagementbackendb326.exception.BadRequestException;
import com.techproed.schoolmanagementbackendb326.exception.ResourceNotFoundException;
import com.techproed.schoolmanagementbackendb326.payload.messages.ErrorMessages;
import com.techproed.schoolmanagementbackendb326.repository.businnes.ContactMessageRepository;
import com.techproed.schoolmanagementbackendb326.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MethodHelper {

  private final UserRepository userRepository;
  private final ContactMessageRepository contactMessageRepository;


  public User isUserExist(Long id) {
    return userRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException(
            String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, id)));
  }

  public void checkBuildIn(User user) {
    if(user.getBuildIn()){
      throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
    }
  }

  public User loadByUsername(String username) {
    User user = userRepository.findByUsername(username);
    if(user == null){
      throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, username));
    }
    return user;
  }

  public void checkIsAdvisor(User user) {
    if(!user.getIsAdvisor()){
      throw new BadRequestException(String.format(ErrorMessages.NOT_ADVISOR_TEACHER_MESSAGE, user.getUsername()));
    }
  }



  public ContactMessage checkContactMessageExistById(
              Long id) {
    return contactMessageRepository.findById(id)
                       .orElseThrow(()->new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_MESSAGE, id)));
  }

  public List<ContactMessage> checkContactMessageExistBySubject(
              String subject) {
    List<ContactMessage> contactMessages = contactMessageRepository.findBySubjectContainsIgnoreCase(subject);
    if (contactMessages.isEmpty()) {
      throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_MESSAGE_BY_SUBJECT, subject));
    }
    return contactMessages;
  }

}
