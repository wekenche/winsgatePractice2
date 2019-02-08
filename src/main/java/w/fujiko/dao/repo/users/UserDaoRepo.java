package w.fujiko.dao.repo.users;

import java.lang.Override;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;
import w.fujiko.dao.users.UserDao;
import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class UserDaoRepo 
	extends UniversalCrudRepo<User ,Integer>
	implements UserDao {

		public UserDaoRepo() {
			super();
			type = User.class;
		}

		@Override
		public Optional<User> getByName(String email) {
			Session session = getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(type);
			Root<User> root = criteria.from(type);
			criteria
			.select(root)
			.where(builder.and(builder.equal(root.get(User_.username), email), builder.equal(root.get(User_.isResigned), false)));
			
			try { 
				User user = session.createQuery(criteria).getSingleResult();
				session.close();
				return Optional.of(user);
			} catch (NoResultException nre) {
				session.close();
				return Optional.empty();
			}
		}
		
		@Override
		public Optional<User> getByUserId(Integer id, Boolean resign) {
			Session session = getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(type);
			Root<User> root = criteria.from(type);
			javax.persistence.criteria.Predicate build;

			criteria.select(root);
			
			if(resign)
				build = builder.and(builder.equal(root.get(User_.id), id) ,builder.equal(root.get(User_.isResigned), false));
			else
				build = builder.equal(root.get(User_.id), id);

			criteria.where(build);

			try { 
				User user = session.createQuery(criteria).getSingleResult();
				session.close();
				return Optional.of(user);
			} catch (NoResultException nre) {
				session.close();
				return Optional.empty();
			}
		}

		@Override
		public Optional<User> getByCode(Short code) {			
			Session session = getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(type);
			Root<User> root = criteria.from(type);
			criteria
			.select(root)
			.where(builder.and(builder.equal(root.get(User_.code), code),
							   builder.equal(root.get(User_.isResigned), false)));
			
			try { 
				User user = session.createQuery(criteria).getSingleResult();			
				session.close();
				return Optional.of(user);
			} catch (NoResultException nre) {
				session.close();
				return Optional.empty();
			}
		}

		@Override
		public List<User> getUsersByNameOrCode(String key, Integer index) {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(type);
			Root<User> root = criteria.from(type);
			
			short code;
			try {
				code = Short.parseShort(key);
			} catch(NumberFormatException nfe) {
				code = -1;
			}
			
			criteria.select(root)
				.where(
					builder.or(
						builder.equal(root.get(User_.code), code), 
						builder.like(
							builder.lower(root.get(User_.username)), 
							"%"+key+"%"
						)
					)
				);
			
			List<User> results = session.createQuery(criteria)
				.setFirstResult(index)
				.setMaxResults(30)
				.getResultList();
			
			session.close();
			return results;
		}

		@Override
		public Optional<User> getUndeletedUserByCode(Short code) {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(type);
			Root<User> root = criteria.from(type);
			
			criteria.select(root)
				.where(
					builder.and(
						builder.equal(root.get(User_.code), code), 
						builder.equal(root.get(User_.isResigned), false)
					)
				);
			
			Optional<User> result;
			
			try {
				result = Optional.of(session.createQuery(criteria).getSingleResult());
			} catch(NoResultException nre) {
				result = Optional.empty();
			}
			session.close();
			
			return result;
		}
}
