package fnal.gov.session;

import fnal.gov.entity.Datatier;
import fnal.gov.entity.Physicsgroup;
import fnal.gov.entity.Primarydataset;
import fnal.gov.entity.Processeddataset;

import fnal.gov.util.LetterPairSimilarity;

import java.util.ArrayList;
import java.util.List;

import java.util.ListIterator;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.regex.Pattern;

@Stateless(name="NVSessionEJB")
public class NVSessionEJBBean implements NVSessionEJBLocal {
    @PersistenceContext(unitName="dm")
    private EntityManager em;
    private static String SAFE_WORD = "[-\\w_\\.%]+";
    
    public NVSessionEJBBean() {
    }

    public Object mergeEntity(Object entity) {
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    /** <code>select o from Datatier o</code> */
    public List<Datatier> queryDatatierFindAll() {
        return em.createNamedQuery("Datatier.findAll").getResultList();
    }

    /** <code>select o from Physicsgroup o</code> */
    public List<Physicsgroup> queryPhysicsgroupFindAll() {
        return em.createNamedQuery("Physicsgroup.findAll").getResultList();
    }

    /** <code>select o from Primarydataset o</code> */
    public List<Primarydataset> queryPrimarydatasetFindAll() {
        return em.createNamedQuery("Primarydataset.findAll").getResultList();
    }

    /** <code>select o from Processeddataset o</code> */
    public List<Processeddataset> queryProcesseddatasetFindAll() {
        return em.createNamedQuery("Processeddataset.findAll").getResultList();
    }

    public List<NameObject> validate(String name, String type) throws Exception {
        ArrayList toReturn = new ArrayList();
        if(name == null || type == null) throw new Exception("Name or Type cannot be NULL");
        if (!Pattern.matches(SAFE_WORD, name)) throw new Exception("Name has invalid characters");
        
        
        double threshold = 0.4;
        if (type.equals("Primary")) {
            List<Primarydataset> results = queryPrimarydatasetFindAll();
            ListIterator li = results.listIterator();
            double similarity = 0;
            for (int i = 0 ; i != results.size(); ++i) {
                String tmpName = ((Primarydataset)li.next()).getName();
                if ((similarity = LetterPairSimilarity.compareStrings(tmpName, name)) > threshold)  {
                    toReturn.add(makeNO(tmpName, similarity));
                }
            }
        }
        
        if (type.equals("Processed")) {
            List<Processeddataset> results = queryProcesseddatasetFindAll();
            ListIterator li = results.listIterator();
            double similarity = 0;
            for (int i = 0 ; i != results.size(); ++i) {
                String tmpName = ((Processeddataset)li.next()).getName();
                if ((similarity = LetterPairSimilarity.compareStrings(tmpName, name)) > threshold)  {
                    toReturn.add(makeNO(tmpName, similarity));
                }
            }
        }
        
        if (type.equals("Physics")) {
            List<Physicsgroup> results = queryPhysicsgroupFindAll();
            ListIterator li = results.listIterator();
            double similarity = 0;
            for (int i = 0 ; i != results.size(); ++i) {
                String tmpName = ((Physicsgroup)li.next()).getPhysicsgroupname();
                if ((similarity = LetterPairSimilarity.compareStrings(tmpName, name)) > threshold)  {
                    toReturn.add(makeNO(tmpName, similarity));
                }
            }
        }
        
        if (type.equals("Tier")) {
            List<Datatier> results = queryDatatierFindAll();
            ListIterator li = results.listIterator();
            double similarity = 0;
            for (int i = 0 ; i != results.size(); ++i) {
                String tmpName = ((Datatier)li.next()).getName();
                if ((similarity = LetterPairSimilarity.compareStrings(tmpName, name)) > threshold)  {
                    toReturn.add(makeNO(tmpName, similarity));
                }
            }
        }
        
        return toReturn;
    }
    
    private NameObject makeNO(String name, double similarity) {
        NameObject no = new NameObject();
        no.setName(name);
        no.setSimilar(new Double(similarity * 100));
        return no;
    }
}
