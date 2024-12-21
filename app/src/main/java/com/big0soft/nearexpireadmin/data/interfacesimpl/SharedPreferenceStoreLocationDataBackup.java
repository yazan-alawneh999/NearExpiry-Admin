package com.big0soft.nearexpireadmin.data.interfacesimpl;


import com.big0soft.nearexpireadmin.data.requests.LocationDetailsRequest;
import com.big0soft.nearexpireadmin.domain.interfaces.DataBackup;
import com.big0soft.resource.data.SharedPreferenceDao;

public class SharedPreferenceStoreLocationDataBackup implements DataBackup<LocationDetailsRequest> {
    private final SharedPreferenceDao dao;
    private static final String KEY_STORE_INFO = "store_info";

    public SharedPreferenceStoreLocationDataBackup(SharedPreferenceDao dao) {
        this.dao = dao;
    }

    @Override
    public void backup(LocationDetailsRequest LocationDetailsRequest) {
        dao.putObject(KEY_STORE_INFO, LocationDetailsRequest);
    }

    @Override
    public LocationDetailsRequest restore() {
        return (LocationDetailsRequest) dao.getObject(KEY_STORE_INFO, new LocationDetailsRequest());
    }

    @Override
    public void clean() {
        dao.clear();
    }
}
