package w.fujiko.dao.repo.transactions.quotations;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import w.fujiko.dao.transactions.quotations.QuotationDetailDao;
import w.fujiko.dao.transactions.quotations.QuotationHeaderDao;
import w.fujiko.dao.transactions.quotations.QuotationInputDao;
import w.fujiko.model.transactions.quotations.QuotationDetail;
import w.fujiko.model.transactions.quotations.QuotationHeader;
import w.fujiko.model.transactions.quotations.histories.QuotationHeaderHistory;
import w.fujiko.util.common.quotations.QuotationTransaction;

@Repository
@Transactional
public class QuotationInputDaoRepo implements QuotationInputDao {

    @Autowired QuotationHeaderDao quotationHeaderDao;
    @Autowired QuotationDetailDao quotationDetailDao;
    @Autowired SessionFactory sessionFactory;    
    
    public QuotationInputDaoRepo() {
    }

    public Session getSessionFactory() {
		return sessionFactory.openSession();
    }
    
    @Override
    public void save(QuotationTransaction quotationTransaction) throws ConstraintViolationException, PersistenceException {
        QuotationHeader quotationHeader = quotationTransaction.getQuotationHeader();
        
        final int BATCH_SIZE = 25;
        int quotationDetailSize = quotationTransaction.getQuotationDetails().size();
        Session session = this.getSessionFactory();
        
        Transaction transaction = session.getTransaction();
        
        try {
            quotationHeaderDao.saveOrUpdate(quotationHeader);
            transaction.begin();
			for(int index = 0; index < quotationDetailSize; index++) {
                QuotationDetail detail = quotationTransaction.getQuotationDetails().get(index);
                detail.getId().setQuotationHeaderId(quotationHeader.getId());
				session.saveOrUpdate(detail);
				if (index > 0 && index % BATCH_SIZE == 0) {
					session.flush();
					session.clear();
				}
			}
			transaction.commit();
		} catch(ConstraintViolationException cve) {
            transaction.rollback();
            quotationHeaderDao.delete(quotationHeader);
			throw cve;
		} catch(PersistenceException cve) {
            transaction.rollback();
            quotationHeaderDao.delete(quotationHeader);
			throw cve;
		}catch(Exception e) {
            transaction.rollback();
            quotationHeaderDao.delete(quotationHeader);
			throw e;
		}finally {
			session.close();
		}

    }

    @Override
    public void deleteAndMoveToHistory(QuotationHeaderHistory headerHistory) throws ConstraintViolationException, PersistenceException {

            Session session = this.getSessionFactory();
            Transaction transaction = session.getTransaction();
            
            try {

                transaction.begin();
                //copy QuotationHeader to history table
                session.createQuery(this.getInsertSelectHeaderHistorySQLStatement())
                       .setParameter("quotHeaderId", headerHistory.getId())
                       .executeUpdate();

                //copy QuotationDetail to history
                session.createNativeQuery(this.getInsertSelectHeaderDetailSQLStatement())
                       .setParameter("quotHeaderId", headerHistory.getId())
                       .executeUpdate();

                quotationDetailDao.deleteByQuotationHeaderId(headerHistory.getId());               
                
                quotationHeaderDao.deleteById(headerHistory.getId());
                transaction.commit();
            } catch(ConstraintViolationException cve) {
                transaction.rollback();
                throw cve;
            } catch(PersistenceException cve) {
                transaction.rollback();
                throw cve;
            }finally {
                session.close();
            }
    }

    private String getInsertSelectHeaderHistorySQLStatement(){
        String insertDetailSQL = " INSERT" 
                                    +" INTO QuotationHeaderHistory (id,"
                                                                    +" slipNumber,"
                                                                    +" version,"
                                                                    +" workingSite,"
                                                                    +" quotationDate,"
                                                                    +" deliveryDate,"
                                                                    +" constructionNumber,"
                                                                    +" constructionName,"
                                                                    +" constructionCategory,"
                                                                    +" constructionCategoryName,"
                                                                    +" unit,"
                                                                    +" redSlipCopyFlag,"
                                                                    +" copyOriginNumber,"
                                                                    +" constructionContractFlag,"
                                                                    +" warrantyCardFlag,"
                                                                    +" user,"
                                                                    +" department,"
                                                                    +" customerBase,"
                                                                    +" deliveryDateMessage,"
                                                                    +" respectUnnecessaryFlag,"
                                                                    +" contactUserName,"
                                                                    +" postMessage,"
                                                                    +" paymentTermsMessage,"
                                                                    +" estimatedDeadlineMessage,"
                                                                    +" deliveryPlaceMessage,"
                                                                    +" sparePartsCostMessage,"
                                                                    +" standardUnitPricePrintingFlag,"
                                                                    +" quotationRank,"
                                                                    +" poStatus,"
                                                                    +" purchaseStatus,"
                                                                    +" salesStatus,"
                                                                    +" billingStatus,"
                                                                    +" paymentStatus,"
                                                                    +" quotationTotalAmount,"
                                                                    +" orderTotalAmount,"
                                                                    +" purchaseTotalAmount,"
                                                                    +" grossProfit,"
                                                                    +" taxRate,"
                                                                    +" isApprove,"
                                                                    +" grossMarginRatio,"
                                                                    +" dateCreated,"
                                                                    +" dateUpdated,"
                                                                    +" createdBy,"
                                                                    +" updatedBy,"
                                                                    +" createdAt,"
                                                                    +" updatedAt)"
                                    +" SELECT  qh.id,"
                                                +"qh.slipNumber,"
                                                +"qh.version,"
                                                +"qh.workingSite,"
                                                +"qh.quotationDate,"
                                                +"qh.deliveryDate,"
                                                +"qh.constructionNumber,"
                                                +"qh.constructionName,"
                                                +"qh.constructionCategory,"
                                                +"qh.constructionCategoryName,"
                                                +"qh.unit,"
                                                +"qh.redSlipCopyFlag,"
                                                +"qh.copyOriginNumber,"
                                                +"qh.constructionContractFlag,"
                                                +"qh.warrantyCardFlag,"
                                                +"qh.user,"
                                                +"qh.department,"
                                                +"qh.customerBase,"
                                                +"qh.deliveryDateMessage,"
                                                +"qh.respectUnnecessaryFlag,"
                                                +"qh.contactUserName,"
                                                +"qh.postMessage,"
                                                +"qh.paymentTermsMessage,"
                                                +"qh.estimatedDeadlineMessage,"
                                                +"qh.deliveryPlaceMessage,"
                                                +"qh.sparePartsCostMessage,"
                                                +"qh.standardUnitPricePrintingFlag,"
                                                +"qh.quotationRank,"
                                                +"qh.poStatus,"
                                                +"qh.purchaseStatus,"
                                                +"qh.salesStatus,"
                                                +"qh.billingStatus,"
                                                +"qh.paymentStatus,"
                                                +"qh.quotationTotalAmount,"
                                                +"qh.orderTotalAmount,"
                                                +"qh.purchaseTotalAmount,"
                                                +"qh.grossProfit,"
                                                +"qh.taxRate,"
                                                +"qh.isApprove,"
                                                +"qh.grossMarginRatio,"
                                                +"qh.dateCreated,"
                                                +"qh.dateUpdated,"
                                                +"qh.createdBy,"
                                                +"qh.updatedBy,"
                                                +"qh.createdAt,"
                                                +"qh.updatedAt"
                                +" FROM QuotationHeader qh WHERE qh.id = :quotHeaderId";
        return insertDetailSQL;
    }

    private String getInsertSelectHeaderDetailSQLStatement(){
        String insertDetailSQL = " INSERT" 
                                    +" INTO trn_quot_detail_history (quotation_slip_id,"                                                                    
                                                                    +" task_id,"
                                                                    +" line_number,"
                                                                    +" processing_classification,"
                                                                    +" symbol,"
                                                                    +" specification_type,"
                                                                    +" product_name,"
                                                                    +" maker_id,"
                                                                    +" product_id,"
                                                                    +" fixed_price,"
                                                                    +" suppliers_id,"
                                                                    +" quantity,"
                                                                    +" unit,"
                                                                    +" ordered_quantity,"
                                                                    +" purchased_quantity,"
                                                                    +" sold_quantity,"
                                                                    +" customer_ratio,"
                                                                    +" unit_price,"
                                                                    +" amount,"
                                                                    +" suppliers_ratio,"
                                                                    +" orig_unit_price,"
                                                                    +" cost_price_amount,"
                                                                    +" stock_use_classification,"
                                                                    +" date_created,"
                                                                    +" date_updated,"
                                                                    +" created_by,"
                                                                    +" updated_by,"
                                                                    +" created_at,"
                                                                    +" updated_at)"
                                +" SELECT  qd.quotation_slip_id,"                                                                    
                                            +"qd.task_id,"
                                            +"qd.line_number,"
                                            +"qd.processing_classification,"
                                            +"qd.symbol,"
                                            +"qd.specification_type,"
                                            +"qd.product_name,"
                                            +"qd.maker_id,"
                                            +"qd.product_id,"
                                            +"qd.fixed_price,"
                                            +"qd.suppliers_id,"
                                            +"qd.quantity,"
                                            +"qd.unit,"
                                            +"qd.ordered_quantity,"
                                            +"qd.purchased_quantity,"
                                            +"qd.sold_quantity,"
                                            +"qd.customer_ratio,"
                                            +"qd.unit_price,"
                                            +"qd.amount,"
                                            +"qd.suppliers_ratio,"
                                            +"qd.orig_unit_price,"
                                            +"qd.cost_price_amount,"
                                            +"qd.stock_use_classification,"
                                            +"qd.date_created,"
                                            +"qd.date_updated,"
                                            +"qd.created_by,"
                                            +"qd.updated_by,"
                                            +"qd.created_at,"
                                            +"qd.updated_at"
                                +" FROM trn_quot_detail qd WHERE qd.quotation_slip_id = :quotHeaderId";
        return insertDetailSQL;
    }
    

}