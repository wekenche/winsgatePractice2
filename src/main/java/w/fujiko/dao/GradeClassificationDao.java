package w.fujiko.dao;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.GradeClassification;

public interface GradeClassificationDao extends UniversalCrud<GradeClassification, Short> {
	
	public Optional<GradeClassification> getUndeletedItemByCode(short code,int makerId);
    public List<GradeClassification> getByMakerId(int makerId);
}