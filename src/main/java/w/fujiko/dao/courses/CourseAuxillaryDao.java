package w.fujiko.dao.courses;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.courses.CourseAuxillary;

public interface CourseAuxillaryDao extends UniversalCrud<CourseAuxillary, Integer> {
	
	public List<CourseAuxillary> 
		getCourseAuxillariesByNameOrCode(Integer courseId, String key, Integer index);
	
	public Optional<CourseAuxillary> getUndeletedCourseAuxillaryByCode(Integer courseId, Integer code);

}
