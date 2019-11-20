/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tdt.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author thuan
 */
@Stateful
public class MobileStoreSystemBean implements MobileStoreSystemBeanRemote {

    @PersistenceContext(unitName = "MobileStoreSystemManagementPU")
    private EntityManager em;

    public MobileStoreSystemBean()
    {

    }
    
    @Override
    public User insertUser(String userName, String userPassword, String fullName, String address, String phone, Long storeId, String email) {
        try {
            User u = new User();
            u.setUserName(userName);
            u.setPassword(userPassword);
            u.setFullName(fullName);
            u.setAddress(address);
            u.setPhone(phone);
            u.setStoreId(this.findStoreById(storeId));
            u.setEmail(email);
            u.setStatus(Boolean.TRUE);
            em.persist(u);

            return (User) em.createNamedQuery("User.findByUserName").setParameter("userName", u.getUserName()).getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean insertRole(String roleName) {
        try {
            Role role = new Role(roleName);
            em.persist(role);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean insertUserRole(Long user_id, String roleName) {
        try {
            User user = em.find(User.class, user_id);
            Role role = em.find(Role.class, roleName);

            user.getRoleCollection().add(role);
            role.getUserCollection().add(user);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public User Login(String username, String password) {
        try {
            Query loginQuery = em.createQuery("SELECT u FROM User u WHERE u.userName = :username AND u.password = :password");
            loginQuery.setParameter("username", username);
            loginQuery.setParameter("password", password);
            User user = (User) loginQuery.getSingleResult();

            return user;
        } catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return em.createNamedQuery("User.findAll").getResultList();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Store insertStore(String name, String address, String phone, String email) {
        try {
            Store store = new Store();
            store.setName(name);
            store.setAddress(address);
            store.setPhone(phone);
            store.setEmail(email);
            store.setStatus(Boolean.TRUE);
            em.persist(store);
            return (Store) em.createNamedQuery("Store.findByName").setParameter("name", name).getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateStore(Long id, String name, String address, String phone, String email, Boolean status) {
        Store store = em.find(Store.class, id);
        if(store == null)
        {
            return false;
        }
        try
        {
            Query updateQuery = em.createQuery("UPDATE Store AS s SET s.name = :name, s.address = :address, s.phone = :phone, s.email = :email, s.status = :status WHERE s.id = :id");
            updateQuery.setParameter("name", name);
            updateQuery.setParameter("address", address);
            updateQuery.setParameter("phone", phone);
            updateQuery.setParameter("email", email);
            updateQuery.setParameter("status", status);
            updateQuery.setParameter("id", store.getId());
            updateQuery.executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteStore(Long id) {
        try
        {
            Store finded = em.find(Store.class, id);
            
            this.deleteAllOrderOfStore(finded);
            this.deleteAllIoWarehouseOfStore(finded);
            this.deleteAllUserOfStore(finded);
            Query deleteQuery = em.createQuery("DELETE FROM Store s WHERE s.id = :id");
            deleteQuery.setParameter("id", finded.getId()).executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    
    @Override
    public boolean deleteAllUserOfStore(Store store){
        for(User u : store.getUserCollection()) {
            this.deleteOrderByUser(u);
            this.deleteIoWarehouseByUser(u);
        }
        Query deleteQuery = em.createQuery("DELETE FROM User u WHERE u.storeId = :store").setParameter("store", store);
        deleteQuery.executeUpdate();
        return true;
    }
    
    @Override
    public boolean deleteAllOrderOfStore(Store store){
        for(Order1 od : store.getOrder1Collection()) {
            this.deleteOrderDetail(od);
        }
        Query deleteQuery = em.createQuery("DELETE FROM Order1 o WHERE o.storeId = :store").setParameter("store", store);
        deleteQuery.executeUpdate();
        return true;
    }
    @Override
    public boolean deleteAllIoWarehouseOfStore(Store store){
        for(IoWarehouse iw : store.getIoWarehouseCollection()) {
            this.deleteIoDetail(iw);
        }
        Query deleteQuery = em.createQuery("DELETE FROM IoWarehouse io WHERE io.storeId = :store").setParameter("store", store);
        deleteQuery.executeUpdate();
        return true;
    }

    @Override
    public List<Store> getAllStores() {
        try {
            return em.createNamedQuery("Store.findAll").getResultList();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Store findStoreById(Long id) {
        try {
            return (Store) em.createNamedQuery("Store.findById").setParameter("id", id).getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateUser(Long id, String userPassword, String fullName, String address, String phone, Long storeId, String email, Boolean status) {
        try
        {
            Query updateQuery = em.createQuery("UPDATE User AS u SET u.password = :password, u.fullName = :fullname, u.address = :address, u.phone = :phone, u.storeId = :storeid, u.email = :email, u.status = :status WHERE u.id = :id");
            updateQuery.setParameter("password", userPassword);
            updateQuery.setParameter("fullname", fullName);
            updateQuery.setParameter("address", address);
            updateQuery.setParameter("phone", phone);
            updateQuery.setParameter("storeid", findStoreById(storeId));
            updateQuery.setParameter("email", email);
            updateQuery.setParameter("status", status);
            updateQuery.setParameter("id", id);
            updateQuery.executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        try
        {
            
            Query deleteQuery = em.createQuery("DELETE FROM User u WHERE u.id = :id");
            deleteQuery.setParameter("id", id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public User findUserById(Long id) {
        return (User) em.createNamedQuery("User.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        try {
            return em.createNamedQuery("Role.findAll").getResultList();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> getUsersByRole(String roleName) {
        try {
            ArrayList<User> arrOutput = new ArrayList<User>();
            Role role = (Role) em.createNamedQuery("Role.findByRoleName").setParameter("roleName", roleName.toUpperCase()).getSingleResult();
            if(role != null && !(role.getUserCollection().isEmpty()))
            {
                arrOutput.addAll(role.getUserCollection());
            }
            return arrOutput;
        } catch (Exception e) {
            return null;
        }
    }

    private Role findRole(String roleName) {
        try {
            return (Role) em.createNamedQuery("Role.findByRoleName").setParameter("roleName", roleName).getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteUserRole(Long user_id, String roleName) {
        try
        {
            User findedUser = findUserById(user_id);
            findedUser.getRoleCollection().remove(findRole(roleName));
            em.persist(findedUser);
            return true;
        }catch(Exception ex)
        {
            return false;
        }
    }
    
    @Override
    public boolean deleteRole(String roleName) {
        try {
            Role role = (Role) em.createNamedQuery("Role.findByRoleName").setParameter("roleName", roleName.toUpperCase()).getSingleResult();
            for(User u : role.getUserCollection()) {
                this.deleteUserRole(u.getId(), roleName);
            }
            Query deleteQuery = em.createQuery("DELETE FROM Role r WHERE r.roleName = :role");
            deleteQuery.setParameter("role", roleName).executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Product insertProduct(String name, Long supplierId, Integer afterCamera, Integer beforeCamera, String color, String description, Integer memory, String operator, Integer price, Integer warranty, String screen, String pin) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setSupplierId(findSupplierById(supplierId));
            product.setAfterCamera(afterCamera);
            product.setBeforeCamera(beforeCamera);
            product.setColor(color);
            product.setDescription(description);
            product.setMemory(memory);
            product.setOperator(operator);
            product.setPrice(price);
            product.setWarranty(warranty);
            product.setScreen(screen);
            product.setPin(pin);
            product.setStatus(Boolean.TRUE);
            em.persist(product);
            return (Product) em.createNamedQuery("Product.findByName").setParameter("name", name).getSingleResult();
        } catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public boolean updateProduct(Long id, String name, Long supplierID, Integer afterCamera, Integer beforeCamera, String color, String description, Integer memory, String operator, Integer price, Integer warranty, String screen, String pin, Boolean status) {
        try
        {
            Product product = em.find(Product.class, id);
            Query updateQuery = em.createQuery("UPDATE Product AS p SET p.name = :name, p.supplierId = :supplier, p.afterCamera = :afterCamera, p.beforeCamera = :beforeCamera, p.color = :color, p.description = :description ,p.memory = :memory ,p.operator = :operator ,p.price = :price , p.warranty = :warranty, p.screen = :screen , p.pin = :pin, p.status = :status WHERE p.id = :id");
            if(name.equals(null) || name.equals("")) {
                updateQuery.setParameter("name", product.getName());
            } else {
                updateQuery.setParameter("name", name);
            }
            
            if(supplierID.equals(null) || supplierID.equals("")) {
                updateQuery.setParameter("supplier", product.getSupplierId());
            } else {
                updateQuery.setParameter("supplier", findSupplierById(supplierID));
            }
            
            if(afterCamera.equals(null) || afterCamera.equals("")) {
                updateQuery.setParameter("afterCamera", product.getAfterCamera());
            } else {
                updateQuery.setParameter("afterCamera", afterCamera);
            }
            
            if(beforeCamera.equals(null) || beforeCamera.equals("")) {
                updateQuery.setParameter("beforeCamera", product.getBeforeCamera());
            } else {
                updateQuery.setParameter("beforeCamera", beforeCamera);
            }
            
            if(color.equals(null) || color.equals("")) {
                updateQuery.setParameter("color", product.getColor());
            } else {
                updateQuery.setParameter("color", color);
            }
            
            if(description.equals(null) || description.equals("")) {
                updateQuery.setParameter("description", product.getDescription());
            } else {
                updateQuery.setParameter("description", description);
            }
            
            if(memory.equals(null) || memory.equals("")) {
                updateQuery.setParameter("memory", product.getMemory());
            } else {
                updateQuery.setParameter("memory", memory);
            }
            
            if(operator.equals(null) || operator.equals("")) {
                updateQuery.setParameter("operator", product.getOperator());
            } else {
                updateQuery.setParameter("operator", operator);
            }
            
            if(price.equals(null) || price.equals("")) {
                updateQuery.setParameter("price", product.getPrice());
            } else {
                updateQuery.setParameter("price", price);
            }
            
            if(warranty.equals(null) || warranty.equals("")) {
                updateQuery.setParameter("warranty", product.getWarranty());
            } else {
                updateQuery.setParameter("warranty", warranty);
            }
            
            if(screen.equals(null) || screen.equals("")) {
                updateQuery.setParameter("screen", product.getScreen());
            } else {
                updateQuery.setParameter("screen", screen);
            }
            
            if(pin.equals(null) || pin.equals("")) {
                updateQuery.setParameter("pin", product.getPin());
            } else {
                updateQuery.setParameter("pin", pin);
            }
            
            if(status.equals(null) || status.equals("")) {
                updateQuery.setParameter("status", product.getStatus());
            } else {
                updateQuery.setParameter("status", status);
            }
            
            updateQuery.setParameter("id", product.getId());
            updateQuery.executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        try
        {
            Query deleteQuery = em.createQuery("DELETE FROM Product p WHERE p.id = :id");
            deleteQuery.setParameter("id", id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            e.getMessage();
            return false;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            return em.createNamedQuery("Product.findAll").getResultList();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Product findProductById(Long id) {
        try {
            return (Product) em.createNamedQuery("Product.findById").setParameter("id", id).getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Supplier insertSupplier(String name, String address, String phone, String email) {
        try {
            Supplier supplier = new Supplier();
            supplier.setName(name);
            supplier.setAddress(address);
            supplier.setPhone(phone);
            supplier.setEmail(email);
            supplier.setStatus(Boolean.TRUE);
            em.persist(supplier);
            return (Supplier) em.createNamedQuery("Supplier.findByName").setParameter("name", name).getSingleResult();
        }catch(Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateSupplier(Long id, String fullname, String address, String phone, String email, Boolean status) {
        try
        {
            Supplier supplier = em.find(Supplier.class, id);
            Query updateQuery = em.createQuery("UPDATE Supplier AS s SET s.name = :name, s.address = :address, s.phone = :phone, s.email = :email ,s.status = :status  WHERE s.id = :id");
            updateQuery.setParameter("name", fullname);
            updateQuery.setParameter("address", address);
            updateQuery.setParameter("phone", phone);
            updateQuery.setParameter("email", email);
            updateQuery.setParameter("status",status);
            updateQuery.setParameter("id", supplier.getId());
            updateQuery.executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteSupplier(Long id) {
        try
        {
            Supplier supplier = this.findSupplierById(id);
            this.deleteAllProductOfSupplier(supplier);
            Query deleteQuery = em.createQuery("DELETE FROM Supplier s WHERE s.id = :id");
            deleteQuery.setParameter("id", id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        } 
    }
    
    @Override
    public boolean deleteAllProductOfSupplier(Supplier supplier) {
        
        for(Product p : supplier.getProductCollection()) {
            this.deleteOrderDetailByProduct(p);
            this.deleteIoDetailByProduct(p);
        }
        return true;
    }
    
    @Override
    public boolean deleteOrderDetailByProduct(Product product) {
        Query deleteOd = em.createQuery("DELETE FROM OrderDetail od WHERE od.product = :product");
        deleteOd.setParameter("product", product).executeUpdate();
        return true;
    }
    
    @Override
    public boolean deleteIoDetailByProduct(Product product) {
        Query deleteOd = em.createQuery("DELETE FROM IoDetail od WHERE od.product = :product");
        deleteOd.setParameter("product", product).executeUpdate();
        return true;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        try {
            return em.createNamedQuery("Supplier.findAll").getResultList();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Supplier findSupplierById(Long id) {
        try {
            return (Supplier) em.createNamedQuery("Supplier.findById").setParameter("id", id).getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Customer insertCustomer(String fullname, String address, String phone, String email) {
        try {
            Customer customer = new Customer();
            customer.setFullname(fullname);
            customer.setAddress(address);
            customer.setPhone(phone);
            customer.setEmail(email);
            em.persist(customer);
            return (Customer) em.createNamedQuery("Customer.findByPhone").setParameter("phone", phone).getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateCustomer(Long id, String fullname, String address, String phone, String email) {
        try
        {
            Customer customer = em.find(Customer.class, id);
            Query updateQuery = em.createQuery("UPDATE Customer AS c SET c.fullname = :fullname, c.address = :address, c.phone = :phone, c.email = :email  WHERE c.id = :id");
            updateQuery.setParameter("fullname", fullname);
            updateQuery.setParameter("address", address);
            updateQuery.setParameter("phone", phone);
            updateQuery.setParameter("email", email);
            updateQuery.setParameter("id", customer.getId());
            updateQuery.executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(Long id) {
       try
        {
            Query deleteQuery = em.createQuery("DELETE FROM Customer c WHERE c.id = :id");
            deleteQuery.setParameter("id", id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }  
    }

    @Override
    public List<Customer> getAllCustomers() {
        try {
            return em.createNamedQuery("Customer.findAll").getResultList();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Customer findCustomerById(Long id) {
        try {
            return (Customer) em.createNamedQuery("Customer.findById").setParameter("id", id).getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Order1 insertOrder(String customerName, String customerAddress, String customerPhone, String customerEmail, Store store, User staff, List<OrderDetail> productList, String note) {
        try {
            List<IoDetail> wareList = new ArrayList();
            int total = 0;
            Customer customer = findCustomerByPhone(customerPhone);
            if(customer == null) {
                customer = this.insertCustomer(customerName, customerAddress, customerPhone, customerEmail);
            }
            //total = price * quantity
            for(OrderDetail detail :productList ) {
                total +=  detail.getQuantity()*detail.getPrice();
            }
            //Create order
            Order1 order = new Order1();
            order.setCustomerId(customer);
            order.setStoreId(store);
            order.setStaffId(staff);
            order.setCreatedAt(new Date());
            order.setTotal(total);
            order.setNote(note);
            em.persist(order);

            for(OrderDetail detail :productList ) {
                this.insertOrderDetail(order, detail.getProduct(), detail.getPrice(), detail.getQuantity());
                IoDetail i = new IoDetail();
                i.setProduct(detail.getProduct());
                i.setPrice(detail.getPrice());
                i.setQuantity(detail.getQuantity());
                wareList.add(i);
            }
            this.insertIoWarehouse(store, staff, wareList, Boolean.FALSE);

            return order;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateOrder(Long id, String customerName, String customerAddress, String customerPhone, String customerEmail, Store store, User staff, List<OrderDetail> productList, String note) { 
        try
        {
            boolean set = false;
            Order1 order_finded = em.find(Order1.class, id);
            int total = 0;
            Customer customer = this.findCustomerByPhone(customerPhone);
            if(customer == null) {
                customer = this.insertCustomer(customerName, customerAddress, customerPhone, customerEmail);
            } else {
                this.updateCustomer(customer.getId(), customerName, customerAddress, customerPhone, customerEmail);
            }
            //this.deleteOrderDetail(order_finded);
            //total = price * quantity
            for(OrderDetail detail :productList ) {
                total +=  detail.getQuantity()*detail.getPrice();
            }
            Query updateQuery = em.createQuery("UPDATE Order1 AS o SET o.customerId = :customer, o.staffId = :staff, o.storeId = :store, o.total = :total, o.note = :note  WHERE o.id = :id");
            updateQuery.setParameter("customer", customer);
            updateQuery.setParameter("staff", staff);
            updateQuery.setParameter("store", store);
            updateQuery.setParameter("total", total);
            updateQuery.setParameter("note", note);
            updateQuery.setParameter("id", order_finded.getId());
            updateQuery.executeUpdate();
            for(OrderDetail d : productList) {
                set = false;
                for(OrderDetail detail : order_finded.getOrderDetailCollection()) {
                    if(detail.getProduct().getId() == d.getProduct().getId()) {
                        this.updateOrderDetail(order_finded, detail.getProduct(), detail.getPrice(), detail.getQuantity());
                        set = true;
                    }
                }
                if(!set) {
                    this.insertOrderDetail(order_finded, d.getProduct(), d.getPrice(), d.getQuantity());
                    set = false;
                }
            }
            
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteOrder(Long id) {
        try
        {
            Order1 order = (Order1) em.createNamedQuery("Order1.findById").setParameter("id", id).getSingleResult();
            deleteOrderDetail(order);
            Query deleteQuery = em.createQuery("DELETE FROM Order1 o WHERE o.id = :id");
            deleteQuery.setParameter("id", id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        }  
    }
    
    @Override
    public boolean insertOrderDetail(Order1 order, Product product, int price, int quantity) {
        try {
            OrderDetail oDetail = new OrderDetail();
            oDetail.setOrder1(order);
            oDetail.setProduct(product);
            oDetail.setOrderDetailPK(new OrderDetailPK(order.getId(), product.getId()));
            oDetail.setPrice(price);
            oDetail.setQuantity(quantity);
            em.persist(oDetail);
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean updateOrderDetail(Order1 order, Product product, int price, int quantity) {
        try {
            Query updateQuery = em.createQuery("UPDATE OrderDetail AS od SET od.price = :price, od.quantity = :quantity  WHERE od.order1.id = :order AND od.product.id = :product");
            updateQuery.setParameter("price", price);
            updateQuery.setParameter("quantity", quantity);
            updateQuery.setParameter("order", order.getId());
            updateQuery.setParameter("product", product.getId());
            updateQuery.executeUpdate();
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    @Override
    public boolean deleteOrderDetail(Order1 order) {
        
        try {
            Query deleteDetailQuery = em.createQuery("DELETE FROM OrderDetail od WHERE od.order1 = :order");
            deleteDetailQuery.setParameter("order", order).executeUpdate();
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    

    @Override
    public List<Order1> getAllOrders() {
        try {
            return em.createNamedQuery("Order1.findAll").getResultList();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Order1 findOrderById(Long id) {
        try {
            return (Order1) em.createNamedQuery("Order1.findById").setParameter("id", id).getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Order1> getOrderByStore(Store store) {
        try {
            Query selectQuery = em.createQuery("SELECT o FROM Order1 o WHERE o.storeId = :store").setParameter("store", store);
            return selectQuery.getResultList();
        }catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<OrderDetail> getOrderDetailById(Long id) {
        try {
            Order1 order = this.findOrderById(id);
            return em.createNamedQuery("OrderDetail.findByOrderId").setParameter("orderId", order.getId()).getResultList();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public IoWarehouse insertIoWarehouse(Store store, User staff, List<IoDetail> wareList, Boolean import1) {
        try {
            int total = 0;
            for(IoDetail i : wareList) {
                total += (i.getPrice() * i.getQuantity());
            }
            IoWarehouse newIo = new IoWarehouse();
            newIo.setStoreId(store);
            newIo.setStaffId(staff);
            newIo.setTotal(total);
            newIo.setCreatedAt(new Date());
            newIo.setImport1(import1);
            em.persist(newIo);
            for(IoDetail i : wareList) {
                this.insertIoDetail(newIo, i.getProduct(), i.getPrice(), i.getQuantity());
            }

            return newIo;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateIoWarehouse(Long id, Store store, User staff, List<IoDetail> wareList, Boolean import1) {
        try {
            boolean set = false;
            IoWarehouse ware_finded = em.find(IoWarehouse.class, id);
            int total = 0;
            //total = price * quantity
            for(IoDetail detail :wareList ) {
                total +=  detail.getQuantity()*detail.getPrice();
            }
            Query updateQuery = em.createQuery("UPDATE IoWarehouse AS o SET o.storeId = :store, o.staffId = :staff, o.total = :total WHERE o.id = :id");
            updateQuery.setParameter("staff", staff);
            updateQuery.setParameter("store", store);
            updateQuery.setParameter("total", total);
            updateQuery.setParameter("id", ware_finded.getId());
            updateQuery.executeUpdate();
            for(IoDetail d : wareList) {
                set = false;
                for(IoDetail detail : ware_finded.getIoDetailCollection()) {
                    if(detail.getProduct().getId() == d.getProduct().getId()) {
                        this.updateIoDetail(ware_finded, detail.getProduct(), detail.getPrice(), detail.getQuantity());
                        set = true;
                    }
                }
                if(!set) {
                    this.insertIoDetail(ware_finded, d.getProduct(), d.getPrice(), d.getQuantity());
                    set = false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteIoWarehouse(Long id) {
        try
        {
            IoWarehouse wareFinded = (IoWarehouse) em.createNamedQuery("IoWarehouse.findById").setParameter("id", id).getSingleResult();
            deleteIoDetail(wareFinded);
            Query deleteQuery = em.createQuery("DELETE FROM IoWarehouse i WHERE i.id = :id");
            deleteQuery.setParameter("id", id).executeUpdate();
            return true;
        }catch(Exception e)
        {
            return false;
        } 
    }
    
    @Override
    public boolean insertIoDetail(IoWarehouse warehouse, Product product, int price, int quantity) {
        try {
                IoDetail io = new IoDetail();
            io.setIoDetailPK(new IoDetailPK(warehouse.getId(), product.getId()));
            io.setIoWarehouse(warehouse);
            io.setProduct(product);
            io.setPrice(price);
            io.setQuantity(quantity);
            em.persist(io);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean updateIoDetail(IoWarehouse warehouse, Product product, int price, int quantity) {
        try {
            Query updateQuery = em.createQuery("UPDATE IoDetail AS io SET io.price = :price, io.quantity = :quantity  WHERE io.ioWarehouse.id = :warehouse AND io.product.id = :product");
            updateQuery.setParameter("price", price);
            updateQuery.setParameter("quantity", quantity);
            updateQuery.setParameter("warehouse", warehouse.getId());
            updateQuery.setParameter("product", product.getId());
            updateQuery.executeUpdate();
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean deleteIoDetail(IoWarehouse warehouse) {
        try {
            Query deleteDetailQuery = em.createQuery("DELETE FROM IoDetail io WHERE io.ioWarehouse.id = :ware");
            deleteDetailQuery.setParameter("ware", warehouse.getId()).executeUpdate();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<IoWarehouse> getAllIoWarehouses() {
        try {
            return em.createNamedQuery("IoWarehouse.findAll").getResultList();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public IoWarehouse findIoWarehouselById(Long id) {
        try {
            return (IoWarehouse) em.createNamedQuery("IoWarehouse.findById").setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Customer findCustomerByPhone(String phone) {
        try {
            return (Customer) em.createNamedQuery("Customer.findByPhone").setParameter("phone", phone).getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<IoWarehouse> getIoWarehousesByStore(Store store) {
        try {
            Query selectQuery = em.createQuery("SELECT iw FROM IoWarehouse iw WHERE iw.storeId = :store").setParameter("store", store);
            return selectQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<IoDetail> getIoDetailById(Long id) {
        try {
            IoWarehouse ware = (IoWarehouse) em.createNamedQuery("IoWarehouse.findById").setParameter("id", id).getSingleResult();
            return em.createNamedQuery("IoDetail.findByWarehouseId").setParameter("warehouseId", ware.getId()).getResultList();   
        } catch (Exception e) {
            return null;
        }   
    }

    @Override
    public boolean deleteOrderByUser(User user) {
        for(Order1 od : user.getOrder1Collection()) {
            this.deleteOrderDetail(od);
        }
        Query deleteOrder = em.createQuery("DELETE FROM Order1 o WHERE o.staffId = :staff");
        deleteOrder.setParameter("staff", user).executeUpdate();
        return true;
    }

    @Override
    public boolean deleteIoWarehouseByUser(User user) {
        for(IoWarehouse iw : user.getIoWarehouseCollection()) {
            this.deleteIoDetail(iw);
        }
        Query deleteIo = em.createQuery("DELETE FROM IoWarehouse i WHERE i.staffId = :staff");
        deleteIo.setParameter("staff", user).executeUpdate();
        return true;
    }   
    

}
