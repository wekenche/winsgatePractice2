/**
 * 
 */
package w.fujiko.service.repo.logs;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.Page;
import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.logs.LogDao;
import w.fujiko.model.logs.Log;
import w.fujiko.service.logs.LogService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class LogServiceRepo extends UniversalServiceRepo<Log, LogDao, Integer> implements LogService {

	@Override
	public Page<Log> findByDateRangeWithPagination(Date from, Date to, int first, int max) {
		return dao.findByDateRangeWithPagination(from, to, first, max);
	}

	@Override
	public List<Log> findByDateRange(Date from, Date to) {
		return dao.findByDateRange(from,to);
	}}
