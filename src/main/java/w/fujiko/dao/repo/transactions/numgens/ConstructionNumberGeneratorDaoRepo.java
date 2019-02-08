package w.fujiko.dao.repo.transactions.numgens;

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

import w.fujiko.dao.transactions.numgens.ConstructionNumberGeneratorDao;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;
import w.fujiko.model.transactions.numbergens.ConstructionNumberGenerator;
import w.fujiko.model.transactions.numbergens.ConstructionNumberGenerator_;

/**
 * @author yagami
 */

@Repository
@Transactional
public class ConstructionNumberGeneratorDaoRepo extends UniversalCrudRepo<ConstructionNumberGenerator, User>
        implements ConstructionNumberGeneratorDao {

    @Override
    public Optional<ConstructionNumberGenerator> getByUserId(Integer userId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ConstructionNumberGenerator> criteria = builder.createQuery(ConstructionNumberGenerator.class);
        Root<ConstructionNumberGenerator> root = criteria.from(ConstructionNumberGenerator.class);
        Join<ConstructionNumberGenerator,User> userJoin = root.join(ConstructionNumberGenerator_.user);

        criteria.select(root)
                .where(builder.equal(
                            userJoin.get(User_.id), userId
                       ));

        Optional<ConstructionNumberGenerator> result;
        try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
        }
        finally{
            session.close();
        }
		
		return result;
    }
}
