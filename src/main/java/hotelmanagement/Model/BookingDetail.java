package hotelmanagement.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "booking_details")
public class BookingDetail {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @Column(name = "number_of_days")
    private Integer numberOfDays;

    @Column(name = "number_of_guests")
    private Integer numberOfGuests;

    @Column(name = "number_of_services")
    private Integer numberOfServices;

    @Column(name = "price_room")
    private BigDecimal priceRoom;

    @Column(name = "price_service")
    private BigDecimal priceService;

    @Column(name = "total_price_room")
    private BigDecimal totalPriceRoom;

    @Column(name = "total_price_service")
    private BigDecimal totalPriceService;

    @Column(name = "total_money")
    private BigDecimal totalMoney;

}
