package ceballos.bank.account.model;

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
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "account_number", unique = true)
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "create_date")
    private Date createDate;

}
