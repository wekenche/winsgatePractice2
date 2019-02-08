package w.fujiko.dao.courses;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.courses.Course;

public interface CourseDao extends UniversalCrud<Course, Integer> {

	public List<Course> getCoursesByNameOrCode(String key, Integer index);
	
	public Optional<Course> getUndeletedCourseByCode(Integer code);
	
}
