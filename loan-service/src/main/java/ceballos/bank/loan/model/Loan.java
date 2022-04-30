package ceballos.bank.loan.model;

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
public class Loan {

    @Id
    @GeneratedValue
    @Column(name = "loan_id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "loan_number", unique = true)
    private Long loanNumber;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private Integer totalLoan;

    @Column(name = "amount_paid")
    private Integer amountPaid;

    @Column(name = "outstanding_amount")
    private Integer outstandingAmount;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "start_date")
    private Date startDate;

}
