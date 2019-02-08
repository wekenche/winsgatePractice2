package w.fujiko.service.defectiveproduct;


import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.defectiveproduct.DefectiveProduct;

import java.util.*;

public interface DefectiveProductService extends UniversalCrud<DefectiveProduct, Integer>{
	
	public List<DefectiveProduct> getAllDefectiveProduct();
	public DefectiveProduct getDefectiveProductById();
	public DefectiveProduct save();
	public DefectiveProduct deleteById();
	public List<DefectiveProduct> getByProductId();
	public List<DefectiveProduct> getByUserCode();
}
