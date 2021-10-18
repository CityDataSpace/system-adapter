package de.fraunhofer.dataspaces.iese.systemadapter.data.database.type;

public enum DatabaseType {
	
	MYSQL(0),
	POSTGRES(1);
	
	private final int databaseType;

	DatabaseType(final int databaseType) {
		this.databaseType = databaseType;
	}
	
	public int getDatabaseType() {
		return this.databaseType;
	}
}
