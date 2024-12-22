package com.big0soft.nearexpireadmin.data.interfacesimpl;


import com.big0soft.nearexpireadmin.data.requests.LocationDetailsRequest;
import com.big0soft.nearexpireadmin.domain.interfaces.DataBackup;
import com.big0soft.resource.data.SharedPreferenceDao;

public class SharedPreferenceStoreLocationDataBackup implements DataBackup<LocationDetailsRequest> {
    private final SharedPreferenceDao dao;
    private static final String KEY_STORE_LOCATION = "Location_info";

    public SharedPreferenceStoreLocationDataBackup(SharedPreferenceDao dao) {
        this.dao = dao;
    }

    @Override
    public void backup(LocationDetailsRequest LocationDetailsRequest) {
        dao.putObject(KEY_STORE_LOCATION, LocationDetailsRequest)
                .apply();
    }

    @Override
    public LocationDetailsRequest restore() {
        LocationDetailsRequest locationDetailsRequest = (LocationDetailsRequest) dao.getObject(KEY_STORE_LOCATION, new LocationDetailsRequest());
        if (locationDetailsRequest == null) {
            locationDetailsRequest = new LocationDetailsRequest();
            locationDetailsRequest.setCityName("");
            locationDetailsRequest.setCountryName("");
            locationDetailsRequest.setStreetName("");
        }

        return locationDetailsRequest;
    }

    @Override
    public void clean() {
        dao.clear();
    }
}
