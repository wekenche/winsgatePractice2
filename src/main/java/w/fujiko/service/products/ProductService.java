package w.fujiko.service.products;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fte.api.Page;
import fte.api.Response;
import fte.api.universal.UniversalCrud;
import w.fujiko.exceptions.MakerCodeNotFoundException;
import w.fujiko.model.masters.products.Product;
import w.fujiko.util.common.csvfileuploadextractor.CSVExtractionError;

@Service
public interface ProductService 
	extends UniversalCrud<Product, Integer> {
	
	public List<Product> getProductsByModelNumberOrName(String key, Integer index);
	public Optional<Product> getUndeletedItemByModelNumber(String modelNumber, int makerId);
	public void save(Product product);
	public  List<CSVExtractionError<Integer,String,String>> getErrors(MultipartFile file) throws Exception;
	public Response saveCSVData(MultipartFile file, Integer makerCode, Integer userId) throws Exception,MakerCodeNotFoundException;
	public String createCSVErrorFile(List<CSVExtractionError<Integer,String,String>> extractionError) throws Exception;
	public InputStream getCSVErrorFile(String fileName) throws Exception;
	public List<Product> getByMakerId(Integer makerId);
	public List<Product> getByMakerId(Integer makerId, Boolean isEnd);
	public List<Product> getByMakerIdWithModelNumberSearch(Integer makerId,String modelNumber);
	Page<Product> getByMakerWithModelNumberSearch(Integer makerId, String modelNumber, int first, int max, Optional<Boolean> isEnd);
	Page<Product> paginate(int first, int max , Optional<Boolean> flagValue , Optional<String> modelNumber);
	Optional<Product> getByModelNumber(String modelNumber);
	Page<Product> search(Optional<Boolean> specialOrderClassificationFlag,
							   Optional<Boolean> abolishedNumberFlag,
							   Optional<Boolean> isEnd,  
							   Optional<String> makerName,
							   Optional<Integer> makerCode,
							   Optional<String> modelNumber,
							   int first,
							   int max);
							   Page<Product> getExceptByMakerName(List<String> makerNames,String modelNumber,Optional<Boolean> isEnd,int first,int max);
}