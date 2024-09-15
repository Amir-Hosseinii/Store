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

public class InvoiceItem {
    private int invoiceItemID;
    private int count;
    private Person person1;
    private Product product2;
}
