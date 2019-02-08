package w.fujiko.service.repo.transactions.quotations;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.quotations.QuotationDetailDao;
import w.fujiko.model.transactions.quotations.QuotationDetail;
import w.fujiko.model.transactions.quotations.QuotationDetailPk;
import w.fujiko.service.transactions.quotations.QuotationDetailService;

@Service
@Transactional
public class QuotationDetailServiceRepo extends
		UniversalServiceRepo<QuotationDetail, QuotationDetailDao, QuotationDetailPk> implements QuotationDetailService {

	@Override
	public List<QuotationDetail> getByQuotationHeaderId(Integer quotationHeaderId) {
		return dao.getByQuotationHeaderId(quotationHeaderId);
	}

	@Override
	public void deleteByQuotationHeaderId(Integer quotationHeaderId) {
		dao.deleteByQuotationHeaderId(quotationHeaderId);
	}

}
