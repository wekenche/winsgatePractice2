package w.fujiko.dao.banks;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.banks.Bank;

public interface BankDao extends UniversalCrud<Bank, Integer> {
	public List<Bank> getBanksByNameOrCode(String key, Integer index);
	public Optional<Bank> getUndeletedBankByCode(Integer code);
	public List<Bank> getBanksByCode(Integer from, Integer to);
}