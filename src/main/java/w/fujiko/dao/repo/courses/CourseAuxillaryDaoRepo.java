package w.fujiko.dao.repo.courses;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.courses.CourseAuxillaryDao;
import w.fujiko.model.masters.courses.CourseAuxillary;
import w.fujiko.util.common.constants.CourseConstants;
import w.fujiko.util.common.constants.ProductConstants;

@Repository
@Transactional
public class CourseAuxillaryDaoRepo extends UniversalCrudRepo<CourseAuxillary, Integer> 
	implements CourseAuxillaryDao {

	public CourseAuxillaryDaoRepo() {
		super();
		type = CourseAuxillary.class;
	}

	@Override
	public List<CourseAuxillary> getCourseAuxillariesByNameOrCode(Integer courseId, String key, Integer index) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<CourseAuxillary> criteria = builder.createQuery(type);
		Root<CourseAuxillary> root = criteria.from(type);
		
		int code;
		try {
			code = Integer.parseInt(key);
		} catch(NumberFormatException nfe) {
			code = -1;
		}
		
		criteria.select(root)
			.where(builder.and(
				builder.equal(root.get(CourseConstants.FIELD_COURSE)
								.get(CourseConstants.FIELD_ID), courseId),
				builder.or(
					builder.equal(root.get(CourseConstants.FIELD_CODE), code),
					builder.like(
						builder.lower(root.get(CourseConstants.FIELD_NAME)), 
						"%"+key+"%"
					)
				)
			));
		
		List<CourseAuxillary> t = session.createQuery(criteria)
			.setFirstResult(index)
			.setMaxResults(30)
			.getResultList();
		
		session.close();
		return t;
	}

	@Override
	public Optional<CourseAuxillary> getUndeletedCourseAuxillaryByCode(Integer courseId, Integer code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<CourseAuxillary> criteria = builder.createQuery(type);
		Root<CourseAuxillary> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(CourseConstants.FIELD_COURSE)
								.get(CourseConstants.FIELD_ID), courseId),
					builder.equal(root.get(ProductConstants.FIELD_CODE), code), 
					builder.equal(root.get(ProductConstants.FIELD_IS_END), false)
				)
			);
		
		Optional<CourseAuxillary> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}
	
}
