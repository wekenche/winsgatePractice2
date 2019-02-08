package w.fujiko.util.common.csvfileuploadextractor;

public interface CheckResult {
    boolean hasError();
    String getErrorMessage();
}