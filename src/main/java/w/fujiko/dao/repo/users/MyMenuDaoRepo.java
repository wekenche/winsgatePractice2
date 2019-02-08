package w.fujiko.dao.repo.users;

import java.lang.Override;
import java.util.List;
import java.util.ArrayList;

import javax.transaction.Transactional;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import w.fujiko.dao.users.MyMenuDao;
import w.fujiko.model.masters.users.MyMenu;
import w.fujiko.model.masters.users.MyMenu_;
import w.fujiko.model.masters.users.RoleProgram;
import w.fujiko.model.masters.users.RoleProgram_;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;

import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class MyMenuDaoRepo 
	extends UniversalCrudRepo<MyMenu ,Integer>
	implements MyMenuDao {

		public MyMenuDaoRepo() {
			super();
			type = MyMenu.class;
		}

		@Override
		public List<MyMenu> getCustomizeMenuOfUser(Integer userid) {
			Session session = getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<MyMenu> criteria = builder.createQuery(type);
			Root<MyMenu> root = criteria.from(type);
			Join<MyMenu,RoleProgram> programJoined = root.join(MyMenu_.roleProgram);
			Join<RoleProgram,User> userJoined = programJoined.join(RoleProgram_.authorizedUser);
			criteria.select(root)
					.where(builder
								.equal(userJoined.get(User_.id),
								       userid)
					);			
			try { 
				List<MyMenu> myMenuList = session.createQuery(criteria).getResultList();
				session.close();
				return myMenuList;
			} catch (NoResultException nre) {
				session.close();
				return new ArrayList<MyMenu>();
			}
	}

	@Override
	public List<MyMenu> findByRoleProgram(RoleProgram roleProgram) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<MyMenu> criteria = builder.createQuery(type);
		Root<MyMenu> root = criteria.from(type);
	
		criteria
			.select(root)
			.where(builder.and(
								builder.equal(
												root.get(MyMenu_.ROLE_PROGRAM),
												roleProgram)
				)
			);
					
		try {
			List<MyMenu> myMenuList = session.createQuery(criteria).getResultList();				
			session.close();
			return myMenuList;
		} catch (NoResultException nre) {
			session.close();
			return new ArrayList<MyMenu>();
		}		
	}
}
