package w.fujiko.dao.products;

import java.util.List;
import java.util.Optional;

import fte.api.Page;
import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.products.Product;

public interface ProductDao extends UniversalCrud<Product, Integer> {
	
	public List<Product> getProductsByModelNumberOrName(String key, Integer index);
	public Optional<Product> getUndeletedItemByModelNumber(String modelNumber, int makerId);
	public Product getLastItem();
	public void batchSave(List<Product> products);
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