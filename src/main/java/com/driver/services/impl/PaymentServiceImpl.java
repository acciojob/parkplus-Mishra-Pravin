package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
        // Fetch the reservation
        Reservation reservation = reservationRepository.findById(reservationId);
        if (reservation == null) {
            throw new Exception("Reservation not found");
        }

        // Check if the amount sent is less than the bill
        if (amountSent < reservation.getBill()) {
            throw new Exception("Insufficient Amount");
        }

        // Validate the payment mode
        if (!mode.equalsIgnoreCase("cash") && !mode.equalsIgnoreCase("card") && !mode.equalsIgnoreCase("upi")) {
            throw new Exception("Payment mode not detected");
        }

        // Create a new payment
        Payment payment = new Payment();
        payment.setReservation(reservation);
        payment.setAmountSent(amountSent);
        payment.setMode(mode);

        // Save the payment
        paymentRepository.save(payment);

        return payment;
    }
}