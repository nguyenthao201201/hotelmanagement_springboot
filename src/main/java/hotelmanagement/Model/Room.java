package hotelmanagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity (name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;
    @Column(name = "price")
    private Double price;
    @Column(name = "room_number")
    private String roomNumber;
    @Column(name = "status")
    private String status;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;

}
