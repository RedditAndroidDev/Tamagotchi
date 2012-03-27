package your.redditandroiddevelopers.model;

public enum Gender {
	MALE('M'), FEMALE('F');
	
	private char dbValue;
	
	Gender(char dbChar) {
		dbValue = dbChar;
	}
	
	public char getDbValue() {
		return dbValue;
	}
	
	public Gender fromDbValue(char dbValue) {
		for (Gender g : Gender.values()) {
			if (dbValue == g.getDbValue()) {
				return g;
			}
		}
		return null;
	}
}
