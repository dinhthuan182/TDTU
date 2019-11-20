package edu.tdt.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderDetail.class)
public abstract class OrderDetail_ {

	public static volatile SingularAttribute<OrderDetail, Product> product;
	public static volatile SingularAttribute<OrderDetail, Integer> quantity;
	public static volatile SingularAttribute<OrderDetail, Integer> price;
	public static volatile SingularAttribute<OrderDetail, Order1> order1;
	public static volatile SingularAttribute<OrderDetail, OrderDetailPK> orderDetailPK;

}

