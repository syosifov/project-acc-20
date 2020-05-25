package net.yosifov.filipov.training.accounting.acc20.system.serialization;

public class JSonResult {
    boolean _isSuccess;
    private void set_isSuccess(boolean _isSuccess) {
        this._isSuccess = _isSuccess;
    }

    public boolean isSussces() {
        return _isSuccess;
    }

    public boolean isError() {
        return !_isSuccess;
    }

    public String get_JSon() {
        return _JSon;
    }

    String _JSon;
}
