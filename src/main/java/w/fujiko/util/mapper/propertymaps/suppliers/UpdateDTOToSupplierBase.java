package w.fujiko.util.mapper.propertymaps.suppliers;

import org.modelmapper.PropertyMap;

import w.fujiko.dto.suppliers.SupplierBaseUpdateDTO;
import w.fujiko.model.masters.suppliers.SupplierBase;

public class UpdateDTOToSupplierBase extends PropertyMap<SupplierBaseUpdateDTO, SupplierBase> {

	@Override
	protected void configure() {
		skip(destination.getCode());
	}

}