package ceballos.bank.card.repository;

import ceballos.bank.card.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    List<Card> findAllByCustomerId(Long customerId);

}
