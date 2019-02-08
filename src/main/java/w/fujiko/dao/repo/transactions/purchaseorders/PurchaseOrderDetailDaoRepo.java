package w.fujiko.dao.repo.transactions.purchaseorders;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.transactions.purchaseorders.PurchaseOrderDetailDao;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetail;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetail_;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetailPk;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader_;

@Repository
@Transactional
public class PurchaseOrderDetailDaoRepo 
	extends UniversalCrudRepo<PurchaseOrderDetail, PurchaseOrderDetailPk> 
	implements PurchaseOrderDetailDao {
	
	public PurchaseOrderDetailDaoRepo() {
		super();
		type = PurchaseOrderDetail.class;
	}

	@Override
	public List<PurchaseOrderDetail> getByPurchaseOrderHeaderId(Integer purchaseOrderHeaderId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PurchaseOrderDetail> criteria = builder.createQuery(PurchaseOrderDetail.class);
		Root<PurchaseOrderDetail> root = criteria.from(PurchaseOrderDetail.class);
		Join<PurchaseOrderDetail, PurchaseOrderHeader> purchaseOrderHeaderJoin = root.join(PurchaseOrderDetail_.purchaseOrderHeader);

		criteria.select(root)
				.where(builder.equal(purchaseOrderHeaderJoin.get(PurchaseOrderHeader_.id), purchaseOrderHeaderId));

		try {
			return session.createQuery(criteria).getResultList();
		} catch(Exception ex) {
			return new ArrayList<PurchaseOrderDetail>();
		}finally{
			session.close();
		}
	}

	@Override
	public void deleteByPurchaseOrderHeaderId(Integer purchaseOrderHeaderId) {
		Session session = this.getSessionFactory();
		Transaction transaction = session.getTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<PurchaseOrderDetail> criteria = builder.createCriteriaDelete(PurchaseOrderDetail.class);
		Root<PurchaseOrderDetail> root = criteria.from(PurchaseOrderDetail.class);

		criteria.where(
				builder.equal(root.get("id").get("purchaseOrderHeaderId"), purchaseOrderHeaderId)
			);
		
		try {
			transaction.begin();
			session.createQuery(criteria).executeUpdate();			
			transaction.commit();			
		} catch(ConstraintViolationException ex) {
			ex.printStackTrace();
			transaction.rollback();
		}
		finally{
			session.close();
		}
	}

}
