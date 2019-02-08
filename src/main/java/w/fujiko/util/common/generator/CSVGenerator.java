package w.fujiko.util.common.generator;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * This class is used when generating CSV
 * 
 * @author festadillo
 *
 * @param <E>
 */
public class CSVGenerator {
	
	private static final char BYTE_ORDER_MARK = '\uFEFF';
	
	
	/**
	 * Generates a CSV formatted data.
	 * 	
	 * @param table
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	public <E>String generate(Table<E> table) 
			throws IllegalArgumentException, IllegalAccessException, IOException {
						
		StringWriter sWriter = new StringWriter();		
		ICsvMapWriter mapWriter = null;

		mapWriter = new CsvMapWriter(sWriter, CsvPreference.STANDARD_PREFERENCE);
		sWriter.write(BYTE_ORDER_MARK);

		/**
		 * Header creation
		 */
		String[] headers = table.getHeaders();
		mapWriter.writeHeader(table.getHeaders());

		/**
		 * Row creation
		 */
		List<Map<String, Object>> result = transformData(table);
		for(Map<String, Object> row: result) {
			mapWriter.write(row, headers);
		}

		mapWriter.close();

		byte[] bytes = sWriter.toString().getBytes();
		return new String(bytes, StandardCharsets.UTF_8);

	}
	
	
	/**
	 * Transforms the List of models into a CSV format data
	 * 
	 * @param table
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private <E>List<Map<String, Object>> transformData(Table<E> table) 
			throws IllegalArgumentException, IllegalAccessException {
		
		List<Map<String, Object>> result = new ArrayList<>();
	
		String[] headerList = table.getHeaders();
		String[] fieldList = table.getFields();
		Map<String, String> defaultValues = table.getDefaultFieldValues();
		List<E> dataSource = table.getDataSource();
		Class<E> klazz = table.getKlazz();
		
		for(E data: dataSource) {
			Map<String, Object> row = new HashMap<>();
			for(int i = 0; i < headerList.length; i++) {
				String fieldName = fieldList[i];
				String headerName = headerList[i];
				String fieldValue = "";	
				if(hasDefaultValue(fieldName, defaultValues)) {
					fieldValue = defaultValues.get(fieldName);
				} else {
					fieldValue = getFieldValue(fieldName, klazz, data);
				}
				row.put(headerName, fieldValue);
			}
			result.add(row);
		}
		
		return result;
	}
	
	/**
	 * Gets the value of the object's instance variable based on the
	 * specified field name.
	 * 
	 * @param fieldName
	 * @param klazz
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private <E>String getFieldValue(String fieldName, Class<?> klazz, E obj) 
			throws IllegalArgumentException, IllegalAccessException {
		
		int index = getFieldIndex(fieldName, klazz);
		Field[] f = klazz.getDeclaredFields();
		f[index].setAccessible(true);
		String result = f[index].get(obj).toString();
		
		return result;
	}

	/**
	 * Gets the field's index number inside a class based on the
	 * specified field name.
	 * 
	 * @param fieldName
	 * @param klazz
	 * @return
	 */
	private int getFieldIndex(String fieldName, Class<?> klazz) {
		Field[] f = klazz.getDeclaredFields();
		return IntStream.range(0, f.length)
				.filter(i -> fieldName.equals(f[i].getName()))
				.findFirst()
				.orElse(-1);
	}
	
	/**
	 * Determines if the specified field name has a default value.
	 * 
	 * @param fieldName
	 * @param defaultValues
	 * @return
	 */
	private boolean hasDefaultValue(String fieldName, Map<String, String> defaultValues) {
		if(defaultValues.size() > 1) {
			if(defaultValues.containsKey(fieldName)) {
				return true;
			}
		}
		return false;
	}

}