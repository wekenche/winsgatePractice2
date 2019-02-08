package w.fujiko.dto.quotations.headers.histories;

import w.fujiko.dto.quotations.headers.QuotationHeaderDTO;

public class QuotationHeaderHistoryDTO extends QuotationHeaderDTO {

    private static final long serialVersionUID = 1L;

    public boolean isHistory = true;

    public boolean getIsHistory() {
        return this.isHistory;
    }

}