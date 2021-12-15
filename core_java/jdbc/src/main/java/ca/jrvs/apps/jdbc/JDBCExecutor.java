package ca.jrvs.apps.jdbc;

import org.apache.log4j.BasicConfigurator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCExecutor {
    public static void main(String[] args) {
        BasicConfigurator.configure();

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "hplussport", "adaobi", "psql1234");

        try {
            Connection connection = dcm.getConnection();

            System.out.println("Customer information");
            CustomerDAO customerDAO = new CustomerDAO(connection);

            Customer customer = new Customer();
            customer.setFirstName("Chandler");
            customer.setLastName("Lee");
            customer.setEmail("chands@email.com");
            customer.setPhone("(437) 223-598");
            customer.setAddress("3987 Green Street");
            customer.setCity("London");
            customer.setState("Ontario");
            customer.setZipCode("HIJ 423");

            Customer dbCustomer = customerDAO.create(customer);
            System.out.println(dbCustomer);

            dbCustomer = customerDAO.findById(dbCustomer.getId());
            System.out.println(dbCustomer);

            dbCustomer.setEmail("chandler@email.com");
            dbCustomer = customerDAO.update(dbCustomer);
            System.out.println(dbCustomer);

            customerDAO.delete(dbCustomer.getId());

            List<Customer> customers = customerDAO.findAllSorted(20);
            customers.forEach(System.out::println);

            System.out.println("Customer list paged");
            for (int i = 1; i < 3; i++) {
                System.out.println("Page number: " +i);
                customerDAO.findAllPaged(10, i).forEach(System.out::println);
            }

            System.out.println("Order information");
            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = orderDAO.findById(1000);
            System.out.println(order);

            System.out.println("Order information for customer with id = 789");
            List<Order> orders = orderDAO.getOrdersForCustomer(789);
            orders.forEach(System.out::println);

        } catch (SQLException ex) {
            dcm.logger.error("An error occurred", ex);
        }
    }
}
