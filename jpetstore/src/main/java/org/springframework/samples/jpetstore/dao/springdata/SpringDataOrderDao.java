package org.springframework.samples.jpetstore.dao.springdata;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.samples.jpetstore.dao.OrderDao;
import org.springframework.samples.jpetstore.domain.LineItem;
import org.springframework.samples.jpetstore.domain.Order;
import org.springframework.samples.jpetstore.generated.domain.QLineitem;
import org.springframework.samples.jpetstore.generated.domain.QOrders;
import org.springframework.samples.jpetstore.generated.domain.QOrderstatus;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class SpringDataOrderDao implements OrderDao {

	private final QOrders qOrders = QOrders.orders;
	
	private final QOrderstatus qOrderStatus = QOrderstatus.orderstatus;
	
	private final QLineitem qLineItem = QLineitem.lineitem;
	
	private QueryDslJdbcTemplate template;
	
	private SpringDataSequenceDao springDataSequenceDao;
	
	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}
	
	public void setSpringDataSequenceDao(SpringDataSequenceDao springDataSequenceDao) {
		this.springDataSequenceDao = springDataSequenceDao;
	}

	public List getOrdersByUsername(String username) throws DataAccessException {
		SQLQuery sqlQuery = template.newSqlQuery()
				.from(qOrders)
				.from(qOrderStatus)
				.where(qOrders.userid.eq(username).and(qOrders.orderid.eq(qOrderStatus.orderid)));

		return template.query(sqlQuery,
				new MappingOrderProjection(qOrders.billaddr1, qOrders.billaddr2, qOrders.billcity, 
						qOrders.billcountry, qOrders.billstate, qOrders.billtofirstname, 
						qOrders.billtolastname, qOrders.billzip, qOrders.shipaddr1,
						qOrders.shipaddr2, qOrders.shipcity, qOrders.shipcountry, qOrders.shipstate,
						qOrders.shiptofirstname, qOrders.shiptolastname, qOrders.shipzip,
						qOrders.cardtype, qOrders.courier, qOrders.creditcard, qOrders.exprdate,
						qOrders.locale, qOrders.orderdate, qOrders.orderid, qOrders.totalprice,
						qOrders.userid, qOrderStatus.status));
	}

	public Order getOrder(int orderId) throws DataAccessException {
	    SQLQuery sqlQuery = template.newSqlQuery()
				.from(qOrders)
				.from(qOrderStatus)
				.where(qOrders.orderid.eq(orderId).and(qOrders.orderid.eq(qOrderStatus.orderid)));

		Order order = template.queryForObject(sqlQuery, 
				new MappingOrderProjection(qOrders.billaddr1, qOrders.billaddr2, qOrders.billcity, 
						qOrders.billcountry, qOrders.billstate, qOrders.billtofirstname, 
						qOrders.billtolastname, qOrders.billzip, qOrders.shipaddr1,
						qOrders.shipaddr2, qOrders.shipcity, qOrders.shipcountry, qOrders.shipstate,
						qOrders.shiptofirstname, qOrders.shiptolastname, qOrders.shipzip,
						qOrders.cardtype, qOrders.courier, qOrders.creditcard, qOrders.exprdate,
						qOrders.locale, qOrders.orderdate, qOrders.orderid, qOrders.totalprice,
						qOrders.userid, qOrderStatus.status));
		
		if (order != null) {
			SQLQuery lineItemsSqlQuery = template.newSqlQuery()
					.from(qLineItem)
					.where(qLineItem.orderid.eq(orderId));
			
			List<LineItem> lineItems = template.query(lineItemsSqlQuery,
					new MappingLineItemProjection(qLineItem.orderid, qLineItem.linenum, 
							qLineItem.itemid, qLineItem.quantity, qLineItem.unitprice));
			
			order.setLineItems(lineItems);
		}
		
		return order;
	}

	public void insertOrder(final Order order) throws DataAccessException {
		order.setOrderId(springDataSequenceDao.getNextId("ordernum"));
		template.insert(qOrders, new SqlInsertCallback() {
			public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
				return sqlInsertClause.columns(qOrders.orderid, qOrders.userid, qOrders.orderdate, 
							qOrders.shipaddr1, qOrders.shipaddr2, qOrders.shipcity, qOrders.shipstate, 
							qOrders.shipzip, qOrders.shipcountry, qOrders.billaddr1, qOrders.billaddr2, 
							qOrders.billcity, qOrders.billstate, qOrders.billzip, qOrders.billcountry, 
							qOrders.courier, qOrders.totalprice, qOrders.billtofirstname, qOrders.billtolastname, 
							qOrders.shiptofirstname, qOrders.shiptolastname, qOrders.creditcard, qOrders.exprdate, 
							qOrders.cardtype, qOrders.locale)
						.values(order.getOrderId(), order.getUsername(), order.getOrderDate(), order.getShipAddress1(), 
								order.getShipAddress2(), order.getShipCity(), order.getShipState(), order.getShipZip(), 
								order.getShipCountry(), order.getBillAddress1(), order.getBillAddress2(), 
								order.getBillCity(), order.getBillState(), order.getBillZip(), order.getBillCountry(), 
								order.getCourier(), order.getTotalPrice(), order.getBillToFirstName(), 
								order.getBillToLastName(), order.getShipToFirstName(), order.getShipToLastName(), 
								order.getCreditCard(), order.getExpiryDate(), order.getCardType(), order.getLocale())
						.execute();
			}
		});
		
		template.insert(qOrderStatus, new SqlInsertCallback() {
			public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
				return sqlInsertClause.columns(qOrderStatus.orderid, qOrderStatus.linenum, 
							qOrderStatus.timestamp, qOrderStatus.status)
						.values(order.getOrderId(), order.getOrderId(), order.getOrderDate(),
								order.getStatus())
						.execute();
			}
		});
		
		for (int i = 0; i < order.getLineItems().size(); i++) {
			final LineItem lineItem = (LineItem) order.getLineItems().get(i);
			lineItem.setOrderId(order.getOrderId());

			template.insert(qLineItem, new SqlInsertCallback(){
				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause.columns(qLineItem.orderid, qLineItem.linenum, 
								qLineItem.itemid, qLineItem.quantity, qLineItem.unitprice)
							.values(lineItem.getOrderId(), lineItem.getLineNumber(),
									lineItem.getItemId(), lineItem.getQuantity(),
									lineItem.getUnitPrice())
							.execute();
				}
			});
		}
	}

	private class MappingOrderProjection extends MappingProjection<Order> {
		
		public MappingOrderProjection(Expression<?>... args) {
			super(Order.class, args);
		}

		@Override
		protected Order map(Tuple tuple) {
			Order order = new Order();

			order.setBillAddress1(tuple.get(qOrders.billaddr1));
			order.setBillAddress2(tuple.get(qOrders.billaddr2));
			order.setBillCity(tuple.get(qOrders.billcity));
			order.setBillCountry(tuple.get(qOrders.billcountry));
			order.setBillState(tuple.get(qOrders.billstate));
			order.setBillToFirstName(tuple.get(qOrders.billtofirstname));
			order.setBillToLastName(tuple.get(qOrders.billtolastname));
			order.setBillZip(tuple.get(qOrders.billzip));
			order.setShipAddress1(tuple.get(qOrders.shipaddr1));
			order.setShipAddress2(tuple.get(qOrders.shipaddr2));
			order.setShipCity(tuple.get(qOrders.shipcity));
			order.setShipCountry(tuple.get(qOrders.shipcountry));
			order.setShipState(tuple.get(qOrders.shipstate));
			order.setShipToFirstName(tuple.get(qOrders.shiptofirstname));
			order.setShipToLastName(tuple.get(qOrders.shiptolastname));
			order.setShipZip(tuple.get(qOrders.shipzip));
			order.setCardType(tuple.get(qOrders.cardtype));
			order.setCourier(tuple.get(qOrders.courier));
			order.setCreditCard(tuple.get(qOrders.creditcard));
			order.setExpiryDate(tuple.get(qOrders.exprdate));
			order.setLocale(tuple.get(qOrders.locale));
			order.setOrderDate(tuple.get(qOrders.orderdate));
			order.setOrderId(tuple.get(qOrders.orderid));
			order.setTotalPrice(tuple.get(qOrders.totalprice).doubleValue());
			order.setUsername(tuple.get(qOrders.userid));
			order.setStatus(tuple.get(qOrderStatus.status));
			
			return order;
		}
	}
	
	private class MappingLineItemProjection extends MappingProjection<LineItem> {

		public MappingLineItemProjection(Expression<?>... args) {
			super(LineItem.class, args);
		}

		@Override
		protected LineItem map(Tuple tuple) {
			LineItem lineItem = new LineItem();
			
			lineItem.setOrderId(tuple.get(qLineItem.orderid));
			lineItem.setLineNumber(tuple.get(qLineItem.linenum));
			lineItem.setItemId(tuple.get(qLineItem.itemid));
			lineItem.setQuantity(tuple.get(qLineItem.quantity));
			lineItem.setUnitPrice(tuple.get(qLineItem.unitprice).doubleValue());
			
			return lineItem;
		}
		
	}
}
