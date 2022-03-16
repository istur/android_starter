package com.istur.android_starter.common.architecture.communication;

public class UiResponse<T> {

    Throwable exception;
    T result;
    Boolean showLoading;

    UiStates uiStates;

    public enum UiStates {
        RenderData, RenderError, Loading
    }

    public UiResponse(T result) {
        this.result = result;
        uiStates = UiStates.RenderData;
    }

    public UiResponse(Throwable exception) {
        this.exception = exception;
        uiStates = UiStates.RenderError;
    }

    public UiResponse(Boolean showLoading) {
        this.showLoading = showLoading;
        uiStates = UiStates.Loading;
    }

    public Throwable getException() {
        return exception;
    }

    public T getResult() {
        return result;
    }

    public UiStates getUiState() {
        return uiStates;
    }

    public Boolean getShowLoading() {
        return showLoading;
    }
}
