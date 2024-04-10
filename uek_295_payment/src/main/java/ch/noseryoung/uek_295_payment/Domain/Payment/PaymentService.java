
package ch.noseryoung.uek_295_payment.Domain.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public List<Payment> getpayment() {
        return repository.findAll();
    }

    public Payment getpaymentById(int index) {
        return repository.findById(index)
                .orElseThrow(() -> new NoSuchElementException("Payment with id " + index + " does not exist"));
    }

    public Payment addpayment(@Valid Payment payment) {
        payment.setPaymentId(null);
        return repository.save(payment);
    }

    @Transactional
    public void deletepayment(int index) {
        if (!repository.existsById(index)) {
            throw new IllegalArgumentException("This id does not exist " + index);
        } else {
            repository.deleteById(index);
        }
    }

    public Payment updatepayment(int index, Payment payment) {
        Payment existingPayment = repository.findById(index)
                .orElseThrow(() -> new NoSuchElementException("Payment with id " + index + " does not exist"));
        existingPayment.setPaymentMethod(payment.getPaymentMethod());
        existingPayment.setChfExchangeRate(payment.getChfExchangeRate());
        existingPayment.setCurrency(payment.getCurrency());
        return repository.save(existingPayment);
    }
}
