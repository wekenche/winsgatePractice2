package w.fujiko.service.repo.courses;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.courses.CourseDao;
import w.fujiko.model.masters.courses.Course;
import w.fujiko.service.courses.CourseService;

@Service
@Transactional
public class CourseServiceRepo 
	extends UniversalServiceRepo<Course, CourseDao, Integer> 
	implements CourseService {

	@Override
	public List<Course> getCoursesByNameOrCode(String key, Integer index) {
		return dao.getCoursesByNameOrCode(key, index);
	}

	@Override
	public Optional<Course> getUndeletedCourseByCode(Integer code) {
		return dao.getUndeletedCourseByCode(code);
	}

}
