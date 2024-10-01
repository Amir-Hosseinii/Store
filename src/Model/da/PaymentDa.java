package Model.da;

import Model.entity.Payment;

import java.util.Collections;
import java.util.List;

public class PaymentDa implements DataAccess<Payment , Integer>{
    @Override
    public void save(Payment payment) throws Exception {

    }

    @Override
    public void edit(Payment payment) throws Exception {

    }

    @Override
    public void remove(Integer id) throws Exception {

    }

    @Override
    public List<Payment> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Payment findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
