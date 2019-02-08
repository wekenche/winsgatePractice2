package w.fujiko.dao.defectiveproduct;

import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.defectiveproduct.DefectiveProduct;

public interface DefectiveProductDao extends UniversalCrud<DefectiveProduct, Integer>{
	public List<DefectiveProduct> getAllDefectiveProduct();
	public DefectiveProduct getDefectiveProductById(Integer prodId);

}
