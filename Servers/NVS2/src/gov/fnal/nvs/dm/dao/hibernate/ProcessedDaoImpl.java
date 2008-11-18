package gov.fnal.nvs.dm.dao.hibernate;

import java.util.List;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;

import gov.fnal.nvs.dm.dao.ProcessedDao;
import gov.fnal.nvs.dm.entity.Processeddataset;

public class ProcessedDaoImpl extends HibernateDaoSupport implements ProcessedDao {
	public ProcessedDaoImpl() {
		super();
	}

	public List<Processeddataset> listProcessedDatasets() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			 public Object doInHibernate(Session session) throws HibernateException, SQLException {
				 return session.createCriteria(Processeddataset.class).list();
			 }
		});
	}
}

