package w.fujiko.dao.companyaccounts;

import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.companyaccounts.CompanyAccount;

public interface CompanyAccountDao extends UniversalCrud<CompanyAccount, Integer> {

	public List<CompanyAccount> getCompanyAccountsRange(Integer from, Integer to);
	
}