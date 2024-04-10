package ch.noseryoung.uek_295_payment.Domain.Payment;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import java.util.NoSuchElementException;
import ch.noseryoung.uek_295_payment.Domain.Payment.Payment;

import javax.management.InstanceNotFoundException;
import javax.management.InstanceAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Operation(description = "Receive a list of all payments that are stored in the db. Access to this Endpoint requires READ authority", summary = "get all payments that are stored in the db")
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/")
    public ResponseEntity<Object> getpayment() {
        return ResponseEntity.ok().body(paymentService.getpayment());
    }

    @Operation(description = "Receive a specific payment by id that is stored in the db. Access to this Endpoint requires READ authority", summary = "get payment by a specific id that is stored in the db")
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getpaymentById(@PathVariable("paymentId") Integer paymentId)
            throws InstanceNotFoundException {
        return ResponseEntity.ok().body(paymentService.getpaymentById(paymentId));
    }

    @Operation(description = "add payment that will be stored in the db, requires CREATE Authority", summary = "add payment that will be stored in the db")
    @PreAuthorize("hasAuthority('CREATE')")
    @PostMapping("/")
    public ResponseEntity<Payment> addpayment(@Valid @RequestBody Payment payment)
            throws AccessDeniedException {
        return ResponseEntity.ok().body(paymentService.addpayment(payment));
    }

    @Operation(description = "Delete payment that is stored in the db by its id. Access to this Endpoint requires DELETE authority", summary = "delete payment by id that is stored in the db")
    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{paymentId}")
    public String deletepayment(@PathVariable("paymentId") int index) {
        paymentService.deletepayment(index);
        return "You Deleted " + index;
    }

    @Operation(description = "Update existing payment in the db with new values. Access to this Endpoint requires UPDATE authority", summary = "update existing payment that is stored in the db")
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




}