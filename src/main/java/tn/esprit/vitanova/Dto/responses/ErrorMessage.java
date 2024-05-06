package tn.esprit.vitanova.Dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {
  private int statusCode;
  private Date timestamp;
  private String message;
  private String description;
}