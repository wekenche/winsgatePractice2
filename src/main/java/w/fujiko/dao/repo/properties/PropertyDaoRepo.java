package w.fujiko.dao.repo.properties;

import static w.fujiko.util.common.constants.PropertyConstants.FIELD_BRANCH;
import static w.fujiko.util.common.constants.PropertyConstants.FIELD_CODE;
import static w.fujiko.util.common.constants.PropertyConstants.FIELD_DATE_UPDATED;
import static w.fujiko.util.common.constants.PropertyConstants.FIELD_DEPARTMENT;
import static w.fujiko.util.common.constants.PropertyConstants.FIELD_ID;
import static w.fujiko.util.common.constants.PropertyConstants.FIELD_NAME;
import static w.fujiko.util.common.constants.PropertyConstants.FIELD_OWNER;
import static w.fujiko.util.common.constants.PropertyConstants.FIELD_PROPERTY_KANA;
import static w.fujiko.util.common.constants.PropertyConstants.FIELD_PROPERTY_NAME;
import static w.fujiko.util.common.constants.PropertyConstants.FIELD_PROPERTY_NO;
import static w.fujiko.util.common.constants.PropertyConstants.FIELD_REG_OFFICER;
import static w.fujiko.util.common.constants.PropertyConstants.FIELD_USER_NAME;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.properties.PropertyDao;
import w.fujiko.model.masters.properties.Property;

@Repository
@Transactional
public class PropertyDaoRepo extends UniversalCrudRepo<Property, Integer> 
	implements PropertyDao {
	
	public PropertyDaoRepo() {
		super();
		type = Property.class;
	}

	@Override
	public List<Property> getProperties(Integer departmentId, Date dateFrom, Date dateTo, 
			Integer propertyNo, String propertyName, String propertyKana, 
			Integer registrationOfficerCode, String registrationOfficerName, Integer branchCode, 
			String branchName, String owner, Integer index) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Property> criteria = builder.createQuery(type);
		Root<Property> root = criteria.from(type);
		
		List<Predicate> restrictions = new ArrayList<>();
		
		if(propertyNo.intValue() != -1) {
			restrictions.add(builder.like(root.get(FIELD_PROPERTY_NO).as(String.class), "%"+propertyNo+"%"));
		}
		
		if(!propertyName.equals("")) {
			restrictions.add(builder.like(builder.lower(root.get(FIELD_PROPERTY_NAME)), "%"+propertyName+"%"));
		}
			
		if(!propertyKana.equals("")) {
			restrictions.add(builder.like(builder.lower(root.get(FIELD_PROPERTY_KANA)), "%"+propertyKana+"%"));
		}
		
		if(departmentId.intValue() != -1) {
			restrictions.add(builder.equal(root.get(FIELD_REG_OFFICER).get(FIELD_DEPARTMENT).get(FIELD_ID), departmentId));
		}
		
		if(registrationOfficerCode.shortValue() != -1) {
			restrictions.add(builder.like(root.get(FIELD_REG_OFFICER).get(FIELD_CODE).as(String.class), "%"+registrationOfficerCode+"%"));
		}
		
		if(!registrationOfficerName.equals("")) {
			restrictions.add(builder.like(root.get(FIELD_REG_OFFICER).get(FIELD_USER_NAME), "%"+registrationOfficerName+"%"));
		}
		
		if(branchCode.intValue() != -1) {
			restrictions.add(builder.like(root.get(FIELD_BRANCH).get(FIELD_CODE).as(String.class), "%"+branchCode+"%"));
		}
		
		if(!branchName.equals("")) {
			restrictions.add(builder.like(root.get(FIELD_BRANCH).get(FIELD_NAME), "%"+branchName+"%"));
		}
		
		if(!owner.equals("")) {
			restrictions.add(builder.like(builder.lower(root.get(FIELD_OWNER)), "%"+owner+"%"));
		}
		
		if(dateFrom != null) {
			restrictions.add(
				builder.greaterThanOrEqualTo(
					root.get(FIELD_DATE_UPDATED), dateFrom));
		}
		
		if(dateTo != null) {
			restrictions.add(
				builder.lessThanOrEqualTo(
					root.get(FIELD_DATE_UPDATED), dateTo));
		}
		
		criteria.select(root);
		if(restrictions.size() > 0) {
			criteria.where(builder.and(restrictions.toArray(new Predicate[restrictions.size()])));
		}
		criteria.orderBy(builder.desc(root.get(FIELD_DATE_UPDATED)));
		
		List<Property> result = session.createQuery(criteria)
			.setFirstResult(index)
			.setMaxResults(30)
			.getResultList();
		
		session.close();
		return result;
		
	}

	@Override
	public Optional<Property> getPropertyByNo(Integer propertyNo) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Property> criteria = builder.createQuery(type);
		Root<Property> root = criteria.from(type);
		
		criteria.select(root).where(builder.equal(root.get(FIELD_PROPERTY_NO), propertyNo));
		
		Optional<Property> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}

	@Override
	public List<Property> getPropertiesByNo(Integer from, Integer to) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Property> criteria = builder.createQuery(type);
		Root<Property> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.between(
					root.get(FIELD_PROPERTY_NO), from, to)
				);
		
		List<Property> result = session.createQuery(criteria)
			.getResultList();
		
		session.close();
		return result;
	}

}