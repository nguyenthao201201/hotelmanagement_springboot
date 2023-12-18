package hotelmanagement.Service;

import hotelmanagement.Model.Service;
import java.util.List;
import hotelmanagement.Dto.ServiceDTO;

public interface IServiceService {
    List<Service> getAllServices();
    Service getServiceById(Long serviceId);
    Service createService(ServiceDTO serviceDTO);
    Service updateService(Long serviceId, ServiceDTO serviceDTO);
    void deleteService(Long serviceId);
}
