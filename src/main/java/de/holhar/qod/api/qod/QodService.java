package de.holhar.qod.api.qod;

import de.holhar.qod.api.qod.response.QodQuote;
import de.holhar.qod.api.qod.response.QodResponse;
import de.holhar.qod.api.qod.response.QodServiceException;
import de.holhar.qod.domain.Quote;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class QodService {

  private final String apiUrl;
  private final String apiKey;
  private final RestTemplate restTemplate;

  @Autowired
  public QodService(
      @Value("${qod.api-url}") String apiUrl,
      @Value("${qod.api-key}") String apiKey,
      RestTemplateBuilder builder
  ) {
    this.apiUrl = apiUrl;
    this.apiKey = apiKey;
    this.restTemplate = builder.build();
  }

  public Quote getQod() throws QodServiceException {
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(apiKey);
    HttpEntity<Object> entity = new HttpEntity<>(headers);

    var responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, QodResponse.class);
    if (responseEntity.getBody() == null) {
      throw new QodServiceException("Qod service response is null");
    }

    List<QodQuote> qodQuoteList = responseEntity.getBody().getContents().getQuotes();
    if (qodQuoteList.isEmpty()) {
      throw new QodServiceException("Qod service response does not contain any quotes");
    }

    var quote = qodQuoteList.get(0).getQuote();
    var author = qodQuoteList.get(0).getAuthor();

    return new Quote(author, quote);
  }
}
