/**
 * 
 */
package w.fujiko.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.systems.SystemLogsDao;
import w.fujiko.model.masters.systems.SystemLogs;

/**
 * @author Try-Parser
 *
 */
@Transactional
@Repository
public class SystemLogsDaoRepo
		extends UniversalCrudRepo<SystemLogs, Integer> 
		implements SystemLogsDao {}
