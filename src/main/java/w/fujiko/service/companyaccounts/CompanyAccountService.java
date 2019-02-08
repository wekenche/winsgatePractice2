package w.fujiko.service.companyaccounts;

import java.io.ByteArrayInputStream;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.companyaccounts.CompanyAccount;

@Service
public interface CompanyAccountService 
	extends UniversalCrud<CompanyAccount, Integer> {

	public ByteArrayInputStream exportToPDF(Integer from, Integer to) throws Exception;
	public String exportToCSV(Integer from, Integer to) throws Exception;
	
}