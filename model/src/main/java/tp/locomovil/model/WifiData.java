package tp.locomovil.model;

public class WifiData {

	private String BSSID;
	private Integer level, frequency;

	private WifiData(WifiDataBuilder builder) {
		this.BSSID = builder.BSSID;
		this.level = builder.level;
		this.frequency = builder.frequency;
	}

	public String getBSSID() {
		return BSSID;
	}

	public Integer getLevel() {
		return level;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public static class WifiDataBuilder {

		private String BSSID;
		private Integer level, frequency;

		public WifiDataBuilder() {
		}

		public WifiDataBuilder bssid(String bssid) {
			this.BSSID = bssid;
			return this;
		}

		public WifiDataBuilder level(int level) {
			this.level = level;
			return this;
		}

		public WifiDataBuilder frequency(int frequency) {
			this.frequency = frequency;
			return this;
		}

		public WifiData build() {
			return new WifiData(this);
		}
	}
}
