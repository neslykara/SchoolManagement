package com.techproed.schoolmanagementbackendb326.controller.user;

import com.techproed.schoolmanagementbackendb326.payload.request.business.AddLessonProgram;
import com.techproed.schoolmanagementbackendb326.payload.request.user.TeacherRequest;
import com.techproed.schoolmanagementbackendb326.payload.response.business.ResponseMessage;
import com.techproed.schoolmanagementbackendb326.payload.response.user.StudentResponse;
import com.techproed.schoolmanagementbackendb326.payload.response.user.UserResponse;
import com.techproed.schoolmanagementbackendb326.service.user.TeacherService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

  private final TeacherService teacherService;

  @PreAuthorize("hasAnyAuthority('Admin')")
  @PostMapping("/save")
  public ResponseMessage<UserResponse>saveTeacher(
      @RequestBody @Valid TeacherRequest teacherRequest) {
    return teacherService.saveTeacher(teacherRequest);
  }

  @PreAuthorize("hasAnyAuthority('Admin')")
  @PutMapping("/update/{userId}")
  public ResponseMessage<UserResponse>updateTeacher(
          @RequestBody @Valid TeacherRequest teacherRequest,
          @PathVariable long userId){
    return teacherService.updateTeacherById(teacherRequest,userId);
  }

  @PreAuthorize("hasAnyAuthority('Teacher')")
  @GetMapping("/getByAdvisorTeacher")
  public List<StudentResponse>getAllStudentByAdvisorTeacher(
          HttpServletRequest httpServletRequest){
    return teacherService.getAllStudentByAdvisorTeacher(httpServletRequest);
  }

  @PreAuthorize("hasAnyAuthority('Admin','Dean','ViceDean')")
  @PostMapping("/addLessonProgram")
  public ResponseMessage<UserResponse>addLessonProgram(
          @RequestBody @Valid AddLessonProgram lessonProgram){
    return teacherService.addLessonProgram(lessonProgram);
  }


  //TODO NESLIHAN
  //deleteTeacherById
  @PreAuthorize("hasAnyAuthority('Admin','Dean','ViceDean')")
  @DeleteMapping("/deleteTeacherById/{teacherId}")
  public ResponseMessage<UserResponse> deleteTeacherById(@PathVariable Long teacherId){
    return teacherService.deleteTeacherById(teacherId);
  }

}
