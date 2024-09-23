package Model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString


public class Bank {
    private int bankID;
    private String bankName;
    private String accountNumber;
    private double bankAmount;
}
