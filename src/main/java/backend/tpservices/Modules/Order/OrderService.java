package backend.tpservices.Modules.Order;

import backend.tpservices.Modules.Courier.Courier;
import backend.tpservices.Modules.Courier.CourierRepository;
import backend.tpservices.Modules.Order.Order;
import backend.tpservices.Modules.Order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CourierRepository courierRepository;

    public Optional<List<Order>> getAllOrders(){
        List<Order> orders = new ArrayList<>();

        orderRepository.findAll().forEach(orders::add);
        return Optional.ofNullable(orders.isEmpty()? null : orders);
    }

    public void insertOrderToDb(Order order){
        orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long id) { return orderRepository.findById(id); }

    public void modifyOrder(Order order){
        Optional<Order> dbOrder = orderRepository.findById(order.getId());
        if(dbOrder.isEmpty()) { return; }

        dbOrder.get().update(order);
        orderRepository.save(dbOrder.get());
    }

    public void deleteOrder(Long id) throws NoSuchObjectException {
        Optional<Order> dbOrder = orderRepository.findById(id);
        if(dbOrder.isEmpty()) { throw new NoSuchObjectException("Order id: "+id+" not found"); }

        orderRepository.delete(dbOrder.get());
    }

    public void assignOrderToCourier(Long orderId, Long courierId) throws NoSuchObjectException {
        Optional<Order> dbOrder = orderRepository.findById(orderId);
        if(dbOrder.isEmpty()) { throw new NoSuchObjectException("Order id: "+orderId+" not found"); }

        Optional<Courier> dbCourier = courierRepository.findById(courierId);
        if(dbCourier.isEmpty()) { throw new NoSuchObjectException("Courier id: "+courierId+" not found"); }

        dbCourier.get().getOrders().add(dbOrder.get());
        courierRepository.save(dbCourier.get());
    }
}
