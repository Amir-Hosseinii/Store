package Model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString



public class Delivery {
    private int ID;
    private Invoice invoice;
    private String address;
    private LocalDateTime sendDateTime;
    private LocalDateTime deliveredDateTime;
    private DeliverStatus deliverStatus;
    private String receiver;
}
