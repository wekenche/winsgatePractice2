/**
 * 
 */
package w.fujiko.service.repo.branches;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.branches.BranchDao;
import w.fujiko.model.masters.branches.Branch;
import w.fujiko.service.branches.BranchService;

/**
 * @author Try-Parser
 *
 */
@Service
@Transactional
public class BranchServiceRepo 
		extends UniversalServiceRepo<Branch, BranchDao, Integer> 
		implements BranchService {

	@Override
	public Optional<Branch> getUndeletedBranchByCode(Integer code) {
		return dao.getUndeletedBranchByCode(code);
	}

	@Override
	public Optional<Branch> getByCode(String code, Optional<Boolean> isEnd) {
		return dao.getByCode(code, isEnd);
	}

	@Override
	public Optional<Branch> getBranchOfUser(Short userCode, Boolean isEnd) {
		return dao.getBranchOfUser(userCode,isEnd);
	}

}