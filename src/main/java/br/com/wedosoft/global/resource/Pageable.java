package br.com.wedosoft.global.resource;

public final class Pageable {


    private Integer firstResult;

    private Integer maxResults;

    private Pageable() {
    }

    private Pageable(Integer firstResult, Integer maxResults) {
        this.firstResult = firstResult;
        this.maxResults = maxResults;
    }

    public static Pageable of(Integer firstResult, Integer maxResults) {
        return new Pageable(firstResult, maxResults);
    }

    public Integer getFirstResult() {
        return firstResult;
    }

    void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Pageable pageable = new Pageable();


        public Builder firstResult(Integer firstResult) {
            this.pageable.setFirstResult(firstResult);
            return this;
        }

        public Builder maxResults(Integer maxResults) {
            this.pageable.setMaxResults(maxResults);
            return this;
        }

        public Pageable build() {
            return pageable;
        }

    }

}
