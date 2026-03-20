package com.codefactor.challenge.one_week.day9;

/**
 * Challenge 03 - Builder Pattern
 *
 * Implement the Builder pattern for constructing complex objects step by step.
 * Common interview question: When to use Builder vs Constructor vs Setter?
 *
 * Use Builder when:
 * - Many constructor parameters (>4)
 * - Some parameters optional
 * - Object should be immutable once built
 */
public class Challenge03 {

    /** Immutable class using Builder */
    static class HttpRequest {
        private final String method;
        private final String url;
        private final String body;
        private final String contentType;
        private final int timeout;
        private final boolean followRedirects;

        private HttpRequest(Builder builder) {
            this.method = builder.method;
            this.url = builder.url;
            this.body = builder.body;
            this.contentType = builder.contentType;
            this.timeout = builder.timeout;
            this.followRedirects = builder.followRedirects;
        }

        @Override
        public String toString() {
            return String.format("HttpRequest{method='%s', url='%s', body='%s', " +
                "contentType='%s', timeout=%d, followRedirects=%s}",
                method, url, body, contentType, timeout, followRedirects);
        }

        static class Builder {
            private final String method; // required
            private final String url;    // required
            private String body = "";
            private String contentType = "application/json";
            private int timeout = 30000;
            private boolean followRedirects = true;

            public Builder(String method, String url) {
                this.method = method;
                this.url = url;
            }

            public Builder body(String body) {
                this.body = body;
                return this;
            }

            public Builder contentType(String contentType) {
                this.contentType = contentType;
                return this;
            }

            public Builder timeout(int timeout) {
                this.timeout = timeout;
                return this;
            }

            public Builder followRedirects(boolean followRedirects) {
                this.followRedirects = followRedirects;
                return this;
            }

            public HttpRequest build() {
                return new HttpRequest(this);
            }
        }
    }

    public static void main(String[] args) {
        // Fluent builder with only required fields
        HttpRequest get = new HttpRequest.Builder("GET", "/api/users")
            .build();
        System.out.println(get);

        // Builder with all options
        HttpRequest post = new HttpRequest.Builder("POST", "/api/users")
            .body("{\"name\": \"Alice\"}")
            .contentType("application/json")
            .timeout(5000)
            .followRedirects(false)
            .build();
        System.out.println(post);
    }
}
