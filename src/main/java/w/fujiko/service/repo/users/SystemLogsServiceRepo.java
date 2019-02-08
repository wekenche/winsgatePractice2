/**
 * 
 */
package w.fujiko.service.repo.users;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.systems.SystemLogsDao;
import w.fujiko.model.masters.systems.SystemLogs;
import w.fujiko.service.systems.SystemLogsService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class SystemLogsServiceRepo 
		extends UniversalServiceRepo<SystemLogs, SystemLogsDao, Integer>
		implements SystemLogsService {}
