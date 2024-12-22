package com.big0soft.nearexpireadmin.data.interfacesimpl;


import com.big0soft.nearexpireadmin.data.requests.ContactDetailsRequest;
import com.big0soft.nearexpireadmin.data.requests.LocationDetailsRequest;
import com.big0soft.nearexpireadmin.domain.interfaces.DataBackup;
import com.big0soft.resource.data.SharedPreferenceDao;

public class SharedPreferenceStoreContactDataBackup implements DataBackup<ContactDetailsRequest> {
    private final SharedPreferenceDao dao;
    private static final String KEY_STORE_CONTACT = "contact_info";

    public SharedPreferenceStoreContactDataBackup(SharedPreferenceDao dao) {
        this.dao = dao;
    }

    @Override
    public void backup(ContactDetailsRequest contactDetailsRequest) {
        dao.putObject(KEY_STORE_CONTACT, contactDetailsRequest)
                .apply();
    }

    @Override
    public ContactDetailsRequest restore() {
        ContactDetailsRequest contactDetailsRequest = (ContactDetailsRequest) dao.getObject(KEY_STORE_CONTACT, new ContactDetailsRequest());
        if (contactDetailsRequest == null) {
            contactDetailsRequest = new ContactDetailsRequest();
            contactDetailsRequest.setEmail("");
            contactDetailsRequest.setPhone("");
            contactDetailsRequest.setWhatsapp("");
        }

        return contactDetailsRequest;
    }

    @Override
    public void clean() {
        dao.clear();
    }
}
