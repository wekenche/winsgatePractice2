package w.fujiko.util.common.csvfileuploadextractor;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

public class CSVFileDataExtractor {
	
	private static final String EXTENSION = "csv";
	private static final String DOT = ".";
	private static final String CELL_ERROR = "行番号 [%d] -> フィールド [%s] : %s";
	private static final String CELL_DUPLICATE_ERROR = "行番号 [%d] -> フィールド [%s] : 重複値 = [%s]";
	private static final String FILE_EXTENSION_ERROR_MESSAGE = "無効なファイル拡張子です";
	private static final String ENCODING = "Shift_JIS";
	private static final String MISSING_CSV_HEADER = "1.）ヘッダーが間違っていないか確認してください "
			+ "\n2.）ファイルエンコーディングはShift_JISにする必要があります "
			+ "\n3.）正しいCSV形式に従ってください";
	
	private static final String MSG_ERROR = "{ \"status\" : 500, \"message\": \"%s\"}";
	
	public List<Map<String, String>> extractCSVFileToMap(MultipartFile file, String[] headers)
			throws Exception {
		
		checkFileExtension(file);
		ICsvMapReader mapReader = getMapReader(file);
		
		final String[] fileHeaders = mapReader.getHeader(true);
		checkHeaders(fileHeaders, headers, mapReader);
		
		List<Map<String, String>> result = new ArrayList<>();
		Map<String, String> map;
		while( (map = mapReader.read(fileHeaders)) != null ) {
			result.add(trim(map));
		}
		
		mapReader.close();
		
		return result;
	}
	
	public List<CSVExtractionError<Integer,String,String>> getErrors(MultipartFile file, 
			String[] headers, Checker[][] checkers) throws Exception {
		
		List<CSVExtractionError<Integer,String,String>> errors = new ArrayList<>();
		
		checkFileExtension(file);
		ICsvMapReader mapReader = getMapReader(file);
		
		final String[] fileHeaders = mapReader.getHeader(true);
		checkHeaders(fileHeaders, headers, mapReader);
		
		boolean isNeededToTrackDuplicate = false;
		List<Map<String, CSVCellInfo>> dups = new ArrayList<>();
		Map<String, String> map = null;
		
		while( (map = mapReader.read(fileHeaders)) != null ) {
			map = trim(map);

			int rowNumber = mapReader.getRowNumber();
			Map<String, CSVCellInfo> dup = new HashMap<>();
			
			for(int i = 0; i < headers.length; i++) {
				for(int j = 0; j < checkers[i].length; j++) {
					if(checkers[i][j] instanceof DuplicateChecker) {
						isNeededToTrackDuplicate = true;
						dup.put(headers[i], new CSVCellInfo(
									rowNumber, headers[i],
									map.get(headers[i])
								));
					} else {
						var checkResult = checkers[i][j].validate(map.get(headers[i]));
						if(checkResult.hasError()) {
							var csvExtractionError = new CSVExtractionError<Integer,String,String>
														 (rowNumber,headers[i],checkResult.getErrorMessage());
							errors.add(csvExtractionError);
						}
					}
				}
			}
			if(dup.size() > 0) {
				dups.add(dup);
			}
			
		}
		
		if(isNeededToTrackDuplicate) {
			var duplicateErrors = getDuplicateErrors(dups);
			if(duplicateErrors.size() > 0) {
				errors.addAll(duplicateErrors);
			}
		}
		
		mapReader.close();
		
		return errors;
	}
			
	private List<CSVExtractionError<Integer,String,String>> getDuplicateErrors(List<Map<String, CSVCellInfo>> dups) {
		
		var result = new ArrayList<CSVExtractionError<Integer,String,String>>();
		List<String> unique = new ArrayList<>();
		int size = dups.size();
		
		// get a sample map to determine headers
		Map<String, CSVCellInfo> map = dups.get(0);
		
		for(String header : map.keySet()) {
			for (int i = 0; i < size; i++) {
				if(!unique.contains(dups.get(i).get(header).getValue())) {
					unique.add(dups.get(i).get(header).getValue());
					boolean hasDuplicate = false;
					for(int j = 0; j < size; j++) {
						CSVCellInfo current = dups.get(i).get(header);
						CSVCellInfo cell2 = dups.get(j).get(header);
						if (current.getValue().equals(cell2.getValue())) {
							if(i != j) {
								int rowNumber = cell2.getRowNumber();
								String value = cell2.getValue();
								String duplicateErrorMessage = "重複値 = "+value;
								
								var csvExtractionError = new CSVExtractionError<Integer,String,String>
															 (rowNumber,header,duplicateErrorMessage);
								result.add(csvExtractionError);
								hasDuplicate = true;
							}
						}
					}
					if(hasDuplicate) {
						CSVCellInfo current = dups.get(i).get(header);
						int rowNumber = current.getRowNumber();
						String value = current.getValue();
						String duplicateErrorMessage = "重複値 = "+value;

						var csvExtractionError = new CSVExtractionError<Integer,String,String>
															 (rowNumber,header,duplicateErrorMessage);
						result.add(csvExtractionError);
					}
				}
			}
		}
		return result;
	}
	
	private ICsvMapReader getMapReader(MultipartFile file) throws Exception {
		InputStream inputStream =  new BufferedInputStream(file.getInputStream());
		InputStreamReader reader = new InputStreamReader(inputStream, ENCODING);
		ICsvMapReader mapReader = new CsvMapReader(reader, CsvPreference.STANDARD_PREFERENCE);
		return mapReader;
	}
	
	private void checkFileExtension(MultipartFile file) {
		String fileExtension = getFileExtension(file.getOriginalFilename());
		if(!fileExtension.equals(EXTENSION)) {
			throw new IllegalArgumentException(String.format(MSG_ERROR, FILE_EXTENSION_ERROR_MESSAGE));
		}
	}
	
	private void checkHeaders(String[] fileHeaders, String[] headers,
			ICsvMapReader mapReader) throws Exception {
		
		if(!areHeadersFoundInFileHeaders(fileHeaders, headers)) {
			mapReader.close();
			throw new IllegalArgumentException(String.format(MSG_ERROR, MISSING_CSV_HEADER));
		}
	}
	
	private Map<String, String> trim(Map<String, String> map) {
		Map<String, String> result = new HashMap<>();
		for (String key : map.keySet()) {
			String value = map.get(key);
			String trimmedValue = value == null ? "" : value.trim();
			String trimmedKey = key.trim();
			result.put(trimmedKey, trimmedValue);
		}
		return result;
	}
	
	private boolean areHeadersFoundInFileHeaders(String[] fileHeaders, String[] headers) {
		List<String> headersList = Arrays.asList(fileHeaders);
		for(String header : headers) {
			boolean exist = headersList.stream()
				.anyMatch(e -> e.trim().equals(header));
			if(!exist) {
				return false;
			}
		}
		
		return true;
	}
	
	private String getFileExtension(String fileName) {
		String extension = fileName.substring(fileName.lastIndexOf(DOT) + 1);
		return extension;
	}

}