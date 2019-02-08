package w.fujiko.service.transactions.quotations;

import java.util.List;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.quotations.QuotationDetailPk;
import w.fujiko.model.transactions.quotations.QuotationDetail;

@Service
public interface QuotationDetailService extends UniversalCrud<QuotationDetail, QuotationDetailPk> {
    List<QuotationDetail> getByQuotationHeaderId(Integer quotationHeaderId);
    void deleteByQuotationHeaderId(Integer quotationHeaderId);
}
