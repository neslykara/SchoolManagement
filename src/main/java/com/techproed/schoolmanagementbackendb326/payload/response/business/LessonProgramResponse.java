package com.techproed.schoolmanagementbackendb326.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techproed.schoolmanagementbackendb326.entity.concretes.business.EducationTerm;
import com.techproed.schoolmanagementbackendb326.entity.concretes.business.Lesson;
import com.techproed.schoolmanagementbackendb326.entity.enums.Day;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonProgramResponse {

  private Long lessonProgramId;
  private Day day;
  private LocalTime startTime;
  private LocalTime stopTime;
  private Set<Lesson>lessonName;
  private EducationTerm educationTerm;

}
