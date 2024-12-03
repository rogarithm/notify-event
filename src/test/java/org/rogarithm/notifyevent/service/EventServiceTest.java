package org.rogarithm.notifyevent.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

class EventServiceTest {

    class X {
        String x;

        X(String x) { this.x = x; }

        boolean hasX() { return this.x.startsWith("x"); }

        @Override
        public String toString() {
            return "X{" + "x='" + x + '\'' + '}';
        }
    }

    class K {
        String k;

        K(String k) { this.k = k; }

        K from(X x) {
            String idx = x.x.replace("x", "");
            return new K("k" + idx);
        }

        @Override
        public String toString() {
            return "K{" + "k='" + k + '\'' + '}';
        }
    }

    @DisplayName("필터링 로직에 문제가 없는지 확인한다")
    @Test
    public void test_filter_result() {
        K k = new K("k");
        List<K> ks = List.of(new X("x1"), new X("x2"), new X("y1"))
                .stream()
                .filter(xish -> xish.hasX())
                .map(x -> k.from(x))
                .collect(Collectors.toList());
        System.out.println(ks);
    }

    @DisplayName("한글 필터링에 문제가 없는지 확인한다")
    @Test
    public void test_filter_korean_result() {
        K k = new K("k");
        List<X> ks = List.of(new X("5일 이벤트"), new X("1일 이벤트"), new X("그냥 이벤트"))
                .stream()
                .filter(xish -> xish.x.matches("^.*일.*$"))
                .collect(Collectors.toList());
        System.out.println(ks);
    }
}
