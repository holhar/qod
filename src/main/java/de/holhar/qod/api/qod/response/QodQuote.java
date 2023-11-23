package de.holhar.qod.api.qod.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QodQuote {
  private String id;
  private String quote;
  private int length;
  private String author;
  private String language;
  private String[] tags;
  private String sfw;
  private String permalink;
  private String title;
  private String category;
  private String background;
  private String date;
}
