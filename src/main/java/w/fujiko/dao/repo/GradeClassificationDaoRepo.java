package w.fujiko.dao.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.GradeClassificationDao;
import w.fujiko.model.masters.GradeClassification;
import w.fujiko.model.masters.GradeClassification_;
import w.fujiko.model.masters.Maker;
import w.fujiko.model.masters.Maker_;

@Repository
@Transactional
public class GradeClassificationDaoRepo 
	extends UniversalCrudRepo<GradeClassification, Short>
	implements GradeClassificationDao {
	
	public GradeClassificationDaoRepo() {
		super();
		type = GradeClassification.class;
	}

	@Override
	public Optional<GradeClassification> getUndeletedItemByCode(short code,int makerId) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<GradeClassification> criteria = builder.createQuery(type);
		Root<GradeClassification> root = criteria.from(type);
		Join<GradeClassification,Maker> joinMaker = root.join(GradeClassification_.maker);
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(joinMaker.get(Maker_.id), makerId),
					builder.equal(root.get(GradeClassification_.code), code),
					builder.equal(root.get(GradeClassification_.isEnd), false)
				)
			);
		
		Optional<GradeClassification> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;

	}

	@Override
	public List<GradeClassification> getByMakerId(int makerId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<GradeClassification> criteria = builder.createQuery(type);		
		Root<GradeClassification> root = criteria.from(type);
		Join<GradeClassification,Maker> joinMaker = root.join(GradeClassification_.maker);

		criteria.select(root)
			.where(
				builder.equal(joinMaker.get(Maker_.id), makerId)
			);
		
		try {
			return session.createQuery(criteria).getResultList();
		} catch(NoResultException nre) {
			return new ArrayList<GradeClassification>();
		}finally{
			session.close();
		}
	}

}