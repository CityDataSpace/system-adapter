package de.fraunhofer.dataspaces.iese.systemadapter.data.database.type;

/**
 * This enum contains databases that are currently implemented in systemadapter. More database implementations to follow.
 */
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
