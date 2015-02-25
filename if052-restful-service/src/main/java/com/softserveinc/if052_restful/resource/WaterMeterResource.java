package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.Address;
import com.softserveinc.if052_restful.domain.Indicator;
import com.softserveinc.if052_restful.domain.WaterMeter;
import com.softserveinc.if052_restful.service.AddressService;
import com.softserveinc.if052_restful.service.IndicatorService;
import com.softserveinc.if052_restful.service.WaterMeterService;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import org.apache.log4j.Logger;
import org.h2.jdbc.JdbcSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Danylo Tiahun on 11.02.2015.
 */

@Path("/watermeters")
public class WaterMeterResource {

    @Autowired
    private WaterMeterService waterMeterService;

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private AddressService addressService;

    private static Logger logger = Logger.getLogger(WaterMeterResource.class.getName());

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllWaterMeters() {
    logger.info("INFO: Searching for the whole collection of watermeters.");
    List<WaterMeter> waterMeters = waterMeterService.getAllWaterMeters();
    logger.info("INFO: The whole collection of watermeter has been found.");
    return Response
            .status(Response.Status.OK)
            .entity(waterMeters)
            .build();
    }

    @GET @Path("{waterMeterId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getWaterMeter(@PathParam("waterMeterId") int waterMeterId) {
        logger.info("INFO: Searching for the watermeter with id " + waterMeterId + "." );
        if(waterMeterService.getWaterMeterById(waterMeterId) == null) {
            logger.info("INFO: Watermeter with requested id " + waterMeterId + " has not been found.");
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(waterMeterId);
        logger.info("INFO: Watermeter with requested id " + waterMeterId + " has been successfully found.");
        return Response
                .status(Response.Status.OK)
                .entity(waterMeter)
                .build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response insertWaterMeter(WaterMeter waterMeter) {
        logger.info("INFO: Adding a new watermeter.");
        waterMeterService.insertWaterMeter(waterMeter);
        logger.info("INFO: Watermeter has been successfully added with id " + waterMeter.getWaterMeterId() + ".");
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

    @POST @Path("{waterMeterId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response insertWaterMeter(@PathParam("waterMeterId") int waterMeterId, WaterMeter waterMeter) {
        logger.info("INFO: Watermeter id cannot be provided by the request.");
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateWaterMeter(WaterMeter waterMeter) {
        logger.info("INFO: The whole collection of watermeters cannot be updated.");
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @PUT @Path("{waterMeterId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateWaterMeter(@PathParam("waterMeterId") int waterMeterId, WaterMeter waterMeter) {
        logger.info("INFO: Updating a watermeter with id " + waterMeterId + ".");
        if(waterMeterService.getWaterMeterById(waterMeterId) == null) {
            logger.info("INFO: Watermeter with requested id " + waterMeterId + " is not found.");
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        waterMeterService.updateWaterMeter(waterMeter);
        logger.info("INFO: Watermeter with id " + waterMeterId + " has been successfully updated.");
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @DELETE
    public Response deleteWaterMeter() {
        logger.info("INFO: The whole collection of watermeters cannot be deleted.");
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @DELETE @Path("{waterMeterId}")
    public Response deleteWaterMeter(@PathParam("waterMeterId") int waterMeterId) {
        logger.info("INFO: Deleting a watermeter with id " + waterMeterId + ".");
        if(waterMeterService.getWaterMeterById(waterMeterId) == null) {
            logger.info("INFO : Watermeter with requested id " + waterMeterId + " is not found.");
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        try {
            waterMeterService.deleteWaterMeter(waterMeterId);
            logger.info("INFO : Watermeter with id " + waterMeterId + " has been successfully deleted.");
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } catch (DataIntegrityViolationException e) {
            logger.warn("WARNING: Watermeter with requester id " + waterMeterId
                    + " contains list of indicators so it cannot be deleted.", e);
        }
        return Response
                .status(Response.Status.CONFLICT)
                .build();
    }


}