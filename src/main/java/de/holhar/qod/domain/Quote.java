package de.holhar.qod.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Quote {
  private String author;
  private String quote;
}
