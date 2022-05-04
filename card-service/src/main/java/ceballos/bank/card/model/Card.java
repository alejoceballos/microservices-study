package ceballos.bank.card.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue
    @Column(name = "card_id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "card_number", unique = true)
    private Long cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_limit")
    private Integer totalLimit;

    @Column(name = "amount_used")
    private Integer amountUsed;

    @Column(name = "available_amount")
    private Integer availableAmount;

    @Column(name = "create_date")
    private Date createDate;

}
