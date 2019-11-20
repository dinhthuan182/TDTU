package edu.tdt.entity;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IoWarehouse.class)
public abstract class IoWarehouse_ {

	public static volatile SingularAttribute<IoWarehouse, Date> createdAt;
	public static volatile SingularAttribute<IoWarehouse, Boolean> import1;
	public static volatile SingularAttribute<IoWarehouse, BigInteger> total;
	public static volatile CollectionAttribute<IoWarehouse, IoDetail> ioDetailCollection;
	public static volatile SingularAttribute<IoWarehouse, Long> id;
	public static volatile SingularAttribute<IoWarehouse, Store> storeId;
	public static volatile SingularAttribute<IoWarehouse, User> staffId;

}

