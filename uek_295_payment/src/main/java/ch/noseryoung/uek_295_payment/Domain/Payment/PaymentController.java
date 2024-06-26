package ch.noseryoung.uek_295_payment.Domain.Payment;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.NoSuchElementException;
import javax.management.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import org.springframework.web.bind.MethodArgumentNotValidException;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Operation (description = "this methode gets a list of all payments that are stored in the db, this requires READ authority" , summary = "gets a list of all payments")
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/")
    public ResponseEntity<Object> getpayment() {
        return ResponseEntity.ok().body(paymentService.getpayment());
    }

    @Operation (description = "this methode gets a payment by its id that is stored in the db, this requires READ authority" , summary = "gets a payment by its id")
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getpaymentById(@PathVariable("paymentId") Integer paymentId)
            throws InstanceNotFoundException {
        return ResponseEntity.ok().body(paymentService.getpaymentById(paymentId));
    }

    @Operation(description = "this methode adds a payment that will be stored in the db, this requires CREATE authority" , summary = "adds a payment")
    @PreAuthorize("hasAuthority('CREATE')")
    @PostMapping("/")
    public ResponseEntity<Payment> addpayment(@Valid @RequestBody Payment payment)
            throws AccessDeniedException {
        return ResponseEntity.ok().body(paymentService.addpayment(payment));
    }

    @Operation( description = "this methode deletes a payment that is stored in the db, this requires DELETE authority" , summary = "deletes a payment")
    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{paymentId}")
    public String deletepayment(@PathVariable("paymentId") int index) {
        paymentService.deletepayment(index);
        return "You Deleted " + index;
    }

    @Operation(description = "this methode updates a payment that is stored in the db, this requires UPDATE authority" , summary = "updates a payment")
    @PreAuthorize("hasAuthority('UPDATE')")
    @PutMapping("/{paymentId}")
    public ResponseEntity<Payment> updatepayment(@PathVariable("paymentId") int paymentId,
            @Valid @RequestBody Payment payment)
            throws AccessDeniedException, InstanceNotFoundException{
        return ResponseEntity.ok().body(paymentService.updatepayment(paymentId, payment));
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException element) {
        return ResponseEntity.status(404).body("" + element.getMessage() + " ");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException element) {
        return ResponseEntity.badRequest().body(element.getBindingResult().getFieldError().getField() + " is invalid: "
                + element.getBindingResult().getFieldError().getDefaultMessage());

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException element) {
        return ResponseEntity.status(400).body("" + element.getMessage() + " ");
    }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found: " + ex.getMessage());
        }


        @ControllerAdvice
        public class GlobalExceptionHandler {
            
            @ExceptionHandler(Exception.class)
            public ResponseEntity<String> handleException(Exception ex) {
                return ResponseEntity.status(404).body("" + ex.getMessage() + " ¯\\ __(ツ)__/¯ ");
            }
        }

}