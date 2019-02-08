package w.fujiko.dao.repo.transactions.sales;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import w.fujiko.dao.transactions.sales.SalesDetailDao;
import w.fujiko.dao.transactions.sales.SalesHeaderDao;
import w.fujiko.dao.transactions.sales.SalesInputDao;

import w.fujiko.model.transactions.sales.SalesDetail;
import w.fujiko.model.transactions.sales.SalesHeader;
import w.fujiko.model.transactions.sales.histories.SalesHeaderHistory;

import w.fujiko.util.common.sales.SalesTransaction;

@Repository
@Transactional
public class SalesInputDaoRepo implements SalesInputDao {

	@Autowired SalesHeaderDao salesHeaderDao;
	@Autowired SalesDetailDao salesDetailDao;
	@Autowired SessionFactory sessionFactory;

	public SalesInputDaoRepo() {

	}

	public Session getSessionFactory() {
		return sessionFactory.openSession();
	}

	@Override
	public void save(SalesTransaction salesTransaction) throws ConstraintViolationException, PersistenceException {
		SalesHeader salesHeader = salesTransaction.getSalesHeader();
        
        final int BATCH_SIZE = 25;
        int salesDetailSize = salesTransaction.getSalesDetails().size();

        Session session = this.getSessionFactory();
        Transaction transaction = session.getTransaction();
        
        try {
        	salesHeaderDao.saveOrUpdate(salesHeader);
            transaction.begin();
			for(int index = 0; index < salesDetailSize; index++) {
                SalesDetail detail = salesTransaction.getSalesDetails().get(index);
               
                detail.getId().setSalesHeaderId(salesHeader.getId());
				session.saveOrUpdate(detail);
				if (index > 0 && index % BATCH_SIZE == 0) {
					session.flush();
					session.clear();
				}
			}
			transaction.commit();
		} catch(ConstraintViolationException cve) {
            transaction.rollback();
            salesHeaderDao.delete(salesHeader);
			throw cve;
		} catch(PersistenceException cve) {
			transaction.rollback();
			salesHeaderDao.delete(salesHeader);
			throw cve;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteAndMoveToHistory(SalesHeaderHistory headerHistory)
			throws ConstraintViolationException, PersistenceException {
		
		Session session = this.getSessionFactory();
		Transaction transaction = session.getTransaction();
		
		try {

			transaction.begin();
			//copy SalesHeader to history table
			session.createQuery(this.getInsertSelectHeaderHistorySQLStatement())
                 	.setParameter("salesHeaderId", headerHistory.getId())
                 	.executeUpdate();

			//copy SalesDetail to history
			session.createNativeQuery(this.getInsertSelectHeaderDetailSQLStatement())
                   .setParameter("salesHeaderId", headerHistory.getId())
                   .executeUpdate();
 
            transaction.commit();

            salesDetailDao.deleteBySalesHeaderId(headerHistory.getId());               
            salesHeaderDao.deleteById(headerHistory.getId());
            
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
                                    +" INTO SalesHeaderHistory (id,"
                                                            +" slipNumber,"
                                                            +" version,"
                                                            +" quotationHeader,"
                                                            +" salesDate,"
                                                            +" billingDate,"
                                                            +" customerBase,"
                                                            +" customerName,"
                                                            +" destination,"
                                                            +" constructionNumber,"
                                                            +" constructionName,"
                                                            +" salesType,"
                                                            +" constructionContractorFlag,"
                                                            +" slipType,"
                                                            +" consumptionTaxType,"
                                                            +" user,"
                                                            +" department,"
                                                            +" remarks,"
                                                            +" salesTaxRate,"
                                                            +" priceList,"
                                                            +" priceRatio,"
                                                            +" sales,"
                                                            +" totalReturn,"
                                                            +" totalDiscount,"
                                                            +" totalSales,"
                                                            +" totalTaxIncluded,"
                                                            +" totalTaxConsumption,"
                                                            +" totalGrossProfit,"
                                                            +" dateCreated,"
                                                            +" dateUpdated,"
                                                            +" createdBy,"
                                                            +" updatedBy,"
                                                            +" createdAt,"
                                                            +" updatedAt)"
                                    +" SELECT  sh.id,"
                                                +" sh.slipNumber,"
                                                +" sh.version,"
                                                +" sh.quotationHeader,"
                                                +" sh.salesDate,"
                                                +" sh.billingDate,"
                                                +" sh.customerBase,"
                                                +" sh.customerName,"
                                                +" sh.destination,"
                                                +" sh.constructionNumber,"
                                                +" sh.constructionName,"
                                                +" sh.salesType,"
                                                +" sh.constructionContractorFlag,"
                                                +" sh.slipType,"
                                                +" sh.consumptionTaxType,"
                                                +" sh.user,"
                                                +" sh.department,"
                                                +" sh.remarks,"
                                                +" sh.salesTaxRate,"
                                                +" sh.priceList,"
                                                +" sh.priceRatio,"
                                                +" sh.sales,"
                                                +" sh.totalReturn,"
                                                +" sh.totalDiscount,"
                                                +" sh.totalSales,"
                                                +" sh.totalTaxIncluded,"
                                                +" sh.totalTaxConsumption,"
                                                +" sh.totalGrossProfit,"
                                                +" sh.dateCreated,"
                                                +" sh.dateUpdated,"
                                                +" sh.createdBy,"
                                                +" sh.updatedBy,"
                                                +" sh.createdAt,"
                                                +" sh.updatedAt"
                                +" FROM SalesHeader sh WHERE sh.id = :salesHeaderId";
        return insertDetailSQL;
    }

    private String getInsertSelectHeaderDetailSQLStatement(){
        String insertDetailSQL = " INSERT" 
                                    +" INTO trn_sales_detail_history (sales_slip_id,"                                                                    
                                                                    +" quotation_slip_id,"
                                                                    +" task_id,"
                                                                    +" line_number,"
                                                                    +" sales_section,"
                                                                    +" model_number,"
                                                                    +" product_name,"
                                                                    +" maker_id,"
                                                                    +" product_id,"
                                                                    +" unit_id,"
                                                                    +" quantity,"                                                                    
                                                                    +" unit_price,"
                                                                    +" original_unit_price,"
                                                                    +" amount,"
                                                                    +" remarks,"
                                                                    +" date_created,"
                                                                    +" date_updated,"
                                                                    +" created_by,"
                                                                    +" updated_by,"
                                                                    +" created_at,"
                                                                    +" updated_at)"
                                +" SELECT  sd.sales_slip_id,"                                                                    
                                            +" sd.quotation_slip_id,"
                                            +" sd.task_id,"
                                            +" sd.line_number,"
                                            +" sd.sales_section,"
                                            +" sd.model_number,"
                                            +" sd.product_name,"
                                            +" sd.maker_id,"
                                            +" sd.product_id,"
                                            +" sd.unit_id,"
                                            +" sd.quantity,"                                                                    
                                            +" sd.unit_price,"
                                            +" sd.original_unit_price,"
                                            +" sd.amount,"
                                            +" sd.remarks,"
                                            +" sd.date_created,"
                                            +" sd.date_updated,"
                                            +" sd.created_by,"
                                            +" sd.updated_by,"
                                            +" sd.created_at,"
                                            +" sd.updated_at"
                                +" FROM trn_sales_detail sd WHERE sd.sales_slip_id = :salesHeaderId";
        return insertDetailSQL;
    }

}