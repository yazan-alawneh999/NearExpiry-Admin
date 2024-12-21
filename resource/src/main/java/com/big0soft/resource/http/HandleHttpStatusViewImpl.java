package com.big0soft.resource.http;

public class HandleHttpStatusViewImpl implements IHandleHttpStatusView{
    @Override
    public HttpHandle handleSuccess() {
        return new HttpHandle().setMessage("Success");
    }

    @Override
    public HttpHandle handleCreate() {
        return new HttpHandle().setMessage("Created");
    }

    @Override
    public HttpHandle handleNotFound() {
         return new HttpHandle().setMessage("Not Found");
    }

    @Override
    public HttpHandle handleUnauthorized() {
         return new HttpHandle().setMessage("Unauthorized");
    }

    @Override
    public HttpHandle handleMethodNotAllowed() {
         return new HttpHandle().setMessage("Method Not Allowed");
    }

    @Override
    public HttpHandle handleNoNetwork() {
         return new HttpHandle().setMessage("Please check network Connection");
    }

    @Override
    public HttpHandle handleTimeout() {
         return new HttpHandle().setMessage("Timeout");
    }

    @Override
    public HttpHandle handleUnknown() {
         return new HttpHandle().setMessage("Unknown Error");
    }
}
