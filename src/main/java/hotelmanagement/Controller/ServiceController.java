package hotelmanagement.Controller;

import hotelmanagement.Dto.ServiceDTO;
import hotelmanagement.Model.Service;
import hotelmanagement.Service.ServiceService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/services")
public class ServiceController {
    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
}
    @GetMapping("")//http://localhost:8080/api/v1/services?page=1&limit=10
    public ResponseEntity<List<Service>> getServices( //ResponseEntity is a wrapper for the response body and status code
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        List<Service> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("{id}")

    public ResponseEntity<Service> getServicebyId(@PathVariable("id") int serviceId) {
        Service service = serviceService.getServiceById((long) serviceId);
        if (service != null) {
            return ResponseEntity.ok(service);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insertService(//@Valid
                                               @RequestBody ServiceDTO serviceDTO,
                                                BindingResult result) {

        if (result.hasErrors()) {
            StringBuilder errormessage = new StringBuilder();
            result.getFieldErrors().forEach(
                    fieldError -> {
                        errormessage.append(fieldError.getDefaultMessage() + "\n");
                    });
            return ResponseEntity.badRequest().body(errormessage.toString());
        }
        Service service = serviceService.convertToEntity(serviceDTO);
        Service savedService = serviceService.saveService(service);
        if (savedService != null) {
            return new ResponseEntity<>("Service saved successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Service saved failed", HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateService(@PathVariable("id")   int serviceId, @RequestBody ServiceDTO serviceDTO) {
        Service service = serviceService.updateService(serviceId, serviceDTO);
        if (service != null) {
            return ResponseEntity.ok("Service updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")

    public ResponseEntity<String> deleteService(@PathVariable("id") int serviceId) {
        serviceService.deleteService((long) serviceId);
        return new ResponseEntity<>("Service deleted successfully", HttpStatus.OK);
    }


}
