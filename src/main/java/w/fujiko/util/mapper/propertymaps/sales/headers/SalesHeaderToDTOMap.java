package w.fujiko.util.mapper.propertymaps.sales.headers;

import org.modelmapper.PropertyMap;

import w.fujiko.dto.sales.headers.SalesHeaderDTO;
import w.fujiko.model.transactions.sales.SalesHeader;

public class SalesHeaderToDTOMap extends PropertyMap<SalesHeader, SalesHeaderDTO> {

	@Override
	protected void configure() {
		map().setId(source.getId());
		map().setSlipNumber(source.getSlipNumber());
		map().setVersion(source.getVersion());
		map().setQuotationHeaderId(source.getQuotationHeader().getId());
        map().setSalesDate(source.getSalesDate());
		map().setBillingDate(source.getBillingDate());
        map().getCustomerBase().setId(source.getCustomerBase().getId());
        map().getCustomerBase().setBillingId(source.getCustomerBase().getBill().getId());
        map().getCustomerBase().setBranchId(source.getCustomerBase().getBranch().getId());
        map().getCustomerBase().setCode(source.getCustomerBase().getCode());
        map().getCustomerBase().setName(source.getCustomerBase().getName());
        map().setCustomerName(source.getCustomerName());
        map().getDestination().setId(source.getDestination().getId());
        map().setConstructionNumber(source.getConstructionNumber());
        map().setConstructionName(source.getConstructionName());
        map().setSalesType(source.getSalesType());
        map().setConstructionContractorFlag(source.getConstructionContractorFlag());
        map().setSlipType(source.getSlipType());
        map().setConsumptionTaxType(source.getConsumptionTaxType());
        map().getUser().setId(source.getUser().getId());
        map().getUser().setCode(source.getUser().getCode());
        map().getUser().setUsername(source.getUser().getUsername());
        map().getDepartment().setId(source.getDepartment().getId());
        map().setRemarks(source.getRemarks());
        map().setSalesTaxRate(source.getSalesTaxRate());
        map().setPriceList(source.getPriceList());
		map().setPriceRatio(source.getPriceRatio());
        map().setSales(source.getSales());
        map().setTotalReturn(source.getTotalReturn());
        map().setTotalDiscount(source.getTotalDiscount());
        map().setTotalSales(source.getTotalSales());
        map().setTotalTaxIncluded(source.getTotalTaxIncluded());
        map().setTotalTaxConsumption(source.getTotalTaxConsumption());
        map().setTotalGrossProfit(source.getTotalGrossProfit());
        map().setDateCreated(source.getDateCreated());
        map().setCreatedById(source.getCreatedBy().getId());
        map().setCreatedAtId(source.getCreatedAt().getId());
	}

}