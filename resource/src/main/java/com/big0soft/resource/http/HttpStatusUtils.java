package com.big0soft.resource.http;

import java.util.HashMap;
import java.util.Map;

public class HttpStatusUtils {
    private static final Map<Integer, String> STATUS_MESSAGES = new HashMap<>();

    static {
        STATUS_MESSAGES.put(200, "OK - The request has been successfully processed.");
        STATUS_MESSAGES.put(201, "Created - The request has been fulfilled, and a new resource has been created.");
        STATUS_MESSAGES.put(202, "Accepted - The request has been accepted for processing.");
        STATUS_MESSAGES.put(203, "Non-Authoritative Information - The returned information is not authoritative but might be from a local cache.");
        STATUS_MESSAGES.put(204, "No Content - The request was successfully processed, but there is no content to return.");
        STATUS_MESSAGES.put(205, "Reset Content - The request was successfully processed, and the user agent should reset the document view.");
        STATUS_MESSAGES.put(206, "Partial Content - The server has fulfilled the partial GET request.");
        STATUS_MESSAGES.put(300, "Multiple Choices - The requested resource corresponds to multiple possibilities; the user or user agent can choose one.");
        STATUS_MESSAGES.put(301, "Moved Permanently - The requested resource has been permanently moved to a new URL.");
        STATUS_MESSAGES.put(302, "Found (Moved Temporarily) - The requested resource has been temporarily moved to a different URL.");
        STATUS_MESSAGES.put(303, "See Other - The response to the request can be found under a different URL.");
        STATUS_MESSAGES.put(304, "Not Modified - The resource has not been modified since the last request.");
        STATUS_MESSAGES.put(305, "Use Proxy - The requested resource must be accessed through the proxy specified in the Location header.");
        STATUS_MESSAGES.put(400, "Bad Request - The server could not understand the request.");
        STATUS_MESSAGES.put(401, "Unauthorized - Authentication is required to access the resource.");
        STATUS_MESSAGES.put(402, "Payment Required - Reserved for future use.");
        STATUS_MESSAGES.put(403, "Forbidden - The server understood the request but refuses to fulfill it.");
        STATUS_MESSAGES.put(404, "Not Found - The requested resource could not be found on the server.");
        STATUS_MESSAGES.put(405, "Method Not Allowed - The method specified in the request is not allowed.");
        STATUS_MESSAGES.put(406, "Not Acceptable - The server cannot produce a response matching the list of acceptable values defined in the request's headers.");
        STATUS_MESSAGES.put(407, "Proxy Authentication Required - Authentication with a proxy server is required.");
        STATUS_MESSAGES.put(408, "Request Timeout - The client did not produce a request within the server's specified time limit.");
        STATUS_MESSAGES.put(409, "Conflict - The request could not be completed due to a conflict with the current state of the resource.");
        STATUS_MESSAGES.put(410, "Gone - The requested resource is no longer available at the server.");
        STATUS_MESSAGES.put(411, "Length Required - The server requires a Content-Length header.");
        STATUS_MESSAGES.put(412, "Precondition Failed - The server does not meet one of the preconditions specified in the request.");
        STATUS_MESSAGES.put(413, "Request Entity Too Large - The request entity is too large for the server to process.");
        STATUS_MESSAGES.put(414, "Request-URI Too Long - The requested URL is too long for the server to process.");
        STATUS_MESSAGES.put(415, "Unsupported Media Type - The server does not support the media type used in the request.");
        STATUS_MESSAGES.put(500, "Internal Server Error - An internal server error has occurred.");
        STATUS_MESSAGES.put(501, "Not Implemented - The server does not support the functionality required to fulfill the request.");
        STATUS_MESSAGES.put(502, "Bad Gateway - The server, acting as a gateway or proxy, received an invalid response from the upstream server.");
        STATUS_MESSAGES.put(503, "Service Unavailable - The server is currently unable to handle the request due to temporary overloading or maintenance.");
        STATUS_MESSAGES.put(504, "Gateway Timeout - The server, acting as a gateway or proxy, did not receive a timely response from the upstream server or application.");
        STATUS_MESSAGES.put(505, "HTTP Version Not Supported - The server does not support the HTTP protocol version used in the request.");
    }

    public static String getMessageForStatusCode(int statusCode) {
        String message = STATUS_MESSAGES.get(statusCode);
        return message != null ? message : "Unknown Status Code";
    }

    public static boolean needReAuth(int statusCode) {
        return statusCode == 401 || statusCode == 403;
    }
}
