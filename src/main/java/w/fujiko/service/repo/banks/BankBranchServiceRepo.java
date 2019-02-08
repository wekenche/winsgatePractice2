package w.fujiko.service.repo.banks;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.banks.BankBranchDao;
import w.fujiko.model.masters.banks.BankBranch;
import w.fujiko.service.banks.BankBranchService;

@Service
@Transactional
public class BankBranchServiceRepo 
	extends UniversalServiceRepo<BankBranch, BankBranchDao, Integer> 
	implements BankBranchService {

	@Override
	public List<BankBranch> getBranchesByBankId(Integer bankId, Integer index) {
		return dao.getBranchesByBankId(bankId, index);
	}

	@Override
	public List<BankBranch> getBranchesByNameOrCode(Integer bankId, String key, Integer index) {
		return dao.getBranchesByNameOrCode(bankId, key, index);
	}

	@Override
	public Optional<BankBranch> getUndeletedBranchByCode(Integer bankId, Integer code) {
		return dao.getUndeletedBranchByCode(bankId, code);
	}

}