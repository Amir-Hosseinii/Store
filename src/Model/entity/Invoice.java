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


public class Invoice {
    private int invoiceID;
    private Timestamp invoiceDate;
    private double totalAmount;
    private InvoiceType invoiceType;
    private InvoiceItem invoiceItem1;
}
