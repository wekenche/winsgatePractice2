package w.fujiko.service.repo.products;

import static w.fujiko.util.common.constants.ProductMasterConstants.BALLAST_FLAG;
import static w.fujiko.util.common.constants.ProductMasterConstants.BALLAST_NAME;
import static w.fujiko.util.common.constants.ProductMasterConstants.CHECKERS;
import static w.fujiko.util.common.constants.ProductMasterConstants.COMPLETE_PRODUCT;
import static w.fujiko.util.common.constants.ProductMasterConstants.DATE_FORMAT;
import static w.fujiko.util.common.constants.ProductMasterConstants.GEN_CLASS_CODE_1;
import static w.fujiko.util.common.constants.ProductMasterConstants.GEN_CLASS_CODE_2;
import static w.fujiko.util.common.constants.ProductMasterConstants.GEN_CLASS_CODE_3;
import static w.fujiko.util.common.constants.ProductMasterConstants.GEN_CLASS_CODE_4;
import static w.fujiko.util.common.constants.ProductMasterConstants.GEN_CLASS_CODE_5;
import static w.fujiko.util.common.constants.ProductMasterConstants.HEADERS;
import static w.fujiko.util.common.constants.ProductMasterConstants.INSERT;
import static w.fujiko.util.common.constants.ProductMasterConstants.INSERTED_ROWS;
import static w.fujiko.util.common.constants.ProductMasterConstants.INSTRUMENT_BODY;
import static w.fujiko.util.common.constants.ProductMasterConstants.INVENTORY_UNIT_PRICE_DATE;
import static w.fujiko.util.common.constants.ProductMasterConstants.INVENTORY_UNIT_PRICE_NO_TAX;
import static w.fujiko.util.common.constants.ProductMasterConstants.KANA;
import static w.fujiko.util.common.constants.ProductMasterConstants.LAMP_FLAG;
import static w.fujiko.util.common.constants.ProductMasterConstants.LAMP_NAME;
import static w.fujiko.util.common.constants.ProductMasterConstants.LAST_UNIT_COST_PER_UNIT_DATE;
import static w.fujiko.util.common.constants.ProductMasterConstants.LAST_UNIT_COST_PER_UNIT_PRICE;
import static w.fujiko.util.common.constants.ProductMasterConstants.LIST_PRICE_DATE;
import static w.fujiko.util.common.constants.ProductMasterConstants.LIST_PRICE_NO_TAX;
import static w.fujiko.util.common.constants.ProductMasterConstants.MAKER_NOT_EXISTING;
import static w.fujiko.util.common.constants.ProductMasterConstants.MODEL_NUMBER;
import static w.fujiko.util.common.constants.ProductMasterConstants.NOTES;
import static w.fujiko.util.common.constants.ProductMasterConstants.NOT_EXISTING;
import static w.fujiko.util.common.constants.ProductMasterConstants.NOT_SUBJECT_TO_SLIP_PRINTING;
import static w.fujiko.util.common.constants.ProductMasterConstants.PRODUCT_CATEGORY1;
import static w.fujiko.util.common.constants.ProductMasterConstants.PRODUCT_CATEGORY2;
import static w.fujiko.util.common.constants.ProductMasterConstants.PRODUCT_CATEGORY3;
import static w.fujiko.util.common.constants.ProductMasterConstants.PRODUCT_CATEGORY4;
import static w.fujiko.util.common.constants.ProductMasterConstants.PRODUCT_CATEGORY5;
import static w.fujiko.util.common.constants.ProductMasterConstants.PRODUCT_CLASS_CODE;
import static w.fujiko.util.common.constants.ProductMasterConstants.PRODUCT_CLASS_ITEM_CODE;
import static w.fujiko.util.common.constants.ProductMasterConstants.PRODUCT_NAME;
import static w.fujiko.util.common.constants.ProductMasterConstants.PROGRAM_ID;
import static w.fujiko.util.common.constants.ProductMasterConstants.PURCHASING_COST_DATE;
import static w.fujiko.util.common.constants.ProductMasterConstants.PURCHASING_COST_NO_TAX;
import static w.fujiko.util.common.constants.ProductMasterConstants.RATE_CLASS_CODE;
import static w.fujiko.util.common.constants.ProductMasterConstants.SPECIAL_ORDER_CLASS;
import static w.fujiko.util.common.constants.ProductMasterConstants.TRANS_FLAG;
import static w.fujiko.util.common.constants.ProductMasterConstants.TRANS_NAME;
import static w.fujiko.util.common.constants.ProductMasterConstants.UNIT_CODE;
import static w.fujiko.util.common.constants.ProductMasterConstants.UPDATE;
import static w.fujiko.util.common.constants.ProductMasterConstants.UPDATED_ROWS;
import static w.fujiko.util.common.constants.ProductMasterConstants.WARRANTY_OBJ_CLASS;
import static w.fujiko.util.common.constants.ProductMasterConstants.CSV_ERROR_HEADER;
import static w.fujiko.util.common.constants.ProductMasterConstants.CSV_ERROR_FIELDS;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fte.api.Page;
import fte.api.Response;
import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.MakerDao;
import w.fujiko.dao.RatioClassificationDao;
import w.fujiko.dao.UnitDao;
import w.fujiko.dao.generalpurposes.items.GeneralPurposeItemDao;
import w.fujiko.dao.products.ProductClassificationDao;
import w.fujiko.dao.products.ProductClassificationItemDao;
import w.fujiko.dao.products.ProductDao;
import w.fujiko.exceptions.MakerCodeNotFoundException;
import w.fujiko.model.masters.Maker;
import w.fujiko.model.masters.RatioClassification;
import w.fujiko.model.masters.Unit;
import w.fujiko.model.masters.generalpurposes.GeneralPurposeItem;
import w.fujiko.model.masters.products.CSVUploadErrorResponse;
import w.fujiko.model.masters.products.CSVUploadSuccessResponse;
import w.fujiko.model.masters.products.Product;
import w.fujiko.model.masters.products.ProductCSVExtractionErrorModel;
import w.fujiko.model.masters.products.ProductClassification;
import w.fujiko.model.masters.products.ProductClassificationItem;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;
import w.fujiko.service.products.ProductService;
import w.fujiko.util.common.csvfileuploadextractor.CSVExtractionError;
import w.fujiko.util.common.csvfileuploadextractor.CSVFileDataExtractor;
import w.fujiko.util.common.filehandler.CSVErrorFileHandler;
import w.fujiko.util.common.generator.CSVGenerator;
import w.fujiko.util.common.generator.Table;
import w.fujiko.util.common.sourcepath.ProductCSVErrorDirectorySourcePath;

@Service
@Transactional
public class ProductServiceRepo extends UniversalServiceRepo<Product, ProductDao, Integer> 
	implements ProductService {
	
	@Autowired 
	private MakerDao makerDao;
	
	@Autowired 
	private ProductClassificationDao productClassDao;
	
	@Autowired 
	private ProductClassificationItemDao productClassItemDao;
	
	@Autowired 
	private RatioClassificationDao ratioClassDao;
	
	@Autowired 
	private UnitDao unitDao;
	
	@Autowired 
	private GeneralPurposeItemDao generalPurposeItemDao;
	
	@Autowired
	private CSVErrorFileHandler fileHandler;
	
	@Override
	public List<Product> getProductsByModelNumberOrName(String key, Integer index) {
		return dao.getProductsByModelNumberOrName(key, index);
	}
	
	@Override
	public Optional<Product> getUndeletedItemByModelNumber(String modelNumber, int makerId) {
		return dao.getUndeletedItemByModelNumber(modelNumber, makerId);
	}
	
	@Override
	public Optional<Product> getByModelNumber(String modelNumber) {
		return dao.getByModelNumber(modelNumber);
	}

	@Override
	public void save(Product product) {
		Product lastProduct = dao.getLastItem();
		String lastProductCode = "0";
		if(lastProduct != null) {
			lastProductCode = lastProduct.getCode();
		}
		
		int newCodeNum = Integer.parseInt(lastProductCode) + 1;
		String formattedCode = String.format("%07d", newCodeNum);
		product.setCode(formattedCode);
		dao.saveOrUpdate(product);
	}

	@Override
	public List<CSVExtractionError<Integer,String,String>> getErrors(MultipartFile file) throws Exception {
		CSVFileDataExtractor extractor = new CSVFileDataExtractor();
		return extractor.getErrors(file, HEADERS, CHECKERS);		
	}

	@Override
	public Response saveCSVData(MultipartFile file, Integer makerCode, Integer userId) throws Exception,MakerCodeNotFoundException {
		CSVFileDataExtractor extractor = new CSVFileDataExtractor();
		var errors = new ArrayList<CSVExtractionError<Integer,String,String>>();
		Map<String, Integer> dataSummaryCount = new HashMap<>();
		List<Map<String, String>> maps = extractor.extractCSVFileToMap(file, HEADERS);
		List<Product> products = preSave(maps, errors, dataSummaryCount, makerCode, userId);
		if(errors.size() == 0) {
			dao.batchSave(products);
			List<String> info = new ArrayList<>();
			info.add(String.format(INSERTED_ROWS, dataSummaryCount.get(INSERT)));
			info.add(String.format(UPDATED_ROWS, dataSummaryCount.get(UPDATE)));
			return new CSVUploadSuccessResponse(info);
		} else {
			return new CSVUploadErrorResponse<List<CSVExtractionError<Integer,String,String>>>(errors);
		}
	}

	@Override
	public List<Product> getByMakerId(Integer makerId) {
		return dao.getByMakerId(makerId);
	}
	
	@Override
	public List<Product> getByMakerId(Integer makerId, Boolean isEnd) {
		return dao.getByMakerId(makerId, isEnd);
	}

	@Override
	public List<Product> getByMakerIdWithModelNumberSearch(Integer makerId,String modelNumber) {
		return dao.getByMakerIdWithModelNumberSearch(makerId, modelNumber);
	}

	@Override
	public Page<Product> getByMakerWithModelNumberSearch(Integer makerId, String modelNumber, int first, int max, Optional<Boolean> isEnd) {
		return dao.getByMakerWithModelNumberSearch(makerId,modelNumber,first,max,isEnd);
	}

	@Override
	public Page<Product> paginate(int first, int max, Optional<Boolean> flagValue, Optional<String> modelNumber) {
		return dao.paginate(first, max, flagValue, modelNumber);
	}

	@Override
	public Page<Product> search(Optional<Boolean> specialOrderClassificationFlag, Optional<Boolean> abolishedNumberFlag,
			Optional<Boolean> isEnd, Optional<String> makerName, Optional<Integer> makerCode,
			Optional<String> modelNumber, int first, int max) {
		return dao.search(specialOrderClassificationFlag, abolishedNumberFlag, isEnd, makerName, makerCode, modelNumber, first, max);
	}
	
	@Override
	public Page<Product> getExceptByMakerName(List<String> makerNames,String modelNumber,Optional<Boolean> isEnd,int first,int max) {
		return dao.getExceptByMakerName(makerNames,modelNumber,isEnd,first,max);
	}
	/*
	 * 
	 * Below methods are the helper logic.
	 * They are CSV Upload related stuff. 
	 * 
	 */
	private List<Product> preSave(List<Map<String, String>> maps, List<CSVExtractionError<Integer,String,String>> er, 
			Map<String, Integer> dataSummaryCount, Integer makerCode, Integer userId)throws MakerCodeNotFoundException {
		
		List<Product> result = new ArrayList<>();
		
		int toBeInsertedCount = 0;
		int toBeUpdatedCount = 0;
		
		Maker maker = null;
		int makerId = -1;
		Optional<Maker> mkr = makerDao.getUndeletedItemByCode(makerCode);
		if(!mkr.isPresent()) {
			throw new MakerCodeNotFoundException(String.format(MAKER_NOT_EXISTING, makerCode));
		} else {
			maker = mkr.get();
			makerId = maker.getId();
			
			Product lastProduct = dao.getLastItem();
			String lastProductCode = "0";
			if(lastProduct != null) {
				lastProductCode = lastProduct.getCode();
			}
			
			List<Product> productResults = getByMakerId(makerId, false); 
			
			int size = maps.size();
			for(int i = 0; i < size; i++) {
				Map<String, String> map = maps.get(i);
				
				Product product = null;
				Optional<Product> productResult = this.findProduct(map.get(MODEL_NUMBER), productResults);
				
				User user = new User();
				user.setId(userId);
				
				Program prog = new Program();
				prog.setId(PROGRAM_ID);
				
				if(productResult.isPresent()) {
					toBeUpdatedCount++;
					product = productResult.get();
					product.setUpdatedBy(user);
					product.setUpdatedAt(prog);
				} else {
					toBeInsertedCount++;
					product = new Product();
					
					int newCodeNum = Integer.parseInt(lastProductCode) + toBeInsertedCount;
					String formattedCode = String.format("%07d", newCodeNum);
					product.setCode(formattedCode);
					product.setAbolishedNumberFLG(false);
					product.setIsEnd(false);
					product.setCreatedBy(user);
					product.setCreatedAt(prog);
				}
				
				product.setMaker(maker);
				product = setProperties((i + 2), product, map, makerId, er);
				result.add(product);
				
			}
			
			dataSummaryCount.put(INSERT, toBeInsertedCount);
			dataSummaryCount.put(UPDATE, toBeUpdatedCount);
		}
		
		return result;
	}
	
	private Product setProperties(int rowNum, Product product, Map<String, String> map, int makerId, List<CSVExtractionError<Integer,String,String>> er) {
		product.setModelNumber(map.get(MODEL_NUMBER));
		product.setName(map.get(PRODUCT_NAME));
		product.setKana(map.get(KANA));
		product.setInstrumentBody(map.get(INSTRUMENT_BODY));
		
		product.setLampFLG(parseInt(map.get(LAMP_FLAG)));
		product.setLampName(map.get(LAMP_NAME));
		product.setBallastFLG(parseInt(map.get(BALLAST_FLAG)));
		product.setBallastName(map.get(BALLAST_NAME));
		product.setTransformerFLG(parseInt(map.get(TRANS_FLAG)));
		product.setTransformerName(map.get(TRANS_NAME));
		product.setSpecialOrderClassification(getBooleanValue(map.get(SPECIAL_ORDER_CLASS)));
		product.setWarrantyObjectClassification(getBooleanValue(map.get(WARRANTY_OBJ_CLASS)));
		product.setCompleteProduct(getBooleanValue(map.get(COMPLETE_PRODUCT)));
		product.setNotSubjectToSlipPrinting(getBooleanValue(map.get(NOT_SUBJECT_TO_SLIP_PRINTING)));
		
		product.setPurchasingCostDate(getDateValue(map.get(PURCHASING_COST_DATE), DATE_FORMAT));
		product.setPurchasingCostNoTax(parseDouble(map.get(PURCHASING_COST_NO_TAX)));
		product.setInventoryUnitPriceDate(getDateValue(map.get(INVENTORY_UNIT_PRICE_DATE), DATE_FORMAT));
		product.setInventoryUnitPriceNoTax(parseDouble(map.get(INVENTORY_UNIT_PRICE_NO_TAX)));
		product.setLastUnitCostPerUnitDate(getDateValue(map.get(LAST_UNIT_COST_PER_UNIT_DATE), DATE_FORMAT));
		product.setLastUnitCostPerUnitPrice(parseDouble(map.get(LAST_UNIT_COST_PER_UNIT_PRICE)));
		product.setListPriceDate(getDateValue(map.get(LIST_PRICE_DATE), DATE_FORMAT));
		product.setListPriceNoTax(parseDouble(map.get(LIST_PRICE_NO_TAX)));
		
		product.setNotes(map.get(NOTES));
		product = setForeignKeys(rowNum, product, map, makerId, er);
		
		return product;
	}
	
	private Product setForeignKeys(int rowNum, Product product, Map<String, String> map, int makerId, List<CSVExtractionError<Integer,String,String>> er) {

		String newProdClassCodeValue = map.get(PRODUCT_CLASS_CODE);
		ProductClassification prodClass = product.getProductClassification();
		
		if(newProdClassCodeValue != null &&  newProdClassCodeValue.length() != 0) {
			if(prodClass != null) {
				String originalCodeValue = prodClass.getCode().toString();
				if(!originalCodeValue.equals(newProdClassCodeValue)) {
					Optional<ProductClassification> result = 
							productClassDao.getUndeletedProductClassificationByCode(Integer.parseInt(newProdClassCodeValue));
					if(!result.isPresent()) {
						var errorMessage = String.format(NOT_EXISTING,newProdClassCodeValue);
						var extractionError = new CSVExtractionError<Integer,String,String>
												  (rowNum,PRODUCT_CLASS_CODE,errorMessage);
						er.add(extractionError);
					} else {
						product.setProductClassification(result.get());
					}
				}
			} else {
				Optional<ProductClassification> result = 
						productClassDao.getUndeletedProductClassificationByCode(Integer.parseInt(newProdClassCodeValue));
				if(!result.isPresent()) {
					var errorMessage = String.format(NOT_EXISTING,newProdClassCodeValue);
					var extractionError = new CSVExtractionError<Integer,String,String>
											  (rowNum,PRODUCT_CLASS_CODE,errorMessage);
					er.add(extractionError);				
				} else {
					product.setProductClassification(result.get());
				}
			}
		} else {
			product.setProductClassification(null);
		}
		
		int prodClassId = 0;
		if(product.getProductClassification() != null) {
			prodClassId = product.getProductClassification().getId();
		}
		
		String newProdClassItemCodeValue = map.get(PRODUCT_CLASS_ITEM_CODE);
		if(newProdClassItemCodeValue != null &&  newProdClassItemCodeValue.length() != 0) {
			ProductClassificationItem prodClassItem = product.getProductClassificationItem();
			if(prodClassItem != null) {
				String originalCodeValue = prodClassItem.getCode().toString();
				if(!originalCodeValue.equals(newProdClassItemCodeValue)) {
					Optional<ProductClassificationItem> result = 
							productClassItemDao.getUndeletedProductClassificationItemByCode
								(prodClassId, Integer.parseInt(newProdClassItemCodeValue));
					if(!result.isPresent()) {
						var errorMessage = String.format(NOT_EXISTING,newProdClassItemCodeValue);
						var extractionError = new CSVExtractionError<Integer,String,String>
												  (rowNum,PRODUCT_CLASS_ITEM_CODE,errorMessage);
						er.add(extractionError);						
					} else {
						product.setProductClassificationItem(result.get());
					}
				}
			} else {
				Optional<ProductClassificationItem> result = 
						productClassItemDao.getUndeletedProductClassificationItemByCode
							(prodClassId, Integer.parseInt(newProdClassItemCodeValue));
				if(!result.isPresent()) {
					var errorMessage = String.format(NOT_EXISTING,newProdClassItemCodeValue);
					var extractionError = new CSVExtractionError<Integer,String,String>
												(rowNum,PRODUCT_CLASS_ITEM_CODE,errorMessage);
					er.add(extractionError);				
				} else {
					product.setProductClassificationItem(result.get());
				}
			}
		} else {
			product.setProductClassificationItem(null);
		}
		
		String newRatioCodeValue = map.get(RATE_CLASS_CODE);
		if(newRatioCodeValue != null &&  newRatioCodeValue.length() != 0) {
			RatioClassification ratioClass = product.getRatioClassification();
			int newRatioCodeValueNum = Integer.parseInt(newRatioCodeValue);
			if(ratioClass != null) {
				int originalCodeValue = ratioClass.getCode();
				if(originalCodeValue != newRatioCodeValueNum) {
					Optional<RatioClassification> result = ratioClassDao.getUndeletedItemByCode(newRatioCodeValueNum, makerId);
					if(!result.isPresent()) {
						var errorMessage = String.format(NOT_EXISTING,newRatioCodeValue);
						var extractionError = new CSVExtractionError<Integer,String,String>
													(rowNum,RATE_CLASS_CODE,errorMessage);
						er.add(extractionError);					
					} else {
						product.setRatioClassification(result.get());
					}
				}
			} else {
				Optional<RatioClassification> result = ratioClassDao.getUndeletedItemByCode(newRatioCodeValueNum, makerId);
				if(!result.isPresent()) {
					var errorMessage = String.format(NOT_EXISTING,newRatioCodeValue);
					var extractionError = new CSVExtractionError<Integer,String,String>
												(rowNum,RATE_CLASS_CODE,errorMessage);
					er.add(extractionError);				
				} else {
					product.setRatioClassification(result.get());
				}
			}
		} else {
			product.setRatioClassification(null);
		}
		
		String newUnitCodeValue = map.get(UNIT_CODE);
		if(newUnitCodeValue != null &&  newUnitCodeValue.length() != 0) {
			Unit unit = product.getUnit();
			int newUnitCodeValueNum = Integer.parseInt(newUnitCodeValue);
			if(unit != null) {
				int originalCodeValue = unit.getCode();
				if(originalCodeValue != newUnitCodeValueNum) {
					Optional<Unit> result = unitDao.getUndeletedItemByCode(newUnitCodeValueNum);
					if(!result.isPresent()) {
						var errorMessage = String.format(NOT_EXISTING,newUnitCodeValue);
						var extractionError = new CSVExtractionError<Integer,String,String>
													(rowNum,UNIT_CODE,errorMessage);
						er.add(extractionError);					
					} else {
						product.setUnit(result.get());
					}
				}
			} else {
				Optional<Unit> result = unitDao.getUndeletedItemByCode(newUnitCodeValueNum);
				if(!result.isPresent()) {
					var errorMessage = String.format(NOT_EXISTING,newUnitCodeValue);
					var extractionError = new CSVExtractionError<Integer,String,String>
												(rowNum,UNIT_CODE,errorMessage);
					er.add(extractionError);					
				} else {
					product.setUnit(result.get());
				}
			}
		} else {
			product.setUnit(null);
		} 
		
		String newCatCodeValue1 = map.get(PRODUCT_CATEGORY1);
		if(newCatCodeValue1 != null &&  newCatCodeValue1.length() != 0) {
			GeneralPurposeItem cat1 = product.getGenPurpItem1();
			if(cat1 != null) {
				String originalCodeValue = cat1.getCode();
				if(!originalCodeValue.equals(newCatCodeValue1)) {
					Optional<GeneralPurposeItem> result = 
							generalPurposeItemDao.getUndeletedItemByCode(GEN_CLASS_CODE_1 , newCatCodeValue1);
					if(!result.isPresent()) {
						var errorMessage = String.format(NOT_EXISTING,newCatCodeValue1);
						var extractionError = new CSVExtractionError<Integer,String,String>
													(rowNum,PRODUCT_CATEGORY1,errorMessage);
						er.add(extractionError);					
					} else {
						product.setGenPurpItem1(result.get());
					}
				}
			} else {
				Optional<GeneralPurposeItem> result = 
						generalPurposeItemDao.getUndeletedItemByCode(GEN_CLASS_CODE_1 , newCatCodeValue1);
				if(!result.isPresent()) {
					var errorMessage = String.format(NOT_EXISTING,newCatCodeValue1);
					var extractionError = new CSVExtractionError<Integer,String,String>
												(rowNum,PRODUCT_CATEGORY1,errorMessage);
					er.add(extractionError);					
				} else {
					product.setGenPurpItem1(result.get());
				}
			}
		} else {
			product.setGenPurpItem1(null);
		}
		
		String newCatCodeValue2 = map.get(PRODUCT_CATEGORY2);
		if(newCatCodeValue2 != null &&  newCatCodeValue2.length() != 0) {
			GeneralPurposeItem cat2 = product.getGenPurpItem2();
			if(cat2 != null) {
				String originalCodeValue = cat2.getCode();
				if(!originalCodeValue.equals(newCatCodeValue2)) {
					Optional<GeneralPurposeItem> result = 
							generalPurposeItemDao.getUndeletedItemByCode(GEN_CLASS_CODE_2 , newCatCodeValue2);
					if(!result.isPresent()) {
						var errorMessage = String.format(NOT_EXISTING,newCatCodeValue2);
						var extractionError = new CSVExtractionError<Integer,String,String>
													(rowNum,PRODUCT_CATEGORY2,errorMessage);
						er.add(extractionError);					
					} else {
						product.setGenPurpItem2(result.get());
					}
				}
			} else {
				Optional<GeneralPurposeItem> result = 
						generalPurposeItemDao.getUndeletedItemByCode(GEN_CLASS_CODE_2 , newCatCodeValue2);
				if(!result.isPresent()) {
					var errorMessage = String.format(NOT_EXISTING,newCatCodeValue2);
					var extractionError = new CSVExtractionError<Integer,String,String>
												(rowNum,PRODUCT_CATEGORY2,errorMessage);
					er.add(extractionError);					
				} else {
					product.setGenPurpItem2(result.get());
				}
			}
		} else {
			product.setGenPurpItem2(null);
		}
		
		String newCatCodeValue3 = map.get(PRODUCT_CATEGORY3);
		if(newCatCodeValue3 != null &&  newCatCodeValue3.length() != 0) {
			GeneralPurposeItem cat3 = product.getGenPurpItem3();
			if(cat3 != null) {
				String originalCodeValue = cat3.getCode();
				if(!originalCodeValue.equals(newCatCodeValue3)) {
					Optional<GeneralPurposeItem> result = 
							generalPurposeItemDao.getUndeletedItemByCode(GEN_CLASS_CODE_3 , newCatCodeValue3);
					if(!result.isPresent()) {
						var errorMessage = String.format(NOT_EXISTING,newCatCodeValue3);
						var extractionError = new CSVExtractionError<Integer,String,String>
													(rowNum,PRODUCT_CATEGORY3,errorMessage);
						er.add(extractionError);
					} else {
						product.setGenPurpItem3(result.get());
					}
				}
			} else {
				Optional<GeneralPurposeItem> result = 
						generalPurposeItemDao.getUndeletedItemByCode(GEN_CLASS_CODE_3 , newCatCodeValue3);
				if(!result.isPresent()) {
					var errorMessage = String.format(NOT_EXISTING,newCatCodeValue3);
					var extractionError = new CSVExtractionError<Integer,String,String>
												(rowNum,PRODUCT_CATEGORY3,errorMessage);
					er.add(extractionError);					
				} else {
					product.setGenPurpItem3(result.get());
				}
			}
		} else {
			product.setGenPurpItem3(null);
		}
		
		String newCatCodeValue4 = map.get(PRODUCT_CATEGORY4);
		if(newCatCodeValue4 != null &&  newCatCodeValue4.length() != 0) {
			GeneralPurposeItem cat4 = product.getGenPurpItem4();
			if(cat4 != null) {
				String originalCodeValue = cat4.getCode();
				if(!originalCodeValue.equals(newCatCodeValue4)) {
					Optional<GeneralPurposeItem> result = 
							generalPurposeItemDao.getUndeletedItemByCode(GEN_CLASS_CODE_4 , newCatCodeValue4);
					if(!result.isPresent()) {
						var errorMessage = String.format(NOT_EXISTING,newCatCodeValue4);
						var extractionError = new CSVExtractionError<Integer,String,String>
													(rowNum,PRODUCT_CATEGORY4,errorMessage);
						er.add(extractionError);						
					} else {
						product.setGenPurpItem4(result.get());
					}
				}
			} else {
				Optional<GeneralPurposeItem> result = 
						generalPurposeItemDao.getUndeletedItemByCode(GEN_CLASS_CODE_4 , newCatCodeValue4);
				if(!result.isPresent()) {
					var errorMessage = String.format(NOT_EXISTING,newCatCodeValue4);
					var extractionError = new CSVExtractionError<Integer,String,String>
												(rowNum,PRODUCT_CATEGORY4,errorMessage);
					er.add(extractionError);					
				} else {
					product.setGenPurpItem4(result.get());
				}
			}
		} else {
			product.setGenPurpItem4(null);
		}
		
		String newCatCodeValue5 = map.get(PRODUCT_CATEGORY5);
		if(newCatCodeValue5 != null &&  newCatCodeValue5.length() != 0) {
			GeneralPurposeItem cat5 = product.getGenPurpItem5();
			if(cat5 != null) {
				String originalCodeValue = cat5.getCode();
				if(!originalCodeValue.equals(newCatCodeValue5)) {
					Optional<GeneralPurposeItem> result = 
							generalPurposeItemDao.getUndeletedItemByCode(GEN_CLASS_CODE_5 , newCatCodeValue5);
					if(!result.isPresent()) {
						var errorMessage = String.format(NOT_EXISTING,newCatCodeValue5);
						var extractionError = new CSVExtractionError<Integer,String,String>
													(rowNum,PRODUCT_CATEGORY5,errorMessage);
						er.add(extractionError);						
					} else {
						product.setGenPurpItem5(result.get());
					}
				}
			} else {
				Optional<GeneralPurposeItem> result = 
						generalPurposeItemDao.getUndeletedItemByCode(GEN_CLASS_CODE_5 , newCatCodeValue5);
				if(!result.isPresent()) {
					var errorMessage = String.format(NOT_EXISTING,newCatCodeValue5);
					var extractionError = new CSVExtractionError<Integer,String,String>
												(rowNum,PRODUCT_CATEGORY5,errorMessage);
					er.add(extractionError);				
				} else {
					product.setGenPurpItem5(result.get());
				}
			}
		} else {
			product.setGenPurpItem5(null);
		}

		return product;
	}
	
	private Optional<Product> findProduct(String modelNumber, List<Product> products) {
		Optional<Product> matchingObject = products.stream().
			filter(p -> {
				return p.getModelNumber().equals(modelNumber);
			}).
			findFirst();
		return matchingObject;
	}
	
	private Date getDateValue(String value, String dateFormat) {
		value = value.trim();
		if(value == null || value.length() == 0) {
			return null;
		} else {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT); 
			Date date = null;
			try {
				date = df.parse(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}
	}
	
	private boolean getBooleanValue(String value) {
		value = value.trim();
		if(value == null || value.length() == 0) {
			return false;
		} else {
			int intValue = Integer.parseInt(value);
			return intValue == 1 ? true : false;
		}
	}
	
	private Integer parseInt(String value) {
		value = value.trim();
		if(value == null || value.length() == 0) {
			return 0;
		} else {
			return Integer.parseInt(value);
		}
	}
	
	/*
		Workaround to parse special chars to double
		Example : From:  To: 200.0
	*/
	private Double parseDouble(String value) {
		value = value.trim();
		if(value == null || value.length() == 0) {
			return 0d;
		} else {
			return Double.parseDouble(convertToNormalNumber(value));
		}
	}
	
	private static String convertToNormalNumber(String str) {
		String result = "";
		int size = str.length();
		for(int i = 0; i < size; i++) {
			char c = str.charAt(i);
			if(Character.isDigit(c)) {
				int num = Character.getNumericValue(c);
				result += num;
			} else {
				result += c;
			}
		}
		return result;
	}

	@Override
	public String createCSVErrorFile(List<CSVExtractionError<Integer,String,String>> extractionError) throws Exception {
		List<ProductCSVExtractionErrorModel> models = tranformErrorDataToModel(extractionError);
		Table<ProductCSVExtractionErrorModel> table = new Table<>();
		table.setHeaders(CSV_ERROR_HEADER);
		table.setFields(CSV_ERROR_FIELDS);
		table.setDataSource(models);
		table.setKlazz(ProductCSVExtractionErrorModel.class);
		String fileName = this.getFileName();
		this.fileHandler.setDirectorySourcePath(new ProductCSVErrorDirectorySourcePath());
		this.fileHandler.store(new CSVGenerator().generate(table),fileName);
		return fileName;
	}

	@Override
	public InputStream getCSVErrorFile(String fileName) throws Exception {
		this.fileHandler.setDirectorySourcePath(new ProductCSVErrorDirectorySourcePath());
		return this.fileHandler.get(fileName);
	}

	private List<ProductCSVExtractionErrorModel> tranformErrorDataToModel(List<CSVExtractionError<Integer,String,String>> extractionErrors) {
		List<ProductCSVExtractionErrorModel> result = new ArrayList<>();
		for(var extractionError : extractionErrors) {
			
			int row = extractionError.getRow();
			String column = extractionError.getColumn();
			String error = extractionError.getError();
			
			result.add(new ProductCSVExtractionErrorModel(row, column,error));
		}
		return result;
	}

	private String getFileName(){
		final String BASE_NAME="csv_error";
		Date date = Calendar.getInstance().getTime();  
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	    String stringifyDate = dateFormat.format(date);
		stringifyDate=stringifyDate.replaceAll("\\s+","").replaceAll(":","").replaceAll("-","");
		
		return BASE_NAME
			  .concat("_")
			  .concat(stringifyDate)
			  .concat(".csv");
    }
}