package backend.tpservices.Modules.Order;

import backend.tpservices.Modules.General.ResponseObjects.ErrorObject;
import backend.tpservices.Modules.General.ResponseObjects.ResponseObject;
import backend.tpservices.Modules.General.ResponseObjects.SuccessObject;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.io.InvalidObjectException;
import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping()
    private ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders().orElseThrow(NoResultException::new);

        return orders.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT):
                new ResponseEntity<>(orders, HttpStatus.OK);

    }

    @GetMapping(value = "/{orderId}")
    private ResponseEntity<Order> getOrderById(@PathVariable final Long orderId) throws NoSuchObjectException {
        Order order = orderService
                .getOrderById(orderId)
                .orElseThrow(() -> new NoSuchObjectException("Order with id = "+orderId+" not found"));

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<Order> addOrder(@RequestBody Order order) throws InvalidObjectException, NoSuchObjectException {

        order.verifyFields();

        orderService.insertOrderToDb(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping(value = "/{orderId}")
    private ResponseEntity<ResponseObject> modifyOrder(@PathVariable final Long orderId, @RequestBody Order order){

        order.verifyFields();
        order.setId(orderId);
        Order updatedOrder = orderService.modifyOrder(order);

        if(updatedOrder == null){
            return new ResponseEntity<>(new ErrorObject(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new SuccessObject(updatedOrder), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{orderId}")
    private ResponseEntity<SuccessObject> deleteOrder(@PathVariable final Long orderId) throws NoSuchObjectException {

        orderService.deleteOrder(orderId);


        SuccessObject success = new SuccessObject(HttpStatus.OK,
                "User with id = " + orderId + " successfully deleted");
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PutMapping(value = "/assign/orderId/{orderId}/courierId/{courierId}")
    private ResponseEntity<ResponseObject> assignOrderToCourier(@PathVariable final Long orderId, @PathVariable final Long courierId) throws NoSuchObjectException {

        orderService.assignOrderToCourier(orderId, courierId);

        return new ResponseEntity<>(new SuccessObject("Order "+orderId+" assigned to courier "+courierId), HttpStatus.OK);
    }
}
