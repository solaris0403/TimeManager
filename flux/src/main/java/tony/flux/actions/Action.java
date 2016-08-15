package tony.flux.actions;

/**
 * Action通过ActionCreator的帮助类产生并传递给Dispatcher
 */
public class Action<T> {
    private final String mType;
    private final T mData;

    /**
     * @param type 类型
     * @param data 数据
     */
    public Action(String type, T data) {
        this.mType = type;
        this.mData = data;
    }

    public String getType() {
        return mType;
    }

    public T getData() {
        return mData;
    }
}
