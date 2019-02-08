package w.fujiko.dao.repo.systems;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.systems.ProgramCategory;
import w.fujiko.model.masters.systems.ProgramCategory_;
import w.fujiko.dao.systems.ProgramCategoryDao;
import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class ProgramCategoryDaoRepo 
	extends UniversalCrudRepo<ProgramCategory ,String>
	implements ProgramCategoryDao {
		
		public ProgramCategoryDaoRepo() {
			super();
			type = ProgramCategory.class;
		}

		@Override
		public List<ProgramCategory> getAllCategorySortedByCategoryId() {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ProgramCategory> criteria = builder.createQuery(type);
			Root<ProgramCategory> root = criteria.from(type);
			criteria.select(root)
			.orderBy(builder.asc(root.get(ProgramCategory_.id)));;
			List<ProgramCategory> t = session.createQuery(criteria).getResultList();
			session.close();
			return t;
		}

		@Override
		public List<ProgramCategory> getCategoryWithProgram(){
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ProgramCategory> criteria = builder.createQuery(type);
			Root<ProgramCategory> root = criteria.from(type);
			Join<ProgramCategory,Program> programJoined = root.join(ProgramCategory_.programs);

			criteria
			.distinct(true)
			.select(root)
			.orderBy(builder.asc(root.get(ProgramCategory_.id)));;
			List<ProgramCategory> t = session.createQuery(criteria).getResultList();
			session.close();
			return t;
		}
}