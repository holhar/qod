package de.holhar.qod.api.qod.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contents {
  private List<QodQuote> quotes;
}
