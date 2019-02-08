package w.fujiko.dao.payments;

import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.payments.Payment;

public interface PaymentDao extends UniversalCrud<Payment, Integer> {
	public Optional<Payment> getUndeletedPaymentByCode(Integer code);
}
