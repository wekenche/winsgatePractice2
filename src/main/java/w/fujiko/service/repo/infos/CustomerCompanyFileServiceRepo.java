/**
 * 
 */
package w.fujiko.service.repo.infos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.infos.CustomerCompanyFileDao;
import w.fujiko.model.infos.CustomerCompanyFile;
import w.fujiko.service.infos.CustomerCompanyFileService;

/**
 * @author Yagami
 *
 */
@Service
@Transactional
public class CustomerCompanyFileServiceRepo
		extends UniversalServiceRepo<CustomerCompanyFile, CustomerCompanyFileDao, Integer>
		implements CustomerCompanyFileService {

	@Override
	public Optional<CustomerCompanyFile> findByDocumentDate(Date documentDate) {
		return dao.findByDocumentDate(documentDate);
	}

	@Override
	public List<CustomerCompanyFile> findByCustomerGroupId(Integer customerGroupId) {
		return dao.findByCustomerGroupId(customerGroupId);
	}

	@Override
	public Boolean isUniqueDocumentDate(Date documentDate, int id) {
		return dao.isUniqueDocumentDate(documentDate, id);
	}
}
