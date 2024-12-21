package com.big0soft.resource.http;

public interface IHandleHttpStatusView {
    HttpHandle handleSuccess();

    HttpHandle handleCreate();

    HttpHandle handleNotFound();

    HttpHandle handleUnauthorized();

    HttpHandle handleMethodNotAllowed();

    HttpHandle handleNoNetwork();

    HttpHandle handleTimeout();

    HttpHandle handleUnknown();
}
