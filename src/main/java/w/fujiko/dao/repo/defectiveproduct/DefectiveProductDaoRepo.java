package w.fujiko.dao.repo.defectiveproduct;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.defectiveproduct.DefectiveProductDao;
import w.fujiko.model.masters.defectiveproduct.DefectiveProduct;

@Repository
@Transactional
public class DefectiveProductDaoRepo extends UniversalCrudRepo<DefectiveProduct, Integer> implements DefectiveProductDao {

	@Override
	public List<DefectiveProduct> getAllDefectiveProduct() {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<DefectiveProduct> criteria = builder.createQuery(type);
		Root<DefectiveProduct> root = criteria.from(type);
		try {
			criteria.select(root);
			return session.createQuery(criteria).getResultList();
		}catch(Exception ex) {
			return new ArrayList<>();
		}
		
	}

	@Override
	public DefectiveProduct getDefectiveProductById(Integer prodId) {


		return null;
	}

}
