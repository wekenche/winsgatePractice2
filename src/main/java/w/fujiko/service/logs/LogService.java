package w.fujiko.service.logs;

import java.util.Date;
import java.util.List;

import fte.api.Page;
import fte.api.universal.UniversalCrud;
import w.fujiko.model.logs.Log;

/**
 * @author Yagami
 *
 */
public interface LogService extends UniversalCrud<Log,Integer>{
    public Page<Log> findByDateRangeWithPagination(Date from,Date to,int first, int max);
    public List<Log> findByDateRange(Date from,Date to);
}