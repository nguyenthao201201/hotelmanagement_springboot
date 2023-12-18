package hotelmanagement.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookings")
public class Booking extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "check_in")
    private Date checkIn;

    @Column(name = "check_out")
    private Date checkOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String note;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    private String status;

    @Column(name = "total_money")
    private BigDecimal totalMoney;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    private boolean active;
}
