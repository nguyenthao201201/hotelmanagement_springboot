package hotelmanagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "services")
public class Service {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "service_id")
    private Long serviceId;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "price")
    private Double price;
    @Column(name = "description")
    private String description;

}
