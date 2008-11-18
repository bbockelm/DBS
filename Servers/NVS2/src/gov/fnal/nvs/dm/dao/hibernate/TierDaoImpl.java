package gov.fnal.nvs.dm.dao.hibernate;

import java.util.List;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;

import gov.fnal.nvs.dm.dao.TierDao;
import gov.fnal.nvs.dm.entity.Datatier;

public class TierDaoImpl extends HibernateDaoSupport implements TierDao {
	public TierDaoImpl() {
		super();
	}

	public List<Datatier> listDataTiers() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			 public Object doInHibernate(Session session) throws HibernateException, SQLException {
				 return session.createCriteria(Datatier.class).list();
			 }
		});
	}
}

