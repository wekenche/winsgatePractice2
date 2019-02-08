package w.fujiko.dao.repo.generalpurposes;

import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.generalpurposes.GeneralPurposeDao;
import w.fujiko.model.masters.generalpurposes.GeneralPurpose;
import w.fujiko.model.masters.generalpurposes.GeneralPurpose_;

@Repository
@Transactional
public class GeneralPurposeDaoRepo 
	extends UniversalCrudRepo<GeneralPurpose, Integer>
	implements GeneralPurposeDao {
	
	public GeneralPurposeDaoRepo() {
		super();
		type = GeneralPurpose.class;
	}

	@Override
	public Optional<GeneralPurpose> getUndeletedItemByCode(String code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<GeneralPurpose> criteria = builder.createQuery(type);
		Root<GeneralPurpose> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(GeneralPurpose_.code), code), 
					builder.equal(root.get(GeneralPurpose_.isEnd), false)
				)
			);
		
		Optional<GeneralPurpose> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;

	}

}
