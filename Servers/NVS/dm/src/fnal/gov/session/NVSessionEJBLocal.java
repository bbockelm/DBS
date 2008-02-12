package fnal.gov.session;

import fnal.gov.entity.Datatier;
import fnal.gov.entity.Physicsgroup;
import fnal.gov.entity.Primarydataset;
import fnal.gov.entity.Processeddataset;

import java.util.List;

import javax.ejb.Local;

@Local
public interface NVSessionEJBLocal {
    Object mergeEntity(Object entity);

    Object persistEntity(Object entity);

    List<Datatier> queryDatatierFindAll();

    List<Physicsgroup> queryPhysicsgroupFindAll();

    List<Primarydataset> queryPrimarydatasetFindAll();

    List<Processeddataset> queryProcesseddatasetFindAll();

    List<NameObject> validate(String name, String type) throws Exception;
}
