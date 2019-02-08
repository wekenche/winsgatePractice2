package w.fujiko.service.repo.companyaccounts;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.companyaccounts.CompanyAccountDao;
import w.fujiko.model.masters.banks.Bank;
import w.fujiko.model.masters.banks.BankBranch;
import w.fujiko.model.masters.companyaccounts.CompanyAccount;
import w.fujiko.model.masters.companyaccounts.CompanyAccountCSVExtractionModel;
import w.fujiko.model.masters.companyaccounts.CompanyAccountPDFExtractionModel;
import w.fujiko.service.companyaccounts.CompanyAccountService;
import w.fujiko.util.common.constants.CompanyAccountConstants;
import w.fujiko.util.common.generator.CSVGenerator;
import w.fujiko.util.common.generator.PDFListGenerator;
import w.fujiko.util.common.generator.PDFListTable;
import w.fujiko.util.common.generator.Table;

@Service
@Transactional
public class CompanyAccountServiceRepo 
	extends UniversalServiceRepo<CompanyAccount, CompanyAccountDao, Integer> 
	implements CompanyAccountService {

	@Override
	public ByteArrayInputStream exportToPDF(Integer from, Integer to) throws Exception {
		List<CompanyAccount> entities = dao.getCompanyAccountsRange(from, to);
		
		String rangeContent = CompanyAccountConstants.SHORT_PAGE_NAME + "[" + from + "] - [" + to +"]";
		
		List<CompanyAccountPDFExtractionModel> models = tranformPDFData(entities);
		PDFListTable<CompanyAccountPDFExtractionModel> table = new PDFListTable<>();
		table.setTitle(CompanyAccountConstants.LONG_PAGE_NAME);
		table.setRangeContent(rangeContent);
		table.setHeaders(CompanyAccountConstants.PDF_HEADER_LIST);
		table.setFields(CompanyAccountConstants.PDF_FIELD_LIST);
		table.setDataSource(models);
		table.setKlazz(CompanyAccountPDFExtractionModel.class);

		return (new PDFListGenerator().generate(table));
	}

	@Override
	public String exportToCSV(Integer from, Integer to) throws Exception {
		List<CompanyAccount> entities = dao.getCompanyAccountsRange(from, to);
		
		List<CompanyAccountCSVExtractionModel> models = tranformCSVData(entities);
		Table<CompanyAccountCSVExtractionModel> table = new Table<>();
		table.setHeaders(CompanyAccountConstants.CSV_HEADER_LIST);
		table.setFields(CompanyAccountConstants.CSV_FIELD_LIST);
		table.setDataSource(models);
		table.setKlazz(CompanyAccountCSVExtractionModel.class);

		return (new CSVGenerator().generate(table));
	}
	
	private List<CompanyAccountPDFExtractionModel> tranformPDFData(List<CompanyAccount> companyAccounts) {
		List<CompanyAccountPDFExtractionModel> result = new ArrayList<>();
		for(CompanyAccount companyAccount : companyAccounts) {
			Integer companyAccountNo = companyAccount.getId();
			BankBranch bankBranch = companyAccount.getBankBranch();
			Bank bank = bankBranch.getBank();
			
			Integer bankCode = bank.getBankCode();
			String bankCodeTxt = String.format("%04d", bankCode);
			
			String bankName = bank.getBankName();
			String bankTxt = bankCodeTxt + " " + bankName;
			
			Integer branchCode = bankBranch.getBankBranchCode();
			String branchCodeTxt = String.format("%03d", branchCode);
			
			String branchName = bankBranch.getBankBranchName();
			String branchTxt = branchCodeTxt + " " + branchName;
			String depositType = getDepositType(companyAccount.getDepositType());
			
			Integer accountNo = companyAccount.getAccountNumber();
			String accountNoTxt = String.format("%08d", accountNo);
			
			Long companyNo = companyAccount.getCompanyNumber();
			String companyNoTxt = String.format("%010d", companyNo);
			
			String companyNameKana = companyAccount.getCompanyNameKana();
			
			String dateCreatedTxt = "";
			Date dateCreatedVal = companyAccount.getDateCreated();
			if(dateCreatedVal != null) {
				dateCreatedTxt = new SimpleDateFormat(CompanyAccountConstants.DATE_FORMAT).format(dateCreatedVal);
			}
			
			String lastUpdatedTxt = "";
			Date lastUpdatedVal = companyAccount.getDateUpdated();
			if(lastUpdatedVal != null) {
				lastUpdatedTxt = new SimpleDateFormat(CompanyAccountConstants.DATE_FORMAT).format(lastUpdatedVal);
			}
			
			result.add(new CompanyAccountPDFExtractionModel(companyAccountNo,
					bankTxt, branchTxt, depositType, accountNoTxt, 
					companyNoTxt, companyNameKana, dateCreatedTxt, lastUpdatedTxt));
		}
		return result;
	}

	private List<CompanyAccountCSVExtractionModel> tranformCSVData(List<CompanyAccount> companyAccounts) {
		List<CompanyAccountCSVExtractionModel> result = new ArrayList<>();
		for(CompanyAccount companyAccount : companyAccounts) {
			Integer companyAccountNo = companyAccount.getId();
			BankBranch bankBranch = companyAccount.getBankBranch();
			Bank bank = bankBranch.getBank();
			Integer bankCode = bank.getBankCode();
			String bankName = bank.getBankName();
			Integer branchCode = bankBranch.getBankBranchCode();
			String branchName = bankBranch.getBankBranchName();
			String depositType = getDepositType(companyAccount.getDepositType());
			Integer accountNo = companyAccount.getAccountNumber();
			Long companyNo = companyAccount.getCompanyNumber();
			String companyNameKana = companyAccount.getCompanyNameKana();
			
			String dateCreatedTxt = "";
			Date dateCreatedVal = companyAccount.getDateCreated();
			if(dateCreatedVal != null) {
				dateCreatedTxt = new SimpleDateFormat(CompanyAccountConstants.DATE_FORMAT).format(dateCreatedVal);
			}
			
			String lastUpdatedTxt = "";
			Date lastUpdatedVal = companyAccount.getDateUpdated();
			if(lastUpdatedVal != null) {
				lastUpdatedTxt = new SimpleDateFormat(CompanyAccountConstants.DATE_FORMAT).format(lastUpdatedVal);
			}
			
			result.add(new CompanyAccountCSVExtractionModel(companyAccountNo,
					bankCode, bankName, branchCode, 
					branchName, depositType, accountNo, 
					companyNo, companyNameKana, dateCreatedTxt, lastUpdatedTxt));
		}
		return result;
	}
	
	public String getDepositType(Short depositType) {
		String result = "";
		switch(depositType.shortValue()) {
			case 1: result = CompanyAccountConstants.USUAL;
				break;
			case 2: result = CompanyAccountConstants.CURRENT;
				break;
			case 9: result = CompanyAccountConstants.OTHER;
				break;
		}
		return result;
	}
	
}