package Model.da;

import Model.entity.Invoice;

import java.util.Collections;
import java.util.List;

public class InvoiceDa implements DataAccess<Invoice , Integer>{
    @Override
    public void save(Invoice invoice) throws Exception {

    }

    @Override
    public void edit(Invoice invoice) throws Exception {

    }

    @Override
    public void remove(Integer id) throws Exception {

    }

    @Override
    public List<Invoice> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Invoice findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
