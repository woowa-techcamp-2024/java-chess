package com.example.demo.context;

/**
 * 체스에서 File은 열을 나타냅니다.
 * File은 0부터 7까지 총 8개의 열을 가지고 Rank(행)과 구분하기 위해서 a부터 h까지의 문자로 표현합니다.
 */
public enum File {
    A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7);

    private final int index;

    File(int index) {
        this.index = index;
    }

    public int index(){
        return this.index;
    }

    /**
     * 현재 File에서 step만큼 이동한 File을 반환합니다.
     * @param step 이동할 거리
     * @return 이동한 위치의 File 입니다. 만약 이동한 위치가 존재하지 않는다면 null을 반환합니다.
     */
    public File move(int step){
        try {
            return values()[index + step];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
