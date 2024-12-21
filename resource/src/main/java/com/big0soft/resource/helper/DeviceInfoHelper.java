package com.big0soft.resource.helper;

import android.content.ContentResolver;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class DeviceInfoHelper {
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;

        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    public static String deviceSettingName(ContentResolver contentResolver) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            return Settings.Global.getString(contentResolver, Settings.Global.DEVICE_NAME);
        }
        return "";
    }

    public static String deviceUUID(ContentResolver contentResolver) {

        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID);
    }

    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    public  static class NetworkUtils {

        /**
         * Convert byte array to hex string
         * @param bytes toConvert
         * @return hexValue
         */
        public static String bytesToHex(byte[] bytes) {
            StringBuilder sbuf = new StringBuilder();
            for (byte aByte : bytes) {
                int intVal = aByte & 0xff;
                if (intVal < 0x10) sbuf.append("0");
                sbuf.append(Integer.toHexString(intVal).toUpperCase());
            }
            return sbuf.toString();
        }

        /**
         * Get utf8 byte array.
         * @param str which to be converted
         * @return  array of NULL if error was found
         */
        public static byte[] getUTF8Bytes(String str) {
            try { return str.getBytes(StandardCharsets.UTF_8); } catch (Exception ex) { return null; }
        }

        /**
         * Load UTF8withBOM or any ansi text file.
         * @param filename which to be converted to string
         * @return String value of File
         * @throws java.io.IOException if error occurs
         */
        public static String loadFileAsString(String filename) throws java.io.IOException {
            final int BUFLEN=1024;
            try (BufferedInputStream is = new BufferedInputStream(Files.newInputStream(Paths.get(filename)), BUFLEN)) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream(BUFLEN);
                byte[] bytes = new byte[BUFLEN];
                boolean isUTF8 = false;
                int read, count = 0;
                while ((read = is.read(bytes)) != -1) {
                    if (count == 0 && bytes[0] == (byte) 0xEF && bytes[1] == (byte) 0xBB && bytes[2] == (byte) 0xBF) {
                        isUTF8 = true;
                        baos.write(bytes, 3, read - 3); // drop UTF8 bom marker
                    } else {
                        baos.write(bytes, 0, read);
                    }
                    count += read;
                }
                return isUTF8 ? baos.toString("UTF-8") : baos.toString();
            }
        }

        /**
         * Returns MAC address of the given interface name.
         * @param interfaceName eth0, wlan0 or NULL=use first interface
         * @return  mac address or empty string
         */
        public static String getMACAddress(String interfaceName) {
            try {
                List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
                for (NetworkInterface intf : interfaces) {
                    if (interfaceName != null) {
                        if (!intf.getName().equalsIgnoreCase(interfaceName)) continue;
                    }
                    byte[] mac = intf.getHardwareAddress();
                    if (mac==null) return "";
                    StringBuilder buf = new StringBuilder();
                    for (byte aMac : mac) buf.append(String.format("%02X:",aMac));
                    if (buf.length()>0) buf.deleteCharAt(buf.length()-1);
                    return buf.toString();
                }
            } catch (Exception ignored) { } // for now eat exceptions
            return "";

        }

        /**
         * Get IP address from first non-localhost interface
         * @param useIPv4   true=return ipv4, false=return ipv6
         * @return  address or empty string
         */
        public static String getIPAddress(boolean useIPv4) {
            try {
                List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
                for (NetworkInterface intf : interfaces) {
                    List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                    for (InetAddress addr : addrs) {
                        if (!addr.isLoopbackAddress()) {
                            String sAddr = addr.getHostAddress();
//                            boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                            boolean isIPv4 = sAddr.indexOf(':')<0;

                            if (useIPv4) {
                                if (isIPv4)
                                    return sAddr;
                            } else {
                                if (!isIPv4) {
                                    int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                    return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                                }
                            }
                        }
                    }
                }
            } catch (Exception ignored) { } // for now eat exceptions
            return "";
        }

        public static String getIPAddress2(boolean useIPv4) {
            try (Socket s = new Socket("8.8.8.8", 53)) {
                for (NetworkInterface intf : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                    if (intf.isUp()) {
                        for (InetAddress addr : Collections.list(intf.getInetAddresses())) {
                            if (!addr.isLoopbackAddress()) {
                                String sAddr = addr.getHostAddress();
                                boolean isIPv4 = sAddr.indexOf(':') < 0;

                                if (useIPv4) {
                                    if (isIPv4) {
                                        return sAddr;
                                    }
                                } else {
                                    if (!isIPv4) {
                                        int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                        return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception ignored) { } // for now eat exceptions
            return "";
        }

        public static String getWifiIp(Context context){
            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
            return ip;
        }
        public static String wifiIpAddress(Context context) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

            // Convert little-endian to big-endianif needed
            if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
                ipAddress = Integer.reverseBytes(ipAddress);
            }

            byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

            String ipAddressString;
            try {
                ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
            } catch (UnknownHostException ex) {
                Log.e("WIFIIP", "Unable to get host address.");
                ipAddressString = getIPAddress(true);
            }

            return ipAddressString;
        }
    }

}
