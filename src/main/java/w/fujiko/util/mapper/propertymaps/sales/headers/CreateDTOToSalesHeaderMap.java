package w.fujiko.util.mapper.propertymaps.sales.headers;

import org.modelmapper.PropertyMap;

import w.fujiko.dto.sales.headers.SalesHeaderCreateDTO;
import w.fujiko.model.transactions.sales.SalesHeader;

public class CreateDTOToSalesHeaderMap extends PropertyMap<SalesHeaderCreateDTO, SalesHeader> {

	@Override
	protected void configure() {
		skip(destination.getId());
		map().setSlipNumber(source.getSlipNumber());
		map().setVersion(source.getVersion());
		map().getQuotationHeader().setId(source.getQuotationHeaderId());
        map().setSalesDate(source.getSalesDate());
		map().setBillingDate(source.getBillingDate());
        map().getCustomerBase().setId(source.getCustomerBaseId());
        map().setCustomerName(source.getCustomerName());
        map().getDestination().setId(source.getDestinationId());
        map().setConstructionNumber(source.getConstructionNumber());
        map().setConstructionName(source.getConstructionName());
        map().setSalesType(source.getSalesType());
        map().setConstructionContractorFlag(source.getConstructionContractorFlag());
        map().setSlipType(source.getSlipType());
        map().setConsumptionTaxType(source.getConsumptionTaxType());
        map().getUser().setId(source.getUserId());
        map().getDepartment().setId(source.getDepartmentId());
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
        map().getCreatedBy().setId(source.getCreatedById());
		map().getCreatedAt().setId(source.getCreatedAtId());
	}

}