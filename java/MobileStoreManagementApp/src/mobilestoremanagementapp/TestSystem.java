/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobilestoremanagementapp;

import edu.tdt.entity.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author thuan
 */
public class TestSystem {
    private Properties props;
    private InitialContext ctx;

    public TestSystem()
    {
        readJNDI();
    }

    /**
    * Read the JNDI properties file
    */
    private void readJNDI()
    {
        props = new Properties();

        try
        {
            props.load(new FileInputStream("jndi.properties"));
        } catch(IOException e)
        {
            e.getMessage();
        }

        try
        {
            ctx = new InitialContext(props);
        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    /**
    * Construct and return the JNDI address of called class
    * @return String
    */
    private String getJNDI()
    {
        String appName = "";
        String moduleName = "MobileStoreSystemManagement";
        String distinctName = "";
        String sessionBeanName = MobileStoreSystemBean.class.getSimpleName();
        String viewClassName = MobileStoreSystemBeanRemote.class.getName() + "?stateful";

        return "ejb:"+appName+"/"+moduleName+"/"+distinctName+"/"+sessionBeanName+"!"+viewClassName;
    }

    /**
    * Show the GUI in console window
    */
    
    private void showMainMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("Mobile System Store Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. Store Management \n2. User Management \n3. Product Management \n4. Supplier Management \n5. Customer Management \n6. Order Management \n7. IO Warehouse Management \n8. Exit \nEnter Choice: ");
    }
    
    private void showStoreMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("Mobile Store Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List Store \n2. Add Store \n3. Edit Store \n4. Remove Store \n5. Exit \nEnter Choice: ");
    }
    
    private void showUserMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("Mobile User Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List User \n2. Add User \n3. Edit User \n4. Remove User \n5. List Role \n6. Add Role \n7. Remove Role \n8. Add User Role \n9. List User of Role \n10. List Role of User \n11. Delete User Role \n12. Exit \nEnter Choice: ");
    }
    
    private void showProductMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("Mobile Product Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List Product \n2. Add Product \n3. Edit Product \n4. Remove Product \n5. Exit \nEnter Choice: ");
    }
    
    private void showSupplierMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("Mobile Supplier Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List Supplier \n2. Add Supplier \n3. Edit Supplier \n4. Remove Supplier \n5. Exit \nEnter Choice: ");
    }
    
    private void showCustomerMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("Mobile Customer Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List Customer \n2. Add Customer \n3. Edit Customer \n4. Remove Customer \n5. Exit \nEnter Choice: ");
    }
    
    private void showOrderMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("Mobile Order Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List Order \n2. Add Order \n3. Edit Order \n4. Remove Order \n5. Order Detail \n6. Invoice Statistics \n7 Exit \nEnter Choice: ");
    }
    
    private void showIOWarehouseMenuGUI()
    {
        System.out.println("\n==================================");
        System.out.println("Mobile IO Warehouse Management");
        System.out.println("----------------------------------");
        System.out.print("Options: \n1. List IO Warehouse \n2. Add IO Warehouse \n3. Edit IO Warehouse \n4. Remove IO Warehouse \n5. Show Inventory Of Store \n6. Exit \nEnter Choice: ");
    }
    
    private void getAllStores()
    {
        try
        {
        // We can define another bean to access the MobileStoreSystemBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<Store> storesList = libBean2.getAllStores();

        // Print all stores
        if(storesList == null)
        {
            System.err.println("There are no stores in the system.\n");
            return;
        }

        System.out.println("==================================");
        System.out.println("------------List Store------------");
        System.out.println(String.format("%3s %2s %20s %12s %17s %10s %8s %8s %19s %11s %6s", "ID", "|", "Name", "|", "Address", "|", "Phone", "|","email", "|", "Employee"));
        System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------------"));
        for (Store s : storesList)
        {
            System.out.println(s.toString());
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllUsers()
    {
        try
        {
        // We can define another bean to access the MobileStoreSystemBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<User> usersList = libBean2.getAllUsers();

        // Print all books
        if(usersList.isEmpty())
        {
            System.err.println("There are no users in the system!\n");
            return;
        }
        String rolestr = "";
        System.out.println("==================================");
        System.out.println("------------List User-------------");
        System.out.println(String.format("%3s %2s %10s %5s %14s %8s %17s %10s %8s %8s %12s %10s %20s %12s %2s", "ID", "|", "Username", "|", "Fullname", "|", "Address", "|", "Phone", "|","email", "|", "Store", "|", "Role"));
        System.out.println(String.format("%s", "-----------------------------------------------------------------------------------------------------------------------------------------------------------------"));
        for (User u : usersList)
        {
            System.out.println(u.toString());
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllRoles()
    {
        try
        {
        // We can define another bean to access the MobileStoreSystemBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<Role> rolesList = libBean2.getAllRoles();

        // Print all books
        if(rolesList.isEmpty())
        {
            System.err.println("There are no roles in the system!\n");
            return;
        }

        System.out.println("==================================");
        System.out.println("------------List Role-------------");
        System.out.println(String.format("%3s %2s %10s %2s %5s", "STT", "|", "Role name", "|", "Employee"));
        System.out.println("-------------------------------");
        for (int i = 0; i < rolesList.size(); i++)
        {
            System.out.println(String.format("%3s %2s %10s %2s %5s", i, "|", rolesList.get(i).getRoleName().replaceAll("\\s\\s", ""), "|", rolesList.get(i).getUserCollection().size()));
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getUsersByRole(String role) {
        try
        {
            // We can define another bean to access the MobileStoreSystemBeanRemote
            MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
            List<User> usersList = libBean2.getUsersByRole(role);

            if(usersList.isEmpty())
            {
                System.out.println("There is no user in the role!\n");
                return;
            }

            System.out.println("==================================");
            System.out.println("----------List "+ role.replaceAll("\\s\\s", "") +" Role---------");
            System.out.println(String.format("%3s %2s %10s %5s %10s %12s %13s %14s %8s %8s %8s %14s %10s", "ID", "|", "Username", "|", "Fullname", "|", "Address", "|", "Phone", "|","email", "|", "Store"));
            System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------------------------------"));
            for (int i = 0; i < usersList.size(); i++)
            {
                System.out.println(usersList.get(i).toString());
            }
            System.out.println("----------------------------------");

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getRolesByUser(Long id) {
        try
        {
            // We can define another bean to access the MobileStoreSystemBeanRemote
            MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
            User user = libBean2.findUserById(id);
            List<Role> rolesList = (List<Role>) user.getRoleCollection();
            // Print all books
            if(user == null)
            {
                System.out.println("User is not exist in the system.\n");
                return;
            }

            System.out.println("==================================");
            System.out.println("--------List Role of "+ user.getUserName().replaceAll("\\s\\s", "") +"---------");
            for (int i = 0; i < rolesList.size(); i++)
            {
                System.out.println((i+1)+"\t"+rolesList.get(i).getRoleName());
            }
            System.out.println("----------------------------------");

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllProducts()
    {
        try
        {
            // We can define another bean to access the MobileStoreSystemBeanRemote
            MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
            List<Product> productsList = libBean2.getAllProducts();

            // Print all books
            if(productsList.isEmpty())
            {
                System.out.println("There is no product in the system!\n");
                return;
            }

            System.out.println("==================================");
            System.out.println("-----------List Product-----------");
            System.out.println(String.format("%3s %2s %17s %15s %10s %2s %11s %10s %8s %2s %10s %12s %12s %20s %4s %2s %18s %19s %5s", "ID", "|", "Name", "|", "Supplier", "|", "Operator", "|", "Memory", "|", "Color", "|","Screen", "|", "Camera(After-Before)", "|", "Pin", "|", "Price($)"));
            System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
            for (Product p : productsList)
            {
                System.out.println(p.toString());
            }
            System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllSuppliers()
    {
        try
        {
        // We can define another bean to access the MobileStoreSystemBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<Supplier> suppliersList = libBean2.getAllSuppliers();

        if(suppliersList.isEmpty())
        {
            System.out.println("There is no supplier in the system!\n");
            return;
        }

        System.out.println("==================================");
        System.out.println("-----------List Supplier------------");
        System.out.println(String.format("%3s %2s %10s %7s %17s %10s %8s %8s %13s %9s %6s", "ID", "|", "Name", "|", "Address", "|", "Phone", "|","email", "|", "Product"));
        System.out.println(String.format("%s", "--------------------------------------------------------------------------------------------------------------"));
        for (Supplier s : suppliersList)
        {
            System.out.println(s.toString());
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllCustomers()
    {
        try
        {
        // We can define another bean to access the MobileStoreSystemBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<Customer> customersList = libBean2.getAllCustomers();

        // Print all books
        if(customersList.isEmpty())
        {
            System.out.println("There is no customer in the system!\n");
            return;
        }

        System.out.println("==================================");
        System.out.println("----------List Customer-----------");
        System.out.println(String.format("%3s %2s %12s %8s %14s %11s %9s %7s %12s %13s %6s", "ID", "|", "Fullname", "|", "Address", "|", "Phone", "|","email", "|", "Order"));
        System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------"));
        for (Customer c : customersList)
        {
            System.out.println(c.toString());
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllOrders()
    {
        try
        {
        // We can define another bean to access the LibrarySessionBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<Order1> ordersList = libBean2.getAllOrders();

        // Print all books
        if(ordersList.isEmpty())
        {
            System.out.println("There is no order in the system!\n");
            return;
        }

        System.out.println("==================================");
        System.out.println("-----------List Order-------------");
        System.out.println(String.format("%3s %2s %12s %10s %14s %18s %10s %7s %8s %2s %5s", "ID", "|", "Cutomer", "|", "Store", "|", "Staff", "|","Total($)", "|", "Date"));
        System.out.println(String.format("%s", "-----------------------------------------------------------------------------------------------------------"));
        for (Order1 o : ordersList)
        {
            System.out.println(o.toString());
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void getAllIOWarehouses()
    {
        try
        {
        // We can define another bean to access the LibrarySessionBeanRemote
        MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
        List<IoWarehouse> warehousesList = libBean2.getAllIoWarehouses();

        // Print all books
        if(warehousesList.isEmpty())
        {
            System.out.println("There is no IO warehouse in the system!\n");
            return;
        }

        System.out.println("==================================");
        System.out.println("--------List IO Warehouse---------");
        System.out.println(String.format("%3s %2s %12s %20s %7s %7s %6s %6s %8s %2s %8s", "ID", "|", "Store", "|", "Staff", "|", "Date", "|","Total($)", "|", "Status"));
        System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------"));
        for (IoWarehouse i : warehousesList)
        {
            System.out.println(i.toString());
        }
        System.out.println();

        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    private void ViewOrderDetail(Long id) {
        try {
            int count = 0;
            MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
            Order1 order = libBean2.findOrderById(id);
            if(order == null)
            {
                System.out.println("There is no order in the system!\n");
                return;
            }
            System.out.println("==================================");
            System.out.println("-----------Order Detail-----------");
            System.out.println("Customer : " + order.getCustomerId().toString());
            System.out.println("Store : "+ order.getStoreId().getName());
            System.out.println("Staff : "+ order.getStaffId().getUserName());
            System.out.println("Date : "+ order.getCreatedAt());
            System.out.println("Product list : ");
            List<OrderDetail> oderList = libBean2.getOrderDetailById(order.getId());
            System.out.println(String.format("%3s %2s %10s %17s %6s %2s %5s %2s %5s", "STT", "|", "Product", "|", "Price($)", "|", "Quantity", "|", "Sum($)"));
            System.out.println(String.format("%s", "---------------------------------------------------------------------"));
            for(int y = 0; y < oderList.size(); y++) {
                System.out.println(String.format("%3s %2s %25s %2s %6s %4s %5s %5s %5s", (y+1), "|", oderList.get(y).getProduct().getName().replaceAll("\\s\\s", ""), "|", oderList.get(y).getPrice(), "|", oderList.get(y).getQuantity(), "|", (oderList.get(y).getPrice() * oderList.get(y).getQuantity())));
            }
            System.out.println("Total($) : "+ order.getTotal());
            System.out.println("Note : "+ order.getNote());
        }catch (Exception e) {
            e.getMessage();
        }
    }
    private void getInvoiceStatisticsByStore(Long id) {
        try {
            MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
            Store store = libBean2.findStoreById(id);
            if(store == null) {
                System.out.println("The store is not exist in the system.");
                return;
            }
            List<Order1> orderList = libBean2.getOrderByStore(store);
            if(orderList == null) {
                System.out.println("The store has not any order.");
                return;
            }
            List<OrderDetail> detailList = new ArrayList();
            int sum, total =0;
            boolean exist;
            for(Order1 o : orderList) {
                for(OrderDetail od1 : libBean2.getOrderDetailById(o.getId())) {
                    exist = false;
                    for(OrderDetail od2 : detailList) {
                        if(od2.getProduct().getId() == od1.getProduct().getId()) {
                            //Sum quantity of product if exist in list
                            od2.setQuantity(od2.getQuantity() + od1.getQuantity());
                            exist = true;
                        }
                    }
                    if(!exist) {
                        //Add new product if not exist
                        detailList.add(od1);
                    }
                }
            }
            System.out.println("Store : " + store.toString());
            System.out.println("Product list : ");
            System.out.println(String.format("%3s %2s %11s %16s %5s %3s %5s %2s %3s", "STT", "|", "Product", "|", "Price($)", "|", "Quantity", "|", "Sum($)"));
            System.out.println(String.format("%s", "-------------------------------------------------------------------"));
            for(int y = 0; y < detailList.size(); y++) {
                sum = detailList.get(y).getQuantity() * detailList.get(y).getPrice();
                total += sum;
                System.out.println(String.format("%3s %2s %25s %2s %6s %2s %5s %5s %2s", (y+1), "|", detailList.get(y).getProduct().getName().replaceAll("\\s\\s", ""), "|", detailList.get(y).getPrice(), "|", detailList.get(y).getQuantity(), "|", sum));
            }
            System.out.println("Total($) : " +total);
        } catch (Exception e) {
            e.getMessage();
        }
    }
    private void getInventoryByStore(Long id) {
        try {
            MobileStoreSystemBeanRemote libBean2 = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
            Store store = libBean2.findStoreById(id);
            if(store == null) {
                System.out.println("The store is not exist in the system.");
                return;
            }
            //List input/output warehouse
            List<IoWarehouse> wareList = libBean2.getIoWarehousesByStore(store);
            if(wareList == null) {
                System.out.println("The store has not io warehouse.");
                return;
            }
            List<IoDetail> inventory = new ArrayList();
            int sum, total=0;
            boolean exist;
            for(IoWarehouse iw : wareList) {
                for(IoDetail d1 : libBean2.getIoDetailById(iw.getId())) {
                    exist = false;
                    for(IoDetail d2 : inventory) {
                        if(d2.getProduct().getId() == d1.getProduct().getId()) {
                            //Sum quantity of product if exist in list
                            if(iw.getImport1()){
                                d2.setQuantity(d2.getQuantity() + d1.getQuantity());
                            } else {
                                d2.setQuantity(d2.getQuantity() - d1.getQuantity());
                            }
                            exist = true;
                        }
                    }
                    if(!exist) {
                        //Add new product if not exist
                        inventory.add(d1);
                    }
                }
            }
            System.out.println("Store : " + store.toString());
            System.out.println("Product list : ");
            System.out.println(String.format("%3s %2s %12s %15s %8s %2s %8s %2s %5s", "STT", "|", "Product", "|", "Price($)", "|", "Quantity", "|", "Sum($)"));
            System.out.println(String.format("%s", "--------------------------------------------------------------------"));
            for(int y = 0; y < inventory.size(); y++) {
                sum = inventory.get(y).getPrice() * inventory.get(y).getQuantity();
                total += sum;
                System.out.println(String.format("%3s %2s %25s %2s %6s %4s %6s %4s %5s", (y+1), "|", inventory.get(y).getProduct().getName().replaceAll("\\s\\s", ""), "|", inventory.get(y).getPrice(), "|", inventory.get(y).getQuantity(), "|", sum));
            }
            System.out.println("Total($) : " + total);
        }catch(Exception e) {
            e.getMessage();
        }
    }
    
    /**
    * Test the Stateless EJB
    */
    public void testEntityEJB()
    {
        User current_user = null;
        try
        {
            Scanner sc = new Scanner(System.in);

            // Lookup the SystemManagementRemote
            MobileStoreSystemBeanRemote session = (MobileStoreSystemBeanRemote) ctx.lookup(getJNDI());
            // Log-in
            String _username, _password;
            System.out.println("Log-in to TDT System Management");
            System.out.print("Username: ");
            _username = sc.nextLine();
            System.out.print("Password: ");
            _password = sc.nextLine();
            current_user = session.Login(_username, _password);
            if(current_user == null)
            {
                System.err.println("Wrong username/password!");
                return;
            }
            
            System.out.println("==================================");
            System.out.println("current user : " + current_user.getUserName());
            List<Role> userRole = (List<Role>) current_user.getRoleCollection();
            for(Role r : userRole) {
                System.out.println(r.getRoleName().replaceAll("\\s\\s", ""));
            }
            System.out.println("----------------------------------");

            
            // Show GUI
            int choice;
            
            do
            {
                showMainMenuGUI();
                choice = Integer.parseInt(sc.nextLine());
                switch(choice)
                {
                    case 1: //Store Management
                        Long store_id;
                        String store_name, store_address, store_phone, store_email;
                        Boolean store_status;
                        int storeChoice;
                        do{
                            showStoreMenuGUI();
                            storeChoice = Integer.parseInt(sc.nextLine());
                            switch(storeChoice)
                            {
                                case 1: // List store
                                    getAllStores();
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 2: // Add store
                                    System.out.print("Enter store name : ");
                                    store_name = sc.nextLine();
                                    System.out.print("Enter store address : ");
                                    store_address = sc.nextLine();
                                    System.out.print("Enter store phone : ");
                                    store_phone = sc.nextLine();
                                    System.out.print("Enter store email : ");
                                    store_email = sc.nextLine();
                                    Store store_created = session.insertStore(store_name, store_address, store_phone, store_email);
                                    if(store_created != null) { 
                                        System.out.println("Create Successful!");
                                    }else
                                    {
                                        System.out.println("Create Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;

                                case 3: // Update Store
                                    System.out.print("Enter store id : ");
                                    store_id = Long.parseLong(sc.nextLine());
                                    Store finded = session.findStoreById(store_id);
                                    if(finded == null)
                                    {
                                        System.out.println("The store id is not exist.");
                                        break;
                                    }
                                    System.out.print("Enter store new name : ");
                                    store_name = sc.nextLine();
                                    System.out.print("Enter store new address : ");
                                    store_address = sc.nextLine();
                                    System.out.print("Enter store new phone : ");
                                    store_phone = sc.nextLine();
                                    System.out.print("Enter store new email : ");
                                    store_email = sc.nextLine();
                                    System.out.print("Enter store new status : ");
                                    store_status = Boolean.parseBoolean(sc.nextLine());
                                    boolean res = session.updateStore(store_id, store_name, store_address, store_phone, store_email, store_status);
                                    if(res == true)
                                    {
                                        System.out.println("Update Successful!");
                                    }else
                                    {
                                        System.out.println("Update Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;

                                case 4: // Delete store
                                    System.out.print("Enter id of store : ");
                                    store_id = Long.parseLong(sc.nextLine());
                                    if(session.deleteStore(store_id))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                   
                                default:
                                    break;
                            }
                        } while(storeChoice != 5);
                        break;             
                                    
                    case 2: //User Management
                        Long store_id_new, user_id;
                        String user_name, user_address, user_phone, user_email, user_password, full_name, user_role;
                        Boolean user_status;
                        int userChoice;
                        
                        do{
                            showUserMenuGUI();
                            userChoice = Integer.parseInt(sc.nextLine());
                            switch(userChoice) {
                                case 1: //List User
                                    getAllUsers();
                                    System.out.println("----------------------------------");
                                    break;
                                                                        
                                case 2: //Add User
                                    System.out.print("Enter username : ");
                                    user_name = sc.nextLine();
                                    System.out.print("Enter password : ");
                                    user_password = sc.nextLine();
                                    System.out.print("Enter full name : ");
                                    full_name = sc.nextLine();
                                    System.out.print("Enter user address : ");
                                    user_address = sc.nextLine();
                                    System.out.print("Enter user phone : ");
                                    user_phone = sc.nextLine();
                                    getAllStores();
                                    System.out.print("Enter store id : ");
                                    store_id_new = Long.parseLong(sc.nextLine());
                                    System.out.print("Enter user email : ");
                                    user_email = sc.nextLine();
                                    System.out.print("Enter role name : ");
                                    user_role = sc.nextLine();

                                    User createdUser = session.insertUser(user_name, user_password, full_name, user_address, user_phone, store_id_new, user_email);
                                    session.insertUserRole(createdUser.getId(), user_role.toUpperCase());
                                    if(createdUser != null)
                                    {
                                        System.out.println("Create Successful!");
                                    }else
                                    {
                                        System.out.println("Create Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 3: //Edit User
                                    System.out.print("Enter user id : ");
                                    user_id = Long.parseLong(sc.nextLine());
                                    User user_finded = session.findUserById(user_id);
                                    if(user_finded == null)
                                    {
                                        System.out.println("The ID is not exist.");
                                        break;
                                    }
                                    System.err.println(user_finded.toString());
                                    System.out.print("Enter new password : ");
                                    user_password = sc.nextLine();
                                    System.out.print("Enter new full name : ");
                                    full_name = sc.nextLine();
                                    System.out.print("Enter new address : ");
                                    user_address = sc.nextLine();
                                    System.out.print("Enter new phone : ");
                                    user_phone = sc.nextLine();
                                    getAllStores();
                                    System.out.print("Enter new store id : ");
                                    store_id_new = Long.parseLong(sc.nextLine());
                                    System.out.print("Enter new email : ");
                                    user_email = sc.nextLine();
                                    System.out.print("Enter new status : ");
                                    user_status = Boolean.parseBoolean(sc.nextLine());
                                    boolean res = session.updateUser(user_id, user_password, full_name, user_address, user_phone, store_id_new, user_email, user_status);
                                    if(res == true)
                                    {
                                        System.out.println("Update Successful!");
                                    }else
                                    {
                                        System.out.println("Update Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 4: //Remove User
                                    System.out.print("Enter user id : ");
                                    user_id = Long.parseLong(sc.nextLine());
                                    if(session.deleteUser(user_id))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                   
                                case 5: // List Role
                                    getAllRoles();
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 6: //Add Role
                                    System.out.print("Enter role: ");
                                    user_role = sc.nextLine();
                                    boolean role_res = session.insertRole(user_role.toUpperCase());
                                    if(role_res)
                                    {
                                        System.out.println("Add Successful!");
                                    }else
                                    {
                                        System.out.println("Add Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                case 7: //Remove Role
                                    System.out.print("Enter role: ");
                                    user_role = sc.nextLine();
                                    if(session.deleteRole(user_role.toUpperCase()))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 8: //Add User Role
                                    System.out.print("Enter user id : ");
                                    user_id = Long.parseLong(sc.nextLine());
                                    System.out.print("Enter role for user : ");
                                    user_role = sc.nextLine();
                                    boolean user_res = session.insertUserRole(user_id, user_role.toUpperCase());
                                    if(user_res)
                                    {
                                        System.out.println("Add Successful!");
                                    }else
                                    {
                                        System.out.println("Add Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                 
                                case 9: //List User of Role
                                    System.out.print("Enter role name : ");
                                    user_role = sc.nextLine();
                                    getUsersByRole(user_role.toUpperCase());
                                    break;
                                    
                                case 10: //list Role of User
                                    System.out.print("Enter user id : ");
                                    user_id = Long.parseLong(sc.nextLine());
                                    getRolesByUser(user_id);
                                    break;
                                    
                                case 11: //Delete User Role
                                    System.out.print("Enter user id : ");
                                    user_id = Long.parseLong(sc.nextLine());
                                    System.out.print("Enter role for user : ");
                                    user_role = sc.nextLine();
                                    
                                    if(session.deleteUserRole(user_id, user_role.toUpperCase()))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                default:
                                    break;
                            }
                        } while(userChoice != 12);
                        
                        break;
                        
                    case 3: // Product Management
                        Long product_id, product_supplier;
                        String product_name, product_color, product_operator, product_screen, product_pin, product_description;
                        int product_camera1,product_camera2, product_memory, product_price, product_warranty;
                        Boolean product_status;
                        Product product;
                        
                        int productChoice;
                        do{
                            showProductMenuGUI();
                            productChoice = Integer.parseInt(sc.nextLine());
                            switch(productChoice)
                            {
                                case 1: //List product
                                    getAllProducts();
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 2: //Add product
                                    System.out.print("Enter phone name : ");
                                    product_name = sc.nextLine();
                                    getAllSuppliers();
                                    System.out.print("Enter supplier id : ");
                                    product_supplier = Long.parseLong(sc.nextLine());
                                    System.out.print("Enter after camera : ");
                                    product_camera1 = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter before camera : ");
                                    product_camera2 = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter color : ");
                                    product_color = sc.nextLine();
                                    System.out.print("Enter memory : ");
                                    product_memory= Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter operator : ");
                                    product_operator = sc.nextLine();
                                    System.out.print("Enter warranty : ");
                                    product_warranty = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter screen : ");
                                    product_screen = sc.nextLine();
                                    System.out.print("Enter pin : ");
                                    product_pin = sc.nextLine();
                                    System.out.print("Enter price : ");
                                    product_price = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter Description : ");
                                    product_description = sc.nextLine();
                                    
                                    Product product_created = session.insertProduct(product_name, product_supplier, product_camera1, product_camera2, product_color, product_description, product_memory, product_operator, product_price, product_warranty, product_screen, product_pin);
                                    if(product_created != null) {
                                        System.out.println("Create successfull");
                                    } else {
                                        System.out.println("Create faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 3: //Edit product
                                    System.out.print("Enter product id : ");
                                    product_id = Long.parseLong(sc.nextLine());
                                    product = session.findProductById(product_id);
                                    if(product == null)
                                    {
                                        System.out.println("The ID is not exist.");
                                        break;
                                    }
                                    System.err.println(product.toString());
                                    System.out.print("Enter new phone name : ");
                                    product_name = sc.nextLine();
                                    getAllSuppliers();
                                    System.out.print("Enter new supplier id :");
                                    product_supplier = Long.parseLong(sc.nextLine());
                                    System.out.print("Enter new after camera : ");
                                    product_camera1 = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter new before camera : ");
                                    product_camera2 = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter color : ");
                                    product_color = sc.nextLine();
                                    System.out.print("Enter new memory : ");
                                    product_memory= Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter new operator : ");
                                    product_operator = sc.nextLine();
                                    System.out.print("Enter new warranty : ");
                                    product_warranty = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter new screen : ");
                                    product_screen = sc.nextLine();
                                    System.out.print("Enter new pin : ");
                                    product_pin = sc.nextLine();
                                    System.out.print("Enter new price : ");
                                    product_price = Integer.parseInt(sc.nextLine());
                                    System.out.print("Enter new description : ");
                                    product_description = sc.nextLine();
                                    System.out.print("Enter new status : ");
                                    product_status = Boolean.parseBoolean(sc.nextLine());
                                    
                                    boolean product_res = session.updateProduct(product_id, product_name, product_supplier, product_camera1, product_camera2, product_color, product_description, product_memory, product_operator, product_price, product_warranty, product_screen, product_pin, product_status);
                                    if(product_res) {
                                        System.out.println("Update successfull");
                                    } else {
                                        System.out.println("Update faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 4: //Remove product
                                    System.out.print("Enter product id : ");
                                    product_id = Long.parseLong(sc.nextLine());
                                    if(session.deleteProduct(product_id))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                default:
                                    break;
                            }
                        } while(productChoice != 5);
                        
                        break;
                        
                    case 4: //Supplier Management
                        Long supplier_id;
                        String supplier_name, supplier_address, supplier_phone, supplier_email;
                        Boolean supplier_status, supplier_res;
                        Supplier supplier;
                        int supplierChoice;
                        do{
                            showSupplierMenuGUI();
                            supplierChoice = Integer.parseInt(sc.nextLine());
                            switch(supplierChoice)
                            {
                                case 1: //List Supplier
                                    getAllSuppliers();
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 2: //Add Supplier
                                    System.out.print("Enter supplier name : ");
                                    supplier_name = sc.nextLine();
                                    System.out.print("Enter supplier address : ");
                                    supplier_address = sc.nextLine();
                                    System.out.print("Enter supplier phone : ");
                                    supplier_phone = sc.nextLine();
                                    System.out.print("Enter supplier email : ");
                                    supplier_email = sc.nextLine();
                                    Supplier supplier_created = session.insertSupplier(supplier_name, supplier_address, supplier_phone, supplier_email);
                                    if(supplier_created != null) {
                                        System.out.println("Create successfull");
                                    } else {
                                        System.out.println("Create faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 3: //Edit Supplier
                                    System.out.print("Enter supplier id : ");
                                    supplier_id = Long.parseLong(sc.nextLine());
                                    supplier = session.findSupplierById(supplier_id);
                                    if(supplier == null)
                                    {
                                        System.out.println("The ID is not exist.");
                                        break;
                                    }
                                    System.out.print("Enter supplier name : ");
                                    supplier_name = sc.nextLine();
                                    System.out.print("Enter supplier address : ");
                                    supplier_address = sc.nextLine();
                                    System.out.print("Enter supplier phone : ");
                                    supplier_phone = sc.nextLine();
                                    System.out.print("Enter supplier email : ");
                                    supplier_email = sc.nextLine();
                                    System.out.print("Enter supplier status : ");
                                    supplier_status = Boolean.parseBoolean(sc.nextLine());
                                    supplier_res = session.updateSupplier(supplier_id, supplier_name, supplier_address, supplier_phone, supplier_email, supplier_status);
                                    if(supplier_res) {
                                        System.out.println("Update successfull");
                                    } else {
                                        System.out.println("Update faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 4: //Remove Supplier
                                    System.out.print("Enter supplier id : ");
                                    supplier_id = Long.parseLong(sc.nextLine());
                                    if(session.deleteSupplier(supplier_id))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                default:
                                    break;
                            }
                        } while(supplierChoice != 5);
                        
                        break;
                        
                    case 5: //Customer Management
                        Long customer_id;
                        String customer_fullname, customer_address, customer_phone, customer_email;
                        Boolean customer_res;
                        Customer customer;
                        int customerChoice;
                        do{
                            showCustomerMenuGUI();
                            customerChoice = Integer.parseInt(sc.nextLine());
                            switch(customerChoice)
                            {
                                case 1: //List Customer
                                    getAllCustomers();
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 2: //Add Customer
                                    System.out.print("Enter customer name : ");
                                    customer_fullname = sc.nextLine();
                                    System.out.print("Enter customer address : ");
                                    customer_address = sc.nextLine();
                                    System.out.print("Enter customer phone : ");
                                    customer_phone = sc.nextLine();
                                    System.out.print("Enter customer email : ");
                                    customer_email = sc.nextLine();
                                    Customer customer_created = session.insertCustomer(customer_fullname, customer_address, customer_phone, customer_email);
                                    if(customer_created != null) {
                                        System.out.println("Create successfull");
                                    } else {
                                        System.out.println("Create faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 3: //Edit Customer
                                    System.out.print("Enter customer id : ");
                                    customer_id = Long.parseLong(sc.nextLine());
                                    customer = session.findCustomerById(customer_id);
                                    if(customer == null)
                                    {
                                        System.out.println("The ID is not exist.");
                                        break;
                                    }
                                    System.err.println(customer.toString());
                                    System.out.print("Enter customer new name : ");
                                    customer_fullname = sc.nextLine();
                                    System.out.print("Enter customer new address : ");
                                    customer_address = sc.nextLine();
                                    System.out.print("Enter customer new phone : ");
                                    customer_phone = sc.nextLine();
                                    System.out.print("Enter customer new email : ");
                                    customer_email = sc.nextLine();
                                    customer_res = session.updateCustomer(customer_id, customer_fullname, customer_address, customer_phone, customer_email);
                                    if(customer_res) {
                                        System.out.println("Update successfull");
                                    } else {
                                        System.out.println("Update faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 4: //Remove Customer
                                    System.out.print("Enter customer id : ");
                                    customer_id = Long.parseLong(sc.nextLine());
                                    if(session.deleteCustomer(customer_id))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                default:
                                    break;
                            }
                        } while(customerChoice != 5);
                        break;
                        
                    case 6: //Order Management
                        Long order_id, order_customer_id, order_staff_id, order_store_id, order_product_id;
                        boolean order_has, order_res;
                        String order_customer_fullname, order_customer_address, order_customer_phone, order_customer_email, order_note;
                        int orderChoice, order_next, order_quantity;
                        Store store_finded;
                        Order1 order_finded;
                        List<OrderDetail> productlist = new ArrayList();
                        do{
                            showOrderMenuGUI();
                            orderChoice = Integer.parseInt(sc.nextLine());
                            switch(orderChoice)
                            {
                                case 1: //List Order
                                    getAllOrders();
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 2: //Add Order
                                    productlist = new ArrayList();
                                    System.out.print("Enter customer name : ");
                                    order_customer_fullname = sc.nextLine();
                                    System.out.print("Enter customer address :");
                                    order_customer_address = sc.nextLine();
                                    System.out.print("Enter customer phone : ");
                                    order_customer_phone = sc.nextLine();
                                    System.out.print("Enter customer email : ");                                   
                                    order_customer_email = sc.nextLine();
                                    getAllStores();
                                    System.out.print("Enter store id : ");
                                    order_store_id  = Long.parseLong(sc.nextLine());
                                    store_finded = session.findStoreById(order_store_id);
                                    if(store_finded == null) {
                                        System.out.println("The store is not exist!");
                                        break;
                                    }
                                    do {
                                        System.out.print("1. Add product.\n2. Delete product.\n3. Finish.\nEnter choise : ");
                                        order_next = Integer.parseInt(sc.nextLine());
                                        if(order_next == 1) {
                                            getAllProducts();
                                            System.out.print("Enter product id : ");
                                            order_product_id = Long.parseLong(sc.nextLine());
                                            Product p = session.findProductById(order_product_id);
                                            if(p == null) {
                                                System.out.println("The product is not exist!");
                                                break;
                                            }
                                            System.out.print("Enter quantity of "+ p.getName().replaceAll("\\s\\s", "") +" : ");
                                            order_quantity = Integer.parseInt(sc.nextLine());
                                            OrderDetail order = new OrderDetail();
                                            order.setProduct(p);
                                            order.setQuantity(order_quantity);
                                            order.setPrice(p.getPrice());

                                            order_has=false;
                                            for(OrderDetail o : productlist) {
                                                if(o.getProduct().equals(order.getProduct())) {
                                                    o.setQuantity(o.getQuantity() + order_quantity);
                                                    order_has = true;
                                                }
                                            }
                                            if(!order_has) {
                                                productlist.add(order); 
                                            }
                                            
                                        } else if(order_next == 2) {
                                            for(int i =0; i<productlist.size(); i++) {
                                                System.out.println(i +". "+ productlist.get(i).getProduct().getName() +" "+ productlist.get(i).getPrice() +" "+ productlist.get(i).getQuantity());
                                            }
                                            System.out.print("Enter product id : ");
                                            order_product_id = Long.parseLong(sc.nextLine());
                                            for (int j = 0; j<productlist.size(); j++) {
                                                if(productlist.get(j).getProduct().getId() == order_product_id)
                                                {
                                                    productlist.remove(j);
                                                }
                                            }
                                            
                                        } else {
                                            break;
                                        }
                                    }while(order_next != 3);
                                    for(int i =0; i<productlist.size(); i++) {
                                        System.out.println(i +". "+ productlist.get(i).getProduct().getName() +" "+ productlist.get(i).getPrice() +" "+ productlist.get(i).getQuantity());
                                    }
                                    System.out.print("Note : ");
                                    order_note = sc.nextLine();
                                    
                                    Order1 order_created = session.insertOrder(order_customer_fullname, order_customer_address, order_customer_phone, order_customer_email, store_finded, current_user, productlist, order_note);
                                    if(order_created != null) {
                                        System.out.println("Create successfull");
                                    } else {
                                        System.out.println("Create faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 3: //Edit Order
                                    productlist = new ArrayList();
                                    System.out.print("Enter order id : ");
                                    order_id = Long.parseLong(sc.nextLine());
                                    order_finded = session.findOrderById(order_id);
                                    if(order_finded == null) {
                                        System.out.println("The id is not exist!");
                                        break;
                                    }else {
                                        productlist.addAll(session.getOrderDetailById(order_finded.getId()));
                                    }
                                    System.err.println(order_finded.toString());
                                    System.out.print("Enter customer new name : ");
                                    order_customer_fullname = sc.nextLine();
                                    System.out.print("Enter customer new address :");
                                    order_customer_address = sc.nextLine();
                                    System.out.print("Enter customer new phone : ");
                                    order_customer_phone = sc.nextLine();
                                    System.out.print("Enter customer new email : ");                                   
                                    order_customer_email = sc.nextLine();
                                    getAllStores();
                                    System.out.print("Enter store new id : ");
                                    order_store_id  = Long.parseLong(sc.nextLine());
                                    store_finded = session.findStoreById(order_store_id);
                                    if(store_finded == null) {
                                        System.out.println("The store is not exist!");
                                        break;
                                    }
                                    do {
                                        System.out.print("1. Add product.\n2. Delete product.\n3. Finish.\nEnter choise : ");
                                        order_next = Integer.parseInt(sc.nextLine());
                                        if(order_next == 1) {
                                            getAllProducts();
                                            System.out.print("Enter product id : ");
                                            order_product_id = Long.parseLong(sc.nextLine());
                                            Product p = session.findProductById(order_product_id);
                                            if(p == null) {
                                                System.out.println("The product is not exist!");
                                                break;
                                            }
                                            System.out.print("Enter quantity of "+ p.getName().replaceAll("\\s\\s", "") +" : ");
                                            order_quantity = Integer.parseInt(sc.nextLine());
                                            OrderDetail order = new OrderDetail();
                                            order.setProduct(p);
                                            order.setQuantity(order_quantity);
                                            order.setPrice(p.getPrice());

                                            order_has=false;
                                            for(OrderDetail o : productlist) {
                                                if(o.getProduct().equals(order.getProduct())) {
                                                    o.setQuantity(o.getQuantity() + order_quantity);
                                                    order_has = true;
                                                }
                                            }
                                            if(!order_has) {
                                                productlist.add(order); 
                                            }
                                            
                                        } else if(order_next == 2) {
                                            for(int i = 0; i < productlist.size(); i++) {
                                                System.out.println(i +". "+ productlist.get(i).getProduct().getName() +" "+ productlist.get(i).getPrice() +" "+ productlist.get(i).getQuantity());
                                            }
                                            System.out.print("Enter product id : ");
                                            order_product_id = Long.parseLong(sc.nextLine());
                                            for (int j = 0; j<productlist.size(); j++) {
                                                if(productlist.get(j).getProduct().getId() == order_product_id)
                                                {
                                                    productlist.remove(j);
                                                }
                                            }
                                            
                                        } else {
                                            break;
                                        }
                                    }while(order_next != 3);
                                    for(int i = 0; i < productlist.size(); i++) {
                                        System.out.println(i +". "+ productlist.get(i).getProduct().getName() +" "+ productlist.get(i).getPrice() +" "+ productlist.get(i).getQuantity());
                                    }
                                    System.out.print("Note : ");
                                    order_note = sc.nextLine();
                                    order_res = session.updateOrder(order_id, order_customer_fullname, order_customer_address, order_customer_phone, order_customer_email, store_finded, current_user, productlist, order_note);
                                    if(order_res)
                                    {
                                        System.out.println("Update Successful!");
                                    }else
                                    {
                                        System.out.println("Update Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 4: //Remove Order
                                    System.out.print("Enter order id : ");
                                    order_id = Long.parseLong(sc.nextLine());
                                    if(session.deleteOrder(order_id))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                  
                                case 5: //Get order detail
                                    System.out.print("Enter order id : ");
                                    order_id = Long.parseLong(sc.nextLine());
                                    ViewOrderDetail(order_id);
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 6:
                                    System.out.print("Enter store id : ");
                                    order_id = Long.parseLong(sc.nextLine());
                                    getInvoiceStatisticsByStore(order_id);
                                    System.out.println("----------------------------------");
                                    break;
                                default:
                                    break;
                            }
                        } while(orderChoice != 7);
                        break;
                        
                    case 7: //IO Warehouse Management
                        int warehouseChoice;
                        Long w_id, w_store_id, w_staff_id, w_product_id;
                        Boolean w_import, w_has, w_res;
                        int w_price, w_quantity, w_next;
                        Product w_product;
                        Store w_store;
                        List<IoDetail> w_list;
                        IoWarehouse w_finded;
                        do{
                            showIOWarehouseMenuGUI();
                            warehouseChoice = Integer.parseInt(sc.nextLine());
                            switch(warehouseChoice)
                            {
                                case 1: //List IO Warehouse
                                    getAllIOWarehouses();
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 2: //Add IO Warehouse
                                    w_list = new ArrayList();
                                    System.out.print("Choise import(true) or export(false) :");
                                    w_import = Boolean.parseBoolean(sc.nextLine());
                                    getAllStores();
                                    System.out.print("Enter store id : ");
                                    w_store_id = Long.parseLong(sc.nextLine());
                                    w_store = session.findStoreById(w_store_id);
                                    if(w_store == null) {
                                        System.out.println("The store is not exist!");
                                        break;
                                    }
                                    do {
                                        System.out.print("1. Add product.\n2. Delete product.\n3. Finish.\nEnter choise : ");
                                        w_next = Integer.parseInt(sc.nextLine());
                                        if(w_next == 1) {
                                            getAllProducts();
                                            System.out.print("Enter product id : ");
                                            w_product_id = Long.parseLong(sc.nextLine());
                                            w_product = session.findProductById(w_product_id);
                                            if(w_product == null) {
                                                System.out.println("The product is not exist!");
                                                break;
                                            }
                                            if(w_import) {
                                                System.out.print("Enter price of "+w_product.getName().replaceAll("\\s\\s", "")+" : ");
                                                w_price = Integer.parseInt(sc.nextLine());
                                            } else {
                                                w_price = w_product.getPrice();
                                            }
                                            System.out.print("Enter quantity of "+w_product.getName().replaceAll("\\s\\s", "")+" : ");
                                            w_quantity = Integer.parseInt(sc.nextLine());
                                            
                                            IoDetail ware = new IoDetail();
                                            ware.setProduct(w_product);
                                            ware.setPrice(w_price);
                                            ware.setQuantity(w_quantity);

                                            w_has = false;
                                            for(IoDetail i : w_list) {
                                                if(i.getProduct().equals(ware.getProduct())) {
                                                    i.setQuantity(i.getQuantity() + w_quantity);
                                                    w_has = true;
                                                }
                                            }
                                            if(!w_has) {
                                                w_list.add(ware); 
                                            }
                                            
                                        } else if(w_next == 2) {
                                            for(int i = 0; i < w_list.size(); i++) {
                                                System.out.println(i +". "+ w_list.get(i).getProduct().getName() +" "+ w_list.get(i).getPrice() +" "+ w_list.get(i).getQuantity());
                                            }
                                            System.out.print("Enter product id : ");
                                            order_product_id = Long.parseLong(sc.nextLine());
                                            for (int j = 0; j < w_list.size(); j++) {
                                                if(w_list.get(j).getProduct().getId() == order_product_id)
                                                {
                                                    w_list.remove(j);
                                                }
                                            }
                                            
                                        } else {
                                            break;
                                        }
                                    }while(w_next != 3);
                                    IoWarehouse w_created = session.insertIoWarehouse(w_store, current_user, w_list, w_import);
                                    if(w_created != null) {
                                        System.out.println("Create successfull");
                                    } else {
                                        System.out.println("Create faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 3: //Edit IO Warehouse
                                    w_list = new ArrayList();
                                    System.out.print("Enter io warehouse id : ");
                                    w_id = Long.parseLong(sc.nextLine());
                                    w_finded = session.findIoWarehouselById(w_id);
                                    if(w_finded == null) {
                                        System.out.println("The id is not exist!");
                                        break;
                                    }else {
                                        w_list.addAll(session.getIoDetailById(w_finded.getId()));
                                    }
                                    System.out.print("Choise import(true) or export(false) :");
                                    w_import = Boolean.parseBoolean(sc.nextLine());
                                    System.out.print("Enter new store id : ");
                                    w_store_id = Long.parseLong(sc.nextLine());
                                    w_store = session.findStoreById(w_store_id);
                                    if(w_store == null) {
                                        System.out.println("The store is not exist!");
                                        break;
                                    }
                                    
                                    do {
                                        System.out.print("1. Add product.\n2. Delete product.\n3. Finish.\nEnter choise : ");
                                        w_next = Integer.parseInt(sc.nextLine());
                                        if(w_next == 1) {
                                            getAllProducts();
                                            System.out.print("Enter product id : ");
                                            w_product_id = Long.parseLong(sc.nextLine());
                                            w_product = session.findProductById(w_product_id);
                                            if(w_product == null) {
                                                System.out.println("The product is not exist!");
                                                break;
                                            }
                                            if(w_import) {
                                                System.out.print("Enter price of "+w_product.getName().replaceAll("\\s\\s", "")+" : ");
                                                w_price = Integer.parseInt(sc.nextLine());
                                            } else {
                                                w_price = w_product.getPrice();
                                            }
                                            System.out.print("Enter quantity of "+w_product.getName().replaceAll("\\s\\s", "")+" : ");
                                            w_quantity = Integer.parseInt(sc.nextLine());
                                            
                                            IoDetail ware = new IoDetail();
                                            ware.setProduct(w_product);
                                            ware.setPrice(w_price);
                                            ware.setQuantity(w_quantity);

                                            w_has = false;
                                            for(IoDetail i : w_list) {
                                                if(i.getProduct().equals(ware.getProduct())) {
                                                    i.setQuantity(i.getQuantity() + w_quantity);
                                                    w_has = true;
                                                }
                                            }
                                            if(!w_has) {
                                                w_list.add(ware); 
                                            }
                                            
                                        } else if(w_next == 2) {
                                            for(int i = 0; i < w_list.size(); i++) {
                                                System.out.println(i +". "+ w_list.get(i).getProduct().getName() +" "+ w_list.get(i).getPrice() +" "+ w_list.get(i).getQuantity());
                                            }
                                            System.out.print("Enter product id : ");
                                            order_product_id = Long.parseLong(sc.nextLine());
                                            for (int j = 0; j < w_list.size(); j++) {
                                                if(w_list.get(j).getProduct().getId() == order_product_id)
                                                {
                                                    w_list.remove(j);
                                                }
                                            }
                                            
                                        } else {
                                            break;
                                        }
                                    }while(w_next != 3);
                                    w_res = session.updateIoWarehouse(w_id, w_store, current_user, w_list, w_import);
                                    if(w_res) {
                                        System.out.println("Create successfull");
                                    } else {
                                        System.out.println("Create faild");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                    
                                case 4: //Remove IO Warehouse
                                    System.out.print("Enter io warehouse id : ");
                                    w_id = Long.parseLong(sc.nextLine());
                                    if(session.deleteIoWarehouse(w_id))
                                    {
                                        System.out.println("Delete Successful!");
                                    }else
                                    {
                                        System.out.println("Delete Faild!");
                                    }
                                    System.out.println("----------------------------------");
                                    break;
                                
                                case 5: //Show Inventory of Store
                                    System.out.print("Enter store id : ");
                                    w_store_id = Long.parseLong(sc.nextLine());
                                    getInventoryByStore(w_store_id);
                                    System.out.println("----------------------------------");
                                    break;
                                default:
                                    break;
                            }
                        } while(warehouseChoice != 6);
                        break;
                        
                    default:
                        break;
                        
                }
            } while(choice != 8);

        } catch (NamingException ex)
        {
            Logger.getLogger(TestSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
