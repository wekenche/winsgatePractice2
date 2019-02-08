package w.fujiko.util.common.csvfileuploadextractor;

public class DefaultCheckResult implements CheckResult{
    private boolean isError;
    private String errorMessage;

    public DefaultCheckResult(){
        this(false,"");
    }

    public DefaultCheckResult(boolean isError, String errorMessage){
        this.isError = isError;
        this.errorMessage = errorMessage;
    }

    public void setIsError(boolean isError){
        this.isError = isError;
    }

    @Override
    public boolean hasError() {
        return this.isError;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

}