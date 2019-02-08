/**
 * 
 */
package w.fujiko.service.infos;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.infos.ProductFile;
import w.fujiko.model.masters.products.Product;

/**
 * @author Yagami
 *
 */

@Service
@Repository
public interface ProductFileService extends UniversalCrud<ProductFile,Product> {}
