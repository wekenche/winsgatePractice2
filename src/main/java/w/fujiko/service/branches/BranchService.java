/**
 * 
 */
package w.fujiko.service.branches;

import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.branches.Branch;

/**
 * @author Try-Parser
 *
 */
public interface BranchService extends UniversalCrud<Branch,Integer> {
	public Optional<Branch> getUndeletedBranchByCode(Integer code);
	Optional<Branch> getByCode(String code,Optional<Boolean> isEnd);
	Optional<Branch> getBranchOfUser(Short userCode,Boolean isEnd);
}