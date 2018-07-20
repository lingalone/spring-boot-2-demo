package io.github.lingalone.springboot2feature.functional_bean_registration;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/7/20
 */

public class TestService {

    public int getRandomNumber() {
        return new Random().nextInt(10);
    }
}
