package be.inniger.scratch.sql.service;

import be.inniger.scratch.sql.dao.AddressDao;
import be.inniger.scratch.sql.model.Address;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
public class AddressService {

    private final AddressDao dao;

    public void save(Address address) {
        dao.save(address);
    }
}
