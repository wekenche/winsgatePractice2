package w.fujiko.service.repo.payments;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.payments.PaymentDao;
import w.fujiko.model.masters.payments.Payment;
import w.fujiko.service.payments.PaymentService;

@Service
@Transactional
public class PaymentServiceRepo 
	extends UniversalServiceRepo<Payment, PaymentDao, Integer> 
	implements PaymentService {

	@Override
	public Optional<Payment> getUndeletedPaymentByCode(Integer code) {
		return dao.getUndeletedPaymentByCode(code);
	}

}
