package w.fujiko.dao.repo.generalpurposes.items;

import java.util.ArrayList;
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
import w.fujiko.dao.generalpurposes.items.GeneralPurposeItemDao;
import w.fujiko.model.masters.generalpurposes.GeneralPurposeItem;
import w.fujiko.model.masters.generalpurposes.GeneralPurposeItem_;
import w.fujiko.model.masters.generalpurposes.GeneralPurpose_;

@Repository
@Transactional
public class GeneralPurposeItemDaoRepo 
	extends UniversalCrudRepo<GeneralPurposeItem, Integer>
	implements GeneralPurposeItemDao {
	
	public GeneralPurposeItemDaoRepo() {
		super();
		type = GeneralPurposeItem.class;
	}

	@Override
	public Optional<GeneralPurposeItem> 
		getUndeletedItemByCode(String generalPurposeCode, String generalPurposeItemCode) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<GeneralPurposeItem> criteria = builder.createQuery(type);
		Root<GeneralPurposeItem> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get("generalPurpose").get("code"), generalPurposeCode),
					builder.equal(root.get("code"), generalPurposeItemCode),
					builder.equal(root.get("isEnd"), false)
				)
			);
		
		Optional<GeneralPurposeItem> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;

    }

    @Override
    public List<GeneralPurposeItem> getByGeneralPurposeId(Integer genericPurposeId,Optional<String> code) {
        Session session = this.getSessionFactory();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<GeneralPurposeItem> criteria = builder.createQuery(type);
		Root<GeneralPurposeItem> root =  criteria.from(type);
		var predicate = builder.equal(root.get("generalPurpose").get("id"), genericPurposeId);

		if(code.isPresent())
			predicate = builder.and(predicate,builder.equal(root.get(GeneralPurposeItem_.code), code.get()));
		
        try{
            criteria.select(root)
                    .where(predicate);
                    
            return session.createQuery(criteria).getResultList();
        }catch(Exception ex){
            return new ArrayList<>();
        }
	}

	@Override
    public List<GeneralPurposeItem> getByGeneralPurposeCode(String generalPurposeCode,Boolean isEnd) {
        Session session = this.getSessionFactory();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<GeneralPurposeItem> criteria = builder.createQuery(type);
		Root<GeneralPurposeItem> root =  criteria.from(type);
		var joinGeneralPurpose = root.join(GeneralPurposeItem_.generalPurpose);

        try{
            criteria.select(root)
					.where(builder
						.and(
						    builder.equal(
								  joinGeneralPurpose.get(GeneralPurpose_.code),
								  generalPurposeCode
							),
							builder.equal(
								joinGeneralPurpose.get(GeneralPurpose_.isEnd),
								isEnd
							)
						)
					);
                    
            return session.createQuery(criteria).getResultList();
        }catch(Exception ex){
            return new ArrayList<>();
        }
	}

	@Override
    public Optional<GeneralPurposeItem> getByCodeAndByGeneralPurposeCode(String code,String generalPurposeCode,Boolean isEnd) {
        Session session = this.getSessionFactory();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<GeneralPurposeItem> criteria = builder.createQuery(type);
		Root<GeneralPurposeItem> root =  criteria.from(type);
		var joinGeneralPurpose = root.join(GeneralPurposeItem_.generalPurpose);

        try{
            criteria.select(root)
					.where(builder
						.and(
							builder.equal(
								root.get(GeneralPurposeItem_.code),
								code
						  	),
						    builder.equal(
								joinGeneralPurpose.get(GeneralPurpose_.code),
								generalPurposeCode
							),
							builder.equal(
								joinGeneralPurpose.get(GeneralPurpose_.isEnd),
								isEnd
							)
						)
					);
                    
            return Optional.of(session.createQuery(criteria).getSingleResult());
        }catch(Exception ex){
            return Optional.empty();
        }
	}

}