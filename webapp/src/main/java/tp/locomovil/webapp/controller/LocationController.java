package tp.locomovil.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp.locomovil.inter.service.LocationService;
import tp.locomovil.inter.service.ScanService;
import tp.locomovil.model.Location;
import tp.locomovil.model.Scan;
import tp.locomovil.webapp.dto.LocationDTO;
import tp.locomovil.webapp.forms.FormScan;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/location")
@Component
@Produces(value = {MediaType.APPLICATION_JSON})
public class LocationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);
	@Autowired
	private LocationService locationService;

	@Autowired
	private ScanService scanService;

	@Context
	private UriInfo uriContext;

	@POST
	@Path("/map")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getLocationByMapNN(final FormScan f) {
		Scan queryScan = f.toScan();
		long start = System.currentTimeMillis();
		List<Scan> calibrationScans = scanService.getScansForMapId(queryScan.getMapId());
		Location approximateLocation = locationService.getApproximateLocationNN(queryScan, calibrationScans);
		long end = System.currentTimeMillis();

		LOGGER.info("Finished getLocationByMapNN in: {} millis", end - start);
		if (approximateLocation == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		return Response.ok().entity(new LocationDTO(approximateLocation)).build();
	}

	@POST
	@Path("/mapAverage")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getLocationByMapKNNAverage(final FormScan f, @QueryParam("K") int K) {
		if (K < 1)
			return Response.status(Response.Status.BAD_REQUEST).build();
		Scan queryScan = f.toScan();

		long start = System.currentTimeMillis();
		List<Scan> calibrationScans = scanService.getScansForMapId(queryScan.getMapId());
		Location approximateLocation = locationService.getApproximateLocationKNNAverage(queryScan, calibrationScans, K);
		long end = System.currentTimeMillis();

		LOGGER.info("Finished getLocationByMapKNNAverage in: {} millis", end - start);
		if (approximateLocation == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		return Response.ok().entity(new LocationDTO(approximateLocation)).build();
	}

	@POST
	@Path("/project")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getLocationByProject(final FormScan f) {
		Scan queryScan = f.toScan();

		long start = System.currentTimeMillis();
		List<Scan> calibrationScans = scanService.getScansForProjectId(queryScan.getProjectId());
		if (calibrationScans.isEmpty())
			return Response.status(Response.Status.NOT_FOUND).build();

		Location approximateLocation = locationService.getApproximateLocationNN(queryScan, calibrationScans);
		long end = System.currentTimeMillis();

		LOGGER.info("Finished getLocationByProject in: {} millis", end - start);
		return Response.ok().entity(new LocationDTO(approximateLocation)).build();
	}


}
