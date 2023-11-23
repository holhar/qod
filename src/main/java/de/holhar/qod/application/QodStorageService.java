package de.holhar.qod.application;

import de.holhar.qod.domain.Qod;
import de.holhar.qod.persistence.Quote;
import de.holhar.qod.persistence.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QodStorageService {

  private final QuoteRepository quoteRepository;

  @Autowired
  public QodStorageService(QuoteRepository quoteRepository) {
    this.quoteRepository = quoteRepository;
  }

  public void saveQod(Qod qod) {
    Quote quote = new Quote();
    quote.setQuote(qod.getQuote());
    quote.setAuthor(qod.getAuthor());
    quote.setDate(qod.getDate());
    quoteRepository.save(quote);
  }
}
