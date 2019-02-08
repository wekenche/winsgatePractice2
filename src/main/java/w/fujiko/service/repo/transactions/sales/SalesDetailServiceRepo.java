package w.fujiko.service.repo.transactions.sales;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;

import w.fujiko.dao.transactions.sales.SalesDetailDao;
import w.fujiko.model.transactions.sales.SalesDetail;
import w.fujiko.model.transactions.sales.SalesDetailPk;
import w.fujiko.service.transactions.sales.SalesDetailService;

@Service
@Transactional
public class SalesDetailServiceRepo 
	extends UniversalServiceRepo<SalesDetail, SalesDetailDao, SalesDetailPk>
	implements SalesDetailService {

	@Override
	public List<SalesDetail> getBySalesHeaderId(Integer salesHeaderId) {
		return dao.getBySalesHeaderId(salesHeaderId);
	}

}