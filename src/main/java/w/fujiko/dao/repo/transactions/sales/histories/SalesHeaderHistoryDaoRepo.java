package w.fujiko.dao.repo.transactions.sales.histories;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;

import w.fujiko.dao.transactions.sales.histories.SalesHeaderHistoryDao;

import w.fujiko.model.transactions.sales.histories.SalesHeaderHistory;

@Repository
@Transactional
public class SalesHeaderHistoryDaoRepo 
	extends UniversalCrudRepo<SalesHeaderHistory, Integer> 
	implements SalesHeaderHistoryDao {
	
	public SalesHeaderHistoryDaoRepo() {
		super();
		type = SalesHeaderHistory.class;
	}

}