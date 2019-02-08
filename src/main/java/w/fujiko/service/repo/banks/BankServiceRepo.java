package w.fujiko.service.repo.banks;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.banks.BankDao;
import w.fujiko.model.masters.banks.Bank;
import w.fujiko.model.masters.banks.BankBranch;
import w.fujiko.model.masters.banks.BankExtractionModel;
import w.fujiko.service.banks.BankService;
import w.fujiko.util.common.constants.BankConstants;
import w.fujiko.util.common.generator.CSVGenerator;
import w.fujiko.util.common.generator.PDFListGenerator;
import w.fujiko.util.common.generator.PDFListTable;
import w.fujiko.util.common.generator.Table;

@Service
@Transactional
public class BankServiceRepo extends UniversalServiceRepo<Bank, BankDao, Integer> 
	implements BankService {
	
	private static final String[] headerList = {
			BankConstants.BANK_CODE,
			BankConstants.BANK_KANA,
			BankConstants.BANK_NAME,
			BankConstants.BANK_BRANCH_CODE,
			BankConstants.BANK_BRANCH_KANA,
			BankConstants.BANK_BRANCH_NAME,
			BankConstants.DATE_CREATED,
			BankConstants.LAST_UPDATED};
	
	private static final String[] fieldList = {
			BankConstants.FIELD_BANK_CODE,
			BankConstants.FIELD_BANK_KANA,
			BankConstants.FIELD_BANK_NAME,
			BankConstants.FIELD_BRANCH_CODE,
			BankConstants.FIELD_BRANCH_KANA,
			BankConstants.FIELD_BRANCH_NAME,
			BankConstants.FIELD_DATE_CREATED,
			BankConstants.FIELD_LAST_UPDATED };

	@Override
	public List<Bank> getBanksByNameOrCode(String key, Integer index) {
		return dao.getBanksByNameOrCode(key, index);
	}

	@Override
	public Optional<Bank> getUndeletedBankByCode(Integer code) {
		return dao.getUndeletedBankByCode(code);
	}

	@Override
	public ByteArrayInputStream exportToPDF(Integer from, Integer to) throws Exception {
		// 
		
		List<Bank> entities = dao.getBanksByCode(from, to);
		
		String rangeContent = BankConstants.SHORT_PAGE_NAME + "[" + from + "] - [" + to +"]";

		List<BankExtractionModel> models = tranformData(entities, true);
		PDFListTable<BankExtractionModel> table = new PDFListTable<>();
		table.setTitle(BankConstants.LONG_PAGE_NAME);
		table.setRangeContent(rangeContent);
		table.setHeaders(headerList);
		table.setFields(fieldList);
		table.setColumnWidths(new float[] {3, 4, 6, 3, 4, 6, 4, 4});
		table.setDataSource(models);
		table.setKlazz(BankExtractionModel.class);

		return (new PDFListGenerator().generate(table));
	}

	@Override
	public String exportToCSV(Integer from, Integer to) throws Exception {
		List<Bank> entities = dao.getBanksByCode(from, to);

		List<BankExtractionModel> models = tranformData(entities, false);
		Table<BankExtractionModel> table = new Table<>();
		table.setHeaders(headerList);
		table.setFields(fieldList);
		table.setDataSource(models);
		table.setKlazz(BankExtractionModel.class);

		return (new CSVGenerator().generate(table));
	}
	
	private List<BankExtractionModel> tranformData(List<Bank> banks, boolean formatCode) {
		List<BankExtractionModel> result = new ArrayList<>();
		for(Bank bank : banks) {
			
			String bankCode = bank.getBankCode().toString();
			if(formatCode)
				bankCode = String.format("%04d", Integer.parseInt(bankCode));
			
			String bankKana = bank.getBankKana();
			String bankName = bank.getBankName();
			
			List<BankBranch> branches = bank.getBranches();
			if(branches != null && branches.size() > 0) {
				for(BankBranch branch : branches) {
					String bankBranchCode = branch.getBankBranchCode().toString();
					if(formatCode)
						bankBranchCode = String.format("%03d", Integer.parseInt(bankBranchCode));
					
					String bankBranchKana = branch.getBankBranchKana();
					String bankBranchName = branch.getBankBranchName();
					
					String dateCreated = new SimpleDateFormat(BankConstants.DATE_FORMAT)
							.format(branch.getDateCreated());
					
					String lastUpdated = new SimpleDateFormat(BankConstants.DATE_FORMAT)
							.format(branch.getDateUpdated());
							
					result.add(new BankExtractionModel(bankCode, bankKana, 
							bankName, bankBranchCode, bankBranchKana, 
							bankBranchName, dateCreated, lastUpdated));
				}
			} else {
				result.add(new BankExtractionModel(bankCode, bankKana, 
						bankName, null, "", "", "", ""));
			}
		}
		return result;
	}

}