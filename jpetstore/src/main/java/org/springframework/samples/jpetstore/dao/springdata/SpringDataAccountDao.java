package org.springframework.samples.jpetstore.dao.springdata;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;
import org.springframework.samples.jpetstore.dao.AccountDao;
import org.springframework.samples.jpetstore.domain.Account;
import org.springframework.samples.jpetstore.generated.domain.QAccount;
import org.springframework.samples.jpetstore.generated.domain.QBannerdata;
import org.springframework.samples.jpetstore.generated.domain.QProfile;
import org.springframework.samples.jpetstore.generated.domain.QSignon;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class SpringDataAccountDao implements AccountDao {

	private final QAccount qAccount = QAccount.account;
	
	private final QProfile qProfile = QProfile.profile;
	
	private final QSignon qSignon = QSignon.signon;
	
	private final QBannerdata qBannerdata = QBannerdata.bannerdata;
	
	private QueryDslJdbcTemplate template;
	
	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}
	
	public Account getAccount(String username) throws DataAccessException {
		SQLQuery sqlQuery = template.newSqlQuery()
				.from(qAccount)
				.from(qProfile)
				.from(qSignon)
				.from(qBannerdata)
				.where(qAccount.userid.eq(username)
						.and(qSignon.username.eq(qAccount.userid))
						.and(qProfile.userid.eq(qAccount.userid))
						.and(qProfile.favcategory.eq(qBannerdata.favcategory)));
		
		return template.queryForObject(sqlQuery, 
				new MappingAccountProjection(qSignon.username, qAccount.email, qAccount.firstname, 
						qAccount.lastname, qAccount.status, qAccount.addr1, qAccount.addr2,
						qAccount.city, qAccount.state, qAccount.zip, qAccount.country,
						qAccount.phone, qProfile.langpref, qProfile.favcategory,
						qProfile.mylistopt,	qProfile.banneropt, qBannerdata.bannername));
	}

	public Account getAccount(final String username, final String password)
			throws DataAccessException {
	
		SQLQuery sqlQuery = template.newSqlQuery()
				.from(qAccount)
				.from(qProfile)
				.from(qSignon)
				.from(qBannerdata)
				.where(qAccount.userid.eq(username)
						.and(qSignon.password.eq(password))
						.and(qSignon.username.eq(qAccount.userid))
						.and(qProfile.userid.eq(qAccount.userid))
						.and(qProfile.favcategory.eq(qBannerdata.favcategory)));
		
		return template.queryForObject(sqlQuery, 
				new MappingAccountProjection(qSignon.username, qAccount.email, qAccount.firstname, 
						qAccount.lastname, qAccount.status, qAccount.addr1, qAccount.addr2,
						qAccount.city, qAccount.state, qAccount.zip, qAccount.country,
						qAccount.phone, qProfile.langpref, qProfile.favcategory,
						qProfile.mylistopt,	qProfile.banneropt, qBannerdata.bannername));
	}

	public void insertAccount(final Account account) throws DataAccessException {
		template.insert(qAccount, new SqlInsertCallback() {
			
			public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
				return sqlInsertClause.columns(qAccount.email, qAccount.firstname, qAccount.lastname,
						qAccount.status, qAccount.addr1, qAccount.addr2, qAccount.city, qAccount.state,
						qAccount.zip, qAccount.country, qAccount.phone, qAccount.userid)
						.values(account.getEmail(), account.getFirstName(), account.getLastName(),
								account.getStatus(), account.getAddress1(), account.getAddress2(),
								account.getCity(), account.getState(), account.getZip(), account.getCountry(),
								account.getPhone(), account.getUsername())
						.execute();
			}
		});
		
		template.insert(qProfile, new SqlInsertCallback() {
			
			public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
				return sqlInsertClause.columns(qProfile.langpref, qProfile.favcategory, qProfile.mylistopt, 
						qProfile.banneropt, qProfile.userid)
						.values(account.getLanguagePreference(), account.getFavouriteCategoryId(), 
								account.getListOptionAsInt(), account.getBannerOptionAsInt(), 
								account.getUsername())
						.execute();
			}
		});
		
		template.insert(qSignon, new SqlInsertCallback() {
			
			public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
				return sqlInsertClause.columns(qSignon.password,qSignon.username)
						.values(account.getPassword(), account.getUsername())
						.execute();
			}
		});
	}

	public void updateAccount(final Account account) throws DataAccessException {
		template.update(qAccount, new SqlUpdateCallback() {
			
			public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
				return sqlUpdateClause.where(qAccount.userid.eq(account.getUsername()))
						.set(qAccount.email, account.getEmail())
						.set(qAccount.firstname, account.getFirstName())
						.set(qAccount.lastname, account.getLastName())
						.set(qAccount.status, account.getStatus())
						.set(qAccount.addr1, account.getAddress1())
						.set(qAccount.addr2, account.getAddress2())
						.set(qAccount.city, account.getCity())
						.set(qAccount.state, account.getState())
						.set(qAccount.zip, account.getZip())
						.set(qAccount.country, account.getCountry())
						.set(qAccount.phone, account.getPhone())
						.execute();
			}
			
		});
		
		template.update(qProfile, new SqlUpdateCallback(){
			
			public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
				return sqlUpdateClause.where(qProfile.userid.eq(account.getUsername()))
						.set(qProfile.langpref, account.getLanguagePreference())
						.set(qProfile.favcategory, account.getFavouriteCategoryId())
						.set(qProfile.mylistopt, (short) account.getListOptionAsInt())
						.set(qProfile.banneropt, (short) account.getBannerOptionAsInt())
						.execute();
			}
			
		});
		
		if (account.getPassword() != null && account.getPassword().length() > 0) {
			template.update(qSignon, new SqlUpdateCallback(){
				
				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qSignon.username.eq(account.getUsername()))
							.set(qSignon.password, account.getPassword())
							.execute();
				}
				
			});
		}
	}

	public List getUsernameList() throws DataAccessException {
		SQLQuery sqlQuery = template.newSqlQuery()
				.from(qSignon);
				
		return template.query(sqlQuery, qSignon.username);
	}
	
	private class MappingAccountProjection extends MappingProjection<Account> {
		
		public MappingAccountProjection(Expression<?>... args) {
			super(Account.class, args);
		}

		@Override
		protected Account map(Tuple tuple) {
			Account account = new Account();
			
			account.setUsername(tuple.get(qSignon.username));
			account.setEmail(tuple.get(qAccount.email));
			account.setFirstName(tuple.get(qAccount.firstname));
			account.setLastName(tuple.get(qAccount.lastname));
			account.setStatus(tuple.get(qAccount.status));
			account.setAddress1(tuple.get(qAccount.addr1));
			account.setAddress2(tuple.get(qAccount.addr2));
			account.setCity(tuple.get(qAccount.city));
			account.setState(tuple.get(qAccount.state));
			account.setZip(tuple.get(qAccount.zip));
			account.setCountry(tuple.get(qAccount.country));
			account.setPhone(tuple.get(qAccount.phone));
			account.setLanguagePreference(tuple.get(qProfile.langpref));
			account.setFavouriteCategoryId(tuple.get(qProfile.favcategory));
			account.setListOption((tuple.get(qProfile.mylistopt) > 0)? true : false);
			account.setBannerOption((tuple.get(qProfile.banneropt) > 0)? true : false);
			account.setBannerName(tuple.get(qBannerdata.bannername));
			
			return account;
		}
	}

}
