package edu.tdt.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Supplier.class)
public abstract class Supplier_ {

	public static volatile SingularAttribute<Supplier, String> address;
	public static volatile CollectionAttribute<Supplier, Product> productCollection;
	public static volatile SingularAttribute<Supplier, String> phone;
	public static volatile SingularAttribute<Supplier, String> name;
	public static volatile SingularAttribute<Supplier, Long> id;
	public static volatile SingularAttribute<Supplier, String> email;
	public static volatile SingularAttribute<Supplier, Boolean> status;

}

