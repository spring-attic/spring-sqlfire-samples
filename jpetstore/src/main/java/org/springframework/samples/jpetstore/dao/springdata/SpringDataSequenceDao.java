package org.springframework.samples.jpetstore.dao.springdata;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlUpdateCallback;
import org.springframework.samples.jpetstore.domain.Sequence;
import org.springframework.samples.jpetstore.generated.domain.QSequence;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class SpringDataSequenceDao {

	private final QSequence qSequence = QSequence.sequence;

	private QueryDslJdbcTemplate template;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}
	
	/**
	 * This is a generic sequence ID generator that is based on a database table
	 * called 'SEQUENCE', which contains two columns (NAME, NEXTID). This
	 * approach should work with any database.
	 * 
	 * @param name
	 *            the name of the sequence
	 * @return the next ID
	 */
	public int getNextId(final String name) throws DataAccessException {
		SQLQuery sqlQuery = template.newSqlQuery()
				.from(qSequence)
				.where(qSequence.name.eq(name));
		final Sequence sequence = template.queryForObject(sqlQuery, 
				new MappingSequenceProjection(qSequence.name, qSequence.nextid));
		
		if (sequence == null) {
			throw new DataRetrievalFailureException(
					"Could not get next value of sequence '" + name
							+ "': sequence does not exist");
		}
		
		template.update(qSequence, new SqlUpdateCallback() {
			public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
				return sqlUpdateClause.where(qSequence.name.eq(name))
						.set(qSequence.nextid, sequence.getNextId() + 1)
						.execute();
			}
		});
		
		return sequence.getNextId();
	}
	
	private class MappingSequenceProjection extends MappingProjection<Sequence> {

		public MappingSequenceProjection(Expression<?>... args) {
			super(Sequence.class, args);
		}

		@Override
		protected Sequence map(Tuple tuple) {
			Sequence sequence = new Sequence();
			
			sequence.setName(tuple.get(qSequence.name));
			sequence.setNextId(tuple.get(qSequence.nextid));
			
			return sequence;
		}
		
	}
}
