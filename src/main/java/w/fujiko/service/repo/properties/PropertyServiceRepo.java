package w.fujiko.service.repo.properties;

import static w.fujiko.util.common.constants.PropertyConstants.DATE_FORMAT;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.properties.PropertyDao;
import w.fujiko.model.masters.properties.Property;
import w.fujiko.model.masters.properties.PropertyCSVExtractionModel;
import w.fujiko.model.masters.properties.PropertyPDFExtractionModel;
import w.fujiko.service.properties.PropertyService;
import w.fujiko.util.common.constants.PropertyConstants;
import w.fujiko.util.common.generator.CSVGenerator;
import w.fujiko.util.common.generator.PDFListGenerator;
import w.fujiko.util.common.generator.PDFListTable;
import w.fujiko.util.common.generator.Table;

@Service
@Transactional
public class PropertyServiceRepo  extends UniversalServiceRepo<Property, PropertyDao, Integer> 
	implements PropertyService {
	
	private static final String[] headerListCSV = {
			PropertyConstants.PROPERTY_NO,
			PropertyConstants.PROPERTY_KANA,
			PropertyConstants.PROPERTY_NAME,
			PropertyConstants.USER_CD,
			PropertyConstants.USER_NAME,
			PropertyConstants.BRANCH_CD,
			PropertyConstants.BRANCH_NAME,
			PropertyConstants.OWNER,
			PropertyConstants.DATE_CREATED,
			PropertyConstants.LAST_UPDATED };
	
	private static final String[] fieldListCSV = {
			PropertyConstants.FIELD_PROPERTY_NO,
			PropertyConstants.FIELD_PROPERTY_KANA,
			PropertyConstants.FIELD_PROPERTY_NAME,
			PropertyConstants.FIELD_USER_CD,
			PropertyConstants.FIELD_USER_NAME,
			PropertyConstants.FIELD_BRANCH_CD,
			PropertyConstants.FIELD_BRANCH_NAME,
			PropertyConstants.FIELD_OWNER,
			PropertyConstants.FIELD_DATE_CREATED,
			PropertyConstants.FIELD_LAST_UPDATED };
	
	private static final String[] headerListPDF = {
			PropertyConstants.PROPERTY_NO,
			PropertyConstants.PROPERTY_KANA + "\n" + PropertyConstants.PROPERTY_NAME,
			PropertyConstants.USER_NAME + "\n" + PropertyConstants.BRANCH,
			PropertyConstants.OWNER,
			PropertyConstants.DATE_CREATED,
			PropertyConstants.LAST_UPDATED };
	
	private static final String[] fieldListPDF = {
			PropertyConstants.FIELD_PROPERTY_NO,
			PropertyConstants.FIELD_PROPERTY_NAME_AND_KANA,
			PropertyConstants.FIELD_USER_AND_BRANCH,
			PropertyConstants.FIELD_OWNER,
			PropertyConstants.FIELD_DATE_CREATED,
			PropertyConstants.FIELD_LAST_UPDATED };

	@Override
	public List<Property> getProperties(Integer departmentId, Date dateFrom, Date dateTo, 
			Integer propertyNo, String propertyName, String propertyKana, 
			Integer registrationOfficerCode, String registrationOfficerName, Integer branchCode, 
			String branchName, String owner, Integer index) {
		
		return dao.getProperties(departmentId, dateFrom, dateTo, 
				propertyNo, propertyName, propertyKana, 
				registrationOfficerCode, registrationOfficerName, 
				branchCode, branchName, owner, index);
	}

	@Override
	public Optional<Property> getPropertyByNo(Integer propertyNo) {
		return dao.getPropertyByNo(propertyNo);
	}

	@Override
	public ByteArrayInputStream exportToPDF(Integer from, Integer to) throws Exception {
		List<Property> entities = dao.getPropertiesByNo(from, to);
		
		String rangeContent = PropertyConstants.PROPERTY_NO + "[" + from + "] - [" + to +"]";

		List<PropertyPDFExtractionModel> models = tranformPDFData(entities);
		PDFListTable<PropertyPDFExtractionModel> table = new PDFListTable<>();
		table.setTitle(PropertyConstants.PAGE_NAME);
		table.setRangeContent(rangeContent);
		table.setHeaders(headerListPDF);
		table.setFields(fieldListPDF);
		table.setColumnWidths(new float[] {4, 10, 10, 10, 5, 5});
		table.setDataSource(models);
		table.setKlazz(PropertyPDFExtractionModel.class);

		return (new PDFListGenerator().generate(table));
	}

	@Override
	public String exportToCSV(Integer from, Integer to) throws Exception {
		List<Property> entities = dao.getPropertiesByNo(from, to);

		List<PropertyCSVExtractionModel> models = tranformCSVData(entities);
		Table<PropertyCSVExtractionModel> table = new Table<>();
		table.setHeaders(headerListCSV);
		table.setFields(fieldListCSV);
		table.setDataSource(models);
		table.setKlazz(PropertyCSVExtractionModel.class);

		return (new CSVGenerator().generate(table));
	}
	
	private List<PropertyPDFExtractionModel> tranformPDFData(List<Property> properties) {
		List<PropertyPDFExtractionModel> result = new ArrayList<>();
		for(Property property : properties) {

			Integer propertyNo = property.getPropertyNo();
			String propertyKana = property.getPropertyKana();
			String propertyName = property.getPropertyName();
			String propertyNameAndKana = propertyKana + "\n" + propertyName;
			Short userCode = property.getRegistrationOfficer().getCode();
			String username = property.getRegistrationOfficer().getUsername();
			Integer branchCode = property.getBranch().getCode();
			String branchName = property.getBranch().getName();
			
			String userAndBranch = userCode + " " + username;
			userAndBranch += "\n";
			userAndBranch += branchCode + " " + branchName;
			
			String owner = property.getOwner();
			
			Date dateCreated = property.getDateCreated();
			String dateCreatedTxt = "";
			if(dateCreated != null) {
				dateCreatedTxt = 
						new SimpleDateFormat(DATE_FORMAT).format(dateCreated);
			}

			Date lastUpdated = property.getDateUpdated();
			String lastUpdatedTxt = "";
			if(lastUpdated != null) {
				lastUpdatedTxt = 
					new SimpleDateFormat(DATE_FORMAT).format(lastUpdated);
			}
			
			result.add(new PropertyPDFExtractionModel(
					propertyNo, propertyNameAndKana, userAndBranch,	
					owner, dateCreatedTxt, lastUpdatedTxt));
		}
		return result;
	}
	
	private List<PropertyCSVExtractionModel> tranformCSVData(List<Property> properties) {
		List<PropertyCSVExtractionModel> result = new ArrayList<>();
		for(Property property : properties) {

			Integer propertyNo = property.getPropertyNo();
			String propertyKana = property.getPropertyKana();
			String propertyName = property.getPropertyName();
			Short userCode = property.getRegistrationOfficer().getCode();
			String username = property.getRegistrationOfficer().getUsername();
			Integer branchCode = property.getBranch().getCode();
			String branchName = property.getBranch().getName();
			String owner = property.getOwner();
			
			Date dateCreated = property.getDateCreated();
			String dateCreatedTxt = "";
			if(dateCreated != null) {
				dateCreatedTxt = 
						new SimpleDateFormat(DATE_FORMAT).format(dateCreated);
			}

			Date lastUpdated = property.getDateUpdated();
			String lastUpdatedTxt = "";
			if(lastUpdated != null) {
				lastUpdatedTxt = 
					new SimpleDateFormat(DATE_FORMAT).format(lastUpdated);
			}
			
			result.add(new PropertyCSVExtractionModel(
					propertyNo, propertyKana, propertyName, owner, 
					userCode, username, branchCode, branchName, 
					dateCreatedTxt, lastUpdatedTxt));
		}
		return result;
	}

}