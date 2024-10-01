package Model.da;

import Model.entity.InvoiceItem;

import java.util.Collections;
import java.util.List;

public class InvoiceItemDa implements DataAccess<InvoiceItem , Integer>{
    @Override
    public void save(InvoiceItem invoiceItem) throws Exception {

    }

    @Override
    public void edit(InvoiceItem invoiceItem) throws Exception {

    }

    @Override
    public void remove(Integer id) throws Exception {

    }

    @Override
    public List<InvoiceItem> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public InvoiceItem findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
