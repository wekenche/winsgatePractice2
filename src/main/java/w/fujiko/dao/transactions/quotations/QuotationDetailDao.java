package w.fujiko.dao.transactions.quotations;

import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.quotations.QuotationDetailPk;
import w.fujiko.model.transactions.quotations.QuotationDetail;

public interface QuotationDetailDao extends UniversalCrud<QuotationDetail, QuotationDetailPk> {
    List<QuotationDetail> getByQuotationHeaderId(Integer quotationHeaderId);
    void deleteByQuotationHeaderId(Integer quotationHeaderId);
}
