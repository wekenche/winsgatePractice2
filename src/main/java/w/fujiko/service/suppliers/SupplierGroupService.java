package w.fujiko.service.suppliers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.suppliers.SupplierGroup;

@Service
public interface SupplierGroupService extends UniversalCrud<SupplierGroup, Integer> {
	public Optional<SupplierGroup> getUndeletedSupplierGroupByCode(String code);
	public List<SupplierGroup> getByUserId(Integer userId);
}