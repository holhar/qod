package de.holhar.qod.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Qod {
  private String author;
  private String quote;
  private LocalDate date;
}

