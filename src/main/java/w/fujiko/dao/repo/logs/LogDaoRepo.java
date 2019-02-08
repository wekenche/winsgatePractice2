package w.fujiko.dao.repo.logs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import w.fujiko.model.logs.Log;
import w.fujiko.model.logs.Log_;
import w.fujiko.dao.logs.LogDao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import fte.api.Page;
import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class LogDaoRepo 
	extends UniversalCrudRepo<Log ,Integer>
	implements LogDao {

		public LogDaoRepo() {
			super();
			type = Log.class;
		}

		@Override
		public Page<Log> paginate(int first, int max) {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Log> criteria = builder.createQuery(type);
			Root<Log> root =  criteria.from(type);
			criteria.select(root);

			Page<Log> page = new Page<Log>(); 
			CriteriaQuery<Long> total = builder.createQuery(Long.class);
			total.select(builder.count(total.from(type)));

			page.setSuccess(true);
			page.setFirst(first);
			page.setMax(max);
			page.setResults(session
							.createQuery(criteria)
							.setFirstResult(first)
							.setMaxResults(max)
							.getResultList());

			page.setTotal(session.createQuery(total).getSingleResult());
			session.close();
			return page;
		}

		public Page<Log> findByDateRangeWithPagination(Date from,Date to,int first, int max) {

			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Log> criteria = builder.createQuery(type);
			Root<Log> root =  criteria.from(type);
			criteria
			.select(root)
			.where(builder.between(root.get(Log_.logDate), from, to));
			Page<Log> page = new Page<Log>(); 
			
			CriteriaQuery<Long> total = builder.createQuery(Long.class);
			total.select(builder.count(total.from(Log.class)));

			page.setTotal(session.createQuery(total).getSingleResult());
			page.setSuccess(true);
			page.setFirst(first);
			page.setMax(max);
			page.setResults(session
							.createQuery(criteria)
							.setFirstResult(first)
							.setMaxResults(max)
							.getResultList());	
			session.close();
			return page;
		}

		public List<Log> findByDateRange(Date from,Date to) {

			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Log> criteria = builder.createQuery(type);
			Root<Log> root =  criteria.from(type);
			List<Log> logs = new ArrayList<>();
			ParameterExpression<Date> param = builder.parameter(Date.class,"param0");
			Path<Date> logDate = root.get(Log_.logDate);

			criteria
			.select(root)
			.where(builder.between(root.get(Log_.logDate),
								   from,
								   to));
		
			logs = session
					.createQuery(criteria)
					.getResultList();
			
			session.close();
			return logs;
		}
}
