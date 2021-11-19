package de.fraunhofer.dataspaces.iese.systemadapter.data.formatter;

import java.util.List;

/**
 * This interface should be used by Services that implement functionality of repositories.
 * @param <T> To be used with Payload Model.
 */
public interface PayloadFormatter<T> {
	
	abstract List<T> reformatPayloadData(List<T> payloads);
}
