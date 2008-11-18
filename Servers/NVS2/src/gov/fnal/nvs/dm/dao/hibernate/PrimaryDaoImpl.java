package gov.fnal.nvs.dm.dao.hibernate;

import java.util.List;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;

import gov.fnal.nvs.dm.dao.PrimaryDao;
import gov.fnal.nvs.dm.entity.Primarydataset;

public class PrimaryDaoImpl extends HibernateDaoSupport implements PrimaryDao {
	public PrimaryDaoImpl() {
		super();
	}

	public List<Primarydataset> listPrimaryDatasets() {
		/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Primarydataset> result = (List<Primarydataset>)session.createCriteria(Primarydataset.class).list();
		session.getTransaction().commit();
		return result;*/
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			 public Object doInHibernate(Session session) throws HibernateException, SQLException {
				 return session.createCriteria(Primarydataset.class).list();
			 }
		});
		

		
	}

	public List<Primarydataset> listPrimaryDatasets(final String name) {
		/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Primarydataset> result = (List<Primarydataset>)session.createCriteria(Primarydataset.class)
		.add(Restrictions.like("name", name))
			.list();
		session.getTransaction().commit();
		return result;*/
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			 public Object doInHibernate(Session session) throws HibernateException, SQLException {
				 return session.createCriteria(Primarydataset.class)
					.add(Restrictions.like("name", name)).
					list();
			 }
		});

	}
}

