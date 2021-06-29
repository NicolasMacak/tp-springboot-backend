package backend.tpservices.Modules.Courier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourierService {
    @Autowired
    CourierRepository courierRepository;

    public Optional<List<Courier>> getAllCouriers(){
        List<Courier> couriers = new ArrayList<>();

        courierRepository.findAll().forEach(couriers::add);
        return Optional.ofNullable(couriers.isEmpty()? null : couriers);
    }

    public Optional<Courier> getCourierById(Long id){
        return courierRepository.findById(id);
    }

    @Transactional
    public void insertCourierToDb(Courier courier){ courierRepository.save(courier); }

    @Transactional
    public boolean modifyCourier(Long id, Courier courier){
        Optional<Courier> dbCourier = courierRepository.findById(id);
        if(dbCourier.isEmpty()) { return false; }

        dbCourier.get().update(courier);
        courierRepository.save(dbCourier.get());
        return true;
    }
    @Transactional
    public boolean deleteCourier(Long id){
        Optional<Courier> dbCourier = courierRepository.findById(id);
        if(dbCourier.isEmpty()){ return false; }
        courierRepository.delete(dbCourier.get());
        return true;
    }
}
