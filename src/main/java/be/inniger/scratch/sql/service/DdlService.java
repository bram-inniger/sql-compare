package be.inniger.scratch.sql.service;

import be.inniger.scratch.sql.dao.DdlDao;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
public class DdlService {

    private final DdlDao dao;

    public void resetDB() {
        dao.enforceForeignKeys();
        dao.dropAddressTable();
        dao.dropPersonTable();
        dao.createPersonTable();
        dao.createAddressTable();
    }
}
