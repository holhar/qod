package de.holhar.qod.api.qod.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QodResponse {
  private Success success;
  private Contents contents;
  private Copyright copyright;
}
