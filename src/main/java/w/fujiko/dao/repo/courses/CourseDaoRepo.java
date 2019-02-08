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
import w.fujiko.dao.courses.CourseDao;
import w.fujiko.model.masters.courses.Course;
import w.fujiko.util.common.constants.CourseConstants;

@Repository
@Transactional
public class CourseDaoRepo extends UniversalCrudRepo<Course, Integer> 
	implements CourseDao {
	
	public CourseDaoRepo() {
		super();
		type = Course.class;
	}

	@Override
	public List<Course> getCoursesByNameOrCode(String key, Integer index) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Course> criteria = builder.createQuery(type);
		Root<Course> root = criteria.from(type);
		
		int code;
		try {
			code = Integer.parseInt(key);
		} catch(NumberFormatException nfe) {
			code = -1;
		}
		
		criteria.select(root)
			.where(
				builder.or(
					builder.equal(root.get(CourseConstants.FIELD_CODE), code),
					builder.like(
						builder.lower(root.get(CourseConstants.FIELD_NAME)), 
						"%"+key+"%"
					)
				)
			);
		
		List<Course> t = session.createQuery(criteria)
			.setFirstResult(index)
			.setMaxResults(30)
			.getResultList();
		
		session.close();
		return t;
	}

	@Override
	public Optional<Course> getUndeletedCourseByCode(Integer code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Course> criteria = builder.createQuery(type);
		Root<Course> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(CourseConstants.FIELD_CODE), code), 
					builder.equal(root.get(CourseConstants.FIELD_IS_END), false)
				)
			);
		
		Optional<Course> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}

}
