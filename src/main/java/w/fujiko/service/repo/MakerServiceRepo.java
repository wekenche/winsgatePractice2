package w.fujiko.service.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.MakerDao;
import w.fujiko.model.masters.Maker;
import w.fujiko.service.MakerService;

@Service
@Transactional
public class MakerServiceRepo extends UniversalServiceRepo<Maker, MakerDao,Integer> implements MakerService {

	@Override
	public Optional<Maker> getUndeletedItemByCode(Integer code) {
		return dao.getUndeletedItemByCode(code);
	}

	@Override
	public Optional<Maker> getByName(String name, Optional<Boolean> isEnd) {
		return dao.getByName(name, isEnd);
	}
	
}
