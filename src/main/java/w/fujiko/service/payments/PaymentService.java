package w.fujiko.service.payments;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.payments.Payment;

@Service
public interface PaymentService extends UniversalCrud<Payment,Integer> {
	public Optional<Payment> getUndeletedPaymentByCode(Integer code);
}