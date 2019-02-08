package w.fujiko.service.repo.transactions.sales;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


import fte.api.universal.UniversalServiceRepo;

import w.fujiko.dao.transactions.sales.SalesHeaderDao;
import w.fujiko.model.transactions.sales.SalesHeader;
import w.fujiko.service.transactions.sales.SalesHeaderService;

@Service
@Transactional
public class SalesHeaderServiceRepo 
	extends UniversalServiceRepo<SalesHeader, SalesHeaderDao, Integer> 
	implements SalesHeaderService {

	@Override
	public Optional<SalesHeader> getBySlipNumber(String slipNumber) {
		return dao.getBySlipNumber(slipNumber);
	}

	// @Override
	// public Page<SalesHeader> salesHeaderSearch(SalesHeaderSearchDTO searchKeys) {
	// 	return dao.purchaseOrderHeaderSearch(searchKeys);
	// }

}