package Model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString


public class Payment {
    private int paymentID;
    private Timestamp paymentDate;
    private double inventory;
    private Invoice invoice1;
}
