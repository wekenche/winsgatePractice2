package w.fujiko.dao.infos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.infos.CustomerCompanyFile;

public interface CustomerCompanyFileDao extends UniversalCrud<CustomerCompanyFile,Integer> {	     
     public Optional<CustomerCompanyFile> findByDocumentDate(Date documentDate);
     public List<CustomerCompanyFile> findByCustomerGroupId(Integer customerGroupId);
     public Boolean isUniqueDocumentDate(Date documentDate,int id);
}