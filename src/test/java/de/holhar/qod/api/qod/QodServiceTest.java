package de.holhar.qod.api.qod;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.holhar.qod.api.qod.response.QodServiceException;
import de.holhar.qod.domain.Quote;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

@RestClientTest(QodService.class)
class QodServiceTest {

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private MockRestServiceServer server;

  @Autowired
  private QodService service;

  @Value("classpath:qod-example-response.json")
  private Resource qodExampleResponse;

  @Test
  void returnsQodWhenSuccessful() throws QodServiceException, IOException {
    // given
    String expectedContentAsString = qodExampleResponse.getContentAsString(StandardCharsets.UTF_8);
    server
        .expect(requestTo("http://replace.me/qod"))
        .andRespond(withSuccess(expectedContentAsString, MediaType.APPLICATION_JSON));

    // when
    Quote qod = service.getQod();

    // then
    assertThat(qod)
        .isEqualTo(new Quote("John Doe", "Foo, the bar."));
  }
}
