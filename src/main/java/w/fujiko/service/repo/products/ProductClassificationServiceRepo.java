package w.fujiko.service.repo.products;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.products.ProductClassificationDao;
import w.fujiko.model.masters.products.ProductClassification;
import w.fujiko.model.masters.products.ProductClassificationItem;
import w.fujiko.model.masters.products.ProductExtractionModel;
import w.fujiko.service.products.ProductClassificationService;
import w.fujiko.util.common.constants.ProductConstants;
import w.fujiko.util.common.generator.CSVGenerator;
import w.fujiko.util.common.generator.PDFListGenerator;
import w.fujiko.util.common.generator.PDFListTable;
import w.fujiko.util.common.generator.Table;

@Service
@Transactional
public class ProductClassificationServiceRepo 
	extends UniversalServiceRepo<ProductClassification, ProductClassificationDao, Integer> 
	implements ProductClassificationService {
	
	private static final String[] headerList = {
			ProductConstants.PRODUCT_CLASS_CODE, 
			ProductConstants.PRODUCT_CLASS_NAME, 
			ProductConstants.PRODUCT_ITEM_CODE, 
			ProductConstants.PRODUCT_ITEM_NAME,
			ProductConstants.DATE_CREATED,
			ProductConstants.LAST_UPDATED
			};
	
	private static final String[] fieldList = {
			ProductConstants.FIELD_PRODUCT_CLASS_CODE, 
			ProductConstants.FIELD_PRODUCT_CLASS_NAME,
			ProductConstants.FIELD_PRODUCT_ITEM_CODE, 
			ProductConstants.FIELD_PRODUCT_ITEM_NAME,
			ProductConstants.FIELD_DATE_CREATED,
			ProductConstants.FIELD_LAST_UPDATED
			};


	@Override
	public List<ProductClassification> getProductClassificationsByItemNameOrCode(String key, Integer index) {
		return dao.getProductClassificationsByNameOrCode(key, index);
	}
	
	@Override
	public Optional<ProductClassification> getUndeletedProductClassificationByCode(Integer code) {
		return dao.getUndeletedProductClassificationByCode(code);
	}
	
	@Override
	public ByteArrayInputStream exportToPDF(Integer from, Integer to) throws Exception{
		List<ProductClassification> entities = 
				dao.getProductClassificationsByCode(from, to);
		
		String rangeContent = ProductConstants.SHORT_PAGE_NAME + "[" + from + "] - [" + to +"]";

		List<ProductExtractionModel> models = tranformData(entities);
		PDFListTable<ProductExtractionModel> table = new PDFListTable<>();
		table.setTitle(ProductConstants.LONG_PAGE_NAME);
		table.setRangeContent(rangeContent);
		table.setHeaders(headerList);
		table.setFields(fieldList);
		table.setColumnWidths(new float[] {3, 5, 3, 8, 3, 3});
		table.setDataSource(models);
		table.setKlazz(ProductExtractionModel.class);

		return (new PDFListGenerator().generate(table));
	}
	
	@Override
	public String exportToCSV(Integer from, Integer to) throws Exception {
		List<ProductClassification> entities = 
				dao.getProductClassificationsByCode(from, to);

		List<ProductExtractionModel> models = tranformData(entities);
		Table<ProductExtractionModel> table = new Table<>();
		table.setHeaders(headerList);
		table.setFields(fieldList);
		table.setDataSource(models);
		table.setKlazz(ProductExtractionModel.class);

		return (new CSVGenerator().generate(table));
	}
	
	private List<ProductExtractionModel> tranformData(List<ProductClassification> products) {
		List<ProductExtractionModel> result = new ArrayList<>();
		for(ProductClassification product : products) {
			
			Integer productCode = product.getCode();
			String productName = product.getName();
			
			List<ProductClassificationItem> items = product.getProductItems();
			if(items != null && items.size() > 0) {
				for(ProductClassificationItem item : items) {
					Integer itemCode = item.getCode();
					String itemName = item.getName();
					
					String dateCreated = new SimpleDateFormat(ProductConstants.DATE_FORMAT)
							.format(item.getDateCreated());
					
					String lastUpdated = new SimpleDateFormat(ProductConstants.DATE_FORMAT)
							.format(item.getDateUpdated());
					
					result.add(new ProductExtractionModel(productCode, productName, 
							itemCode, itemName, dateCreated, lastUpdated));
				}
			} else {
				result.add(new ProductExtractionModel(productCode, 
						productName, null, "", "", ""));
			}
		}
		return result;
	}
}