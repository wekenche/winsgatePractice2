package w.fujiko.dao.repo.transactions.purchaseorders;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import w.fujiko.dao.transactions.purchaseorders.PurchaseOrderDetailDao;
import w.fujiko.dao.transactions.purchaseorders.PurchaseOrderHeaderDao;
import w.fujiko.dao.transactions.purchaseorders.PurchaseOrderInputDao;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetail;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader;
import w.fujiko.model.transactions.purchaseorders.histories.PurchaseOrderHeaderHistory;
import w.fujiko.util.common.purchaseorders.PurchaseOrderTransaction;

@Repository
@Transactional
public class PurchaseOrderInputDaoRepo implements PurchaseOrderInputDao {

	@Autowired PurchaseOrderHeaderDao purchaseOrderHeaderDao;
	@Autowired PurchaseOrderDetailDao purchaseOrderDetailDao;
	@Autowired SessionFactory sessionFactory;

	public PurchaseOrderInputDaoRepo() {

	}

	public Session getSessionFactory() {
		return sessionFactory.openSession();
	}

	@Override
	public void save(PurchaseOrderTransaction purchaseOrderTransaction) throws ConstraintViolationException, PersistenceException {
		PurchaseOrderHeader purchaseOrderHeader = purchaseOrderTransaction.getPurchaseOrderHeader();
        
        final int BATCH_SIZE = 25;
        int purchaseOrderDetailSize = purchaseOrderTransaction.getPurchaseOrderDetails().size();
        
        Session session = this.getSessionFactory();
        Transaction transaction = session.getTransaction();
        
        try {
        	purchaseOrderHeaderDao.saveOrUpdate(purchaseOrderHeader);
            transaction.begin();
			for(int index = 0; index < purchaseOrderDetailSize; index++) {
                PurchaseOrderDetail detail = purchaseOrderTransaction.getPurchaseOrderDetails().get(index);
                detail.getId().setPurchaseOrderHeaderId(purchaseOrderHeader.getId());
				session.saveOrUpdate(detail);
				if (index > 0 && index % BATCH_SIZE == 0) {
					session.flush();
					session.clear();
				}
			}
			transaction.commit();
		} catch(ConstraintViolationException cve) {
            transaction.rollback();
            purchaseOrderHeaderDao.delete(purchaseOrderHeader);
			throw cve;
		} catch(PersistenceException cve) {
			transaction.rollback();
			purchaseOrderHeaderDao.delete(purchaseOrderHeader);
			throw cve;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteAndMoveToHistory(PurchaseOrderHeaderHistory headerHistory)
			throws ConstraintViolationException, PersistenceException {
		
		Session session = this.getSessionFactory();
		Transaction transaction = session.getTransaction();
		
		try {

			transaction.begin();
			//copy PurchaseOrderHeader to history table
			session.createQuery(this.getInsertSelectHeaderHistorySQLStatement())
                 	.setParameter("purchaseOrderHeaderId", headerHistory.getId())
                 	.executeUpdate();

			//copy PurchaseOrderDetail to history
			session.createNativeQuery(this.getInsertSelectHeaderDetailSQLStatement())
                   .setParameter("purchaseOrderHeaderId", headerHistory.getId())
                   .executeUpdate();
 
            transaction.commit();

            purchaseOrderDetailDao.deleteByPurchaseOrderHeaderId(headerHistory.getId());               
            purchaseOrderHeaderDao.deleteById(headerHistory.getId());
            
		} catch(ConstraintViolationException cve) {
            cve.printStackTrace();
			transaction.rollback();
			throw cve;
		} catch(PersistenceException cve) {
            cve.printStackTrace();
			transaction.rollback();
			throw cve;
		}catch(Exception e) {
            e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	private String getInsertSelectHeaderHistorySQLStatement(){
        String insertDetailSQL = " INSERT" 
                                    +" INTO PurchaseOrderHeaderHistory (id,"
                                                                    +" slipNumber,"
                                                                    +" version,"
                                                                    +" quotationHeader,"
                                                                    +" purchaseOrderDate,"
                                                                    +" deliveryDate,"
                                                                    +" supplierBase,"
                                                                    +" slipType,"
                                                                    +" consumptionTaxType,"
                                                                    +" salesTaxRate,"
                                                                    +" contactPersonIncharge,"
                                                                    +" secureFlg,"
                                                                    +" correctionDeadline,"
                                                                    +" destination,"
                                                                    +" user," 
                                                                    +" department,"                                                                   
                                                                    +" purchaseType,"
                                                                    +" remarks,"
                                                                    +" deliveryType,"
                                                                    +" directDelivery,"
                                                                    +" directDeliveryTimezone,"
                                                                    +" directDeliveryDesignationType,"
                                                                    +" hertzType,"
                                                                    +" priceList,"
                                                                    +" priceRatio,"
                                                                    +" totalPurchase,"
                                                                    +" totalReturn,"
                                                                    +" totalDiscount,"
                                                                    +" totalOrder,"
                                                                    +" totalTaxIncluded,"
                                                                    +" totalTaxConsumption,"
                                                                    +" purchaseOrderAmount,"
                                                                    +" dateCreated,"
                                                                    +" dateUpdated,"
                                                                    +" createdBy,"
                                                                    +" updatedBy,"
                                                                    +" createdAt,"
                                                                    +" updatedAt)"
                                    +" SELECT  ph.id,"
			                                    +"ph.slipNumber,"
			                                    +" ph.version,"
			                                    +" ph.quotationHeader,"
			                                    +" ph.purchaseOrderDate,"
			                                    +" ph.deliveryDate,"
			                                    +" ph.supplierBase,"
			                                    +" ph.slipType,"
			                                    +" ph.consumptionTaxType,"
			                                    +" ph.salesTaxRate,"
			                                    +" ph.contactPersonIncharge,"
			                                    +" ph.secureFlg,"
			                                    +" ph.correctionDeadline,"
			                                    +" ph.destination,"
                                                +" ph.user,"		
                                                +" ph.department,"	                                    
			                                    +" ph.purchaseType,"
			                                    +" ph.remarks,"
			                                    +" ph.deliveryType,"
			                                    +" ph.directDelivery,"
			                                    +" ph.directDeliveryTimezone,"
			                                    +" ph.directDeliveryDesignationType,"
			                                    +" ph.hertzType,"
			                                    +" ph.priceList,"
			                                    +" ph.priceRatio,"
			                                    +" ph.totalPurchase,"
			                                    +" ph.totalReturn,"
			                                    +" ph.totalDiscount,"
			                                    +" ph.totalOrder,"
			                                    +" ph.totalTaxIncluded,"
			                                    +" ph.totalTaxConsumption,"
			                                    +" ph.purchaseOrderAmount,"
			                                    +" ph.dateCreated,"
			                                    +" ph.dateUpdated,"
			                                    +" ph.createdBy,"
			                                    +" ph.updatedBy,"
			                                    +" ph.createdAt,"
                                                +"ph.updatedAt"
                                +" FROM PurchaseOrderHeader ph WHERE ph.id = :purchaseOrderHeaderId";
        return insertDetailSQL;
    }

    private String getInsertSelectHeaderDetailSQLStatement(){
        String insertDetailSQL = " INSERT" 
                                    +" INTO trn_po_detail_history (purchase_order_slip_id,"                                                                    
                                                                    +" quotation_slip_id,"
                                                                    +" task_id,"
                                                                    +" line_number,"
                                                                    +" purchasing_section,"
                                                                    +" model_number,"
                                                                    +" product_name,"
                                                                    +" maker_id,"
                                                                    +" product_id,"
                                                                    +" unit_id,"
                                                                    +" quantity,"
                                                                    +" estimated_quantity,"
                                                                    +" unit_price,"
                                                                    +" purchased_quantity,"
                                                                    +" amount,"
                                                                    +" remarks,"
                                                                    +" date_created,"
                                                                    +" date_updated,"
                                                                    +" created_by,"
                                                                    +" updated_by,"
                                                                    +" created_at,"
                                                                    +" updated_at)"
                                +" SELECT  pd.purchase_order_slip_id,"                                                                    
                                            +" pd.quotation_slip_id,"
                                            +" pd.task_id,"
                                            +" pd.line_number,"
                                            +" pd.purchasing_section,"
                                            +" pd.model_number,"
                                            +" pd.product_name,"
                                            +" pd.maker_id,"
                                            +" pd.product_id,"
                                            +" pd.unit_id,"
                                            +" pd.quantity,"
                                            +" pd.estimated_quantity,"
                                            +" pd.unit_price,"
                                            +" pd.purchased_quantity,"
                                            +" pd.amount,"
                                            +" pd.remarks,"
                                            +" pd.date_created,"
                                            +" pd.date_updated,"
                                            +" pd.created_by,"
                                            +" pd.updated_by,"
                                            +" pd.created_at,"
                                            +" pd.updated_at"
                                +" FROM trn_po_detail pd WHERE pd.purchase_order_slip_id = :purchaseOrderHeaderId";
        return insertDetailSQL;
    }

}