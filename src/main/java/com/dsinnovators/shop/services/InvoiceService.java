package com.dsinnovators.shop.services;

import com.dsinnovators.shop.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

}
