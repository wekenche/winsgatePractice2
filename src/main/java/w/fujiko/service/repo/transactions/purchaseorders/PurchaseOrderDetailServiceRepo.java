package w.fujiko.service.repo.transactions.purchaseorders;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.purchaseorders.PurchaseOrderDetailDao;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetail;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetailPk;
import w.fujiko.service.transactions.purchaseorders.PurchaseOrderDetailService;

@Service
@Transactional
public class PurchaseOrderDetailServiceRepo 
	extends UniversalServiceRepo<PurchaseOrderDetail, PurchaseOrderDetailDao, PurchaseOrderDetailPk>
	implements PurchaseOrderDetailService {

	@Override
	public List<PurchaseOrderDetail> getByPurchaseOrderHeaderId(Integer purchaseOrderHeaderId) {
		return dao.getByPurchaseOrderHeaderId(purchaseOrderHeaderId);
	}

}