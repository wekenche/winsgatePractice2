package w.fujiko.service.repo.transactions.quotations.histories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.Page;
import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.quotations.histories.QuotationHeaderHistoryDao;
import w.fujiko.model.transactions.quotations.histories.QuotationHeaderHistory;
import w.fujiko.service.transactions.quotations.histories.QuotationHeaderHistoryService;

@Service
@Transactional
public class QuotationHeaderHistoryServiceRepo 
	extends UniversalServiceRepo<QuotationHeaderHistory, QuotationHeaderHistoryDao, Integer> 
	implements QuotationHeaderHistoryService {

	@Override
	public Long getCountByWorkingSiteId(Integer id) {
		return dao.getCountByWorkingSiteId(id);
	}

	@Override
	public Page<QuotationHeaderHistory> quotationHeaderSearch(Optional<Integer> workingSiteNumberFrom,
			Optional<Integer> workingSiteNumberTo, Optional<Integer> constructionNumberFrom,
			Optional<Integer> constructionNumberTo, Optional<Integer> constructionCategoryFrom,
			Optional<Integer> constructionCategoryTo, Optional<Integer> customerBaseCodeFrom,
			Optional<Integer> customerBaseCodeTo, Optional<Short> userCodeFrom, Optional<Short> userCodeTo,
			Optional<Integer> departmentCodeFrom, Optional<Integer> departmentCodeTo, Optional<Date> quotationDateFrom,
			Optional<Date> quotationDateTo, Optional<Date> deliveryDateFrom, Optional<Date> deliveryDateTo,
			Optional<Date> createdDateFrom, Optional<Date> createdDateTo, Optional<Double> totalAmountFrom,
			Optional<Double> totalAmountTo,Optional<Double> grossMarginRatioFrom,
			Optional<Double> grossMarginRatioTo, Optional<List<Integer>> quotationRank, Optional<List<Byte>> orderStatus,
			Optional<List<Byte>> purchaseStatus, Optional<List<Byte>> salesStatus, Optional<List<Byte>> billingStatus,
			Optional<List<Byte>> paymentStatus,
			int first,
			int max) {
				
		return dao.quotationHeaderSearch(workingSiteNumberFrom,
										 workingSiteNumberTo,
										 constructionNumberFrom,
										 constructionNumberTo,
										 constructionCategoryFrom,
										 constructionCategoryTo,
										 customerBaseCodeFrom,
										 customerBaseCodeTo,
										 userCodeFrom,
										 userCodeTo,
										 departmentCodeFrom,
										 departmentCodeTo,
										 quotationDateFrom,
										 quotationDateTo,
										 deliveryDateFrom,
										 deliveryDateTo,
										 createdDateFrom,
										 createdDateTo,
										 totalAmountFrom,
										 totalAmountTo,
										 grossMarginRatioFrom,
										 grossMarginRatioTo,	
										 quotationRank,
										 orderStatus,
										 purchaseStatus,
										 salesStatus,
										 billingStatus,
										 paymentStatus,
										 first,
										 max);
	}

}
