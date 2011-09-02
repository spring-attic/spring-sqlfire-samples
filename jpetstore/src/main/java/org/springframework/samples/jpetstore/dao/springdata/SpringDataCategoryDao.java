package org.springframework.samples.jpetstore.dao.springdata;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.samples.jpetstore.dao.CategoryDao;
import org.springframework.samples.jpetstore.domain.Category;
import org.springframework.samples.jpetstore.generated.domain.QCategory;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class SpringDataCategoryDao implements CategoryDao {
	
	private QueryDslJdbcTemplate template;
	
	private final QCategory qCategory = QCategory.category;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}
	
	public List getCategoryList() throws DataAccessException {
		SQLQuery sqlQuery = template.newSqlQuery()
				.from(qCategory);
		
		return template.query(sqlQuery, new MappingCategoryProjection(qCategory.all()));
	}

	public Category getCategory(String categoryId) throws DataAccessException {
		SQLQuery sqlQuery = template.newSqlQuery()
				.from(qCategory)
				.where(qCategory.catid.eq(categoryId));
		
		return template.queryForObject(sqlQuery, new MappingCategoryProjection(qCategory.all()));
	}

	private class MappingCategoryProjection extends MappingProjection<Category> {
		
		public MappingCategoryProjection(Expression<?>... args) {
			super(Category.class, args);
		}

		@Override
		protected Category map(Tuple tuple) {
			Category category = new Category();
			
			category.setCategoryId(tuple.get(qCategory.catid));
			category.setDescription(tuple.get(qCategory.descn));
			category.setName(tuple.get(qCategory.name));
			
			return category;
		}
		
		
	}
}
