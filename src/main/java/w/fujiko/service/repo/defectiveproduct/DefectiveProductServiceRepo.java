package w.fujiko.service.repo.defectiveproduct;

import java.util.List;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.defectiveproduct.DefectiveProductDao;
import w.fujiko.model.masters.defectiveproduct.DefectiveProduct;
import w.fujiko.service.defectiveproduct.DefectiveProductService;

@Service
public class DefectiveProductServiceRepo extends UniversalServiceRepo<DefectiveProduct,DefectiveProductDao,Integer> implements DefectiveProductService {

	@Override
	public List<DefectiveProduct> getAllDefectiveProduct() {
		// TODO Auto-generated method stub
		return dao.getAllDefectiveProduct();
	}

	@Override
	public DefectiveProduct getDefectiveProductById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefectiveProduct save() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefectiveProduct deleteById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DefectiveProduct> getByProductId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DefectiveProduct> getByUserCode() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
