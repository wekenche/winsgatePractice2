package w.fujiko.service.banks;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.banks.Bank;

@Service
public interface BankService extends UniversalCrud<Bank, Integer> {
	public List<Bank> getBanksByNameOrCode(String key, Integer index);
	public Optional<Bank> getUndeletedBankByCode(Integer code);
	public ByteArrayInputStream exportToPDF(Integer from, Integer to) throws Exception;
	public String exportToCSV(Integer from, Integer to) throws Exception;
}
