package hotelmanagement.Service;

import hotelmanagement.Dto.ServiceDTO;
import hotelmanagement.Repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

@Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
    public List<hotelmanagement.Model.Service> getAllServices() {
        return serviceRepository.findAll();
    }
    public hotelmanagement.Model.Service saveService(hotelmanagement.Model.Service service) {
        return serviceRepository.save(service);
    }
    public hotelmanagement.Model.Service getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
    public hotelmanagement.Model.Service updateService(int serviceId, ServiceDTO service) {
        hotelmanagement.Model.Service service1 = serviceRepository.findById((long) serviceId).orElse(null);
        if (service1 != null) {
            service1.setServiceName(service.getServiceName());
            service1.setPrice(service.getPrice());
            service1.setDescription(service.getDescription());
            return serviceRepository.save(service1);
        }
        return null;
    }


    public hotelmanagement.Model.Service convertToEntity(ServiceDTO serviceDTO) {
        hotelmanagement.Model.Service service = new hotelmanagement.Model.Service();
        BeanUtils.copyProperties(serviceDTO, service);
        return service;
    }
//        Room sd = new Room();
//        BeanUtils.copyProperties(roomDTO, room);
//        return room;
//    }
}
