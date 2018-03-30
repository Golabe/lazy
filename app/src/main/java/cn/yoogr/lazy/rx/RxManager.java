package cn.yoogr.lazy.rx;



public class RxManager {

    private RxManager() {
    }

    private static final class RxManagerHolder {
        private static final RxManager INSTANCE = new RxManager();
    }

    public static RxManager getINSTANCE() {
        return RxManagerHolder.INSTANCE;
    }



}
