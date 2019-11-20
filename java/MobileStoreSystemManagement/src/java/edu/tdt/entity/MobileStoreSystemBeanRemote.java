/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tdt.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author thuan
 */
@Remote
public interface MobileStoreSystemBeanRemote {
      
    User Login(String username,String password);//Done!
    
    
    //Store Management
    Store insertStore(String name, String address, String phone, String email);//Done!
    boolean updateStore(Long id, String name, String address, String phone, String email, Boolean status);//Done!
    boolean deleteStore(Long id);//Done!
    List<Store> getAllStores();//Done!
    Store findStoreById(Long id);//Done!
    boolean deleteAllUserOfStore(Store store);
    boolean deleteAllOrderOfStore(Store store);
    boolean deleteAllIoWarehouseOfStore(Store store);
    //User Management
    User insertUser(String userName, String userPassword, String fullName, String address, String phone, Long storeId, String email); //Done!
    boolean updateUser(Long id, String userPassword, String fullName, String address, String phone, Long storeId, String email, Boolean status);
    boolean deleteUser(Long id);
    boolean insertRole(String roleName);
    boolean insertUserRole(Long user_id, String roleName);
    List<User> getAllUsers();//Done!
    User findUserById(Long id);
    List<Role> getAllRoles();
    List<User> getUsersByRole(String roleName);
    boolean deleteUserRole(Long user_id, String roleName);
    boolean deleteRole(String roleName);
    
    //Product Management
    Product insertProduct(String name, Long supplierId, Integer afterCamera, Integer beforeCamera, String color ,String description, Integer memory ,String operator ,Integer price ,Integer warranty ,String screen,String pin);
    boolean updateProduct(Long id, String name, Long supplierID, Integer afterCamera, Integer beforeCamera, String color ,String description, Integer memory ,String operator ,Integer price ,Integer warranty ,String screen,String pin, Boolean status);
    boolean deleteProduct(Long id);
    List<Product> getAllProducts();
    Product findProductById(Long id);
    boolean deleteAllProductOfSupplier(Supplier supplier);
    
    //Supplier Management
    Supplier insertSupplier(String name, String address, String phone, String email);
    boolean updateSupplier(Long id, String fullname, String address, String phone, String email ,Boolean status);
    boolean deleteSupplier(Long id);
    List<Supplier> getAllSuppliers();
    Supplier findSupplierById(Long id);
    
    //Customer Management
    Customer insertCustomer(String fullname, String address, String phone, String email);
    boolean updateCustomer(Long id, String fullname, String address, String phone, String email);
    boolean deleteCustomer(Long id);
    List<Customer> getAllCustomers();
    Customer findCustomerById(Long id);
    Customer findCustomerByPhone(String phone);
    
    //Order Management
    Order1 insertOrder(String customerName, String customerAddress, String customerPhone, String customerEmail, Store store, User staff, List<OrderDetail> productList, String note );
    boolean updateOrder(Long id , String customerName, String customerAddress, String customerPhone, String customerEmail, Store store, User staff, List<OrderDetail> productList, String note );
    boolean deleteOrder(Long id);
    List<Order1> getAllOrders();
    Order1 findOrderById(Long id);
    List<OrderDetail> getOrderDetailById(Long id);
    List<Order1> getOrderByStore(Store store);
    boolean insertOrderDetail(Order1 order, Product product, int price, int quantity);
    boolean updateOrderDetail(Order1 order, Product product, int price, int quantity);
    boolean deleteOrderDetail(Order1 order);
    boolean deleteOrderByUser(User user);
    boolean deleteOrderDetailByProduct(Product product);
    
    //IO Warehouse Management
    IoWarehouse insertIoWarehouse(Store store, User staff, List<IoDetail> wareList, Boolean import1);
    boolean updateIoWarehouse(Long id, Store store, User staff, List<IoDetail> wareList, Boolean import1);
    boolean deleteIoWarehouse(Long id);
    List<IoWarehouse> getAllIoWarehouses();
    IoWarehouse findIoWarehouselById(Long id);
    List<IoWarehouse> getIoWarehousesByStore(Store store);
    List<IoDetail> getIoDetailById(Long id);
    boolean insertIoDetail(IoWarehouse warehouse, Product product, int price, int quantity);
    boolean updateIoDetail(IoWarehouse warehouse, Product product, int price, int quantity);
    boolean deleteIoDetail(IoWarehouse warehouse);
    boolean deleteIoWarehouseByUser(User user);
    boolean deleteIoDetailByProduct(Product product);
    
}
