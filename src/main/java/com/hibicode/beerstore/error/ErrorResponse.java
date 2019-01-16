package com.hibicode.beerstore.error;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

/**
 * Bean para retornar erros
 * @author s2it_rboni
 * @version $Revision: $<br/>
 * $Id: $
 * @since 21/11/18 15:37
 */
// anotação do jackson para definir que deve considerar propriedades com qualquer visibilidade - neste caso as privadas
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
// lombok c/ modificador de escopo do construtor
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
  private final int statusCode;
  private final List<ApiError> errors;

  /**
   * Builder para retornar erro a partir da lista de erros + http status
   * @param status
   * @param errors
   * @return
   */
  static ErrorResponse of(HttpStatus status, List<ApiError> errors) {
    return new ErrorResponse(status.value(), errors);
  }

  /**
   * Builder para retornar erro + http status
   * @param status
   * @param error
   * @return
   */
  static ErrorResponse of(HttpStatus status, ApiError error) {
    return new ErrorResponse(status.value(), Collections.singletonList(error));
  }
  
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @RequiredArgsConstructor
  static class ApiError {
    private final String code;
    private final String message;
  }
}