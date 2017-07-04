package com.yz.并发编程;



/**
 * EagerInitialization
 * <p/>
 * Eager initialization
 *
 * @author Brian Goetz and Tim Peierls
 */

        public class EagerInitialization {
    private static Resource resource = new Resource();

    public static Resource getResource() {
        return resource;
    }

    static class Resource {
    }
}
