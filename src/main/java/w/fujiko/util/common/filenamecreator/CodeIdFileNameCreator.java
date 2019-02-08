package w.fujiko.util.common.filenamecreator;

import w.fujiko.util.filenamecreator.FilenameCreator;

public class CodeIdFileNameCreator implements FilenameCreator{

    private String code;
    private Integer id;

    public CodeIdFileNameCreator(String code,Integer id){
        this.code = code;
        this.id = id;
    }

    @Override
    public String createName() {
        return this.createDocumentFileName();
    }

    /***
	 * Creates a document filename
	 * @return filename
	 */
	//sample output: 99999_123
	private String createDocumentFileName(){
        final String NAME_SEPARATOR="_";
        String idString = String.valueOf(id).trim();

		return code.trim()
			  .concat(NAME_SEPARATOR)
			  .concat(idString);
    }
}