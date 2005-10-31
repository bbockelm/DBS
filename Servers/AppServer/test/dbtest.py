
import justdbs
w = justdbs.ResultSet()
dsn = justdbs.ResultSet.str("anzar")
uname = justdbs.ResultSet.str("cms_dbs_afaq")
p = justdbs.ResultSet.str("Me1tabOlia6s")
client = justdbs.DBManagement(dsn,uname,p)
client.open()
rs = client.executeQueryWithResults()
print rs.getNoOfRows()
client.close()
