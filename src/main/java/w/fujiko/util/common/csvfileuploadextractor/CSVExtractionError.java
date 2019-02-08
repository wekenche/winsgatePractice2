package w.fujiko.util.common.csvfileuploadextractor;

public class CSVExtractionError<R,C,E>{
    private R row;
    private C column;
    private E error;

    public CSVExtractionError(R row,C column, E error){
        this.row = row;
        this.column = column;
        this.error = error;
    }

    public void setRow(R row){
        this.row = row;
    }

    public R getRow(){
        return this.row;
    }

    public void setColumn(C column){
        this.column = column;
    }

    public C getColumn(){
        return this.column;
    }

    public void setError(E error){
        this.error = error;
    }

    public E getError(){
        return this.error;
    }
}