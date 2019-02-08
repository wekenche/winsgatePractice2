package w.fujiko.service.transactions.sales;

import java.util.List;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;

import w.fujiko.model.transactions.sales.SalesDetail;
import w.fujiko.model.transactions.sales.SalesDetailPk;

@Service
public interface SalesDetailService extends UniversalCrud<SalesDetail, SalesDetailPk> {
	public List<SalesDetail> getBySalesHeaderId(Integer SalesHeaderId);
}