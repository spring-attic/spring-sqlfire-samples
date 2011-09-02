package org.springframework.samples.jpetstore.dao.springdata;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlUpdateCallback;
import org.springframework.samples.jpetstore.dao.ItemDao;
import org.springframework.samples.jpetstore.domain.Item;
import org.springframework.samples.jpetstore.domain.LineItem;
import org.springframework.samples.jpetstore.domain.Order;
import org.springframework.samples.jpetstore.domain.Product;
import org.springframework.samples.jpetstore.generated.domain.QInventory;
import org.springframework.samples.jpetstore.generated.domain.QItem;
import org.springframework.samples.jpetstore.generated.domain.QProduct;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class SpringDataItemDao implements ItemDao {

	private final QItem qItem = QItem.item;
	
	private final QProduct qProduct = QProduct.product;
	
	private final QInventory qInventory = QInventory.inventory;
	
	private QueryDslJdbcTemplate template;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}
	
	public void updateQuantity(Order order) throws DataAccessException {
		for (int i = 0; i < order.getLineItems().size(); i++) {
			LineItem lineItem = (LineItem) order.getLineItems().get(i);
			final String itemId = lineItem.getItemId();
			final Integer increment = new Integer(lineItem.getQuantity());
			
			template.update(qInventory, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qInventory.itemid.eq(itemId))
							.set(qInventory.qty, qInventory.qty.subtract(increment)).execute();
				}
				
			});
		}
	}

	public boolean isItemInStock(String itemId) throws DataAccessException {
		SQLQuery sqlQuery = template.newSqlQuery()
				.from(qInventory)
				.where(qInventory.itemid.eq(itemId).and(qInventory.qty.gt(0)));
		return template.exists(sqlQuery);
	}

	public List getItemListByProduct(String productId)
			throws DataAccessException {
	    
	    SQLQuery sqlQuery = template.newSqlQuery()
	    	.from(qItem)
	    	.from(qProduct)
	    	.where(qProduct.productid.eq(qItem.productid).and(qItem.productid.eq(productId)));
	    
	    return template.query(sqlQuery,
	    		new MappingItemProjection(qItem.itemid, qItem.listprice, qItem.unitcost,
						qItem.supplier, qItem.productid, qProduct.name, qProduct.descn,
						qProduct.category, qItem.status, qItem.attr1, qItem.attr2, qItem.attr3,
						qItem.attr4, qItem.attr5));
	}

	public Item getItem(String itemId) throws DataAccessException {
		SQLQuery sqlQuery = template.newSqlQuery()
		    	.from(qItem)
		    	.from(qInventory)
		    	.from(qProduct)
		    	.where(qProduct.productid.eq(qItem.productid).and(qItem.itemid.eq(qInventory.itemid)).and(qItem.itemid.eq(itemId)));
		    
		    return template.queryForObject(sqlQuery, 
		    		new MappingItemWithQuantityProjection(qItem.itemid, qItem.listprice, qItem.unitcost,
							qItem.supplier, qItem.productid, qProduct.name, qProduct.descn,
							qProduct.category, qItem.status, qItem.attr1, qItem.attr2, qItem.attr3,
							qItem.attr4, qItem.attr5, qInventory.qty));
	}

	private class MappingItemProjection extends MappingProjection<Item> {
		
		public MappingItemProjection(Expression<?>... args) {
			super(Item.class, args);
		}

		@Override
		protected Item map(Tuple tuple) {
			Item item = new Item();
			
			item.setItemId(tuple.get(qItem.itemid));
			item.setListPrice(tuple.get(qItem.listprice).doubleValue());
			item.setUnitCost(tuple.get(qItem.unitcost).doubleValue());
			item.setSupplierId(tuple.get(qItem.supplier));
			item.setProductId(tuple.get(qItem.productid));
			
			Product product = new Product();
			product.setProductId(tuple.get(qItem.productid));
			product.setName(tuple.get(qProduct.name));
			product.setDescription(tuple.get(qProduct.descn));
			product.setCategoryId(tuple.get(qProduct.category));
			
			item.setProduct(product);
			item.setStatus(tuple.get(qItem.status));
			item.setAttribute1(tuple.get(qItem.attr1));
			item.setAttribute2(tuple.get(qItem.attr2));
			item.setAttribute3(tuple.get(qItem.attr3));
			item.setAttribute4(tuple.get(qItem.attr4));
			item.setAttribute5(tuple.get(qItem.attr5));
			
			return item;
		}
	}
	
	private class MappingItemWithQuantityProjection extends MappingItemProjection {

		public MappingItemWithQuantityProjection(Expression<?>... args) {
			super(args);
		}
		
		@Override
		protected Item map(Tuple tuple) {
			Item item = super.map(tuple);
			item.setQuantity(tuple.get(qInventory.qty));
			
			return item;
		}
		
	}
}
