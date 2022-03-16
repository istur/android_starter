package com.istur.android_starter.common.architecture.communication;

public final class ApiStateResponse<T> {

    public enum State {
        FAIL, SUCCESS, LOADING, UNINITIALIZED
    }

    private Throwable throwable;
    private State state;
    private T response;

    ApiStateResponse(State state, T response, Throwable throwable) {
        this.state = state;
        this.response = response;
        this.throwable = throwable;
    }

    public T getResponse() { return response; }

    public Throwable getThrowable() {
        return throwable;
    }

    public State getState() {
        return state;
    }

    public static <T> ApiStateResponse<T> loading() {
        return new ApiStateResponse(State.LOADING, null, null);
    }

    public static <T> ApiStateResponse<T> success(T response) {
        return new ApiStateResponse(State.SUCCESS, response, null);
    }

    public static <T> ApiStateResponse<T> fail(Throwable throwable) {
        return new ApiStateResponse(State.FAIL, null, throwable);
    }

    public static <T> ApiStateResponse<T> init() {
        return new ApiStateResponse(State.UNINITIALIZED, null, null);
    }

    public <T> ApiStateResponse<T> copy(T response) {
        return new ApiStateResponse(
                this.state,
                response,
                this.throwable
        );
    }

    public boolean isSuccess() {
        return this.state == State.SUCCESS;
    }

    public boolean isFail() {
        return this.state == State.FAIL;
    }

    public boolean isLoading() {
        return this.state == State.LOADING;
    }
}
