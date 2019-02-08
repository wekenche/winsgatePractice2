package w.fujiko.service.properties;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.properties.Property;

@Service
public interface PropertyService extends UniversalCrud<Property, Integer> {
	public List<Property> getProperties(Integer departmentId, Date dateFrom, Date dateTo, 
			Integer propertyNo, String propertyName, String propertyKana, 
			Integer registrationOfficerCode, String registrationOfficerName, Integer branchCode, 
			String branchName, String owner, Integer index);
	public Optional<Property> getPropertyByNo(Integer propertyNo);
	public ByteArrayInputStream exportToPDF(Integer from, Integer to) throws Exception;
	public String exportToCSV(Integer from, Integer to) throws Exception;
}