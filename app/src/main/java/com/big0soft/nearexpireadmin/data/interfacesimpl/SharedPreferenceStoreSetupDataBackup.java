package com.big0soft.nearexpireadmin.data.interfacesimpl;


import com.big0soft.nearexpireadmin.data.requests.StoreSetupRequest;
import com.big0soft.nearexpireadmin.domain.interfaces.DataBackup;
import com.big0soft.resource.data.SharedPreferenceDao;

public class SharedPreferenceStoreSetupDataBackup implements DataBackup<StoreSetupRequest> {
    private final SharedPreferenceDao dao;
    private static final String KEY_STORE_INFO = "store_info";

    public SharedPreferenceStoreSetupDataBackup(SharedPreferenceDao dao) {
        this.dao = dao;
    }

    @Override
    public void backup(StoreSetupRequest storeInfoRequest) {
        dao.putString(KEY_STORE_INFO, storeInfoRequest.toJson())
                .apply();
    }

    @Override
    public StoreSetupRequest restore() {
        return (StoreSetupRequest) dao.getObject(KEY_STORE_INFO, new StoreSetupRequest());
    }

    @Override
    public void clean() {
        dao.clear();
    }
}
