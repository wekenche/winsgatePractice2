package w.fujiko.dao.transactions.sales;

import java.util.List;

import fte.api.universal.UniversalCrud;

import w.fujiko.model.transactions.sales.SalesDetail;
import w.fujiko.model.transactions.sales.SalesDetailPk;

public interface SalesDetailDao extends UniversalCrud<SalesDetail, SalesDetailPk> {
	public List<SalesDetail> getBySalesHeaderId(Integer salesHeaderId);
	void deleteBySalesHeaderId(Integer salesHeaderId);
}