package w.fujiko.dao.repo.infos;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import w.fujiko.model.infos.ProductFile;
import w.fujiko.model.infos.ProductFile_;
import w.fujiko.model.masters.products.Product;
import w.fujiko.dao.infos.ProductFileDao;

import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class ProductFileDaoRepo
	extends UniversalCrudRepo<ProductFile ,Product>
	implements ProductFileDao {

	public ProductFileDaoRepo() {
		super();
		type = ProductFile.class;
	}

	@Override
	public Optional<ProductFile> get(Product product) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProductFile> criteria = builder.createQuery(type);
		Root<ProductFile> root = criteria.from(type);

		criteria
		.select(root)
		.where(
			builder.equal(
				root.get(ProductFile_.product),
				product
			)
		);

		try{
			return Optional.of(session.createQuery(criteria).getSingleResult());	
		}catch(Exception ex){
			return Optional.empty();
		}finally{
			session.close();
		}
	}
}
