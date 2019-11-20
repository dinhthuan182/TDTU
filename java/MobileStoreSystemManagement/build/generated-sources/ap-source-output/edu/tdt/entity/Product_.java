package edu.tdt.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, Integer> beforeCamera;
	public static volatile SingularAttribute<Product, Integer> memory;
	public static volatile SingularAttribute<Product, Supplier> supplierId;
	public static volatile SingularAttribute<Product, String> color;
	public static volatile CollectionAttribute<Product, IoDetail> ioDetailCollection;
	public static volatile CollectionAttribute<Product, OrderDetail> orderDetailCollection;
	public static volatile SingularAttribute<Product, Integer> afterCamera;
	public static volatile SingularAttribute<Product, String> description;
	public static volatile SingularAttribute<Product, String> screen;
	public static volatile SingularAttribute<Product, String> operator;
	public static volatile SingularAttribute<Product, String> pin;
	public static volatile SingularAttribute<Product, Integer> price;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, Integer> warranty;
	public static volatile SingularAttribute<Product, Long> id;
	public static volatile SingularAttribute<Product, Boolean> status;

}

