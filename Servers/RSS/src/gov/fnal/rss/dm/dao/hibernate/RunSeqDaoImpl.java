package gov.fnal.rss.dm.dao.hibernate;

import java.util.List;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;

import gov.fnal.rss.dm.dao.RunSeqDao;
import gov.fnal.rss.dm.entity.RunSeq;

public class RunSeqDaoImpl extends HibernateDaoSupport implements RunSeqDao {
	public RunSeqDaoImpl() {
		super();
	}
	

	public RunSeq getRunSeq(final String name) {
		List toReturn =  getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createCriteria(RunSeq.class)
					.add(Restrictions.eq("name", name))
					.list();
		     	}
		});
		if (toReturn.size() > 0) return (RunSeq)toReturn.get(0);
		return null;		
	}
	
	public void updateRunSeq(RunSeq runSeq) {
		this.getHibernateTemplate().update(runSeq);
	}
	public RunSeq saveRunSeq(RunSeq runSeq) {
		this.getHibernateTemplate().save(runSeq);
		return runSeq;
	}
	      
}

