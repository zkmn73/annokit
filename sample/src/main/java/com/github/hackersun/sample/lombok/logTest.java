package com.github.hackersun.sample.lombok;

import lombok.extern.log4j.Log4j2;

/**
 * Desc:
 * Author:sunguoli@meituan.com
 * Date:15/12/18
 */
@Log4j2
public class logTest {

    public static void main(String[] args) {
        log.error("===>>> Something wrong here");
    }
}
