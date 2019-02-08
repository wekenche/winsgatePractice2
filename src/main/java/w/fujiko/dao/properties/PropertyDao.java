package w.fujiko.dao.properties;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.properties.Property;

public interface PropertyDao extends UniversalCrud<Property, Integer> {
	public List<Property> getProperties(Integer departmentId, Date dateFrom, Date dateTo, 
			Integer propertyNo, String propertyName, String propertyKana, 
			Integer registrationOfficerCode, String registrationOfficerName, Integer branchCode, 
			String branchName, String owner, Integer index);
	public Optional<Property> getPropertyByNo(Integer propertyNo);
	public List<Property> getPropertiesByNo(Integer from, Integer to);
}