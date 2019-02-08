package w.fujiko.service.suppliers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.suppliers.SupplierBase;

@Service
public interface SupplierBaseService extends UniversalCrud<SupplierBase, Integer> {
	public Optional<SupplierBase> getUndeletedSupplierBaseByCode(Integer groupId, String code);
	public List<SupplierBase> getByUserId(Integer userId);
	Optional<SupplierBase> getByCode(String code, Optional<String> groupCode, Optional<Boolean> isEnd);
}