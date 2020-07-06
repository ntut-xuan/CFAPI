package cfapi.main;

public class CodeForcesContestData {
	String ID, name, type, phase;
	long durationSeconds, startTimeSeconds, relativeTimeSeconds;
	boolean frozen, russianOnly;

	public CodeForcesContestData(String id, String name, String type, String pha, long dur, long sts, long rts,	boolean fro, boolean rus) {
		this.ID = id;
		this.name = name;
		this.type = type;
		this.phase = pha;
		this.durationSeconds = dur;
		this.startTimeSeconds = sts;
		this.relativeTimeSeconds = rts;
		this.frozen = fro;
		this.russianOnly = rus;
	}
	
	public String getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getPhase() {
		return phase;
	}
	
	public long getDurationSeconds(){
		return durationSeconds;
	}
	
	public long getStartTimeSeconds() {
		return startTimeSeconds;
	}
	
	public long getRelativeTimeSeconds() {
		return relativeTimeSeconds;
	}
	
	public boolean getContestFronze() {
		return frozen;
	}
	
	public boolean russianOnly() {
		return russianOnly;
	}
}
