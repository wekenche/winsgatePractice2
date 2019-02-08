package w.fujiko.dao.repo.transactions.quotations;

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
import w.fujiko.dao.transactions.quotations.QuotationDetailDao;
import w.fujiko.model.transactions.quotations.QuotationDetailPk;
import w.fujiko.model.transactions.quotations.QuotationDetail_;
import w.fujiko.model.transactions.quotations.QuotationHeader;
import w.fujiko.model.transactions.quotations.QuotationHeader_;
import w.fujiko.model.transactions.quotations.QuotationDetail;

@Repository
@Transactional
public class QuotationDetailDaoRepo 
	extends UniversalCrudRepo<QuotationDetail, QuotationDetailPk> 
	implements QuotationDetailDao {
	
	public QuotationDetailDaoRepo() {
		super();
		type = QuotationDetail.class;
	}

	@Override
	public List<QuotationDetail> getByQuotationHeaderId(Integer quotationHeaderId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<QuotationDetail> criteria = builder.createQuery(QuotationDetail.class);
		Root<QuotationDetail> root = criteria.from(QuotationDetail.class);
		Join<QuotationDetail,QuotationHeader> quotationHeaderJoin = root.join(QuotationDetail_.quotationHeader);

		criteria.select(root)
				.where(builder.equal(quotationHeaderJoin.get(QuotationHeader_.id), quotationHeaderId));

		try {
			return session.createQuery(criteria).getResultList();
		} catch(Exception ex) {
			return new ArrayList<QuotationDetail>();
		}finally{
			session.close();
		}
	}

	@Override
	public void deleteByQuotationHeaderId(Integer quotationHeaderID) {
		Session session = this.getSessionFactory();
		Transaction transaction = session.getTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<QuotationDetail> criteria = builder.createCriteriaDelete(QuotationDetail.class);
		Root<QuotationDetail> root = criteria.from(QuotationDetail.class);
		
		criteria.where(
				builder.equal(root.get("id").get("quotationHeaderId"), quotationHeaderID)
			);
		
		try {
			transaction.begin();
			session.createQuery(criteria).executeUpdate();
			transaction.commit();
		} catch(ConstraintViolationException ex) {
			transaction.rollback();
		}
		finally{
			session.close();
		}
	}

}