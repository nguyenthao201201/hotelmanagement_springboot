package hotelmanagement.Controller;

import hotelmanagement.Dto.CustomerDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @GetMapping("")//http://localhost:8080/api/v1/customers?page=1&limit=10
    public ResponseEntity<String> getCustomers( //ResponseEntity is a wrapper for the response body and status code
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok(String.format("getCustomers, page = %d, limit = %d", page, limit));
    }
    @GetMapping("{id}")
    public ResponseEntity<String> getCustomerbyId(@PathVariable("id") int customerId) {
        return ResponseEntity.ok("getCustomer Id here" + customerId);
    }
    @PostMapping("")
    public ResponseEntity<String> insertCustomer(@Valid @RequestBody CustomerDTO customerDTO
                                                , BindingResult result){
        if (result.hasErrors()) {
            StringBuilder errormessage = new StringBuilder();
            result.getFieldErrors().forEach(
                    fieldError -> {
                        errormessage.append(fieldError.getDefaultMessage() + "\n");
                    });
            return ResponseEntity.badRequest().body(errormessage.toString());
        }
        //return message full detail of customer

        return ResponseEntity.ok("insertCustomer success");
    }
    @PutMapping("{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") int customerId) {
        return ResponseEntity.ok("updateCustomer here" + customerId);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") int customerId) {
        return ResponseEntity.ok("deleteCustomer here" + customerId);
    }
}
