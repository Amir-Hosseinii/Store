package Model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString


public class Invoice {
    private int ID;
    private LocalDateTime invoiceDateTime;
    private double totalAmount;
    private Person person;
    private InvoiceType invoiceType;
    private List<InvoiceItem> invoiceItemList;
    private ShoppingType shoppingType;
}
