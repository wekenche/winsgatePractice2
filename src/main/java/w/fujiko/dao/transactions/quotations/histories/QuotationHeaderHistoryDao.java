package w.fujiko.dao.transactions.quotations.histories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import fte.api.Page;
import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.quotations.histories.QuotationHeaderHistory;

public interface QuotationHeaderHistoryDao extends UniversalCrud<QuotationHeaderHistory, Integer> {
	public Long getCountByWorkingSiteId(Integer id);
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
			int max);
}