package w.fujiko.service;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.GradeClassification;

@Service
public interface GradeClassificationService extends UniversalCrud<GradeClassification, Short> {
    public Optional<GradeClassification> getUndeletedItemByCode(short code,int makerId);
    public List<GradeClassification> getByMakerId(int makerId);
}
