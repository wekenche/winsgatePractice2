package w.fujiko.util.mapper.propertymaps.branches;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import w.fujiko.dto.branches.BranchUpdateDTO;
import w.fujiko.model.masters.branches.Branch;

/**
 * Maps BranchUpdateDTO's property to properties of Branch
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: BranchUpdateDTO
 * Destination: Branch
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
@Service
public class UpdateDTOToBranchEntityMap  extends PropertyMap<BranchUpdateDTO, Branch> {

	@Override
	protected void configure() {
        skip(destination.getCode());
        map().setName(source.getName());
        map().setNameKana(source.getNameKana());
        map().setShortName(source.getShortName());
        map().setSalesFlag(source.getSalesFlag());
        map().setSequence(source.getSequence());
        map().setPostalCode(source.getPostalCode());
        map().setAddress1(source.getAddress1());
        map().setAddress2(source.getAddress2());
        map().setTelephoneNumber(source.getTelephoneNumber());
        map().setFaxNumber(source.getFaxNumber());
        map().getWarehouse().setId(source.getWarehouseId());
        map().setReceiptSequenceNumber(source.getReceiptSequenceNumber());
        map().setReceiptSymbol(source.getReceiptSymbol());
        map().setIsIndependent(source.getIsIndependent());
        map().setIsEnd(source.getIsEnd());
	}

}