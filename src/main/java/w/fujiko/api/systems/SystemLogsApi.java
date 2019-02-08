package w.fujiko.api.systems;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import w.fujiko.model.masters.systems.SystemLogs;
import w.fujiko.service.systems.SystemLogsService;

@RestController
@RequestMapping("/api/system/logs")
public class SystemLogsApi 
	extends Api<SystemLogsService, SystemLogs,Integer> {

}
