package org.springframework.samples.jpetstore.dao.springdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.samples.jpetstore.dao.ProductDao;
import org.springframework.samples.jpetstore.domain.Product;
import org.springframework.samples.jpetstore.generated.domain.QProduct;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;
import com.mysema.query.types.template.StringTemplate;

public class SpringDataProductDao implements ProductDao {

	private final QProduct qProduct = QProduct.product;

	private QueryDslJdbcTemplate template;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}
	
	public List getProductListByCategory(final String categoryId)
			throws DataAccessException {
		SQLQuery sqlQuery = template.newSqlQuery().from(qProduct)
				.where(qProduct.category.eq(categoryId));

		return template.query(sqlQuery, new MappingProductProjection(qProduct.productid,
				qProduct.name, qProduct.descn, qProduct.category));
	}

	public List searchProductList(String keywords) throws DataAccessException {
		ProductSearch parameterObject = new ProductSearch(keywords);
		BooleanBuilder builder = new BooleanBuilder();
		for (Iterator iterator = parameterObject.getKeywordList().iterator(); iterator.hasNext();) {
			String parameter = (String) iterator.next();
			
			builder.or(qProduct.name.lower().like(parameter))
				.or(qProduct.category.lower().like(parameter))
				.or(qProduct.descn.lower().like(parameter));
		}
		SQLQuery sqlQuery = template.newSqlQuery().from(qProduct).where(builder);
		
		return template.query(sqlQuery, new MappingProductProjection(qProduct.productid,
				qProduct.name, qProduct.descn, qProduct.category));
	}

	public Product getProduct(String productId) throws DataAccessException {
		SQLQuery sqlQuery = template.newSqlQuery().from(qProduct)
				.where(qProduct.productid.eq(productId));
		
		return template.queryForObject(sqlQuery, new MappingProductProjection(qProduct.productid,
				qProduct.name, qProduct.descn, qProduct.category));
	}

	private class MappingProductProjection extends MappingProjection<Product> {

		public MappingProductProjection(Expression<?>... args) {
			super(Product.class, args);
		}

		@Override
		protected Product map(Tuple tuple) {
			Product product = new Product();

			product.setProductId(tuple.get(qProduct.productid));
			product.setName(tuple.get(qProduct.name));
			product.setDescription(tuple.get(qProduct.descn));
			product.setCategoryId(tuple.get(qProduct.category));

			return product;
		}
	}

	public static class ProductSearch {

		private List keywordList = new ArrayList();

		public ProductSearch(String keywords) {
			StringTokenizer splitter = new StringTokenizer(keywords, " ", false);
			while (splitter.hasMoreTokens()) {
				this.keywordList.add("%" + splitter.nextToken() + "%");
			}
		}

		public List getKeywordList() {
			return keywordList;
		}
	}
}
