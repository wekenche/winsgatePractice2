package w.fujiko.service.banks;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.banks.BankBranch;

@Service
public interface BankBranchService extends UniversalCrud<BankBranch, Integer> {
	public List<BankBranch> getBranchesByBankId(Integer bankId, Integer index);
	public List<BankBranch> getBranchesByNameOrCode(Integer bankId, String key, Integer index);
	public Optional<BankBranch> getUndeletedBranchByCode(Integer bankId, Integer code);
}
