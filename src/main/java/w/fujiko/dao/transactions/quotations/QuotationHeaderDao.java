package w.fujiko.dao.transactions.quotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import fte.api.Page;
import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.quotations.QuotationHeader;

public interface QuotationHeaderDao extends UniversalCrud<QuotationHeader, Integer> {    
    Optional<QuotationHeader> getBySlipNumber(String slipNumber);
    Long getCountByWorkingSiteId(Integer id);
    void deleteById(Integer quotationHeaderId);
    List<QuotationHeader> getByConstructionNumber(String constructionNumber);
    List<QuotationHeader> getConstructionList(Date quotationDateFrom,
                                              Date quotationDateTo,
                                              Integer userId,
                                              Integer customerBaseId,
                                              Optional<Integer> propertyId,
                                              Optional<List<Integer>> quotationRank,
                                              Optional<String> constructionName);

    Page<QuotationHeader> quotationHeaderSearch(final Optional<Integer> workingSiteNumberFrom,
                                        final Optional<Integer> workingSiteNumberTo,
                                        final Optional<Integer> constructionNumberFrom,
                                        final Optional<Integer> constructionNumberTo,
                                        final Optional<Integer> constructionCategoryFrom,
                                        final Optional<Integer> constructionCategoryTo,
                                        final Optional<Integer> customerBaseCodeFrom,
                                        final Optional<Integer> customerBaseCodeTo,											
                                        final Optional<Short> userCodeFrom,
                                        final Optional<Short> userCodeTo,
                                        final Optional<Integer> departmentCodeFrom,
                                        final Optional<Integer> departmentCodeTo,
                                        final Optional<Date> quotationDateFrom,
                                        final Optional<Date> quotationDateTo,
                                        final Optional<Date> deliveryDateFrom,
                                        final Optional<Date> deliveryDateTo,
                                        final Optional<Date> createdDateFrom,
                                        final Optional<Date> createdDateTo,
                                        final Optional<Double> totalAmountFrom,
                                        final Optional<Double> totalAmountTo,	
                                        final Optional<Double> grossMarginRatioFrom,
                                        final Optional<Double> grossMarginRatioTo,										
                                        final Optional<List<Integer>> quotationRank,
                                        final Optional<List<Byte>> orderStatus,
                                        final Optional<List<Byte>> purchaseStatus,
                                        final Optional<List<Byte>> salesStatus,
                                        final Optional<List<Byte>> billingStatus,
                                        final Optional<List<Byte>> paymentStatus,
                                        int first,
                                        int max);
}