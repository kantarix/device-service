package com.kantarix.device_service.api.tuya;

import com.kantarix.device_service.api.tuya.requests.CommandsRequest;
import com.kantarix.device_service.api.tuya.responses.CapabilitiesResponse;
import com.kantarix.device_service.api.tuya.responses.DeviceResponse;
import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;

public interface DeviceConnector {

    @GET("/v2.0/cloud/thing/{deviceId}")
    DeviceResponse getDeviceInfo(@Path("deviceId") String deviceId);

    @GET("/v2.0/cloud/thing/{deviceId}/shadow/properties")
    CapabilitiesResponse getDeviceCapabilities(@Path("deviceId") String deviceId, @Query("codes") String codes);

    @POST("/v1.0/iot-03/devices/{deviceId}/commands")
    Boolean sendCommand(@Path("deviceId") String deviceId, @Body CommandsRequest commands);

}