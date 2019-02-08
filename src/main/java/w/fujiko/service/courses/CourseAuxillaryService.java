package w.fujiko.service.courses;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.courses.CourseAuxillary;

@Service
public interface CourseAuxillaryService extends UniversalCrud<CourseAuxillary, Integer> {
	
	public List<CourseAuxillary> 
		getCourseAuxillariesByNameOrCode(Integer courseId, String key, Integer index);

	public Optional<CourseAuxillary> getUndeletedCourseAuxillaryByCode(Integer courseId, Integer code);

}
