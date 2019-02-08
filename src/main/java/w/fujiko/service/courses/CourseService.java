package w.fujiko.service.courses;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.courses.Course;

@Service
public interface CourseService extends UniversalCrud<Course, Integer> {
	
	public List<Course> getCoursesByNameOrCode(String key, Integer index);
	
	public Optional<Course> getUndeletedCourseByCode(Integer code);

}
