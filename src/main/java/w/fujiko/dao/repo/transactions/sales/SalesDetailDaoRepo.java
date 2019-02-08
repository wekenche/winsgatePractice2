package w.fujiko.dao.repo.transactions.sales;

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

import w.fujiko.dao.transactions.sales.SalesDetailDao;

import w.fujiko.model.transactions.sales.SalesDetail;
import w.fujiko.model.transactions.sales.SalesDetailPk;
import w.fujiko.model.transactions.sales.SalesDetail_;
import w.fujiko.model.transactions.sales.SalesHeader;
import w.fujiko.model.transactions.sales.SalesHeader_;

@Repository
@Transactional
public class SalesDetailDaoRepo 
	extends UniversalCrudRepo<SalesDetail, SalesDetailPk> 
	implements SalesDetailDao {
	
	public SalesDetailDaoRepo() {
		super();
		type = SalesDetail.class;
	}

	@Override
	public List<SalesDetail> getBySalesHeaderId(Integer salesHeaderId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<SalesDetail> criteria = builder.createQuery(SalesDetail.class);
		Root<SalesDetail> root = criteria.from(SalesDetail.class);
		Join<SalesDetail, SalesHeader> salesHeaderJoin = root.join(SalesDetail_.salesHeader);

		criteria.select(root)
				.where(builder.equal(salesHeaderJoin.get(SalesHeader_.id), salesHeaderId));

		try {
			return session.createQuery(criteria).getResultList();
		} catch(Exception ex) {
			return new ArrayList<SalesDetail>();
		}finally{
			session.close();
		}
	}

	@Override
	public void deleteBySalesHeaderId(Integer salesHeaderId) {
		Session session = this.getSessionFactory();
		Transaction transaction = session.getTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<SalesDetail> criteria = builder.createCriteriaDelete(SalesDetail.class);
		Root<SalesDetail> root = criteria.from(SalesDetail.class);

		criteria.where(
				builder.equal(root.get("id").get("salesHeaderId"), salesHeaderId)
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
