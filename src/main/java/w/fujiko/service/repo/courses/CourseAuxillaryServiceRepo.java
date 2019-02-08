package w.fujiko.service.repo.courses;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.courses.CourseAuxillaryDao;
import w.fujiko.model.masters.courses.CourseAuxillary;
import w.fujiko.service.courses.CourseAuxillaryService;

@Service
@Transactional
public class CourseAuxillaryServiceRepo 
	extends UniversalServiceRepo<CourseAuxillary, CourseAuxillaryDao, Integer> 
	implements CourseAuxillaryService {

	@Override
	public List<CourseAuxillary> getCourseAuxillariesByNameOrCode(Integer courseId, String key, Integer index) {
		return dao.getCourseAuxillariesByNameOrCode(courseId, key, index);
	}

	@Override
	public Optional<CourseAuxillary> getUndeletedCourseAuxillaryByCode(Integer courseId, Integer code) {
		return dao.getUndeletedCourseAuxillaryByCode(courseId, code);
	}

}
