package tp.locomovil.inter;

import tp.locomovil.model.Location;
import tp.locomovil.model.WifiData;

import java.util.List;

public interface LocationService {

	Location getApproximateLocation(List<WifiData> wifiScans);
}