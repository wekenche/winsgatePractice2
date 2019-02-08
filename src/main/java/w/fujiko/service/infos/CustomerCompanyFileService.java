/**
 * 
 */
package w.fujiko.service.infos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.infos.CustomerCompanyFile;

/**
 * @author Yagami
 *
 */

@Service
@Repository
public interface CustomerCompanyFileService extends UniversalCrud<CustomerCompanyFile,Integer> {
    public Optional<CustomerCompanyFile> findByDocumentDate(Date documentDate);
    public List<CustomerCompanyFile> findByCustomerGroupId(Integer customerGroupId);
    public Boolean isUniqueDocumentDate(Date documentDate,int id);
}
