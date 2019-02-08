/**
 * 
 */
package w.fujiko.service.repo.infos;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.infos.ProductFileDao;
import w.fujiko.model.infos.ProductFile;
import w.fujiko.model.masters.products.Product;
import w.fujiko.service.infos.ProductFileService;

/**
 * @author Yagami
 *
 */
@Service
@Transactional
public class ProductFileServiceRepo
		extends UniversalServiceRepo<ProductFile, ProductFileDao, Product>
		implements ProductFileService {
}
